package com.example.maxcrm.MaxCrm.Security;

import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Exception.TwoStepAuthenticationExecption;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.example.maxcrm.MaxCrm.Dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class CustomAuthenticationService implements AuthenticationProvider {
    Logger logger = LoggerFactory.getLogger(CustomAuthenticationService.class);
    @Autowired
    UserMasterService userMasterService;
    @Autowired
    LoginHistoryService loginHistoryService;
    @Autowired
    TextMessageService textMessageService;
    @Autowired
    TemplateDocumentService templateDocumentService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    FeatureService featureService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        int number1 = Integer.parseInt(request.getParameter("number1"));
        int number2 = Integer.parseInt(request.getParameter("number2"));
        int total = Integer.parseInt(request.getParameter("total"));
        String ot = request.getParameter("otp");

        if ((number1 + number2) != total) //will throw Tatal Is Incorrect
        {
            throw new UsernameNotFoundException("Total Is Not Corrrect!!Poor Maths!!");
        }

        logger.info("User Trying to login from ip {} {}", name, request.getRemoteAddr());


        UserMasterDao userMasterDao = userMasterService.findByUsername(name);
        logger.info("USer Tried To Login {}",userMasterDao);
        if (userMasterDao == null) {
            throw new UsernameNotFoundException("No User can be found with the given username!!");
        }

        if ( password.equals(userMasterDao.getPassword())) {



            if (!userMasterDao.getStatus().equalsIgnoreCase("Active")) {
                logger.error("Account Disabled {}", name);
                throw new UsernameNotFoundException("Account Is Disabled!!");
            }
            logger.info(">>>>>>>>>>>>>>>>>>>>>> {}",userMasterDao.isTwoStepAuthentication());
            if (userMasterDao.isTwoStepAuthentication()) {

                //disable two step auth after first login
              //  String disableTwoStepAuthAfterFirstLogin = UtilityClass.propertyService.findProperty("CRMDemo","disableTwoStepAuthAfterFirstLogin");
              /*  if(userMasterDao.isDemo() && disableTwoStepAuthAfterFirstLogin.equalsIgnoreCase("true") && userMasterDao.isTwoStepAuthentication()){
                    userMasterDao.setTwoStepAuthentication(false);
                    try {
                        userMasterService.insert(userMasterDao);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/
                logger.info(">>>>>>>>>>>>>>>>>>>>>> {}",userMasterDao.isTwoStepAuthentication());
                HttpSession httpSession = request.getSession();


                if (httpSession.getAttribute("otp") == null) {
                    httpSession.setAttribute("username", name);
                    httpSession.setAttribute("password", password);
                    httpSession.setAttribute("trial", 0);
                    String otp = UtilityClass.generateOtp();
                    httpSession.setAttribute("otp", otp);
                    logger.info("Generating Otp for Username {}", otp);
                    userMasterDao.setPassword(otp);
                    String userTwoStepAuthetication=UtilityClass.propertyService.findProperty("Usermaster","UserTwoStepAuthTemplate");
                    userMasterService.sendNotificationUser(userTwoStepAuthetication,userMasterDao);
                    throw new TwoStepAuthenticationExecption("Two Step Auth Is enabled For This Account An Otp Has Been Sent To " + userMasterDao.getMobile().substring(userMasterDao.getMobile().length() - 2));
                } else {
                    String otp = (String) httpSession.getAttribute("otp");
                    if (otp.equalsIgnoreCase(request.getParameter("otp"))) {
                        httpSession.invalidate();
                        LoginHistoryDao loginHistoryDao = new LoginHistoryDao();
                        loginHistoryDao.setIp(request.getRemoteAddr());
                        loginHistoryDao.setLoginTiming(UtilityClass.getDateRedable());
                        loginHistoryDao.setUserId(userMasterDao.getId());
                        loginHistoryService.insert(loginHistoryDao);
                        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                        GrantedAuthority authority = null;
                        MenuInnerDynamic[] menuInnerDynamics = null;
                        try {
                            List<MenuDynamicDatabaseDao> menuDynamicDatabaseDaos = userMasterService.loadMenu(userMasterDao.getDepartment());

                            for (int i = 0; i < menuDynamicDatabaseDaos.size(); i++) {
                                menuInnerDynamics = menuDynamicDatabaseDaos.get(i).getListment();
                                for (int k = 0; k < menuInnerDynamics.length; k++) {
                                    authority = new SimpleGrantedAuthority(menuInnerDynamics[k].getRolename());

                                    grantedAuthorities.add(authority);

                                }

                            }
                            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_HOME"));
                            userMasterDao.setMenuJson(menuDynamicDatabaseDaos.get(0).convertJson(menuDynamicDatabaseDaos));

                            logger.info("Grating Login To {}", userMasterDao);
                            return new UsernamePasswordAuthenticationToken(
                                    userMasterDao, password, grantedAuthorities);
                        } catch (Exception e) {
                            logger.error("Error Occured During Creating Menu {}", e);
                            throw new UsernameNotFoundException("Error Occured During Creating Menu!!");

                        }


                    } else {
                        logger.error("Otp Is Invalid!!Please Try");
                        int trial = (int) httpSession.getAttribute("trial");
                        int maximumWrongTry = Integer.parseInt(UtilityClass.propertyService.findProperty("Usermaster", "maximumWrongTry"));
                        if (trial >= maximumWrongTry) {
                            userMasterDao.setStatus("Inactive");
                            try {
                                userMasterService.update(userMasterDao);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            throw new TwoStepAuthenticationExecption("Otp Is Incorrect!!Your Account Has Been Locked");
                        }
                        trial = trial + 1;
                        httpSession.setAttribute("trial", trial);
                        throw new TwoStepAuthenticationExecption("Otp Is Incorrect!!Only " + maximumWrongTry + " retry Allowed!!Otp Retry " + trial);
                    }


                }

            }
            LoginHistoryDao loginHistoryDao = new LoginHistoryDao();
            loginHistoryDao.setIp(request.getRemoteAddr());
            loginHistoryDao.setLoginTiming(UtilityClass.getDateRedable());
            loginHistoryDao.setUserId(userMasterDao.getId());
            loginHistoryService.insert(loginHistoryDao);
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            GrantedAuthority authority = null;
            MenuInnerDynamic[] menuInnerDynamics = null;
            try {
                List<MenuDynamicDatabaseDao> menuDynamicDatabaseDaos = userMasterService.loadMenu(userMasterDao.getDepartment());
                for (int i = 0; i < menuDynamicDatabaseDaos.size(); i++) {
                    menuInnerDynamics = menuDynamicDatabaseDaos.get(i).getListment();
                    for (int k = 0; k < menuInnerDynamics.length; k++) {
                        authority = new SimpleGrantedAuthority(menuInnerDynamics[k].getRolename());
                        System.out.println("This is Role Name" + menuInnerDynamics[k].getRolename());
                        grantedAuthorities.add(authority);

                    }

                }

                userMasterDao.setMenuJson(menuDynamicDatabaseDaos.get(0).convertJson(menuDynamicDatabaseDaos));
                userMasterDao.setFeatures(featureService.getAssignedRole(userMasterDao.getDepartment()));

                logger.info("Grating Login To {}", userMasterDao);
                return new UsernamePasswordAuthenticationToken(
                        userMasterDao, password, grantedAuthorities);
            } catch (Exception e) {
                logger.error("Error Occured During Creating Menu {}", e);
                throw new UsernameNotFoundException("Error Occured During Creating Menu!!");

            }


        } else {


            throw new UsernameNotFoundException("Invalid For Password");
        }

    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

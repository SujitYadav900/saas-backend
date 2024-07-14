package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.User;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.CustomAuthenticationService;
import com.example.maxcrm.MaxCrm.Service.RoleService;
import com.example.maxcrm.MaxCrm.Service.UserMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service("userDetailsService")
public class CustomAutheticationService implements UserDetailsService
{
    Logger logger = LoggerFactory.getLogger(CustomAuthenticationService.class);
    @Autowired
    UserMasterService userMasterService;
    @Autowired
    RoleService roleService;
    @Autowired
    private HttpServletRequest request;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ipAddress =  request.getRemoteAddr();

        logger.info("Attempting Login From {}",ipAddress);

        UserMasterDao userMasterDao= findUserbyUername(username);
        if(userMasterDao.isTwoStepAuthentication())
        {
           logger.info("Account Has  Two Step Authentication {}",username);
           throw new UsernameNotFoundException("Account Has Two Step Authetication!!");
        }
        if(!userMasterDao.getStatus().equalsIgnoreCase("Active"))
        {
           logger.info("Account Has Been Disabled {}",username);
           throw new UsernameNotFoundException("Account Has been Disabled");
        }
       User user=new User();
       user.setUserMasterDao(userMasterDao);
       user.setUserId(userMasterDao.getId());
       user.setUsername(userMasterDao.getUsername());
       user.setDepName(userMasterDao.getDepartment());
       user.setPassword(new BCryptPasswordEncoder().encode(userMasterDao.getPassword()));
       user.setRoles(roleService.getAllRolesByDepartment(user.getDepName()));

        return user;
    }


    private UserMasterDao findUserbyUername(String username) {
        UserMasterDao userMasterDao= userMasterService.findByUsername(username);

        if(userMasterDao==null)
        {
            logger.error("Requested Username Cannot Be Found!! {}",username);
            throw new  UsernameNotFoundException("Requested Username Cannot Be Found");
        }
        return userMasterDao;
    }

}

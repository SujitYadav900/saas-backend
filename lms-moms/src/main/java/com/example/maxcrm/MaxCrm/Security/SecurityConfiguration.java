package com.example.maxcrm.MaxCrm.Security;

import com.example.maxcrm.MaxCrm.Dao.UrlAccessDao;
import com.example.maxcrm.MaxCrm.Service.UrlAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationService authProvider;

    @Autowired
    private UrlAccessService urlAccessService;


    public void configure(AuthenticationManagerBuilder auth) {
        System.out.println("Loading Auth");

        auth.authenticationProvider(authProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Allow Login");

        List<UrlAccessDao> accessDaoList = urlAccessService.getMatchers();
        for (UrlAccessDao dao : accessDaoList) {
            http.authorizeRequests().mvcMatchers(dao.getUrl()).hasAuthority(dao.getRoleName());
        }


        http.authorizeRequests()
                .and().formLogin().successForwardUrl("/").loginPage("/login").failureHandler(this::loginFailureHandler).permitAll()
                .and().logout().permitAll();

        http.csrf().disable();
        http.authorizeRequests().antMatchers("/resources/**").permitAll();
        http.authorizeRequests().antMatchers("/demo").permitAll();
        http.authorizeRequests().antMatchers("/privacypolicy").permitAll();
        http.authorizeRequests().antMatchers("/terms").permitAll();
        http.authorizeRequests().antMatchers("/api/**").hasAuthority("ROLE_HOME");
    }
    private void loginFailureHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e) throws IOException {
        e.printStackTrace();
        System.out.println(e.getClass().getSimpleName());


        if (e.getClass().getSimpleName().equalsIgnoreCase("TwoStepAuthenticationExecption")) {
            response.sendRedirect("loginotp?error=" + e.getMessage());

        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.sendRedirect("login?error=" + e.getMessage());
        }


    }
}
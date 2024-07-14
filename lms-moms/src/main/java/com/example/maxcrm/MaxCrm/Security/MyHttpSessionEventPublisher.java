package com.example.maxcrm.MaxCrm.Security;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.security.web.session.HttpSessionEventPublisher;


public class MyHttpSessionEventPublisher extends HttpSessionEventPublisher {

   @Override
   public void sessionCreated(HttpSessionEvent event) {
      super.sessionCreated(event);
      System.out.println("created session");
   }

   @Override
   public void sessionDestroyed(HttpSessionEvent event) {
      //do something
      System.out.println("destroyed session");
      super.sessionDestroyed(event);
   }

}
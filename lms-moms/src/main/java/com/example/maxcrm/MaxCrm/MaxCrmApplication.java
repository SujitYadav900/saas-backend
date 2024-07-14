package com.example.maxcrm.MaxCrm;

import com.example.maxcrm.MaxCrm.Security.MyHttpSessionEventPublisher;
import com.example.maxcrm.MaxCrm.Service.HIS.HisAllServices;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.example.maxcrm.MaxCrm.Service.LeadSourceService;
import com.example.maxcrm.MaxCrm.Service.PropertyInnerService;
import com.example.maxcrm.MaxCrm.Service.PropertyService;
import com.example.maxcrm.MaxCrm.Service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.sql.DataSource;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class MaxCrmApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(MaxCrmApplication.class);
	}
	public static void main(String[] args) throws MessagingException {

		SpringApplication.run(MaxCrmApplication.class, args);

	}

	//uncomment the annotation to register the listener
	//@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new MyHttpSessionEventPublisher());
	}

	//@Bean(name="MailSendingThread")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("Async-");
		threadPoolTaskExecutor.setCorePoolSize(3);
		threadPoolTaskExecutor.setMaxPoolSize(3);
		threadPoolTaskExecutor.setQueueCapacity(20);
		threadPoolTaskExecutor.afterPropertiesSet();
		return threadPoolTaskExecutor;
	}
	//@Bean(name="PersistMessage")
	public Executor persistExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("Async-");
		threadPoolTaskExecutor.setCorePoolSize(2);
		threadPoolTaskExecutor.setMaxPoolSize(3);
		threadPoolTaskExecutor.setQueueCapacity(20);
		threadPoolTaskExecutor.afterPropertiesSet();
		return threadPoolTaskExecutor;
	}

	//@Bean(name="LogHistoryThread")
	public Executor LogHistoryThread() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("Async-");
		threadPoolTaskExecutor.setCorePoolSize(2);
		threadPoolTaskExecutor.setMaxPoolSize(3);
		threadPoolTaskExecutor.setQueueCapacity(20);
		threadPoolTaskExecutor.afterPropertiesSet();
		return threadPoolTaskExecutor;
	}
	@Autowired
	ReportingService reportingService;
	@Autowired
	LeadSourceService leadSourceService;
	@Autowired
	PropertyInnerService propertyInnerService;
	@Autowired
	PropertyService propertyService;
	@Qualifier("dataSource")
	@Autowired
	DataSource dataSource;
	@Autowired
	HisAllServices hisAllServices;


	@PostConstruct
	private void init() throws Exception {

		propertyInnerService.refreshProperty();
		leadSourceService.updateLeadSourceMemory();
		reportingService.updateUserIdReporting();
		UtilityClass.propertyService=propertyService;
		//hisAllServices.generateTokenFirstTime();
	}


}

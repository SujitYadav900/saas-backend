package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.CloudApiErrorCode.CloudApiErrorCodeService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList.CountryWisePriceDaoListService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.UserOrBuisnessIntiatedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableDiscoveryClient
@EnableOAuth2Sso
@EnableFeignClients
@EnableRedisRepositories
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WsbuissnessApplication extends SpringBootServletInitializer  {

    public static void main(String[] args) {

        SpringApplication.run(WsbuissnessApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(WsbuissnessApplication.class);
    }

    @Autowired
    CountryWisePriceDaoListService countryWisePriceDaoListService;
    @Autowired
    CloudApiErrorCodeService cloudApiErrorCodeService;

    @PostConstruct
    private void init() throws Exception {

        try{
            countryWisePriceDaoListService.refreshCountryHashmap();
            cloudApiErrorCodeService.refreshCache();
        }catch (Exception e){
            logger.info("excception occurs");
        }
    }

    @Bean
    public RedisTemplate<String, UserOrBuisnessIntiatedDao> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, UserOrBuisnessIntiatedDao> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // Add some specific configuration here. Key serializers, etc.
        return template;
    }

}

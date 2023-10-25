package com.DesafioPicPay.config;


import com.DesafioPicPay.helper.MessageHelper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    MessageHelper messageHelper(MessageSource messageSource) {
        return new MessageHelper(messageSource);
    }

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:i18n/messages");
        source.setCacheSeconds(3600);
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

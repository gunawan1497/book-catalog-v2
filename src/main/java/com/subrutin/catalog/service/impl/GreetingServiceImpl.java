package com.subrutin.catalog.service.impl;

import com.subrutin.catalog.config.ApplicationProperties;
import com.subrutin.catalog.config.CloudProperties;
import com.subrutin.catalog.service.GreetingService;
import com.subrutin.catalog.web.HelloResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.TimeZone;

@Service
public class GreetingServiceImpl implements GreetingService {

    Logger log = LoggerFactory.getLogger(GreetingServiceImpl.class);

    private ApplicationProperties appProperties;

    private CloudProperties cloudProperties;

    public GreetingServiceImpl(ApplicationProperties appProperties, CloudProperties cloudProperties) {
        this.appProperties = appProperties;
        this.cloudProperties = cloudProperties;
    }

    //    sudah tidak perlu lagi diganti appProperties
//    @Value("${welcome.text}")
//    private String welcomeText;
//
//    @Value("${timezone}")
//    private String timeZone;
//
//    @Value("${currency}")
//    private String currency;

    @Override
    public String sayGreeting() {
        log.trace("this is log trace");
        log.debug("this is log debug");
        log.info("this is log info");
        log.warn("this is log warn");
        log.error("this is log error");
        System.out.println(cloudProperties.getApiKey());
        TimeZone timezone = TimeZone.getTimeZone(appProperties.getTimezone());
        return appProperties.getWelcomeText()+", our timezone :"+timezone.getDisplayName()+", our currency :"+appProperties.getCurrency();
    }
}

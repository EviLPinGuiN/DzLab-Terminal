package com.dz.tele2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Alex on 26.09.15.
 */
@Configuration
@ComponentScan(basePackages = {"com.dz.tele2.service"})
public class ServiceConfig {

    /**
     * This configuration class used only for apply component scan on
     * service classes to create needed beans marked by "@Service"
     * annotation.
     */
}

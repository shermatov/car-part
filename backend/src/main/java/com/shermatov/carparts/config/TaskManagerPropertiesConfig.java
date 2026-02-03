package com.shermatov.carparts.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FrontendProperties.class)
public class TaskManagerPropertiesConfig {
}

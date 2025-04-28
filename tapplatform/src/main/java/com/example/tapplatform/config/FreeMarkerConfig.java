package com.example.tapplatform.config;

import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@org.springframework.context.annotation.Configuration
@ComponentScans({
        @ComponentScan("com.example.tapplatform.codegen")
})
public class FreeMarkerConfig {

    @Bean
    public Configuration customFreeMarkerConfiguration() {  // 将方法名从freeMarkerConfiguration改为customFreeMarkerConfiguration
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }
}
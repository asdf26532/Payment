package com.itbank.smartFarm.config;

import com.itbank.smartFarm.components.Paging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Paging paging() {
        return new Paging();
    }
}

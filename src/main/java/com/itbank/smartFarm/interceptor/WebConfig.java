package com.itbank.smartFarm.interceptor;

import com.itbank.smartFarm.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private NoticeInterceptor noticeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/member/myPage", "/member/update", "/board/freemarket_write", "/board/fBadd");

        registry.addInterceptor(noticeInterceptor)
                .addPathPatterns("/board/notice_write");
    }
}

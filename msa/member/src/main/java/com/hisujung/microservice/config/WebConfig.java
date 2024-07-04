//package com.hisujung.microservice.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//
//@RequiredArgsConstructor
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    public final LoginUserArgumentResolver loginUserArgumentResolver;
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        //HandlerMethodArgumentResolver 구현체를 여기에서 등록해줘야 한다.
//        argumentResolvers.add(loginUserArgumentResolver);
//    }
//}

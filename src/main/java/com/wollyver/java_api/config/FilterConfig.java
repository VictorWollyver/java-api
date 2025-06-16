package com.wollyver.java_api.config;

import com.wollyver.java_api.middlewares.JwtMiddleware;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
  @Bean
  public FilterRegistrationBean<JwtMiddleware> jwtFilter() {
    FilterRegistrationBean<JwtMiddleware> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new JwtMiddleware());
    registrationBean.addUrlPatterns("/users/private");
    return registrationBean;
  }
}
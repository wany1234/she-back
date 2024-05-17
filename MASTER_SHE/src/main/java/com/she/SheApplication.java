package com.she;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.she.file.model.FileStorageProperties;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({ FileStorageProperties.class })
public class SheApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SheApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SheApplication.class, args);
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize(100000000);
        factory.setMaxRequestSize(100000000);

        return factory.createMultipartConfig();
    }

    // @Bean
    // public Filter characterEncodingFilter() {
    // CharacterEncodingFilter characterEncoding = new
    // CharacterEncodingFilter();
    // characterEncoding.setEncoding("UTF-8");
    // characterEncoding.setForceEncoding(true);
    // return characterEncoding;
    // }

    // @Bean
    // public FilterRegistrationBean encodingFilterBean() {
    // FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    // CharacterEncodingFilter characterEncodingFilter = new
    // CharacterEncodingFilter();
    // characterEncodingFilter.setForceEncoding(true);
    // characterEncodingFilter.setEncoding("UTF-8");
    // registrationBean.setFilter(characterEncodingFilter);
    // return registrationBean;
    // }
}
package com.she.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
  * @클래스명 : PasswordEncoderConfig.java
  * @설명 : Spring Security의 암호화 인터페이스
  * @작성일 : 2018
  * @작성자 : 열린기술 (김유경)
  * @변경이력 :
 */
@Configuration
public class PasswordEncoderConfig {
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
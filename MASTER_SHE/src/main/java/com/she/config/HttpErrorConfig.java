package com.she.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * DispatcherServlet에서 기본 처리되는 에러(403, 404, 500)를
 * 공통 에러 형식으로 처리를 하기 위한 설정
 */
@Component
public class HttpErrorConfig implements
                WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
    }
}

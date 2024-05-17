package com.she.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @클래스명 : SwaggerConfig.java
 * @설명 : API Docs를 위한 Swagger 설정
 *     http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
 * @작성일 : 2019. 3. 29.
 * @작성자 : 열린기술 (김유경)
 * @변경이력 :
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private Environment environment;

    @Autowired
    private JwtSettings jwtSettings;

    /**
     * 모든 리소스 조회
     * 
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("she").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/.*")).build().apiInfo(apiInfo(""));
    }

    /**
     * 시스템관리
     * 
     * @return
     */
    @Bean
    public Docket manageApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("manage").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/manage/.*")).build().apiInfo(apiInfo("시스템관리 REST API"));
    }

    /**
     * 메인
     * 
     * @return
     */
    @Bean
    public Docket mainApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("main").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/main/.*")).build().apiInfo(apiInfo("메인 REST API"));
    }

    /**
     * 환경
     * 
     * @return
     */
    @Bean
    public Docket envApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("env").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/env/.*")).build().apiInfo(apiInfo("환경 REST API"));
    }

    /**
     * 안전
     * 
     * @return
     */
    @Bean
    public Docket safApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("saf").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/saf/.*")).build().apiInfo(apiInfo("안전 REST API"));
    }

    /**
     * 기초정보
     * 
     * @return
     */
    @Bean
    public Docket baseinfoApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("baseinfo").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/baseinfo/.*")).build().apiInfo(apiInfo("기초정보 REST API"));
    }

    /**
     * 공통정보
     * 
     * @return
     */
    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("common").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/common/.*")).build().apiInfo(apiInfo("공통정보 REST API"));
    }

    /**
     * 직무위험성평가
     * 
     * @return
     */
    @Bean
    public Docket rsaApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("rsa").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/rsa/.*")).build().apiInfo(apiInfo("직무위험성평가 REST API"));
    }

    /**
     * 물질관리
     * 
     * @return
     */
    @Bean
    public Docket chmApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("chm").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/chm/.*")).build().apiInfo(apiInfo("물질관리 REST API"));
    }

    /**
     * 경영관리
     * 
     * @return
     */
    @Bean
    public Docket mgtApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("mgt").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/mgt/.*")).build().apiInfo(apiInfo("경영관리 REST API"));
    }

    /**
     * PSM관리
     * 
     * @return
     */
    @Bean
    public Docket psmApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("psm").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/psm/.*")).build().apiInfo(apiInfo("PSM관리 REST API"));
    }

    /**
     * 첨부관리
     * 
     * @return
     */
    @Bean
    public Docket attachfileApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("attachfile").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/attachfile/.*")).build().apiInfo(apiInfo("첨부관리 REST API"));
    }

    /**
     * 인증관리
     * 
     * @return
     */
    @Bean
    public Docket authApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("auth").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/auth/.*")).build().apiInfo(apiInfo("인증처리 REST API"));
    }

    /**
     * 첨부
     * 
     * @return
     */
    @Bean
    public Docket restFileApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("file").globalOperationParameters(this.getTokenParam()).select().apis(RequestHandlerSelectors.any()).paths(regex("/api/file/.*")).build().apiInfo(apiInfo("파일첨부 REST API"));
    }

    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration("validatorUrl", "list", "alpha", "schema", UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
    }

    /**
     * 토큰정보설정
     * 
     * @return
     */
    private List<Parameter> getTokenParam() {
        String jwtToken = "Bearer xxx.xxx.xxx";

        // 토큰을 설정에서처리 : 테스트인경우 xxx.xxx.xxx, 개발인경우 30일토큰
        jwtToken = "Bearer " + jwtSettings.getDevtoken();

        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name(jwtSettings.getTokenHeader()).defaultValue(jwtToken).description("jwtToken").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        String swaggerKey = jwtSettings.getSwaggerkey();
        ParameterBuilder cParameterBuilder = new ParameterBuilder();
        cParameterBuilder.name(jwtSettings.getSwaggerHeader()).defaultValue(swaggerKey).description("SwaggerKey").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
        aParameters.add(cParameterBuilder.build());

        return aParameters;
    }

    private ApiInfo apiInfo(String strTitle) {
        @SuppressWarnings("rawtypes")
        ApiInfo apiInfo = new ApiInfo("SHE REST API", "REST API 인증서비스", " API V0.1", "Terms of service", new Contact("YullinTechnology", "http://www.yullin.com", "info@yullin.com"), "License of API", environment.getRequiredProperty("she.defaultUrl") + "/apilicense", new ArrayList<VendorExtension>());
        return apiInfo;
    }

}

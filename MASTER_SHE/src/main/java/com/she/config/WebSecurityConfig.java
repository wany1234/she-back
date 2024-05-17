package com.she.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.she.security.auth.jwt.JwtJoseAuthenticationProvider;
import com.she.security.auth.jwt.JwtJoseTokenAuthenticationProcessingFilter;
import com.she.security.auth.jwt.SkipPathRequestMatcher;
import com.she.security.auth.jwt.extractor.TokenExtractor;
import com.she.security.controller.RestAuthenticationEntryPoint;

/**
 * WebSecurityConfig
 *
 * @author vladimir.stankovic
 *
 *         Aug 3, 2016
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SWAGGER_PATH_PREFIX = "";
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String FORM_SSO_LOGIN_ENTRY_POINT = SWAGGER_PATH_PREFIX + "/api/auth/sso";
    public static final String LANG_ENTRY_POINT = SWAGGER_PATH_PREFIX + "/api/langs";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = SWAGGER_PATH_PREFIX + "/api/auth/login";
    public static final String FORM_VENDOR_LOGIN_ENTRY_POINT = SWAGGER_PATH_PREFIX + "/api/auth/vendorlogin";
    public static final String TOKEN_REFRESH_ENTRY_POINT = SWAGGER_PATH_PREFIX + "/api/auth/refresh";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = SWAGGER_PATH_PREFIX + "/api/**";
    public static final String SWAGGER_API_DOCS_ALL = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=she";
    public static final String SWAGGER_API_DOCS_MANAGE = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=manage";
    public static final String SWAGGER_API_DOCS_MAIN = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=main";
    public static final String SWAGGER_API_DOCS_ENV = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=env";
    public static final String SWAGGER_API_DOCS_SAF = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=saf";
    public static final String SWAGGER_API_DOCS_BASEINFO = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=baseinfo";
    public static final String SWAGGER_API_DOCS_COMMON = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=common";
    public static final String SWAGGER_API_DOCS_RSA = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=rsa";
    public static final String SWAGGER_API_DOCS_CHM = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=chm";
    public static final String SWAGGER_API_DOCS_MGT = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=mgt";
    public static final String SWAGGER_API_DOCS_PSM = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=psm";
    public static final String SWAGGER_API_DOCS_ATTACHFILE = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=attachfile";
    public static final String SWAGGER_API_DOCS_AUTH = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=auth";
    public static final String SWAGGER_API_DOCS_FILE = SWAGGER_PATH_PREFIX + "/v2/api-docs?group=file";
    public static final String SWAGGER_UI = SWAGGER_PATH_PREFIX + "/swagger-ui.html";
    public static final String GROUPWARE_API = SWAGGER_PATH_PREFIX + "/sheappr/**";

    public static final String COMM_EXPORT_ENTRY_POINT = SWAGGER_PATH_PREFIX + "/api/common/export";

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private JwtJoseAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private JwtSettings jwtSettings;

    @Autowired
    private AuthenticationManager authenticationManager;

    protected JwtJoseTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT, FORM_SSO_LOGIN_ENTRY_POINT, LANG_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT, FORM_VENDOR_LOGIN_ENTRY_POINT, SWAGGER_API_DOCS_ALL, SWAGGER_UI, SWAGGER_API_DOCS_MANAGE, SWAGGER_API_DOCS_MAIN, SWAGGER_API_DOCS_ENV, SWAGGER_API_DOCS_SAF, SWAGGER_API_DOCS_BASEINFO,
                SWAGGER_API_DOCS_COMMON, SWAGGER_API_DOCS_RSA, SWAGGER_API_DOCS_CHM, SWAGGER_API_DOCS_MGT, SWAGGER_API_DOCS_PSM, SWAGGER_API_DOCS_ATTACHFILE, SWAGGER_API_DOCS_AUTH, SWAGGER_API_DOCS_FILE, GROUPWARE_API, COMM_EXPORT_ENTRY_POINT);

        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtJoseTokenAuthenticationProcessingFilter filter = new JwtJoseTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, jwtSettings, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // We don't need CSRF for JWT based authentication
                .exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint)

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and().authorizeRequests().antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll() // Login
                                                                                                 // end-point
                .antMatchers(FORM_SSO_LOGIN_ENTRY_POINT).permitAll().antMatchers(LANG_ENTRY_POINT).permitAll().antMatchers(FORM_VENDOR_LOGIN_ENTRY_POINT).permitAll().antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll() // Token
                // refresh
                // end-point
                .antMatchers(SWAGGER_API_DOCS_ALL).permitAll() // Login
                                                               // end-point
                .antMatchers(SWAGGER_UI).permitAll().antMatchers(SWAGGER_API_DOCS_MANAGE).permitAll().antMatchers(SWAGGER_API_DOCS_MAIN).permitAll().antMatchers(SWAGGER_API_DOCS_ENV).permitAll().antMatchers(SWAGGER_API_DOCS_SAF).permitAll().antMatchers(SWAGGER_API_DOCS_BASEINFO).permitAll().antMatchers(SWAGGER_API_DOCS_COMMON).permitAll()
                .antMatchers(SWAGGER_API_DOCS_RSA).permitAll().antMatchers(SWAGGER_API_DOCS_CHM).permitAll().antMatchers(SWAGGER_API_DOCS_MGT).permitAll().antMatchers(SWAGGER_API_DOCS_PSM).permitAll().antMatchers(SWAGGER_API_DOCS_ATTACHFILE).permitAll().antMatchers(SWAGGER_API_DOCS_AUTH).permitAll().antMatchers(SWAGGER_API_DOCS_FILE).permitAll()
                .antMatchers("/console").permitAll() // H2 Console Dash-board -
                                                     // only for testing
                .antMatchers("/resources/**").permitAll() // H2 Console
                                                          // Dash-board - only
                                                          // for testing
                .antMatchers(GROUPWARE_API).permitAll() // 그룹웨어 조회
                .antMatchers(COMM_EXPORT_ENTRY_POINT).permitAll() // Auigrid_export_excel_file
                .and().authorizeRequests().antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated() // Protected
                                                                                                     // API
                                                                                                     // End-points
                .and().addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class).addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

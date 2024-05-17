package com.she.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.Lang;
import com.she.common.model.LangParam;
import com.she.common.service.LangService;
import com.she.security.exception.ExceptionCode;
import com.she.security.exception.RestAuthException;
import com.she.security.model.token.JwtJoseToken;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiOperation;

@RestController
public class LangController {

    @Autowired
    private LangService userLangService;

    @Autowired
    private RequestMapper requestMapper;

    @ApiOperation(httpMethod = "GET", value = "언어 정보 반환(메시지, 라벨)", response = JwtJoseToken.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/api/langs")
    public ResponseEntity<Lang> langs(@RequestBody LangParam langParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (langParam == null || langParam.getLang() == null) {
            throw new RestAuthException(HttpStatus.UNAUTHORIZED, "Fail", ExceptionCode.UNAUTHORIZED.name());
        }

        return ResponseEntity.ok().body(userLangService.getLangInfo(langParam));
    }
}

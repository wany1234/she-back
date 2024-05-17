package com.she.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.mapper.LangMapper;
import com.she.common.model.Lang;
import com.she.common.model.LangParam;

@Service
public class LangService {

    @Autowired
    private LangMapper userLangMapper;

    /**
     * 언어별 메시지/라벨 값을 반환
     * 
     * @param lang
     *            언어
     * @return 언어별 메시지/라벨 값
     * @throws Exception
     */
    public Lang getLangInfo(LangParam langParam) throws Exception {
        Lang langObject = new Lang();
        langObject.setLabel(userLangMapper.getLangInfoLabel(langParam));
        langObject.setMessage(userLangMapper.getLangInfoMessage(langParam));
        return langObject;
    }

}

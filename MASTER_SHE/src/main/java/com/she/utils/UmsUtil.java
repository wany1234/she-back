package com.she.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class UmsUtil {

    private static final Logger logger = LoggerFactory.getLogger(UmsUtil.class);

    private static String API_URL = "http://localhost:8080/api/umsSendAltMnApi.ums";
    private static String SENDER_ID = "SHEADMIN";
    private static String KKOALT_SVC_ID = "";
    private static String KKOALT_TMPL_CD = "";

    public static String sendKakaoTalk() throws Exception {
        String result = "";

        try {
            logger.info("#### sendKakaoTalk start ####");

            URL url = new URL(API_URL);

            // 파라미터 셋팅
            List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
            Map<String, Object> params1 = new HashMap<String, Object>();
            params1.put("id", "test1");
            String[] temp1 = { "01012341234", "홍길동" };
            params1.put("var", temp1);

            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.put("id", "test2");
            String[] temp2 = { "01056785678", "김길동" };
            params2.put("var", temp2);

            paramList.add(params1);
            paramList.add(params2);

            JSONArray jsonArray = new JSONArray();
            for (Map<String, Object> map : paramList) {
                jsonArray.add(new JSONObject(map));
            }

            // String test = "{'testId':['01012345678','홍길동'],
            // 'testId2':['01012345678','박길동']}";
            // Map<String, Object> params = new HashMap<String, Object>();
            // params.put("CUIDS", test);
            //
            // JSONObject jsonObject = new JSONObject(params);
            // for (Map.Entry<String, String> element : params.entrySet()) {
            // jsonObject.put(element.getKey(), element.getValue());
            // }

        } catch (MalformedURLException e) {
            throw e;
        }

        return result;
    }
}

package com.she.security.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthUtil {

    private static final Logger logger = LoggerFactory.getLogger(AuthUtil.class);

    /**
     *
     * <pre>
     * 개요 : 클라이언트 아이피 가져오기
     * 1) 헤더정보를 확인하여 해당 헤더에서 IP가져오기
     * 2) 1번이 없으면 Request 헤더의 "X-FORWARDED-FOR"정보 가져오기
     * 3) 2번도 없으면 request.getRemoteAddr()가져오기
     * </pre>
     * @ClassName : AuthUtil.java
     * @MethodName : getClientIpAddress
     * @param request
     * @param localIp : FrontEnd에서 넘겨주는 아이피 정보
     * @return
     */
    public static String getClientIpAddress(HttpServletRequest request, String localIp) {
        logger.info("*************** localIp *****************");
        logger.info("*************** " + localIp);
        logger.info("*************** localIp *****************");
        logger.info("*************** X-FORWARDED-FOR *****************");
        logger.info("*************** " + request.getHeader("X-FORWARDED-FOR"));
        logger.info("*************** X-FORWARDED-FOR *****************");
        logger.info("*************** getRemoteAddr *****************");
        logger.info("*************** " + request.getRemoteAddr());
        logger.info("*************** getRemoteAddr *****************");
        logger.info("*************** X-Real-IP *****************");
        logger.info("*************** " + request.getHeader("X-Real-IP"));
        logger.info("*************** X-Real-IP *****************");

        if (localIp != null && !localIp.isEmpty()) {
            return localIp;
        }

        String clientIp = request.getHeader("X-FORWARDED-FOR");
        if (clientIp != null && !clientIp.isEmpty()) {
            return clientIp;
        }

        return request.getRemoteAddr();
    }

    /**
     * RequestParam의 헤더정보 로그확인
     * @param request
     */
    public static void writeRequestParamsInfo(HttpServletRequest request) {
        Enumeration<String> em = request.getHeaderNames();
        while (em.hasMoreElements()) {
            String name = em.nextElement();
            String val = request.getHeader(name);
            logger.debug("::: header parameters [name] : " + name  + ",  [val] : " + val);
        }
    }

    /**
     *
     * <pre>
     * 개요 : RequestParam의 헤더정보 가져오기
     * </pre>
     * @ClassName : AuthUtil.java
     * @MethodName : getRequestParamsInfo
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParamsInfo(HttpServletRequest request) {
        Map<String, String> returnResult = new HashMap<String, String>();
        Enumeration<String> em = request.getHeaderNames();
        while (em.hasMoreElements()) {
            String name = em.nextElement();
            String val = request.getHeader(name);
            returnResult.put(name, val);
        }
        return returnResult;
    }

    /**
     *
     * <pre>
     * 개요 : 모바일,타블렛,PC구분
     * </pre>
     * @ClassName : AuthUtil.java
     * @MethodName : isMobile
     * @param req
     * @return
     */
    public static Boolean isMobile(HttpServletRequest req) {
        String userAgent = req.getHeader("User-Agent").toUpperCase();
        if (userAgent.indexOf(DeviceType.MOBILE.name()) > -1) {
            if (userAgent.indexOf(DeviceType.PHONE.name()) == -1) {
                logger.debug("::: is Phone connected! " + DeviceType.MOBILE.name());
            } else {
                logger.debug("::: is Tablet connected! : " + DeviceType.TABLET.name());
            }
            return true;
        } else {
            logger.debug("::: is Desktop connected! : " + DeviceType.PC.name());
            return false;
        }
    }
}

package com.she.security.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
public class WebUtil {


    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

    private static final String CONTENT_TYPE = "Content-type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    public static boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    public static boolean isAjax(SavedRequest request) {
        return request.getHeaderValues(X_REQUESTED_WITH).contains(XML_HTTP_REQUEST);
    }

    public static boolean isContentTypeJson(SavedRequest request) {
        return request.getHeaderValues(CONTENT_TYPE).contains(CONTENT_TYPE_JSON);
    }

    public static String getSubDomain(String requestURL) {
        // requestURL = "http://yullin.clabmate.com/api/auth/login";

        String withoutProtocol = requestURL.replaceAll("(.*\\/{2})", "");
        String withoutPort = withoutProtocol.replaceAll("(:\\d*)", "");
        String domain = withoutPort.replaceAll("(\\/.*)", "");
        String subdomain = domain.split("\\.")[0];

        return subdomain;
    }

    public static String getSubDomain(HttpServletRequest request) {
        // requestURL = "http://yullin.clabmate.com/api/auth/login";
        String domain = request.getServerName();
        String subdomain = domain.split("\\.")[0];

        return subdomain;
    }

    public static String filePathBlackList(String value) {
        String returnValue = value;
        if (returnValue == null || returnValue.trim().equals("")) {
            return "";
        }

        returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
        returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

        return returnValue;
    }

    /**
     * 행안부 보안취약점 점검 조치 방안.
     *
     * @param value
     * @return
     */
    public static String filePathReplaceAll(String value) {
        String returnValue = value;
        if (returnValue == null || returnValue.trim().equals("")) {
            return "";
        }

        returnValue = returnValue.replaceAll("/", "");
        returnValue = returnValue.replaceAll("\\", "");
        returnValue = returnValue.replaceAll("\\.\\.", ""); // ..
        returnValue = returnValue.replaceAll("&", "");

        return returnValue;
    }

    public static String filePathWhiteList(String value) {
        return value;
    }

    public static String clearXSSMinimum(String value) {
        if (value == null || value.trim().equals("")) {
            return "";
        }

        String returnValue = value;

        returnValue = returnValue.replaceAll("&", "&amp;");
        returnValue = returnValue.replaceAll("<", "&lt;");
        returnValue = returnValue.replaceAll(">", "&gt;");
        returnValue = returnValue.replaceAll("\"", "&#34;");
        returnValue = returnValue.replaceAll("\'", "&#39;");
        returnValue = returnValue.replaceAll(".", "&#46;");
        returnValue = returnValue.replaceAll("%2E", "&#46;");
        returnValue = returnValue.replaceAll("%2F", "&#47;");
        return returnValue;
    }

    public static String clearXSSMaximum(String value) {
        String returnValue = value;
        returnValue = clearXSSMinimum(returnValue);

        returnValue = returnValue.replaceAll("%00", null);

        returnValue = returnValue.replaceAll("%", "&#37;");

        // \\. => .

        returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
        returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\
        returnValue = returnValue.replaceAll("\\./", ""); // ./
        returnValue = returnValue.replaceAll("%2F", "");

        return returnValue;
    }

    public static boolean isIPAddress(String str) {
        Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
        return ipPattern.matcher(str).matches();
    }

    public static String removeCRLF(String parameter) {
        return parameter.replaceAll("\r", "").replaceAll("\n", "");
    }

    public static String removeSQLInjectionRisk(String parameter) {
        return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("%", "").replaceAll(";", "").replaceAll("-", "").replaceAll("\\+", "").replaceAll(",", "");
    }

    public static String removeOSCmdRisk(String parameter) {
        return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("|", "").replaceAll(";", "");
    }

     /**
      * 첨부가능한 문서 화이트 리스트 체크
      * @param file
      * @return
      */
    public static boolean isWhiteFileExt(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        final String[] whiteExtension = {"txt", "doc", "docx", "pptx", "ppt", "xls", "xlsx", "pdf", "csv", "zip", "hwp", "dwg", "jpeg"};

        int len = whiteExtension.length;
        for (int i = 0; i < len; i++) {
            if (ext.equalsIgnoreCase(whiteExtension[i])) {
                return true;
            }
        }

        return false;
    }

     /**
      * 확장자조회
      * @param file
      * @return
      */
    public static String getFileExt(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

     /**
      * 첨부가능한 이미지파일 화이트 리스트 체크
      * @param file
      * @return
      */
    public static boolean isWhiteImageExt(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        final String[] whiteExtension = {"jpg", "gif", "png", "jpeg"};

        int len = whiteExtension.length;
        for (int i = 0; i < len; i++) {
            if (ext.equalsIgnoreCase(whiteExtension[i])) {
                return true;
            }
        }
        return false;
    }

     /**
      * 브라우저 정보 조회
      * @param request
      * @return
      */
    public static String getBrowser(HttpServletRequest request) {

        String header = request.getHeader("User-Agent");

        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) {   // IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        } else if (header.indexOf("Safari") > -1) {
            return "Safari";
        }

        return "Firefox";
    }

    public static boolean isImageYn(String ext) {
        final String[] whiteExtension = {"jpg", "gif", "png", "jpeg"};

        int len = whiteExtension.length;
        for (int i = 0; i < len; i++) {
            if (ext.equalsIgnoreCase(whiteExtension[i])) {
                return true;
            }
        }
        return false;
    }

     /**
      * 인코딩된 파일명 반환
      * @param request
      * @param fileName
      * @return
      * @throws UnsupportedEncodingException
      */
    @SuppressWarnings("deprecation")
    public static String getEncodedFileName(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {

        String encodedFilename = "";
        String browser = WebUtil.getBrowser(request);

        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Trident")) {       // IE11 문자열 깨짐 방지
            encodedFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
            encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
            encodedFilename = URLDecoder.decode(encodedFilename);
        } else if (browser.equals("Opera")) {
            encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < fileName.length(); i++) {
                char c = fileName.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
        } else if (browser.equals("Safari")) {
            encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
            encodedFilename = URLDecoder.decode(encodedFilename);
        } else {
            encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
        }

        return encodedFilename;
    }

}

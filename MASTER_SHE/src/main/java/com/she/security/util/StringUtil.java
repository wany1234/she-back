package com.she.security.util;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class StringUtil {
    @SuppressWarnings("unused")
    private static final String SEP = ",";
    private static final char[] ZEROARRAY = "0000000000000000000000000000000000000000000000000000000000000000".toCharArray();

    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    public static String randomString(int len) {
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    public static String makeUniqueRandomString(int len, List<String> lists) {
        String randomStr = StringUtil.randomString(len);
        boolean isFound = false;

        do {
            isFound = false;
            for (String str : lists) {
                if (str.equalsIgnoreCase(randomStr)) {
                    isFound = true;
                    randomStr = StringUtil.randomString(len);
                    break;
                }
            }
        } while (isFound);

        return randomStr;
    }

    /**
     * List형의 데이터가 널이거나 값이 없는 경우 true
     * 
     * @param obj
     * @return
     */
    public static boolean isNullOrEmptyOfList(List<?> obj) {
        return obj == null || obj.size() == 0;
    }

    public static boolean isNotEmpty(String s) {

        if (s == null) {
            return false;
        }

        if (s.trim().length() == 0) {
            return false;
        }

        return true;
    }

    public static boolean isEmpty(String s) {

        if (s == null) {
            return true;
        }

        if (s.trim().length() == 0) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }

        if (object instanceof String) {
            String str = (String) object;
            return str.length() == 0;
        }

        if (object instanceof Collection) {

            Collection<?> collection = (Collection<?>) object;
            return collection.size() == 0;
        }

        if (object.getClass().isArray()) {
            try {
                if (Array.getLength(object) == 0) {
                    return true;
                }
            } catch (IllegalArgumentException iae) {
                logger.error(iae.getMessage());
                return false;
            } catch (Exception e) {
                logger.error(e.getMessage());
                return false;
            }
        }

        return false;
    }

    public static String isEmptyToString(Object object, String retString) {
        if (StringUtil.isEmpty(object)) {
            return retString;
        }

        return object.toString();
    }

    public static boolean isIntegerNullOrLessZero(Integer object) {
        return object == null || object <= 0;
    }

    public static boolean isIntegerNullOrZero(Integer object) {
        return object == null || object == 0;
    }

    public static boolean isLongNullOrLessZero(Long object) {
        return object == null || object <= 0;
    }

    public static boolean isLongNullOrZero(Long object) {
        return object == null || object == 0;
    }

    public static boolean isLongNull(Long object) {
        return object == null;
    }

    public static boolean isIntegerNull(Integer object) {
        return object == null;
    }

    public static Integer convNullToZero(Integer obj) {
        if (obj == null) {
            return 0;
        }

        return obj;
    }

    public static Long convLongNullToZero(Long obj) {
        if (obj == null) {
            return new Long(0);
        }

        return obj;
    }

    public static Float convFloatNullToZero(Float obj) {
        if (obj == null) {
            return new Float(0);
        }

        return obj;
    }

    public static boolean isIntNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Long형 데이터를 널이거나 0인경우 0, 나머지 원래값
     * 
     * @param a
     * @return
     */
    public static Long convLongZeroToNull(Long a) {
        if (a == null) {
            return null;
        } else if (a == 0) {
            return null;
        } else {
            return a;
        }
    }

    public static String zeroPadString(String string, int length) {
        if (string == null || string.length() > length) {
            return string;
        }
        StringBuilder buf = new StringBuilder(length);
        buf.append(ZEROARRAY, 0, length - string.length()).append(string);
        return buf.toString();
    }

    public static String getMsg(MessageSource messageSource, String codeLang, String defaultMessage) {
        return messageSource.getMessage(codeLang, null, defaultMessage, LocaleContextHolder.getLocale());
    }

}

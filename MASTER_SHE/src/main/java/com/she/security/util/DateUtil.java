package com.she.security.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {




    public static String DATE_SEP = "-";

    /**
     * Java.sql.date 형식의 Timestamp를 요청된 포맷으로 String형식으로 반환
     * @param timestamp
     * @param format
     * @return
     * example)
     * return DateUtil.timeStampToDate(this.insertTs, DateFormat.yyyy_MM_dd);
     */
    public static String timeStampToDate(Date date, DateFormat format) {

        if (date == null) {
            return null;
        }

        DateFormatConverter conv = new DateFormatConverter();
        String value = conv.convertToDatabaseColumn(format);

        return new SimpleDateFormat(value).format(date);
    }


    public static String timeStampToDate(DateTime date, DateFormat format) {

        if (date == null) {
            return null;
        }

        DateFormatConverter conv = new DateFormatConverter();
        String value = conv.convertToDatabaseColumn(format);

        return new SimpleDateFormat(value).format(date);
    }

    /**
     * 현재 Timestamp 조회
     * @return
     */
    public static Date currentTimeStamp() {
        return new Date();
    }

    /**
     * 년월일정보만 가지는 현재 날짜 조회
     * @return
     */
    public static Date currentDate() {
        return DateUtil.stringToDate(DateUtil.timeStampToDate(new Date(), DateFormat.yyyy_MM_dd), DateFormat.yyyy_MM_dd);
    }

    public static String currentDateYYYYMMDD() {
        return DateUtil.timeStampToDate(new Date(), DateFormat.yyyyMMdd);
    }

    /**
     * 날짜형식의 문자열을 날짜로 변경
     * @param strDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strDate, DateFormat format) {
        DateFormatConverter conv = new DateFormatConverter();
        String value = conv.convertToDatabaseColumn(format);
        SimpleDateFormat transFormat = new SimpleDateFormat(value);

        try {
            return transFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static java.sql.Date stringToDateSql(String strDate, DateFormat format) {
        DateFormatConverter conv = new DateFormatConverter();
        String value = conv.convertToDatabaseColumn(format);
        SimpleDateFormat transFormat = new SimpleDateFormat(value);

        try {
            return (java.sql.Date) transFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static DateTime stringToDateTime(String strDate, DateFormat format) {
        DateFormatConverter conv = new DateFormatConverter();
        String value = conv.convertToDatabaseColumn(format);
        DateTimeFormatter fmt = org.joda.time.format.DateTimeFormat.forPattern(value);
        DateTime dt = DateTime.parse(strDate, fmt);

        return dt;
    }

    /**
     * 날짜형식이 맞는지 여부
     * @param dateToValidate
     * @return
     */
    public static boolean validateDate(String dateToValidate) {

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static boolean validateDate(String dateToValidate, DateFormat format) {

        if (dateToValidate == null) {
            return false;
        }

        DateFormatConverter conv = new DateFormatConverter();
        String value = conv.convertToDatabaseColumn(format);
        SimpleDateFormat sdf = new SimpleDateFormat(value);

        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            @SuppressWarnings("unused")
            Date date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static boolean validateYyyy(String dateToValidate) {

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static boolean validateYyyyMM(String dateToValidate) {

        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static String uniqueTimebasedVal(String preFix) {
        return preFix + "-" + (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
    }

    /**
     * 마지막날짜 조회
     * @param convDate  : 201710
     * @return 20171031
     */
    public static String getLastDay(String convDate) {
        int year = Integer.parseInt(convDate.substring(0, 4));
        int month = Integer.parseInt(convDate.substring(4, 6));

        int day = 1;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day); //월은 -1해줘야 해당월로 인식

        return  Integer.toString(year)
                + StringUtil.zeroPadString(Integer.toString(month), 2)
                + StringUtil.zeroPadString(Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH)), 2);
    }



    /**
     * 날짜더하기
     * @param date
     * @param addDays
     * @param dateFormat
     * @return
     */
    public static String addDays(String date, Integer addDays, DateFormat dateFormat) {

        DateTimeFormatter fmt = org.joda.time.format.DateTimeFormat.forPattern(dateFormat.getDateFormat());
        DateTime dt = DateTime.parse(date, fmt);
        return dt.plusDays(addDays).toString(dateFormat.getDateFormat());
    }


    /**
     * 날짜더하기
     * @param date
     * @param addDays
     * @param dateFormat
     * @return
     */
    public static String addMonths(String date, Integer addMonths, DateFormat dateFormat) {

        DateTimeFormatter fmt = org.joda.time.format.DateTimeFormat.forPattern(dateFormat.getDateFormat());
        DateTime dt = DateTime.parse(date, fmt);
        return dt.plusMonths(addMonths).toString(dateFormat.getDateFormat());
    }

    /**
     * 주단위 더하기
     * @param date
     * @param addWeeks
     * @param dateFormat
     * @return
     */
    public static String addWeeks(String date, Integer addWeeks, DateFormat dateFormat) {

        DateTimeFormatter fmt = org.joda.time.format.DateTimeFormat.forPattern(dateFormat.getDateFormat());
        DateTime dt = DateTime.parse(date, fmt);
        return dt.plusWeeks(addWeeks).toString(dateFormat.getDateFormat());
    }

    /**
     * 두 일시간의 분 구하기
     * @param fromDate
     * @param toDate
     * @return
     */
    public static Integer getDateDiffMinutes(DateTime fromDate, DateTime toDate) {
        Integer iMin = (int) Minutes.minutesBetween(fromDate, toDate).getMinutes();
        return (int) iMin;
    }

    /**
     * 날짜형식의 문자열에서 문자제거
     */
    public static String clearDateCharacter(String dateTime) {
        return dateTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").trim();
    }

    /**
     * 통계의 날짜조건
     * 구분 : 연간/월간
     * 연간인경우 : startDate=2015, endDate=2017
     * 월간인경우 : startDate=201701, endDate=201705
     * @param conditions
     * @return
     */
    /*public static Map<String, String> getStartEndDate(StatSearchCond conditions) {
        Map<String, String> map = new HashMap<String, String>();

        if (conditions.getDateType().equalsIgnoreCase(DateType.YEAR.name())) {
            // 년간
            if (!StringUtil.isEmpty(conditions.getStartDate()))
                map.put("startDate", conditions.getStartDate() + "0101");
            else
                map.put("startDate", "");

            if (!StringUtil.isEmpty(conditions.getEndDate()))
                map.put("endDate", conditions.getEndDate() + "1231");
            else
                map.put("endDate", "");

            map.put("dateFormat", "%Y");
            map.put("diffDate", conditions.getDiffDate());
        }
        else {
            // 월간
            if (!StringUtil.isEmpty(conditions.getStartDate()))
                map.put("startDate", conditions.getStartDate() + "01");
            else
                map.put("startDate", "");

            if (!StringUtil.isEmpty(conditions.getEndDate()))
                map.put("endDate", DateUtil.getLastDay(conditions.getEndDate()));
            else
                map.put("endDate", "");

            map.put("dateFormat", "%Y.%m");

            if (!StringUtil.isEmpty(conditions.getDiffDate()))
                map.put("diffDate", conditions.getDiffDate().substring(0, 4) + "." + conditions.getDiffDate().substring(4, 6));

        }

        return map;
    }*/
}

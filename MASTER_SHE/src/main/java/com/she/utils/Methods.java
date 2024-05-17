/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */

package com.she.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 공용으로 사용될만한 함수모음
 *
 */
public class Methods {
	private static Logger logger = LoggerFactory.getLogger(Methods.class);

	/**
	 * 문자열 날짜형 변환
	 * 
	 * @param s 문자
	 * @return 변환된 날짜(error->null)
	 */
	public static Date convertStringToDate(String s) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(s);
		} catch (ArithmeticException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * 날짜형 문자 변환(yyyy-MM-dd)
	 * 
	 * @param d 날짜
	 * @return 변환된 문자(error->null)
	 */
	public static String convertDateToString(Date d) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(d);
		} catch (ArithmeticException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * 지정된 포멧으로 날짜형 문자 변환
	 * 
	 * @param d      날짜
	 * @param format 지정포멧
	 * @return 변환된 문자(error->null)
	 */
	public static String convertDateToString(Date d, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(d);
		} catch (ArithmeticException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * padChar로 size길이 만큼 좌측 문자열 채우는 함수
	 * 
	 * @param str     : 원본 문자열
	 * @param size    : 채울 크기
	 * @param padChar : 채울 문자
	 * @return left padding 된 문자열 ex) leftPad('code', 3', '0') → 000code
	 */
	public static String leftPad(String str, int size, char padChar) {
		return StringUtils.leftPad(str, size, padChar);
	}

	/**
	 * padChar로 size길이 만큼 우측 문자열 채우는 함수
	 * 
	 * @param str     : 원본 문자열
	 * @param size    : 채울 크기
	 * @param padChar : 채울 문자
	 * @return ex) rightPad('code', 3', '0') → code000
	 */

	public static String rightPad(String str, int size, char padChar) {
		return StringUtils.rightPad(str, size, padChar);

	}

	/**
	 * int형 배열 arr에 value가 있는 인덱를 리턴하는 함수
	 * 
	 * @param arr   : 배열
	 * @param value : 찾을 값
	 * @return ex) getArrayIndex([1,3,4], 3) → 1 getArrayIndex([1,3,4], 5) → -1
	 */
	public static int getArrayIndex(int[] arr, int value) {
		int returnVal = -1;
		if (arr.length == 0) {
			return returnVal;
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value) {
				returnVal = i;
				break;
			}
		}
		return returnVal;
	}

	/**
	 * is number?
	 * 
	 * @param string
	 * @return ex) isInteger("2014") → true isInteger("dd") → -false
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (ArithmeticException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Json String 변환
	 * 
	 * @param value
	 * @return
	 */
	public static String convertToJsonString(Object value) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(value);
		} catch (ArithmeticException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		}
		return null;
	}
}

package com.she.security.util;

import java.io.Serializable;

/**
 * DateUtil.timeStampToDate에서 paramter로 DateFormat을 넘김.
 * 
 * @author bearsv5
 *
 */
public enum DateFormat implements Serializable {

	yyyyMMdd("yyyyMMdd"),

	yyyy_MM_dd("yyyy-MM-dd"),

	yyyy_MM_dd_hh_mm("yyyy-MM-dd HH:mm"),

	yyyyMMddhhmm("yyyyMMddHHmm"),

	yyyy_MM_dd_hh_mm_ss("yyyy-MM-dd HH:mm:ss"),

	yyyyMMddhhmmss("yyyyMMddHHmmss");

	String dateFormat;

	private DateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public static DateFormat fromCode(String dateFormat) {
		for (DateFormat status : DateFormat.values()) {
			if (status.getDateFormat().equals(dateFormat)) {
				return status;
			}
		}
		try {
			throw new UnsupportedOperationException("The code " + dateFormat + " is not supported!");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;

	}
}

package com.luvina.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Common {
	
	/**
	 * getOffsetPaging
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	public static int getOffsetPaging(int currentPage, int limit) {
		int offset = (currentPage - 1) * limit;
		if (currentPage == 0) {
			offset = 0;
		}
		return offset;
	}
	
	/**
	 * getLimit
	 * @return
	 */
	public static int getLimit() {
		return Constant.LIMIT;
	}
	
	/**
	 *
	 * @return
	 */
	public static int getRange() {
		return Constant.RANGE_OF_PAGE;
	}
	
	/**
	 *
	 * @param totalUser
	 * @param limit
	 * @return
	 */
	public static int getTotalPage(int totalUser, int limit) {
		return (int) Math.ceil((double) totalUser / (double) limit);
	}
	
	/**
	 *
	 * @param totalUser
	 * @param currentPage
	 * @return
	 */
	public static List<Integer> getListPaging(int totalUser, int currentPage) {
		List<Integer> listPage = new ArrayList<>();
		int limit = getLimit();
		int totalPage = getTotalPage(totalUser, limit);
		int range = getRange();
		int start = currentPage - (range / 2);
		int end = currentPage + (range / 2);
		if (start < 1) {
			end = end + (1 - start);
			start = 1;
		}
		if (end > totalPage) {
			start = start - (end - totalPage);
			end = totalPage;
		}
		if (start < 1) {
			start = 1;
		}
		for (int i = start; i < end + 1; i++) {
			listPage.add(i);
		}
		return listPage;
	}
	
	/**
	 * escapeInjection
	 * @param str
	 * @return
	 */
	public static String escapeInjection(String str) {
		
		String tem = "";
		if (str != null) {
			tem = str.trim().replace("'", "\'");
			tem = tem.replace("%", "\\%");
			
			tem = tem.replace("_", "\\_");
		}
		
		return tem;
	}
	
	/**
	 * encodePassword
	 * @param password
	 * @return
	 */
	public static String encodePassword(String password) {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			byte byteData[] = messageDigest.digest();
			
			for (int i = 0; i < byteData.length; i++) {
				stringBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Exception ocscur: " + e.getMessage());
		}
		return stringBuffer.toString();
	}
	
	/**
	 * Anti SQL Injection for order
	 * handleSortType
	 * @param typeSort
	 * @return
	 */
	public static String handleSortType(String typeSort) {
		if (typeSort.equals("ASC")) {
			typeSort = "ASC";
		} else {
			typeSort = "DESC";
		}
		return typeSort;
	}
	
	/**
	 *
	 * @param str
	 * @return
	 */
	public static String handleCSVFile(String str) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\"" + str);
		stringBuilder.append("\"");
		return stringBuilder.toString();
	}
	
	public static String convertDateToString(java.sql.Date dateSql) {
		java.util.Date utilDate = convertFromSQLDateToJAVADate(dateSql);
		DateFormat df = new SimpleDateFormat(Constant.FOMAT_DATE);
		return df.format(utilDate);
	}
	
	public static java.util.Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate) {
		java.util.Date javaDate = null;
		if (sqlDate != null) {
			javaDate = new Date(sqlDate.getTime());
		}
		return javaDate;
	}
	
	public static long getMiniSecondRandom() {
		return new Date().getTime();
	}
	
	public static boolean checkNullAndEmpty(String stringBeforeCheck) {
		if (stringBeforeCheck != null && !stringBeforeCheck.isEmpty()) {
			return true;
		}
		return false;
	}
	public static boolean checkNumberFormatNumberInsurance(String numberInsurance) {
		Pattern pattern = Pattern.compile(Constant.REGEX_FORMAT_NUMBER_INSURANCE);
		if (!(pattern.matcher(numberInsurance).matches())) {
			return true;
		}
		return false;

	}
}

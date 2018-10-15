package com.luvina.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Common {
	
	/**
	 * get offset of paging
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
	 * getLimit get limit in Constant file
	 * @return
	 */
	public static int getLimit() {
		return Constant.LIMIT;
	}
	
	/**
	 * get range of page
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
	 * calculator List paging by total record and current page
	 * @param totalRecord
	 * @param currentPage
	 * @return
	 */
	public static List<Integer> getListPaging(int totalRecord, int currentPage) {
		List<Integer> listPage = new ArrayList<>();
		int limit = getLimit();
		int totalPage = getTotalPage(totalRecord, limit);
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
	 * escape Injection
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
	 * encode password MD5
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
	 * handle String CSV file
	 * @param str
	 * @return
	 */
	public static String handleCSVFile(String str) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\"" + str);
		stringBuilder.append("\"");
		return stringBuilder.toString();
	}

    /**
     * convert date to string
     * @param dateSql
     * @return
     */
	public static String convertDateToString(java.sql.Date dateSql) {
		java.util.Date utilDate = convertFromSQLDateToJAVADate(dateSql);
		DateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
		return df.format(utilDate);
	}

    /**
     * convert date SQL to JAVA date
     * @param sqlDate
     * @return
     */
	public static java.util.Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate) {
		java.util.Date javaDate = null;
		if (sqlDate != null) {
			javaDate = new Date(sqlDate.getTime());
		}
		return javaDate;
	}

    /**
     * random mini second
     * @return
     */
	public static long getMiniSecondRandom() {
		return new Date().getTime();
	}

    /**
     * check null or empty
     * @param stringBeforeCheck
     * @return true when string null
     */
	public static boolean isNullOrEmpty(String stringBeforeCheck) {
		if (stringBeforeCheck == null || stringBeforeCheck.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

    /**
     * check right format number insurance
     * @param insuranceNumber
     * @return
     */
	public static boolean isRightFormatInsuranceNumber(String insuranceNumber) {
		if (isNullOrEmpty(insuranceNumber) == false) {
			Pattern pattern = Pattern.compile(Constant.REGEX_FORMAT_NUMBER_INSURANCE);
			if ((pattern.matcher(insuranceNumber).matches())) {
				return true;
			}
		}
		return false;
		
	}

    /**
     * check right format date
     * @param date
     * @return
     */
	public static boolean isRightFormatDate(String date) {
		
		Pattern pattern = Pattern.compile(Constant.REGEX_FORMAT_DATE);
		if ((pattern.matcher(date).matches())) {
			return true;
		}
		return false;
	}

    /**
     * check date if exists
     * @param date
     * @return
     */
	public static boolean isDateExists(String date) {
		DateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
		df.setLenient(false);
		try {
			df.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

    /**
     * check fomat email
     * @param email
     * @return
     */
	public static boolean checkFormatEmail(String email) {
		Pattern pattern = Pattern.compile(Constant.REGEX_FORMAT_EMAIL);
		if (!(pattern.matcher(email).matches())) {
			return true;
		}
		return false;
	}

    /**
     * check format phone XX-XXXX-XXXX, XXX-XXX-XXXX, XXX-XXXX-XXX, XXXX-XX-XXXX
     * @param phone telephone
     * @return true when match XX-XXXX-XXXX, XXX-XXX-XXXX, XXX-XXXX-XXX, XXXX-XX-XXXX
     */
	public static boolean isFormatPhone(String phone) {
		Pattern pattern = Pattern.compile(Constant.REGEX_FORMAT_PHONE);
		if ((pattern.matcher(phone).matches())) {
			return true;
		}
		return false;
	}

    /**
     * check is param date2 great than param date1
     * @param date1
     * @param date2
     * @return true when param date2 great than param date1
     */
	public static boolean isParamDate2GreatThanParamDate1(String date1, String date2) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date start = new Date();
		Date end = new Date();
		try {
			start = dateFormat.parse(date1);
			end = dateFormat.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return end.after(start);
	}
	
	/**
	 * get today and format to DDMMYYYY
	 * @return String today format DDMMYYYY
	 */
	public static String getTodayDDMMYYYY() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = new Date();
		return dateFormat.format(date);
	}

    /**
     * convert string to date SQL
     * @param dateString string date
     * @return date SQL
     */
	public static java.sql.Date convertStringToDateSQL(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = null;
		try {
			date = formatter.parse(dateString);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new java.sql.Date(date.getTime());
	}

    /**
     * convert and format string replace vn character to latinh character
     * @param s
     * @return string after format
     */
	public static String decomposeString(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		String[] aUp = {
			"A",
			"Â",
			"Ă",
			"Á",
			"Ấ",
			"Ắ",
			"À",
			"Ầ",
			"Ằ",
			"Ả",
			"Ẩ",
			"Ẳ",
			"Ã",
			"Ẫ",
			"Ẵ",
			"Ạ",
			"Ậ",
			"Ặ"
		};
		String[] iUp = {
			"I",
			"Í",
			"Ì",
			"Ỉ",
			"Ĩ",
			"Ị"
		};
		String[] uUp = {
			"U",
			"Ư",
			"Ú",
			"Ứ",
			"Ù",
			"Ừ",
			"Ũ",
			"Ữ",
			"Ủ",
			"Ử",
			"Ụ",
			"Ự"
		};
		String[] eUp = {
			"E",
			"Ê",
			"É",
			"Ế",
			"È",
			"Ề",
			"Ẽ",
			"Ễ",
			"Ẻ",
			"Ể",
			"Ẹ",
			"Ệ"
		};
		String[] oUp = {
			"O",
			"Ô",
			"Ơ",
			"Ó",
			"Ố",
			"Ớ",
			"Ò",
			"Ồ",
			"Ờ",
			"Õ",
			"Ỗ",
			"Ỡ",
			"Ỏ",
			"Ổ",
			"Ở",
			"Ọ",
			"Ộ",
			"Ợ"
		};
		
		String[] dUp = {
			"D",
			"Đ"
		};
		String[] dLow = {
			"d",
			"đ"
		};
		
		String[] aLow = convertUpCaseToLowCase(aUp);
		
		String[] uLow = convertUpCaseToLowCase(uUp);
		
		String[] iLow = convertUpCaseToLowCase(iUp);
		
		String[] eLow = convertUpCaseToLowCase(eUp);
		
		String[] oLow = convertUpCaseToLowCase(oUp);
		
		String result[] = s.split("");
		
		replaceString(result, aUp, aLow, "a");
		replaceString(result, iUp, iLow, "i");
		replaceString(result, uUp, uLow, "u");
		replaceString(result, eUp, eLow, "e");
		replaceString(result, oUp, oLow, "o");
		replaceString(result, dUp, dLow, "d");
		
		for (String string : result) {
			stringBuilder.append(string);
		}
		return stringBuilder.toString();
	}

    /**
     * call methods handle string
     * @param s
     * @return String after handle
     */
	public static String handleString(String s) {
		s = decomposeString(s);
		s = capitalizeString(s);
		s = formatFullName(s);
		return s;
	}

    /**
     * format full name
     * @param s
     * @return string after format
     */
	public static String formatFullName(String s) {
		s = s.replaceAll("[^a-zA-Z ]", "");
		s = s.replaceAll(" +", " ");
		return s;
	}

    /**
     * capitalize string
     * @param s
     * @return string after capitalize
     */
	public static String capitalizeString(String s) {
		char[] chars = s.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
				found = false;
			}
		}
		return String.valueOf(chars);
	}

    /**
     * convert up case to low case
     * @param up
     * @return String after convert
     */
	public static String[] convertUpCaseToLowCase(String[] up) {
		String[] low = new String[up.length];
		for (int i = 0; i < up.length; i++) {
			String s1 = up[i];
			
			low[i] = s1.toLowerCase();
		}
		return low;
	}

    /**
     * replace string
     * @param result
     * @param up
     * @param low
     * @param flag
     */
	public static void replaceString(String[] result, String[] up, String[] low, String flag) {
		for (int j = 0; j < result.length; j++) {
			for (int i = 0; i < up.length; i++) {
				if (result[j].equals(up[i])) {
					result[j] = flag;
				}
			}
			for (int i = 0; i < low.length; i++) {
				if (result[j].equals(low[i])) {
					result[j] = flag;
				}
				
			}
		}
	}
}

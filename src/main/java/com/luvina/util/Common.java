package com.luvina.util;

import com.luvina.entities.TblCompany;
import com.luvina.form.SearchForm;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class Common {
	
	/**
	 * get offset of paging
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	public int getOffsetPaging(int currentPage, int limit) {
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
	public int getLimit() {
		return Constant.LIMIT;
	}
	
	/**
	 * get range of page
	 * @return
	 */
	public int getRange() {
		return Constant.RANGE_OF_PAGE;
	}
	
	/**
	 *
	 * @param totalUser
	 * @param limit
	 * @return
	 */
	public int getTotalPage(int totalUser, int limit) {
		return (int) Math.ceil((double) totalUser / (double) limit);
	}
	
	/**
	 * calculator List paging by total record and current page
	 * @param totalRecord
	 * @param currentPage
	 * @return
	 */
	public List<Integer> getListPaging(int totalRecord, int currentPage) {
		List<Integer> listPage = new ArrayList<>();
		Common common = new Common();
		int limit = common.getLimit();
		int totalPage = common.getTotalPage(totalRecord, limit);
		int range = common.getRange();
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
     * get End Range
     * @param listPaging
     * @return
     */
    public int getEndRange(List<Integer> listPaging) {
        if (listPaging.size() != 0) {
            return listPaging.get(listPaging.size() - 1);
        }
        return 0;
    }

    /**
     * get Search Form Id
     * @param requestParam
     * @return
     */
    public String getSearchFormId(@RequestParam Map<String, String> requestParam) {
        if (requestParam.get("searchFormId") != null) {
            return requestParam.get("searchFormId");
        }
        return Long.toString(new Date().getTime());
    }

	
	/**
	 * escape Injection
	 * @param str
	 * @return
	 */
	public String escapeInjection(String str) {
		
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
	public String encodePassword(String password) throws NoSuchAlgorithmException {
		StringBuffer stringBuffer = new StringBuffer();
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes());
		byte byteData[] = messageDigest.digest();
		for (int i = 0; i < byteData.length; i++) {
			stringBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}
	
	/**
	 * Anti SQL Injection for order
	 * handleSortType
	 * @param typeSort
	 * @return
	 */
	public String handleSortType(String typeSort) {
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
	public String handleCSVFile(String str) {
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
	public String convertDateToString(java.sql.Date dateSql) {
		java.util.Date utilDate = convertFromSQLDateToJAVADate(dateSql);
		DateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
		return df.format(utilDate);
	}
	
	/**
	 * convert date SQL to JAVA date
	 * @param sqlDate
	 * @return
	 */
	public java.util.Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate) {
		java.util.Date javaDate = null;
		if (sqlDate != null) {
			javaDate = new Date(sqlDate.getTime());
		}
		return javaDate;
	}
	
	/**
	 * check null or empty
	 * @param stringBeforeCheck
	 * @return true when string null
	 */
	public boolean isNullOrEmpty(String stringBeforeCheck) {
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
	public boolean isRightFormatInsuranceNumber(String insuranceNumber) {
		Common common = new Common();
		if (common.isNullOrEmpty(insuranceNumber) == false) {
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
	public boolean isRightFormatDate(String date) {
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
	public boolean isDateExists(String date) {
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
	 * is fomat email
	 * @param email
	 * @return
	 */
	public boolean isFormatEmail(String email) {
		Pattern pattern = Pattern.compile(Constant.REGEX_FORMAT_EMAIL);
		if (pattern.matcher(email).matches()) {
			return true;
		}
		return false;
	}
	
	/**
	 * check format phone XX-XXXX-XXXX, XXX-XXX-XXXX, XXX-XXXX-XXX, XXXX-XX-XXXX
	 * @param phone telephone
	 * @return true when match XX-XXXX-XXXX, XXX-XXX-XXXX, XXX-XXXX-XXX, XXXX-XX-XXXX
	 */
	public boolean isFormatPhone(String phone) {
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
	public boolean isParamDate2GreatThanParamDate1(String date1, String date2) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date start;
		Date end;
		try {
			start = dateFormat.parse(date1);
			end = dateFormat.parse(date2);
		} catch (ParseException e) {
			return false;
		}
		return end.after(start);
	}
	
	/**
	 * get today and format to DDMMYYYY
	 * @return String today format DDMMYYYY
	 */
	public String getTodayDDMMYYYY() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/**
	 * convert string to date SQL
	 * @param dateString string date
	 * @return date SQL
	 */
	public java.sql.Date convertStringToDateSQL(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date date;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			return new java.sql.Date(3019, 10, 17);
		}
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * convert and format string replace vn character to latinh character
	 * @param s
	 * @return string after format
	 */
	public String decomposeString(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
	}
	
	/**
	 * call methods handle string
	 * @param s
	 * @return String after handle
	 */
	public String handleString(String s) {
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
	public String formatFullName(String s) {
		s = s.replaceAll("[^a-zA-Z ]", "");
		s = s.replaceAll(" +", " ");
		return s;
	}
	
	/**
	 * capitalize string
	 * @param s
	 * @return string after capitalize
	 */
	public String capitalizeString(String s) {
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
	public String[] convertUpCaseToLowCase(String[] up) {
		String[] low = new String[up.length];
		for (int i = 0; i < up.length; i++) {
			String s1 = up[i];
			low[i] = s1.toLowerCase();
		}
		return low;
	}

    /**
     * get Param From Form
     * @param param
     * @return
     */
	public String getParamFromFormOrRequest(String param, boolean numberFlag) {
        if (isNullOrEmpty(param) == false) {
            return param;
        }
        if (numberFlag) {
            return "0";
        } else {
            return "";
        }
    }

    /**
     * get Company Internal Id
     * @param searchForm
     * @param tblCompanyList
     * @return
     */
    public Integer getCompanyInternalId(SearchForm searchForm, List<TblCompany> tblCompanyList) {
        Integer companyInternalId = searchForm.getSearchByCompanyInternalId();
        if (companyInternalId == null) {
            companyInternalId = tblCompanyList.get(0).getCompanyInternalId();
        }
        return companyInternalId;
    }

}

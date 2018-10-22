package com.luvina.util;

import com.luvina.entities.TblCompany;
import com.luvina.form.SearchForm;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class CommonTest {
	
	@InjectMocks
	private Common sut;
	
	
	/**
	 * Test get offset paging.
	 * input
	 *  currentPage = 1
	 *  limit = 5
	 * output
	 *  offset = 0
	 */
	@Test
	public void testGetOffsetPaging1() {
		//set up
		int currentPage = 1;
		int limit = 5;
		int expected = 0;
		
		// exercise
		int actual = sut.getOffsetPaging(currentPage, limit);
		
		// verify
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * Test get offset paging.
	 * input
	 *  currentPage = 0
	 *  limit = 5
	 * output
	 *  offset = 0
	 */
	@Test
	public void testGetOffsetPaging2() {
		//set up
		int currentPage = 0;
		int limit = 5;
		int offset = 0;
		
		// exercise
		int actual = sut.getOffsetPaging(currentPage, limit);
		
		// verify
		Assert.assertEquals(actual, offset);
	}
	
	/**
	 * Test get limit get from file constant
	 * input
	 *  limit = 5
	 * output
	 *  limit = 5
	 */
	@Test
	public void testGetLimit() {
		//set up
		int limit = 5;
		
		// exercise
		int actual = sut.getLimit();
		
		// verify
		Assert.assertEquals(actual, limit);
	}
	
	/**
	 * Test get range from file constant
	 * input
	 *  range = 5
	 * output
	 *  range = 5
	 */
	@Test
	public void testGetRange() {
		//set up
		int range = 5;
		
		// verify
		Assert.assertEquals(sut.getRange(), range);
	}
	
	/**
	 * Test get total page rounded
	 * input
	 *  totalUser = 9
	 *  limit = 5
	 * output
	 *  totalPage = Math.ceil(9/5) = 2
	 *
	 */
	@Test
	public void testGetTotalPage() {
		//set up
		int totalUser = 9;
		int limit = 5;
		int expected = 2;
		
		// exercise
		int actual = sut.getTotalPage(totalUser, limit);
		
		// verify
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * Test get list paging from total page and current page
	 * input
	 *  totalRecord = 10
	 *  currentPage = 1
	 * output
	 */
	@Test
	public void testGetListPaging1() {
		//set up
		int totalRecord = 10;
		int currentPage = 1;
		List<Integer> expected = Arrays.asList(1, 2);
		
		// exercise
		List<Integer> actual = sut.getListPaging(totalRecord, currentPage);
		
		// verify
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * Test get list paging from total page and current page
	 * input
	 *  totalRecord = 49
	 *  currentPage = 1
	 * output
	 */
	@Test
	public void testGetListPaging2() {
		//set up
		int totalRecord = 49;
		int currentPage = 1;
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
		
		// exercise
		List<Integer> actual = sut.getListPaging(totalRecord, currentPage);
		
		// verify
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * Test get list paging from total page and current page
	 * input
	 *  totalRecord = 49
	 *  currentPage = 5
	 * output
	 */
	@Test
	public void testGetListPaging3() {
		//set up
		int totalRecord = 49;
		int currentPage = 5;
		List<Integer> expected = Arrays.asList(3, 4, 5, 6, 7);
		
		// exercise
		List<Integer> actual = sut.getListPaging(totalRecord, currentPage);
		
		// verify
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * Test get list paging from total page and current page
	 * input
	 *  totalRecord = 50
	 *  currentPage = 50
	 * output
	 * lis paging 6, 7, 8, 9, 10
	 */
	@Test
	public void testGetListPaging4() {
		//set up
		int totalRecord = 50;
		int currentPage = 50;
		List<Integer> expected = Arrays.asList(6, 7, 8, 9, 10);
		
		// exercise
		List<Integer> actual = sut.getListPaging(totalRecord, currentPage);
		
		// verify
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * test Get End Range
	 * input
	 *  listPaging = 1,2
	 *  endRangeExpected = 2
	 * output
	 *  actual end range = 2
	 */
	@Test
	public void testGetEndRange1() {
		//set up
		List<Integer> listPaging = Arrays.asList(1, 2);
		int endRangeExpected = 2;
		
		//exercise
		int actual = sut.getEndRange(listPaging);
		
		// verify
		Assert.assertEquals(endRangeExpected, actual);
	}

    /**
     * test Get End Range
     * input
     *  listPaging = new ArrayList<>();
     *  endRangeExpected = 2
     * output
     *  actual end range = 0
     */
	@Test
	public void testGetEndRange2() {
		//set up
		List<Integer> listPaging = new ArrayList<>();
		int endRangeExpected = 0;
		
		//exercise
		int endRangeActual = sut.getEndRange(listPaging);
		
		// verify
		Assert.assertEquals(endRangeExpected, endRangeActual);
	}
	
	/**
	 * Test Get Search Form Id
     * input
     *  searchFormId = 1426564878
     * output
     * searchFormIdExpected = 1426564878
	 */
	@Test
	public void testGetSearchFormId1() {
		// set up
		String searchFormIdExpected = "1426564878";
		Map<String, String> requestParam = new HashMap<>();
		requestParam.put("searchFormId", "1426564878");
		
		// exercise
		String searchFormIdActual = sut.getSearchFormId(requestParam);
		
		// verify
		Assert.assertEquals(searchFormIdExpected, searchFormIdActual);
	}

    /**
     * Test Get Search Form Id
     * input
     *  searchFormId = null
     * output
     * searchFormIdExpected = random time Long
     */
	@Test
	public void testGetSearchFormId2() {
		// set up
		Map<String, String> requestParam = new HashMap<>();
		
		// exercise
		String searchFormIdActual = sut.getSearchFormId(requestParam);
		
		// verify
		Assert.assertEquals(Long.toString(new Date().getTime()), searchFormIdActual);
	}
	
	/**
	 * Test escape injection
     * input
     *  Data % ,
     * output
     *  Data none %
	 */
	@Test
	public void testEscapeInjection1() {
		//set up
		String stringBefore = "% ";
		String stringAfter = "\\%";

		// exercise
        String actual = sut.escapeInjection(stringBefore);
		
		// verify
		Assert.assertEquals(actual, stringAfter);
	}

    /**
     * Test escape injection
     * input
     *  Data _ ,
     * output
     *  Data none _
     */
	@Test
	public void testEscapeInjection2() {
		//set up
		String stringBefore = "_ ";
		String stringAfter = "\\_";

        // exercise
        String actutal = sut.escapeInjection(stringBefore);

		// verify
		Assert.assertEquals(actutal, stringAfter);
	}

    /**
     * Test escape injection
     * input
     *  Data ' ,
     * output
     *  Data none '
     */
	@Test
	public void testEscapeInjection3() {
		//set up
		String stringBefore = "' ";
		String stringAfter = "\'";

        // exercise
        String actutal = sut.escapeInjection(stringBefore);

		// verify
		Assert.assertEquals(actutal, stringAfter);
	}

    /**
     * Test encode password
     * input
     *  passWord = 123
     * output
     *  encode passWord = 202cb962ac59075b964b07152d234b70
     * @throws NoSuchAlgorithmException
     */
	@Test
	public void testEncodePassword() throws NoSuchAlgorithmException {
		//set up
		String passWord = "123";
		String encodeMD5PassWord = "202cb962ac59075b964b07152d234b70";

        String actual = sut.encodePassword(passWord);

		// verify
		Assert.assertEquals(actual, encodeMD5PassWord);
	}
	
	/**
	 * Test handle sort type
     * input
     *  sortType = ASC
     * output
     *  sortType = ASC
	 */
	@Test
	public void testHandleSortType1() {
		//set up
		String sortTypeBefore = "ASC";
		String sortTypeAfter = "ASC";

		// exercise
        String actual = sut.handleSortType(sortTypeBefore);
		
		// verify
		Assert.assertEquals(actual, sortTypeAfter);
	}

    /**
     * Test handle sort type
     * input
     *  sortType = DESC
     * output
     *  sortType = DESC
     */
	@Test
	public void testHandleSortType2() {
		//set up
		String sortTypeBefore = "DESC";
		String sortTypeAfter = "DESC";

        // exercise
        String actual = sut.handleSortType(sortTypeBefore);
		
		// verify
		Assert.assertEquals(actual, sortTypeAfter);
	}
	
	/**
	 * Test handle CSV file keep comma when export CSV file
     * input
     *  stringBefore = 106, Hoang Quoc Viet
     * output
     *  stringAfter = 106, Hoang Quoc Viet
	 */

	@Test
	public void testHandleCSVFile() {
		//set up
		String stringBefore = "106, Hoang Quoc Viet";
		String stringAfter = "\"106, Hoang Quoc Viet\"";

		// exercise
		String actual = sut.handleCSVFile(stringBefore);

		// verify
		Assert.assertEquals(actual, stringAfter);
	}
	
	/**
	 * Test convert date to string
	 * input
	 * 	Date java.sql.Date new java.sql.Date(2018, 10, 16);
	 * output
	 * 	Date String 16/11/3918
	 */
	@Test
	public void testConvertDateToString() {
		//set up
		java.sql.Date sDate = new java.sql.Date(2018, 10, 16);
		String strDate = "16/11/3918";

		// exercise
		String actual = sut.convertDateToString(sDate);
		
		// verify
		Assert.assertEquals(actual, strDate);
	}
	
	/**
	 * Test convert from SQL date to JAVA date
	 * input
	 *  java.sql.Date sDate
	 * output
     * String java.util.Date jDate
	 */
	@Test
	public void testConvertFromSQLDateToJAVADate() {
		//set up
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		java.sql.Date sDate = new java.sql.Date(2018, 10, 16);
		java.util.Date jDate = new java.util.Date(2018, 10, 16);

        // exercise
        String actual = sut.convertDateToString(sDate);

		// verify
		Assert.assertEquals(actual, formatter.format(jDate));
	}

    /**
     * Test is null or empty
     * input
     *  string = null;
     * output
     *  boolean true
     */
	@Test
	public void testIsNullOrEmpty1() {
		//set up
		String string = null;

        // exercise
		boolean actual = sut.isNullOrEmpty(string);

		// verify
		Assert.assertEquals(actual, true);
	}

    /**
     * Test is null or empty
     * input
     *  string = ""
     * output
     *  boolean true
     */
	@Test
	public void testIsNullOrEmpty2() {
		//set up
		String string = "";

        // exercise
        boolean actual = sut.isNullOrEmpty(string);

		// verify
		Assert.assertEquals(actual, true);
	}

    /**
     * Test is null or empty
     * input
     *  string = "A"
     * output
     *  boolean false
     */
	@Test
	public void testIsNullOrEmpty3() {
		//set up
		String string = "A";

        // exercise
        boolean actual = sut.isNullOrEmpty(string);
		
		// verify
		Assert.assertEquals(actual, false);
	}
	
	/**
	 * Test is right format insurance number right format
     * input
     *  insuranceNumber = "555"
     * output
     *  boolean false not format number insurance
	 */
	@Test
	public void testIsRightFormatInsuranceNumber1() {
		//set up
		String insuranceNumber = "555";

        // exercise
		boolean actual = sut.isRightFormatInsuranceNumber(insuranceNumber);
		
		// verify
		Assert.assertEquals(actual, false);
	}

    /**
     * Test is right format insurance number right format
     * input
     *  insuranceNumber = "1236985478"
     * output
     *  boolean true is format number insurance
     */
	@Test
	public void testIsRightFormatInsuranceNumber2() {
		//set up
		String insuranceNumber = "1236985478";

        // exercise
        boolean actual = sut.isRightFormatInsuranceNumber(insuranceNumber);

		// verify
		Assert.assertEquals(actual, true);
	}

    /**
     * Test is right format insurance number right format
     * input
     *  insuranceNumber = ""
     * output
     *  boolean false not format number insurance
     */
	@Test
	public void testIsRightFormatInsuranceNumber3() {
		//set up
		String insuranceNumber = "";
		
		// verify
		Assert.assertEquals(sut.isRightFormatInsuranceNumber(insuranceNumber), false);
	}
	
	/**
	 * Test is right format date
     * input
     *  date = ""
     * output
     *  false not right format insurance number
     *
	 */
	@Test
	public void testRightFormatDate1() {
		//set up
		String date = "";

		// exercise
        boolean actual = sut.isRightFormatInsuranceNumber(date);

		// verify
		Assert.assertEquals(actual, false);
	}

    /**
     * Test is right format date
     * input
     *  date = "22222"
     * output
     *  false not right format insurance number
     *
     */
	@Test
	public void testRightFormatDate2() {
		//set up
		String date = "22222";

        // exercise
        boolean actual = sut.isRightFormatInsuranceNumber(date);
		
		// verify
		Assert.assertEquals(actual, false);
	}

    /**
     * Test is right format date
     * input
     *  date = "16/10/2018"
     * output
     *  true not right format insurance number
     *
     */
	@Test
	public void testRightFormatDate3() {
		// set up
		String date = "16/10/2018";

        // exercise
        boolean actual = sut.isRightFormatInsuranceNumber(date);
		
		// verify
		Assert.assertEquals(actual, true);
	}
	
	/**
	 * Test is date exists 1
     * input
     *  date = 31/02/2018
     * output
     *  boolean false not is date exists
	 */
	@Test
	public void testIsDateExists1() {
		// set up
		String date = "31/02/2018";

        // exercise
        boolean actual = sut.isDateExists(date);

		// verify
		Assert.assertEquals(actual, false);
	}

    /**
     * Test is date exists 1
     * input
     *  date = 26/02/2018
     * output
     *  boolean true is date exists
     */
	@Test
	public void testIsDateExists2() {
		// set up
		String date = "26/02/2018";

        // exercise
        boolean actual = sut.isDateExists(date);
		
		// verify
		Assert.assertEquals(actual, true);
	}
	
	/**
	 * Test is format email
     * input
     *  email = 26/02/2018
     * output
     *  boolean
     *  Boolean false Not email
	 */
	@Test
	public void testIsFormatEmail1() {
		// set up
		String email = "26/02/2018";

        // exercise
        boolean actual = sut.isFormatEmail(email);

		// verify
		Assert.assertEquals(actual, false);
	}

    /**
     * Test is format email
     * input
     *  email = 26/02/2018
     * output
     *  boolean
     *  Boolean true email
     */
	@Test
	public void testIsFormatEmail2() {
		// set up
		String email = "chelsea@gmail.com";

        // exercise
        boolean actual = sut.isFormatEmail(email);
		
		// verify
		Assert.assertEquals(actual, true);
	}
	
	/**
	 * Test is format phone
     * input
     *  phone = "88-8888-8888"
     * output
     *  Boolean true is format phone
	 */
	@Test
	public void testIsFormatPhone1() {
		// set up
		String phone = "88-8888-8888";

        // exercise
		boolean actual = sut.isFormatPhone(phone);

		// verify
		Assert.assertEquals(actual, true);
	}

    /**
     * Test is format phone
     * input
     *  phone = "88-88-88"
     * output
     *  Boolean false is not format phone
     */
	@Test
	public void testIsFormatPhone2() {
		// set up
		String phone = "88-88-88";

        // exercise
        boolean actual = sut.isFormatPhone(phone);

		// verify
		Assert.assertEquals(actual, false);
	}

    /**
     * Test is format phone
     * input
     *  phone = "883-882-8844"
     * output
     *  Boolean true is format phone
     */
	@Test
	public void testIsFormatPhone3() {
		// set up
		String phone = "883-882-8844";

        // exercise
        boolean actual = sut.isFormatPhone(phone);
		
		// verify
		Assert.assertEquals(actual, true);
	}
	
	/**
	 * Test is param date2 great than param date 1
     * input
     *  param1 = "10/10/2018"
     *  param2 = "10/12/2018"
     * output
     *  Boolean true param2 > param1
	 */
	@Test
	public void testIsParamDate2GreatThanParamDate1() {
		// set up
		String param1 = "10/10/2018";
		String param2 = "10/12/2018";

        // exercise
		boolean actual = sut.isParamDate2GreatThanParamDate1(param1, param2);
		
		// verify
		Assert.assertEquals(actual, true);
	}

    /**
     * Test is param date2 great than param date 1
     * input
     *  param1 = "10/10/2019"
     *  param2 = "10/12/2018"
     * output
     *  Boolean false param2 < param1
     */
	@Test
	public void testIsParamDate2GreatThanParamDate12() {
		// set up
		String param1 = "10/10/2019";
		String param2 = "10/12/2018";

        // exercise
        boolean actual = sut.isParamDate2GreatThanParamDate1(param1, param2);

		// verify
		Assert.assertEquals(actual, false);
	}

    /**
     * Test is param date2 great than param date 1
     * input
     *  param1 = "10/10/2019"
     *  param2 = "10/12/2019"
     * output
     *  Boolean false param2 < param1
     */
	@Test
	public void testIsParamDate2GreatThanParamDate13() {
		// set up
		String param1 = "10/10/2019";
		String param2 = "10/10/2019";

        // exercise
        boolean actual = sut.isParamDate2GreatThanParamDate1(param1, param2);
		
		// verify
		Assert.assertEquals(actual, false);
	}

    /**
     * Test is param date2 great than param date 1
     * input
     *  param1 = "1010/2018"
     *  param2 = "1012/2018"
     * output
     *  Boolean false param2 > param1 but not format date
     */
	@Test
	public void testIsParamDate2GreatThanParamDate4() {
		// set up
		String param1 = "1010/2018";
		String param2 = "1012/2018";

        // exercise
        boolean actual = sut.isParamDate2GreatThanParamDate1(param1, param2);
		
		// verify
		Assert.assertEquals(actual, false);
	}
	
	/**
	 * test get today DDMMYYYY
     * input
     *  today = "18/10/2018"
     * output
     *  today right
	 */
	@Test
	public void testGetTodayDDMMYYYY() {
		// set up
		String today = "18/10/2018";

		String actual = sut.getTodayDDMMYYYY();
		
		// verify
		Assert.assertEquals(actual, today);
	}
	
	/**
	 * Test Convert String To Date SQL
     * input
     *  sDate = "16/11/3918"
     * output
     *  new java.sql.Date(2018, 10, 16);
	 */
	@Test
	public void testConvertStringToDateSQL1() {
		// set up
		String sDate = "16/11/3918";
		java.sql.Date date = new java.sql.Date(2018, 10, 16);
		
		// verify
		Assert.assertEquals(sut.convertStringToDateSQL(sDate), date);
	}

    /**
     * Test Convert String To Date SQL
     * input
     *  sDate = "16/11/3918"
     * output
     *  not convert new java.sql.Date(2018, 10, 16);
     */
	@Test
	public void testConvertStringToDateSQL2() {
		// set up
		String sDate = "1010/2018";
		java.sql.Date sqlDate = new java.sql.Date(3019, 10, 17);
		
		//exercise
		java.sql.Date sqlDateEx = sut.convertStringToDateSQL(sDate);
		
		// verify
		Assert.assertEquals(sqlDateEx, sqlDate);
	}
	
	/**
	 * Test Decompose String convert vi language to english
     * input
     *  stringBefore = "tùng"
     * output
     * String "tung"
	 */
	@Test
	public void testDecomposeString1() {
		// set up
		String stringBefore = "tùng";
		String stringAfter = "tung";

        // exercise
		String actual = sut.decomposeString(stringBefore);
		
		// verify
		Assert.assertEquals(actual, stringAfter);
	}

    /**
     * Test Decompose String convert vi language to english
     * input
     *  stringBefore = "toàn"
     * output
     * String "toan"
     */
	@Test
	public void testDecomposeString2() {
		// set up
		String stringBefore = "toàn";
		String stringAfter = "toan";

        // exercise
        String actual = sut.decomposeString(stringBefore);
		
		// verify
		Assert.assertEquals(actual, stringAfter);
	}

    /**
     * Test Decompose String convert vi language to english
     * input
     *  stringBefore = "được"
     * output
     * String "duoc"
     */
	@Test
	public void testDecomposeString3() {
		// set up
		String stringBefore = "được";
		String stringAfter = "duoc";

        // exercise
        String actual = sut.decomposeString(stringBefore);
		
		// verify
		Assert.assertEquals(actual, stringAfter);
	}
	
	/**
	 * Test Handle String
     * input
     *  stringBefore = "Tr加加加ầN  2加  vi12Ệt hÙ&*@nG  "
     * output
     *  stringAfter = "Tran Viet Hung "
	 */
	@Test
	public void testHandleString() {
		// set up
		String stringBefore = "Tr加加加ầN  2加  vi12Ệt hÙ&*@nG  ";
		String stringAfter = "Tran Viet Hung ";

		// exercise
		String actual = sut.handleString(stringBefore);
		
		// verify
		Assert.assertEquals(actual, stringAfter);
		
	}

    /**
     * Test Handle String
     * input
     *  stringBefore = "KJjjdh +akdoas   +"
     * output
     *  stringAfter = "Kjjjdh Akdoas "
     */
	@Test
	public void testFormatFullName() {
		// set up
		String stringBefore = "KJjjdh +akdoas   +";
		String stringAfter = "Kjjjdh Akdoas ";

        // exercise
        String actual = sut.handleString(stringBefore);

		// verify
		Assert.assertEquals(actual, stringAfter);
	}
	
	/**
	 * test Capitalize String
	 */
	@Test
	public void testCapitalizeString() {
		// set up
		String stringBefore = "nGUYEN Van PHONG";
		String stringAfter = "Nguyen Van Phong";
		
		// verify
		Assert.assertEquals(sut.capitalizeString(stringBefore), stringAfter);
	}
	
	/**
	 * test Convert Up Case To Low Case
     * input String[]
     * aUp = {"A","Â","Ă","Á","Ấ","Ắ","À","Ầ","Ằ","Ả","Ẩ","Ẳ","Ã","Ẫ","Ẵ","Ạ","Ậ","Ặ"}
     * output
     * String[] aLow = {"a","â","ă","á","ấ","ắ","à","ầ","ằ","ả","ẩ","ẳ","ã","ẫ","ẵ","ạ","ậ","ặ"};
     * Lower case String
	 */
	@Test
	public void testConvertUpCaseToLowCase() {
		// set up
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
		String[] aLow = {
			"a",
			"â",
			"ă",
			"á",
			"ấ",
			"ắ",
			"à",
			"ầ",
			"ằ",
			"ả",
			"ẩ",
			"ẳ",
			"ã",
			"ẫ",
			"ẵ",
			"ạ",
			"ậ",
			"ặ"
		};
		
		// verify
		Assert.assertArrayEquals(sut.convertUpCaseToLowCase(aUp), aLow);
	}
	
	/**
	 * Test Get Param From Form Or Request check get data from request
     * input
     *  param = "a1"
     * output
     * String data "a1"
	 */
	@Test
	public void testGetParamFromFormOrRequest1() {
		// set up
		String param = "a1";
		
		// exercise
		String actual = sut.getParamFromFormOrRequest(param, false);
		
		// verify
		Assert.assertEquals(param, actual);
	}

    /**
     * Test Get Param From Form Or Request check get data from request
     * input
     *  param = ""
     * output
     *  String data "a1"
     */
	@Test
	public void testGetParamFromFormOrRequest2() {
		// set up
		String param = "";
		
		// exercise
		String actual = sut.getParamFromFormOrRequest(param, false);
		
		// verify
		Assert.assertEquals(param, actual);
	}

    /**
     * Test Get Param From Form Or Request check get data from request
     * input
     *  param = "0"
     * output
     * String data "a1"
     */
	@Test
	public void testGetParamFromFormOrRequest3() {
		// set up
		String param = "0";
		
		// exercise
		String actual = sut.getParamFromFormOrRequest("", true);
		
		// verify
		Assert.assertEquals(param, actual);
	}
	
	/**
	 * Test Get Company Internal Id
     * input
     *  company internal id = 1
     * output
     *  expected = 1
	 */
	@Test
	public void testGetCompanyInternalId() {
		// set up
		SearchForm searchForm = new SearchForm();
		List<TblCompany> tblCompanyList = new ArrayList<>();
		TblCompany tblCompany = new TblCompany();
		tblCompany.setCompanyInternalId(1);
		tblCompanyList.add(tblCompany);
		Integer expected = 1;
		
		// exercise
		Integer companyInternalIdActual = sut.getCompanyInternalId(searchForm, tblCompanyList);
		
		// verify
		Assert.assertEquals(expected, companyInternalIdActual);
	}
}

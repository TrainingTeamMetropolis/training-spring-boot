package com.luvina.util;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommonTest {
	
	@InjectMocks
	private Common sut;
	
	
	/**
	 * test get offset paging with data setup 1
	 */
	@Test
	public void testGetOffsetPaging1() {
		//set up
		int currentPage = 1;
		int limit = 5;
		int offset = 0;
		
		// verify
		Assert.assertEquals(sut.getOffsetPaging(currentPage, limit), offset);
	}
	
	/**
	 * test get offset paging with data setup 2
	 */
	@Test
	public void testGetOffsetPaging2() {
		//set up
		int currentPage = 0;
		int limit = 5;
		int offset = 0;
		
		// verify
		Assert.assertEquals(sut.getOffsetPaging(currentPage, limit), offset);
	}
	
	/**
	 * test get limit
	 */
	@Test
	public void testGetLimit() {
		//set up
		int limit = 5;
		
		// verify
		Assert.assertEquals(sut.getLimit(), limit);
	}
	
	/**
	 * test get range
	 */
	@Test
	public void testGetRange() {
		//set up
		int range = 5;
		
		// verify
		Assert.assertEquals(sut.getRange(), range);
	}
	
	/**
	 * test get total page
	 */
	@Test
	public void testGetTotalPage() {
		//set up
		int totalUser = 10;
		int limit = 5;
		int totalPage = 2;
		
		// verify
		Assert.assertEquals(sut.getTotalPage(totalUser, limit), totalPage);
	}
	
	/**
	 * test get list paging 1
	 */
	@Test
	public void testGetListPaging1() {
		//set up
		int totalRecord = 10;
		int currentPage = 1;
		List<Integer> listPaging = new ArrayList<>();
		listPaging.add(1);
		listPaging.add(2);
		
		// verify
		Assert.assertEquals(sut.getListPaging(totalRecord, currentPage), listPaging);
	}
	
	/**
	 * test get list paging 1
	 */
	@Test
	public void testGetListPaging2() {
		//set up
		int totalRecord = 50;
		int currentPage = 1;
		List<Integer> listPaging = new ArrayList<>();
		listPaging.add(1);
		listPaging.add(2);
		listPaging.add(3);
		listPaging.add(4);
		listPaging.add(5);
		
		// verify
		Assert.assertEquals(sut.getListPaging(totalRecord, currentPage), listPaging);
	}
	
	/**
	 * test get list paging 3
	 */
	@Test
	public void testGetListPaging3() {
		//set up
		int totalRecord = 50;
		int currentPage = 5;
		List<Integer> listPaging = new ArrayList<>();
		listPaging.add(3);
		listPaging.add(4);
		listPaging.add(5);
		listPaging.add(6);
		listPaging.add(7);
		
		// verify
		Assert.assertEquals(sut.getListPaging(totalRecord, currentPage), listPaging);
	}
	
	/**
	 * test get list paging 4
	 */
	@Test
	public void testGetListPaging4() {
		//set up
		int totalRecord = 50;
		int currentPage = 50;
		List<Integer> listPaging = new ArrayList<>();
		listPaging.add(6);
		listPaging.add(7);
		listPaging.add(8);
		listPaging.add(9);
		listPaging.add(10);
		
		// verify
		Assert.assertEquals(sut.getListPaging(totalRecord, currentPage), listPaging);
	}
	
	/**
	 * test escape injection 1
	 */
	@Test
	public void testEscapeInjection1() {
		//set up
		String stringBefore = "% ";
		String stringAfter = "\\%";
		
		// verify
		Assert.assertEquals(sut.escapeInjection(stringBefore), stringAfter);
	}
	
	/**
	 * test escape injection 2
	 */
	@Test
	public void testEscapeInjection2() {
		//set up
		String stringBefore = "_ ";
		String stringAfter = "\\_";
		
		// verify
		Assert.assertEquals(sut.escapeInjection(stringBefore), stringAfter);
	}
	
	/**
	 * test escape injection 3
	 */
	@Test
	public void testEscapeInjection3() {
		//set up
		String stringBefore = "' ";
		String stringAfter = "\'";
		
		// verify
		Assert.assertEquals(sut.escapeInjection(stringBefore), stringAfter);
	}
	
	/**
	 * test encode password
	 */
	@Test
	public void testEncodePassword() throws NoSuchAlgorithmException {
		//set up
		String passWord = "123";
		String encodeMD5PassWord = "202cb962ac59075b964b07152d234b70";
		
		// verify
		Assert.assertEquals(sut.encodePassword(passWord), encodeMD5PassWord);
	}
	
	/**
	 * test handle sort type 1
	 */
	@Test
	public void testHandleSortType1() {
		//set up
		String sortTypeBefore = "ASC";
		String sortTypeAfter = "ASC";
		
		// verify
		Assert.assertEquals(sut.handleSortType(sortTypeBefore), sortTypeAfter);
	}
	
	/**
	 * test handle sort type 2
	 */
	@Test
	public void testHandleSortType2() {
		//set up
		String sortTypeBefore = "DESC";
		String sortTypeAfter = "DESC";
		
		// verify
		Assert.assertEquals(sut.handleSortType(sortTypeBefore), sortTypeAfter);
	}
	
	/**
	 * test handle CSV file
	 */
	@Test
	public void testHandleCSVFile() {
		//set up
		String stringBefore = "106, Hoang Quoc Viet";
		String stringAfter = "\"106, Hoang Quoc Viet\"";
		
		// verify
		Assert.assertEquals(sut.handleCSVFile(stringBefore), stringAfter);
	}
	
	/**
	 * test convert date to string
	 */
	@Test
	public void testConvertDateToString() {
		//set up
		java.sql.Date sDate = new java.sql.Date(2018, 10, 16);
		String strDate = "16/11/3918";
		
		// verify
		Assert.assertEquals(sut.convertDateToString(sDate), strDate);
	}
	
	/**
	 * test convert from SQL date to JAVA date
	 */
	@Test
	public void testConvertFromSQLDateToJAVADate() {
		//set up
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		java.sql.Date sDate = new java.sql.Date(2018, 10, 16);
		java.util.Date jDate = new java.util.Date(2018, 10, 16);
		
		// verify
		Assert.assertEquals(sut.convertDateToString(sDate), formatter.format(jDate));
	}
	
	/**
	 * test is null or empty 1
	 */
	@Test
	public void testIsNullOrEmpty1() {
		//set up
		String string = null;
		
		// verify
		Assert.assertEquals(sut.isNullOrEmpty(string), true);
	}
	
	/**
	 * test is null or empty 2
	 */
	@Test
	public void testIsNullOrEmpty2() {
		//set up
		String string = "";
		
		// verify
		Assert.assertEquals(sut.isNullOrEmpty(string), true);
	}
	
	/**
	 * test is null or empty 3
	 */
	@Test
	public void testIsNullOrEmpty3() {
		//set up
		String string = "A";
		
		// verify
		Assert.assertEquals(sut.isNullOrEmpty(string), false);
	}
	
	/**
	 * test is right format insurance number 1
	 */
	@Test
	public void testIsRightFormatInsuranceNumber1() {
		//set up
		String insuranceNumber = "555";
		
		// verify
		Assert.assertEquals(sut.isRightFormatInsuranceNumber(insuranceNumber), false);
	}
	
	/**
	 * test is right format insurance number 2
	 */
	@Test
	public void testIsRightFormatInsuranceNumber2() {
		//set up
		String insuranceNumber = "1236985478";
		
		// verify
		Assert.assertEquals(sut.isRightFormatInsuranceNumber(insuranceNumber), true);
	}
	
	/**
	 * test is right format insurance number 3
	 */
	@Test
	public void testIsRightFormatInsuranceNumber3() {
		//set up
		String insuranceNumber = "";
		
		// verify
		Assert.assertEquals(sut.isRightFormatInsuranceNumber(insuranceNumber), false);
	}
	
	/**
	 * test is right format date 1
	 */
	@Test
	public void testRightFormatDate1() {
		//set up
		String date = "";
		
		// verify
		Assert.assertEquals(sut.isRightFormatInsuranceNumber(date), false);
	}
	
	/**
	 * test is right format date 2
	 */
	@Test
	public void testRightFormatDate2() {
		//set up
		String date = "22222";
		
		// verify
		Assert.assertEquals(sut.isRightFormatDate(date), false);
	}
	
	/**
	 * test is right format date 3
	 */
	@Test
	public void testRightFormatDate3() {
		// set up
		String date = "16/10/2018";
		
		// verify
		Assert.assertEquals(sut.isRightFormatDate(date), true);
	}
	
	/**
	 * test is date exists 1
	 */
	@Test
	public void testIsDateExists1() {
		// set up
		String date = "31/02/2018";
		
		// verify
		Assert.assertEquals(sut.isDateExists(date), false);
	}
	
	/**
	 * test is date exists 2
	 */
	@Test
	public void testIsDateExists2() {
		// set up
		String date = "26/02/2018";
		
		// verify
		Assert.assertEquals(sut.isDateExists(date), true);
	}
	
	/**
	 * test is format email 1
	 */
	@Test
	public void testIsFormatEmail1() {
		// set up
		String email = "26/02/2018";
		
		// verify
		Assert.assertEquals(sut.isFormatEmail(email), false);
	}
	
	/**
	 * test is format email 2
	 */
	@Test
	public void testIsFormatEmail2() {
		// set up
		String email = "chelsea@gmail.com";
		
		// verify
		Assert.assertEquals(sut.isFormatEmail(email), true);
	}
	
	/**
	 * test is format phone 1
	 */
	@Test
	public void testIsFormatPhone1() {
		// set up
		String phone = "88-8888-8888";
		
		// verify
		Assert.assertEquals(sut.isFormatPhone(phone), true);
	}
	
	/**
	 * test is format phone 2
	 */
	@Test
	public void testIsFormatPhone2() {
		// set up
		String phone = "88-88-88";
		
		// verify
		Assert.assertEquals(sut.isFormatPhone(phone), false);
	}
	
	/**
	 * test is format phone 3
	 */
	@Test
	public void testIsFormatPhone3() {
		// set up
		String phone = "883-882-8844";
		
		// verify
		Assert.assertEquals(sut.isFormatPhone(phone), true);
	}
	
	/**
	 * test is param date2 great than param date 1
	 */
	@Test
	public void testIsParamDate2GreatThanParamDate1() {
		// set up
		String param1 = "10/10/2018";
		String param2 = "10/12/2018";
		
		// verify
		Assert.assertEquals(sut.isParamDate2GreatThanParamDate1(param1, param2), true);
	}

	/**
	 * test is param date2 great than param date 2
	 */
	@Test
	public void testIsParamDate2GreatThanParamDate2() {
		// set up
		String param1 = "10/10/2019";
		String param2 = "10/12/2018";
		
		// verify
		Assert.assertEquals(sut.isParamDate2GreatThanParamDate1(param1, param2), false);
	}
	
	/**
	 * test is param date2 great than param date 3
	 */
	@Test
	public void testIsParamDate2GreatThanParamDate3() {
		// set up
		String param1 = "10/10/2019";
		String param2 = "10/10/2019";
		
		// verify
		Assert.assertEquals(sut.isParamDate2GreatThanParamDate1(param1, param2), false);
	}

	/**
	 * test is param date2 great than param date 4
	 */
	@Test
	public void testIsParamDate2GreatThanParamDate4() {
		// set up
		String param1 = "1010/2018";
		String param2 = "1012/2018";

		// verify
		Assert.assertEquals(sut.isParamDate2GreatThanParamDate1(param1, param2), false);
	}

	/**
	 * test get today DDMMYYYY
	 */
	@Test
	public void testGetTodayDDMMYYYY() {
		// set up
		String today = "17/10/2018";
		
		// verify
		Assert.assertEquals(sut.getTodayDDMMYYYY(), today);
	}
	
	/**
	 * test Convert String To Date SQL
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
	 * test Convert String To Date SQL
	 */
	@Test
	public void testConvertStringToDateSQL2() {
		// set up
		String sDate = "1010/2018";
		java.sql.Date sqlDate = new java.sql.Date(3019, 10, 17);
		
		//exercise
		java.sql.Date sqlDateEx = sut.convertStringToDateSQL(sDate);
		System.out.println(sqlDateEx + "|" + sqlDate);
		
		// verify
		Assert.assertEquals(sqlDateEx, sqlDate);
	}
	
	/**
	 * test Decompose String 1
	 */
	@Test
	public void testDecomposeString1() {
		// set up
		String stringBefore = "tùng";
		String stringAfter = "tung";
		
		// verify
		Assert.assertEquals(sut.decomposeString(stringBefore), stringAfter);
	}
	
	/**
	 * test Decompose String 2
	 */
	@Test
	public void testDecomposeString2() {
		// set up
		String stringBefore = "toàn";
		String stringAfter = "toan";
		
		// verify
		Assert.assertEquals(sut.decomposeString(stringBefore), stringAfter);
	}
	
	/**
	 * test Decompose String 3
	 */
	@Test
	public void testDecomposeString3() {
		// set up
		String stringBefore = "được";
		String stringAfter = "duoc";
		
		// verify
		Assert.assertEquals(sut.decomposeString(stringBefore), stringAfter);
	}
	
	/**
	 * test Handle String
	 */
	@Test
	public void testHandleString() {
		// set up
		String stringBefore = "Tr加加加ầN  2加  vi12Ệt hÙ&*@nG  ";
		String stringAfter = "Tran Viet Hung ";
		
		// verify
		Assert.assertEquals(sut.handleString(stringBefore), stringAfter);
		
	}
	
	/**
	 * test Format Full Name
	 */
	@Test
	public void testFormatFullName() {
		// set up
		String stringBefore = "KJjjdh +akdoas   +";
		String stringAfter = "Kjjjdh Akdoas ";
		
		// verify
		Assert.assertEquals(sut.handleString(stringBefore), stringAfter);
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
}

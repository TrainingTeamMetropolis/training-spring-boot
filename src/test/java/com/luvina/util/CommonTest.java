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
     */
	@Test
	public void testGetListPaging4() {
		//set up
		int totalRecord = 50;
		int currentPage = 50;
		List<Integer> expected = Arrays.asList(6, 7, 8,9, 10);

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
     *  actual
	 */
	@Test
	public void testGetEndRange1() {
		//set up
		List<Integer> listPaging = Arrays.asList(1,2);
		int endRangeExpected = 2;

		//exercise
		int actual =  sut.getEndRange(listPaging);

		// verify
		Assert.assertEquals(endRangeExpected, actual);
	}
	/**
	 * test Get End Range 2
	 */
	@Test
	public void testGetEndRange2() {
		//set up
		List<Integer> listPaging = new ArrayList<>();
		int endRangeExpected = 0;

		//exercise
		int endRangeActual =  sut.getEndRange(listPaging);

		// verify
		Assert.assertEquals(endRangeExpected, endRangeActual);
	}

	/**
	 * test Get Search Form Id 1
	 */
	@Test
	public void testGetSearchFormId1() {
		// set up
		String searchFormIdExpected = "1426564878";
		Map<String, String> requestParam = new HashMap<>();
		requestParam.put("searchFormId", "1426564878");

		// exercise
		String searchFormIdActual =  sut.getSearchFormId(requestParam);

		// verify
		Assert.assertEquals(searchFormIdExpected, searchFormIdActual);
	}
	/**
	 * test Get Search Form Id 2
	 */
	@Test
	public void testGetSearchFormId2() {
		// set up
		Map<String, String> requestParam = new HashMap<>();

		// exercise
		String searchFormIdActual =  sut.getSearchFormId(requestParam);

		// verify
		Assert.assertEquals(Long.toString(new Date().getTime()), searchFormIdActual);
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
		String today = "18/10/2018";
		
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

	/**
	 * test Get Param From Form Or Request 1
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
	 * test Get Param From Form Or Request 2
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
	 * test Get Param From Form Or Request 2
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
     * test Get Company Internal Id
     */
	@Test
    public void testGetCompanyInternalId() {
        // set up
        SearchForm searchForm = new SearchForm();
        List<TblCompany> tblCompanyList =  new ArrayList<>();
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

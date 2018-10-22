package com.luvina.service.impl;

import com.luvina.entities.TblUser;
import com.luvina.form.RegisterForm;
import com.luvina.form.SearchForm;
import com.luvina.repository.TblUserRepository;
import com.luvina.repository.customize.TblUserRepositoryCustom;
import com.luvina.util.Common;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TblUserServiceImplTest {
	
	@InjectMocks
	TblUserServiceImpl sut;
	
	@Mock
	TblUserRepository tblUserRepository;
	
	@Mock
	TblUserRepositoryCustom tblUserRepositoryCustom;

	
	@Mock
	Common common;

	@Mock
	HttpSession httpSession;
	
	
	/**
	 * Test Find Total Records when first search
	 * input
	 * 	offset = 1
	 * 	limit = 5
	 * 	typeSort = ""
	 * 	companyInternalId = 1
	 * 	userFullName = ""
	 * 	insuranceNumber = ""
	 * 	placeOfRegister = ""
	 * output
	 *  totalRecord = 100
	 */
	@Test
	public void testFindTotalRecords() {
		// set up
		int offset = 1;
		int limit = 5;
		String typeSort = "";
		int companyInternalId = 1;
		String userFullName = "";
		String insuranceNumber = "";
		String placeOfRegister = "";
		Integer totalRecord = new Integer(100);
		when(tblUserRepositoryCustom.findTotalRecords(anyInt(), anyInt(), anyString(), anyInt(), anyString(),
				anyString(), anyString())).thenReturn(totalRecord);
		
		// exercise
		Integer actual = sut.findTotalRecords(offset, limit, typeSort, companyInternalId, userFullName, insuranceNumber,
				placeOfRegister);
		
		// verify
		assertEquals(actual, totalRecord);
	}
	
	/**
	 * Test Find By User Internal Id with any int value of User Internal Id
     * input
     *  any int value of User Internal Id
     * output
     *  TblUser
	 */
	@Test
	public void testFindByUserInternalId() {
		// set up
		TblUser tblUser = new TblUser();
		when(tblUserRepository.findByUserInternalId(anyInt())).thenReturn(tblUser);
		
		// exercise
		TblUser tblUserActual = sut.findByUserInternalId(anyInt());
		
		// verify
		assertEquals(tblUserActual, tblUser);
	}

    /**
     * TestInsertInformationInsuranceOfUser
     * input
     *  data RegisterForm from request
     *  radio button company check new
     *  date birth input 10/10/2018 ...
     * output
     *  insert data success from request data to data base and throw e Exception when error
     */
	@Test
	public void testInsertInformationInsuranceOfUser() {
		// set up
		RegisterForm registerForm = new RegisterForm();
		registerForm.setRadioCompany("new");
		registerForm.setDateBirth("10/10/2018");
		// exercise
		try {
			sut.insertInformationInsuranceOfUser(registerForm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test Save Tbl Company save data TblCompany from request data
     * input
     *  save request data (RegisterForm)
     * output
     *  insert data success
	 */
	@Test
	public void testSaveTblCompany() {
		// set up
		RegisterForm registerForm = new RegisterForm();
		
		// exercise
		sut.saveTblCompany(registerForm);
		
		// verify
	}
	
	/**
	 * Test Find And Search List Data when load data MH02, search and show data
     * input
     *  request data page = 1
     *  limit = 5
     * output
     *  Map data has all data
     *  searchFormId = null (difference other tab search)
     *  tblUserList = new ArrayList<TblUser>()
     *  totalPage = 0
     *  typeSort = null
     *  listPaging = new ArrayList<Integer>()
     *  endRange = 0
     *  currentPage = 1
	 */
	@Test
	public void testFindAndSearchListData1() {
		// set up
		SearchForm searchForm = new SearchForm();
        searchForm.setSearchByCompanyInternalId(0);
        searchForm.setSearchByInsuranceNumber(null);
        searchForm.setSearchByUserFullName(null);
        searchForm.setSearchByPlaceOfRegister(null);
        searchForm.setTypeSort(null);
        searchForm.setPage(1);
		Map expected = new HashMap();
        expected.put("searchFormId", null);
        expected.put("tblUserList", new ArrayList<TblUser>());
        expected.put("totalPage", 0);
        expected.put("typeSort", null);
        expected.put("listPaging", new ArrayList<Integer>());
        expected.put("endRange", 0);
        expected.put("currentPage", 1);

		when(common.getLimit()).thenReturn(5);
		when(common.getParamFromFormOrRequest("1", true)).thenReturn("1");
		Map<String, String> requestParamMap = new HashMap<>();
		requestParamMap.put("page", "1");

		// exercise
		Map actual = sut.findAndSearchListData(new SearchForm(), requestParamMap, httpSession);

        // verify
        Assert.assertEquals(actual,expected);
	}
    /**
     * Test Find And Search List Data when load data MH02, search and show data
     * input
     *  request data page = 1
     *  limit = 5
     * output
     *  Map data has all data
     *  searchFormId = 123456789
     *  tblUserList = new ArrayList<TblUser>()
     *  totalPage = 0
     *  typeSort = null
     *  listPaging = new ArrayList<Integer>()
     *  endRange = 0
     *  currentPage = 1
     */
	@Test
	public void testFindAndSearchListData2() {
		// set up
		SearchForm searchForm = new SearchForm();
        searchForm.setSearchByCompanyInternalId(0);
        searchForm.setSearchByInsuranceNumber(null);
        searchForm.setSearchByUserFullName(null);
        searchForm.setSearchByPlaceOfRegister(null);
        searchForm.setTypeSort(null);
        searchForm.setPage(1);
		Map map = new HashMap();
        map.put("searchFormId", null);
        map.put("tblUserList", new ArrayList<TblUser>());
        map.put("totalPage", 0);
        map.put("typeSort", null);
        map.put("listPaging", new ArrayList<Integer>());
        map.put("endRange", 0);
        map.put("currentPage", 1);

		when(common.getLimit()).thenReturn(5);
		when(common.getParamFromFormOrRequest("1", true)).thenReturn("1");
		when(httpSession.getAttribute(anyString())).thenReturn(searchForm);
		Map<String, String> requestParamMap = new HashMap<>();
		requestParamMap.put("page", "1");

        httpSession.setAttribute("searchFormId", "123456789");

		// exercise
		Map actual = sut.findAndSearchListData(new SearchForm(), requestParamMap, httpSession);

        // verify
        Assert.assertEquals(actual,map);
	}
    /**
     * Test Find And Search List Data when load data MH02, search and show data
     * input
     *  request data page = 0
     *  request data Search By Company Internal Id = 0
     *  limit = 5
     * output
     *  Map data has all data
     *  searchFormId = 123456789
     *  tblUserList = new ArrayList<TblUser>()
     *  totalPage = 0
     *  typeSort = null
     *  listPaging = new ArrayList<Integer>()
     *  endRange = 0
     *  currentPage = 1
     */
	@Test
	public void testFindAndSearchListData3() {
		// set up
		SearchForm searchForm = new SearchForm();
        searchForm.setSearchByCompanyInternalId(0);
        searchForm.setSearchByInsuranceNumber(null);
        searchForm.setSearchByUserFullName(null);
        searchForm.setSearchByPlaceOfRegister(null);
        searchForm.setTypeSort(null);
        searchForm.setPage(0);
		Map map = new HashMap();
        map.put("searchFormId", null);
        map.put("tblUserList", new ArrayList<TblUser>());
        map.put("totalPage", 0);
        map.put("typeSort", null);
        map.put("listPaging", new ArrayList<Integer>());
        map.put("endRange", 0);
        map.put("currentPage", 1);

		when(common.getLimit()).thenReturn(5);
		when(common.getParamFromFormOrRequest("1", true)).thenReturn("1");
		when(httpSession.getAttribute(anyString())).thenReturn(searchForm);
		Map<String, String> requestParamMap = new HashMap<>();
		requestParamMap.put("page", "1");

        httpSession.setAttribute("searchFormId", "123456789");

		// exercise
		Map actual = sut.findAndSearchListData(new SearchForm(), requestParamMap, httpSession);

        // verify
        Assert.assertEquals(actual,map);
	}
}

package com.luvina.service.impl;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblUser;
import com.luvina.form.RegisterForm;
import com.luvina.form.SearchForm;
import com.luvina.repository.TblCompanyRepository;
import com.luvina.repository.TblInsuranceRepository;
import com.luvina.repository.TblUserRepository;
import com.luvina.repository.customize.TblUserRepositoryCustom;
import com.luvina.service.TblCompanyService;
import com.luvina.util.Common;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
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
	TblCompanyRepository tblCompanyRepository;
	
	@Mock
	TblInsuranceRepository tblInsuranceRepository;
	
	@Mock
	TblCompanyService tblCompanyService;
	
	@Mock
	Common common;
	
	@Mock
	RegisterForm registerForm;
	
	@Mock
	HttpSession httpSession;
	
	
	/**
	 * test Find Total Records
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
	 * test Find By User Internal Id
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
	 * test Save Tbl Company
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
	 * test Find And Search List Data
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
		Map<String, String> requestParamMap = new HashMap<>();
		requestParamMap.put("page", "1");

		// exercise
		Map actual = sut.findAndSearchListData(new SearchForm(), requestParamMap, httpSession);

        // verify
        Assert.assertEquals(actual,map);
	}
	/**
	 * test Find And Search List Data
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
	 * test Find And Search List Data 3
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

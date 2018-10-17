package com.luvina.service.impl;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblUser;
import com.luvina.form.RegisterForm;
import com.luvina.repository.TblCompanyRepository;
import com.luvina.repository.TblInsuranceRepository;
import com.luvina.repository.TblUserRepository;
import com.luvina.repository.customize.TblUserRepositoryCustom;
import com.luvina.service.TblCompanyService;
import com.luvina.util.Common;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.NoSuchAlgorithmException;

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
}

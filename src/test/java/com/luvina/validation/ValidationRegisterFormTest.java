package com.luvina.validation;

import com.luvina.entities.TblUser;
import com.luvina.form.RegisterForm;
import com.luvina.service.TblCompanyService;
import com.luvina.service.TblInsuranceService;
import com.luvina.service.TblUserService;
import com.luvina.util.Common;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationRegisterFormTest {
	
	@InjectMocks
	ValidationRegisterForm sut;
	
	@Mock
	TblInsuranceService tblInsuranceService;
	
	@Mock
	TblUserService tblUserService;
	
	@Mock
	TblCompanyService tblCompanyService;
	
	@Mock
	Common common;
	
	@Mock
	Errors errors;
	
	@Mock
	Object target;
	
	
	/**
	 * test validate insurance number 1
	 */
	@Test
	public void testValidateInsuranceNumber1() {
		// set up
		String insuranceNumber = "";
		int insuranceInternalId = 0;
		when(common.isNullOrEmpty(insuranceNumber)).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateInsuranceNumber(errors, insuranceInternalId, insuranceNumber);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceNumber");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.insurance_number");
	}
	
	/**
	 * test validate insurance number 2
	 */
	@Test
	public void testValidateInsuranceNumber2() {
		// set up
		String insuranceNumber = "1569854789";
		int insuranceInternalId = 0;
		when(common.isNullOrEmpty(insuranceNumber)).thenReturn(false);
		when(common.isRightFormatInsuranceNumber(insuranceNumber)).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateInsuranceNumber(errors, insuranceInternalId, insuranceNumber);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceNumber");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("format.insurance_number");
	}
	
	/**
	 * test validate insurance number 3
	 */
	@Test
	public void testValidateInsuranceNumber3() {
		// set up
		String insuranceNumber = "5674746789";
		int insuranceInternalId = 0;
		when(common.isNullOrEmpty(insuranceNumber)).thenReturn(false);
		when(common.isRightFormatInsuranceNumber(insuranceNumber)).thenReturn(true);
		when(tblInsuranceService.isExistsTblInsurance(insuranceInternalId, insuranceNumber)).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateInsuranceNumber(errors, insuranceInternalId, insuranceNumber);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceNumber");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("exists.insurance_number");
	}
	
	/**
	 * test Validate User Full Name 1
	 */
	@Test
	public void testValidateUserFullName1() {
		// set up
		String userFullName = "nguyenvanphong";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateUserFullName(errors, userFullName);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("userFullName");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.user_full_name");
	}
	
	/**
	 * test Validate User Full Name 2
	 */
	@Test
	public void testValidateUserFullName2() {
		// set up
		String userFullName =
				"nguyenvanphongnguyenvanphongnguyenvanphongnguyenvanphongnguyenvanphong " +
						"nguyenvanphongnguyenvanphongnguyenvanphongnguyenvanphongnguyenvanphongnguyenvanphongnguyenvanphong";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateUserFullName(errors, userFullName);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("userFullName");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("length_max.user_full_name");
	}
	
	/**
	 * test Validate UserName
	 */
	@Test
	public void testValidateUserName1() {
		// set up
		String userName = "nguyenphong";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateUserName(errors, userName);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("user_name");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.user_name");
	}
	
	/**
	 * test Validate UserName
	 */
	@Test
	public void testValidateUserName2() {
		// set up
		String userName = "nguyenvanphongnguyenvanphongnguyenvanphongnguyenvanphongnguyenvanphong";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateUserName(errors, userName);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("user_name");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("length_max.user_name");
	}
	
	/**
	 * test Validate Pass Word 1
	 */
	@Test
	public void testValidatePassWord1() {
		// set up
		String passWord = "password";
		String confirmPassWord = "password";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validatePassWord(errors, passWord, confirmPassWord);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("password");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.password");
	}
	
	/**
	 * test Validate Pass Word 2
	 */
	@Test
	public void testValidatePassWord2() {
		// set up
		String passWord = "passwordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpassword";
		String confirmPassWord = "passwordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpassword";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validatePassWord(errors, passWord, confirmPassWord);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("password");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("length_max.password");
	}
	
	/**
	 * test Validate Pass Word 3
	 */
	@Test
	public void testValidatePassWord3() {
		// set up
		String passWord = "passwordpasswor";
		String confirmPassWord = "passwordpasswor3";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validatePassWord(errors, passWord, confirmPassWord);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("password");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("compare_password.password_confirm_password");
	}
	
	/**
	 * test Validate User Sex Division
	 */
	@Test
	public void testValidateUserSexDivision() {
		// set up
		String userSexDivision = "03";
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		// exercise
		sut.validateUserSexDivision(errors, userSexDivision);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("user_sex_division");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("format.user_sex_division");
	}
	
	/**
	 * test Validate Birth Date
	 */
	@Test
	public void testValidateBirthDate1() {
		// set up
		String birthDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateBirthDate(errors, birthDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("dateBirth");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.date_birth");
	}
	
	/**
	 * test Validate Birth Date 2
	 */
	@Test
	public void testValidateBirthDate2() {
		// set up
		String birthDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		when(common.isRightFormatDate(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateBirthDate(errors, birthDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("dateBirth");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("format.date_birth");
	}
	
	/**
	 * test Validate Birth Date 3
	 */
	@Test
	public void testValidateBirthDate3() {
		// set up
		String birthDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		when(common.isRightFormatDate(anyString())).thenReturn(true);
		when(common.isDateExists(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateBirthDate(errors, birthDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("dateBirth");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("exists_date.date_birth");
	}
	
	/**
	 * test Validate Birth Date 4
	 */
	@Test
	public void testValidateBirthDate4() {
		// set up
		String birthDate = "10/10/2019";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		when(common.isRightFormatDate(anyString())).thenReturn(true);
		when(common.isDateExists(anyString())).thenReturn(true);
		when(common.isParamDate2GreatThanParamDate1(common
			.getTodayDDMMYYYY(), birthDate)).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateBirthDate(errors, birthDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("dateBirth");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("compare_date._birth_date_and_today");
	}
	
	/**
	 * test alidate Company Name 1
	 */
	@Test
	public void testValidateCompanyName1() {
		// set up
		String companyName = "fpt";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateCompanyName(errors, companyName);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("companyName");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.company_name");
	}
	
	/**
	 * test alidate Company Name 2
	 */
	@Test
	public void testValidateCompanyName2() {
		// set up
		String companyName = "fptfptfptfptfptfptfptfptfptfptfptf ptfptfptfptfptfptfptfptfptfptfptfpt " +
				"ptfptfptfptfptfptfptfptfptfptfptfpt ptfptfptfptfptfptfptfptfptfptfptfpt";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateCompanyName(errors, companyName);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("companyName");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("length_max.company_name");
	}
	
	/**
	 * test alidate Company Name 3
	 */
	@Test
	public void testValidateCompanyName3() {
		// set up
		String companyName = "FPT";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		when(tblCompanyService.isExistsCompanyName(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateCompanyName(errors, companyName);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("companyName");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("exists_company_name.company_name");
	}
	
	/**
	 * test Validate Address Company
	 */
	@Test
	public void testValidateAddressCompany1() {
		// set up
		String addressCompany = "106 hoang quoc viet";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateAddressCompany(errors, addressCompany);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("address");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.address");
	}
	
	/**
	 * test Validate Address Company
	 */
	@Test
	public void testValidateAddressCompany2() {
		// set up
		String addressCompany =
				"106 hoang quoc viet 106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet106 hoang quoc viet";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateAddressCompany(errors, addressCompany);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("address");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("length_max.address");
	}
	
	/**
	 * test Validate Address Company 1
	 */
	@Test
	public void testValidateEmailCompany1() {
		// set up
		String emailCompany = "fpt@fpt.com";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateEmailCompany(errors, emailCompany);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("email");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.email");
	}
	
	/**
	 * test Validate Address Company 2
	 */
	@Test
	public void testValidateEmailCompany2() {
		// set up
		String emailCompany =
				"fptfptfptfptfptfptfptfptfptffptfptfptfptfptfptfptfptfptfptfptfptfptfptfptfptfptfptfptfptfptfptfptptfptfptfptfptfptfptfptfpt@fpt.com";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateEmailCompany(errors, emailCompany);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("email");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("length_max.email");
	}
	
	/**
	 * test Validate Address Company 3
	 */
	@Test
	public void testValidateEmailCompany3() {
		// set up
		String emailCompany = "45!@fpt.com";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		when(common.isFormatEmail(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateEmailCompany(errors, emailCompany);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("email");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("format.email");
	}
	
	/**
	 * test Validate Phone Company 1
	 */
	@Test
	public void testValidatePhoneCompany1() {
		// set up
		String phoneCompany = "11-2252-5695";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validatePhoneCompany(errors, phoneCompany);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("telephone");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.telephone");
	}
	
	/**
	 * test Validate Phone Company 2
	 */
	@Test
	public void testValidatePhoneCompany2() {
		// set up
		String phoneCompany = "11-2252-5695";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		when(common.isFormatPhone(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validatePhoneCompany(errors, phoneCompany);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("telephone");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("format.telephone");
	}
	
	/**
	 * test Validate Place Of Register
	 */
	@Test
	public void testValidatePlaceOfRegister1() {
		// set up
		String placeOfRegister = "hung yen";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validatePlaceOfRegister(errors, placeOfRegister);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("placeOfRegister");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.place_of_register");
	}
	
	/**
	 * test Validate Place Of Register 2
	 */
	@Test
	public void testValidatePlaceOfRegister2() {
		// set up
		String placeOfRegister =
				"hung yenhung yenhung yenhung yenhung yenhung yenhung yenhung yenhung yenhung yenhung yenhung yenhung yenhung yenhung yen";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validatePlaceOfRegister(errors, placeOfRegister);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("placeOfRegister");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("length_max.place_of_register");
	}
	
	/**
	 * test Validate Start Date
	 */
	@Test
	public void testValidateStartDate1() {
		// set up
		String startDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateStartDate(errors, startDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceStartDate");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.insurance_start_date");
	}
	
	/**
	 * test Validate Start Date 2
	 */
	@Test
	public void testValidateStartDate2() {
		// set up
		String startDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		when(common.isRightFormatDate(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateStartDate(errors, startDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceStartDate");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("format.insurance_start_date");
	}
	
	/**
	 * test Validate Start Date 3
	 */
	@Test
	public void testValidateStartDate3() {
		// set up
		String startDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		when(common.isRightFormatDate(anyString())).thenReturn(true);
		when(common.isDateExists(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateStartDate(errors, startDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceStartDate");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("exists_date.insurance_start_date");
	}
	
	/**
	 * test Validate End Date
	 */
	@Test
	public void testValidateEndDate1() {
		// set up
		String startDate = "10/10/2018";
		String endDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(true);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateEndDate(errors, startDate, endDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceEndDate");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("not_empty.insurance_end_date");
	}
	
	/**
	 * test Validate End Date
	 */
	@Test
	public void testValidateEndDate2() {
		// set up
		String startDate = "10/10/2018";
		String endDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		when(common.isRightFormatDate(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateEndDate(errors, startDate, endDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceEndDate");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("format.insurance_end_date");
	}
	
	/**
	 * test Validate End Date
	 */
	@Test
	public void testValidateEndDate3() {
		// set up
		String startDate = "10/10/2018";
		String endDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		when(common.isRightFormatDate(anyString())).thenReturn(true);
		when(common.isDateExists(anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateEndDate(errors, startDate, endDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceEndDate");
		assertThat(errorCodeCaptor.getValue()).isEqualTo("exists_date.insurance_end_date");
	}
	
	/**
	 * test Validate End Date
	 */
	@Test
	public void testValidateEndDate4() {
		// set up
		String startDate = "10/10/2018";
		String endDate = "10/10/2018";
		when(common.isNullOrEmpty(anyString())).thenReturn(false);
		when(common.isRightFormatDate(anyString())).thenReturn(true);
		when(common.isDateExists(anyString())).thenReturn(true);
		when(common.isParamDate2GreatThanParamDate1(anyString(), anyString())).thenReturn(false);
		
		ArgumentCaptor<String> fieldCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> errorCodeCaptor = ArgumentCaptor.forClass(String.class);
		
		// exercise
		sut.validateEndDate(errors, startDate, endDate);
		
		// verify
		verify(errors, times(1)).rejectValue(fieldCaptor.capture(), errorCodeCaptor.capture());
		assertThat(fieldCaptor.getValue()).isEqualTo("insuranceEndDate");
		assertThat(errorCodeCaptor.getValue())
			.isEqualTo("compare_date.insurance_start_date_insurance_and_end_date_insurance");
	}

	/**
	 * test Get Insurance Internal Id
	 */
	@Test
	public void testGetInsuranceInternalId1() {
		// set up
		RegisterForm registerForm = new RegisterForm();
		registerForm.setUserInternalId(2);
		TblUser tblUser = new TblUser();
		when(tblUserService.findByUserInternalId(registerForm.getUserInternalId())).thenReturn(tblUser);

		// exercise
		int actual = sut.getInsuranceInternalId(registerForm);

		// verify
		Assert.assertEquals(0,actual);
	}
	/**
	 * test Get Insurance Internal Id
	 */
	@Test
	public void testGetInsuranceInternalId2() {
		// set up
		RegisterForm registerForm = new RegisterForm();
		registerForm.setUserInternalId(0);
		TblUser tblUser = new TblUser();
		when(tblUserService.findByUserInternalId(registerForm.getUserInternalId())).thenReturn(tblUser);

		// exercise
		int actual = sut.getInsuranceInternalId(registerForm);

		// verify
		Assert.assertEquals(0,actual);
	}
}

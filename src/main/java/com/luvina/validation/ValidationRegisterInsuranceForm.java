package com.luvina.validation;

import com.luvina.entities.TblUser;
import com.luvina.form.RegisterInsuranceForm;
import com.luvina.service.ITblInsuranceService;
import com.luvina.service.ITblUserService;
import com.luvina.util.Common;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidationRegisterInsuranceForm implements Validator {
	
	@Autowired
	private ITblInsuranceService iTblInsuranceService;
	
	@Autowired
	private ITblUserService iTblUserService;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterInsuranceForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		RegisterInsuranceForm registerInsuranceForm = (RegisterInsuranceForm) target;
		int insuranceInternalId = 0;
		if (registerInsuranceForm.getUserInternalId() != 0) {
			Integer userInternalId = registerInsuranceForm.getUserInternalId();
			TblUser tblUser = iTblUserService.findByUserInternalId(userInternalId);
			insuranceInternalId = tblUser.getInsuranceInternalId();
		}
		int userInternalId = registerInsuranceForm.getUserInternalId();
		String insuranceNumber = registerInsuranceForm.getInsuranceNumber();
		String userFullName = registerInsuranceForm.getUserFullName();
		String userName = registerInsuranceForm.getUserName();
		String passWord = registerInsuranceForm.getPassWord();
		String confirmPassWord = registerInsuranceForm.getConfirmPassWord();
		String userSexDivision = registerInsuranceForm.getUserSexDivision();
		String dateBirth = registerInsuranceForm.getDateBirth();
		int companyInternalId = registerInsuranceForm.getCompanyInternalId();
		String companyName = registerInsuranceForm.getCompanyName();
		String address = registerInsuranceForm.getAddress();
		String email = registerInsuranceForm.getEmail();
		String telephone = registerInsuranceForm.getTelephone();
		String placeOfRegister = registerInsuranceForm.getPlaceOfRegister();
		String insuranceStartDate = registerInsuranceForm.getInsuranceEndDate();
		String insuranceEndDate = registerInsuranceForm.getInsuranceEndDate();
		String checkRadioCompany = registerInsuranceForm.getCheck();

		validateInsuranceNumber(errors, insuranceInternalId, insuranceNumber);
		validateUserFullName(errors, userFullName);
		validateUserName(errors, userName);
		validatePassWord(errors, passWord, confirmPassWord);
		validateUserSexDivision(errors, userSexDivision);
		validateBirthDate(errors, dateBirth);
		if (!checkRadioCompany.equals("old")) {
			validateCompanyName(errors, companyName);
			validateAddressCompany(errors, address);
			validateEmailCompany(errors, email);
			validatePhoneCompany(errors, telephone);
		}
		validatePlaceOfRegister(errors, placeOfRegister);
		validateStartDate(errors, insuranceStartDate);
		validateEndDate(errors, insuranceStartDate, insuranceEndDate);
	}
	
	private void validateInsuranceNumber(Errors errors, int insuranceInternalId, String insuranceNumber) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(insuranceNumber) == true) {
			hasErrorFlag = true;
			errors.rejectValue("insuranceNumber", "NotEmpty.RegisterInsuranceForm.insuranceNumber");
		}
		if (hasErrorFlag == false && Common.isRightFormatInsuranceNumber(insuranceNumber) == false) {
			errors.rejectValue("insuranceNumber", "Format.RegisterInsuranceForm.insuranceNumber");
		}
	}
	
	private void validateUserFullName(Errors errors, String userFullName) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(userFullName)) {
			hasErrorFlag = true;
			errors.rejectValue("userFullName", "NotEmpty.RegisterInsuranceForm.userFullName");
		}
		if (hasErrorFlag == false && userFullName.trim().length() > 50) {
			errors.rejectValue("userFullName", "LengthMax.RegisterInsuranceForm.userFullName");
		}
	}
	
	private void validateUserName(Errors errors, String userName) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(userName)) {
			hasErrorFlag = true;
			errors.rejectValue("userFullName", "NotEmpty.RegisterInsuranceForm.userName");
		}
		if (hasErrorFlag == false && userName.trim().length() > 16) {
			errors.rejectValue("userFullName", "LengthMax.RegisterInsuranceForm.userName");
		}
	}
	
	private void validatePassWord(Errors errors, String passWord, String confirmPassWord) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(passWord)) {
			hasErrorFlag = true;
			errors.rejectValue("passWord", "NotEmpty.RegisterInsuranceForm.passWord");
		}
		if (hasErrorFlag == false && passWord.trim().length() > 16) {
			hasErrorFlag = true;
			errors.rejectValue("passWord", "LengthMax.RegisterInsuranceForm.passWord");
		}
		if (hasErrorFlag == false && !confirmPassWord.equals(passWord)) {
			errors.rejectValue("passWord", "ComparePassWord.RegisterInsuranceForm.passWord_confirmPassWord");
		}
	}
	
	private void validateUserSexDivision(Errors errors, String userSexDivision) {
		if ((userSexDivision.equals("0") == false) && (userSexDivision.equals("1") == false)) {
			errors.rejectValue("userSexDivision", "Format.RegisterInsuranceForm.userSexDivision");
		}
	}
	
	private void validateBirthDate(Errors errors, String dateBirth) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(dateBirth)) {
			hasErrorFlag = true;
			errors.rejectValue("dateBirth", "NotEmpty.RegisterInsuranceForm.dateBirth");
		}
		if (hasErrorFlag == false && Common.isRightFormatDate(dateBirth) == false) {
			hasErrorFlag = true;
			errors.rejectValue("dateBirth", "Format.RegisterInsuranceForm.dateBirth");
		}
		if (hasErrorFlag = false && Common.isDateExists(dateBirth) == false) {
			errors.rejectValue("dateBirth", "ExistsDate.RegisterInsuranceForm.dateBirth");
		}
	}
	
	private void validateCompanyName(Errors errors, String companyName) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(companyName)) {
			hasErrorFlag = true;
			errors.rejectValue("companyName", "NotEmpty.RegisterInsuranceForm.companyName");
		}
		if (hasErrorFlag == false && companyName.trim().length() > 50) {
			errors.rejectValue("companyName", "LengthMax.RegisterInsuranceForm.companyName");
		}
	}
	
	private void validateAddressCompany(Errors errors, String address) {
		if (Common.isNullOrEmpty(address)) {
			errors.rejectValue("address", "NotEmpty.RegisterInsuranceForm.address");
		} else if (address.trim().length() > 100) {
			errors.rejectValue("address", "LengthMax.RegisterInsuranceForm.address");
		}
	}
	
	private void validateEmailCompany(Errors errors, String email) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(email)) {
			hasErrorFlag = true;
			errors.rejectValue("email", "NotEmpty.RegisterInsuranceForm.email");
		}
		if (email.trim().length() > 50) {
			hasErrorFlag = true;
			errors.rejectValue("email", "LengthMax.RegisterInsuranceForm.email");
		}
		if (hasErrorFlag == false && Common.checkFormatEmail(email)) {
			errors.rejectValue("email", "Format.RegisterInsuranceForm.email");
		}
	}
	
	private void validatePhoneCompany(Errors errors, String telephone) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(telephone)) {
			hasErrorFlag = true;
			errors.rejectValue("telephone", "NotEmpty.RegisterInsuranceForm.telephone");
		}
		if (telephone.trim().length() > 15) {
			hasErrorFlag = true;
			errors.rejectValue("telephone", "LengthMax.RegisterInsuranceForm.telephone");
		}
		if (hasErrorFlag == false && Common.isFormatPhone(telephone) == false) {
			errors.rejectValue("telephone", "Format.RegisterInsuranceForm.telephone");
		}
	}
	
	private void validatePlaceOfRegister(Errors errors, String place) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(place)) {
			hasErrorFlag = true;
			errors.rejectValue("placeOfRegister", "NotEmpty.RegisterInsuranceForm.placeOfRegister");
		}
		if (hasErrorFlag == false && place.trim().length() > 50) {
			errors.rejectValue("placeOfRegister", "LengthMax.RegisterInsuranceForm.placeOfRegister");
		}
	}
	
	private void validateStartDate(Errors errors, String startDate) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(startDate)) {
			hasErrorFlag = true;
			errors.rejectValue("insuranceStartDate", "NotEmpty.RegisterInsuranceForm.insuranceStartDate");
		}
		if (hasErrorFlag == false && Common.isRightFormatDate(startDate) == false) {
            hasErrorFlag = true;
			errors.rejectValue("insuranceStartDate", "Format.RegisterInsuranceForm.insuranceStartDate");
		}
		if (hasErrorFlag == false && Common.isDateExists(startDate) == false) {
			errors.rejectValue("insuranceStartDate", "ExistsDate.RegisterInsuranceForm.insuranceStartDate");
		}
	}
	
	private void validateEndDate(Errors errors, String startDate, String endDate) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(endDate)) {
			hasErrorFlag = true;
			errors.rejectValue("insuranceEndDate", "NotEmpty.RegisterInsuranceForm.insuranceEndDate");
		}
		if (hasErrorFlag == false && Common.isRightFormatDate(endDate) == false) {
            hasErrorFlag = true;
			errors.rejectValue("insuranceEndDate", "Format.RegisterInsuranceForm.insuranceEndDate");
		}
		if (hasErrorFlag == false && Common.isDateExists(endDate) == false) {

            hasErrorFlag = true;
			errors.rejectValue("insuranceEndDate", "ExistsDate.RegisterInsuranceForm.insuranceEndDate");
		}
		if (hasErrorFlag == false && Common.isEndDateThanStartDate(startDate, endDate) == true) {
			errors.rejectValue("insuranceEndDate",
					"CompareDate.RegisterInsuranceForm.insuranceStartDate_insuranceEndDate");
		}
		
	}
}

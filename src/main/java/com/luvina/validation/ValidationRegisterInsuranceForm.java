package com.luvina.validation;

import com.luvina.entities.TblUser;
import com.luvina.form.RegisterInsuranceForm;
import com.luvina.service.ITblInsuranceService;
import com.luvina.service.ITblUserService;
import com.luvina.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidationRegisterInsuranceForm implements Validator {
	
	@Autowired
	private ITblInsuranceService iTblInsuranceService;
	
	@Autowired
	private ITblUserService iTblUserService;
	
	
	/**
	 * method Override supports validate
	 * @param clazz
	 * @return RegisterInsuranceForm.class.isAssignableFrom(clazz);
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterInsuranceForm.class.isAssignableFrom(clazz);
	}
	
	/**
	 * method Override validate method
	 * @param target target object validate
	 * @param errors all errors
	 */
	@Override
	public void validate(Object target, Errors errors) {
		RegisterInsuranceForm registerInsuranceForm = (RegisterInsuranceForm) target;
		int insuranceInternalId = 0;
		if (registerInsuranceForm.getUserInternalId() != 0) {
			Integer userInternalId = registerInsuranceForm.getUserInternalId();
			TblUser tblUser = iTblUserService.findByUserInternalId(userInternalId);
			insuranceInternalId = tblUser.getInsuranceInternalId();
		}
		String checkRadioCompany = registerInsuranceForm.getRadioCompany();
		validateInsuranceNumber(errors, insuranceInternalId, registerInsuranceForm.getInsuranceNumber());
		validateUserFullName(errors, registerInsuranceForm.getUserFullName());
		validateUserName(errors, registerInsuranceForm.getUserName());
		validatePassWord(errors, registerInsuranceForm.getPassWord(), registerInsuranceForm.getConfirmPassWord());
		validateUserSexDivision(errors, registerInsuranceForm.getRadioUserSexDivision());
		validateBirthDate(errors, registerInsuranceForm.getDateBirth());
		if (checkRadioCompany.equals("old") == false) {
			validateCompanyName(errors, registerInsuranceForm.getCompanyName());
			validateAddressCompany(errors, registerInsuranceForm.getAddress());
			validateEmailCompany(errors, registerInsuranceForm.getEmail());
			validatePhoneCompany(errors, registerInsuranceForm.getTelephone());
		}
		validatePlaceOfRegister(errors, registerInsuranceForm.getPlaceOfRegister());
		validateStartDate(errors, registerInsuranceForm.getInsuranceStartDate());
		validateEndDate(errors, registerInsuranceForm.getInsuranceStartDate(),
				registerInsuranceForm.getInsuranceEndDate());
	}
	
	/**
	 * validate insurance number
	 * @param errors all error
	 * @param insuranceInternalId
	 * @param insuranceNumber
	 */
	private void validateInsuranceNumber(Errors errors, int insuranceInternalId, String insuranceNumber) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(insuranceNumber) == true) {
			hasErrorFlag = true;
			errors.rejectValue("insuranceNumber", "not_empty.insurance_number");
		}
		if (hasErrorFlag == false && Common.isRightFormatInsuranceNumber(insuranceNumber) == false) {
			hasErrorFlag = true;
			errors.rejectValue("insuranceNumber", "format.insurance_number");
		}
		if (hasErrorFlag == false && iTblInsuranceService.isExistsTblInsurance(insuranceInternalId, insuranceNumber)) {
			errors.rejectValue("insuranceNumber", "exists.insurance_number");
		}
	}
	
	/**
	 * validate user full name
	 * @param errors all error
	 * @param userFullName
	 */
	private void validateUserFullName(Errors errors, String userFullName) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(userFullName)) {
			hasErrorFlag = true;
			errors.rejectValue("userFullName", "not_empty.user_full_name");
		}
		if (hasErrorFlag == false && userFullName.trim().length() > 50) {
			errors.rejectValue("userFullName", "length_max.user_full_name");
		}
	}
	
	/**
	 * validate user name
	 * @param errors all error
	 * @param userName
	 */
	private void validateUserName(Errors errors, String userName) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(userName)) {
			hasErrorFlag = true;
			errors.rejectValue("userFullName", "not_empty.user_name");
		}
		if (hasErrorFlag == false && userName.trim().length() > 16) {
			errors.rejectValue("userFullName", "length_max.user_name");
		}
	}
	
	/**
	 * validate password
	 * @param errors all error
	 * @param passWord
	 * @param confirmPassWord
	 */
	private void validatePassWord(Errors errors, String passWord, String confirmPassWord) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(passWord)) {
			hasErrorFlag = true;
			errors.rejectValue("passWord", "not_empty.password");
		}
		if (hasErrorFlag == false && passWord.trim().length() > 16) {
			hasErrorFlag = true;
			errors.rejectValue("passWord", "LengthMax.RegisterInsuranceForm.passWord");
		}
		if (hasErrorFlag == false && !confirmPassWord.equals(passWord)) {
			errors.rejectValue("passWord", "compare_password.password_confirm_password");
		}
	}
	
	/**
	 * validate user sex division
	 * @param errors all errors
	 * @param userSexDivision
	 */
	private void validateUserSexDivision(Errors errors, String userSexDivision) {
		if ((userSexDivision.equals("01") == false) && (userSexDivision.equals("02") == false)) {
			errors.rejectValue("userSexDivision", "format.user_sex_division");
		}
	}
	
	/**
	 * validate birth of date
	 * @param errors all errors
	 * @param dateBirth
	 */
	private void validateBirthDate(Errors errors, String dateBirth) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(dateBirth) == false && Common.isRightFormatDate(dateBirth) == false) {
			hasErrorFlag = true;
			errors.rejectValue("dateBirth", "format.date_birth");
		}
		if (Common.isNullOrEmpty(dateBirth) == false && hasErrorFlag == false
				&& Common.isDateExists(dateBirth) == false) {
			errors.rejectValue("dateBirth", "exists_date.date_birth");
		}
	}
	
	/**
	 * validate company name
	 * @param errors all errors
	 * @param companyName
	 */
	private void validateCompanyName(Errors errors, String companyName) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(companyName)) {
			hasErrorFlag = true;
			errors.rejectValue("companyName", "not_empty.company_name");
		}
		if (hasErrorFlag == false && companyName.trim().length() > 50) {
			errors.rejectValue("companyName", "length_max.company_name");
		}
	}
	
	/**
	 * validate address company
	 * @param errors all errors
	 * @param address
	 */
	private void validateAddressCompany(Errors errors, String address) {
		if (Common.isNullOrEmpty(address)) {
			errors.rejectValue("address", "not_empty.address");
		} else if (address.trim().length() > 100) {
			errors.rejectValue("address", "length_max.address");
		}
	}
	
	/**
	 * validate email company
	 * @param errors all errors
	 * @param email
	 */
	private void validateEmailCompany(Errors errors, String email) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(email)) {
			hasErrorFlag = true;
			errors.rejectValue("email", "not_empty.email");
		}
		if (email.trim().length() > 50) {
			hasErrorFlag = true;
			errors.rejectValue("email", "length_max.email");
		}
		if (hasErrorFlag == false && Common.checkFormatEmail(email)) {
			errors.rejectValue("email", "format.email");
		}
	}
	
	/**
	 * validate phone company
	 * @param errors all errors
	 * @param telephone
	 */
	private void validatePhoneCompany(Errors errors, String telephone) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(telephone)) {
			hasErrorFlag = true;
			errors.rejectValue("telephone", "not_empty.telephone");
		}
		if (telephone.trim().length() > 15) {
			hasErrorFlag = true;
			errors.rejectValue("telephone", "length_max.telephone");
		}
		if (hasErrorFlag == false && Common.isFormatPhone(telephone) == false) {
			errors.rejectValue("telephone", "format.telephone");
		}
	}
	
	/**
	 * validate place of register
	 * @param errors all errors
	 * @param place
	 */
	private void validatePlaceOfRegister(Errors errors, String place) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(place)) {
			hasErrorFlag = true;
			errors.rejectValue("placeOfRegister", "not_empty.place_of_register");
		}
		if (hasErrorFlag == false && place.trim().length() > 50) {
			errors.rejectValue("placeOfRegister", "length_max.place_of_register");
		}
	}
	
	/**
	 * validate start date
	 * @param errors all errors
	 * @param startDate
	 */
	private void validateStartDate(Errors errors, String startDate) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(startDate)) {
			hasErrorFlag = true;
			errors.rejectValue("insuranceStartDate", "not_empty.insurance_start_date");
		}
		if (hasErrorFlag == false && Common.isRightFormatDate(startDate) == false) {
			hasErrorFlag = true;
			errors.rejectValue("insuranceStartDate", "format.insurance_start_date");
		}
		if (hasErrorFlag == false && Common.isDateExists(startDate) == false) {
			errors.rejectValue("insuranceStartDate", "exists_date.insurance_start_date");
		}
	}
	
	/**
	 * validate end date
	 * @param errors all errors
	 * @param startDate
	 * @param endDate
	 */
	private void validateEndDate(Errors errors, String startDate, String endDate) {
		boolean hasErrorFlag = false;
		if (Common.isNullOrEmpty(endDate)) {
			hasErrorFlag = true;
			errors.rejectValue("insuranceEndDate", "not_empty.insurance_end_date");
		}
		if (hasErrorFlag == false && Common.isRightFormatDate(endDate) == false) {
			hasErrorFlag = true;
			errors.rejectValue("insuranceEndDate", "format.insurance_end_date");
		}
		if (hasErrorFlag == false && Common.isDateExists(endDate) == false) {
			
			hasErrorFlag = true;
			errors.rejectValue("insuranceEndDate", "exists_date.insurance_end_date");
		}
		if (hasErrorFlag == false && Common.isEndDateThanStartDate(startDate, endDate) == false) {
			errors.rejectValue("insuranceEndDate",
					"compare_date.insurance_start_date_insurance_end_date");
		}
	}
}

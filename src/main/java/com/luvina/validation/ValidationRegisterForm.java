package com.luvina.validation;

import com.luvina.entities.TblUser;
import com.luvina.form.RegisterForm;
import com.luvina.service.TblCompanyService;
import com.luvina.service.TblInsuranceService;
import com.luvina.service.TblUserService;
import com.luvina.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidationRegisterForm implements Validator {
	
	@Autowired
	private TblInsuranceService tblInsuranceService;
	
	@Autowired
	private TblUserService tblUserService;
	
	@Autowired
	private TblCompanyService tblCompanyService;
	
	@Autowired
	Common common;


	/**
	 * method Override supports validate
	 * @param clazz
	 * @return RegisterForm.class.isAssignableFrom(clazz);
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterForm.class.isAssignableFrom(clazz);
	}
	
	/**
	 * method Override validate method
	 * @param target target object validate
	 * @param errors all errors
	 */
	@Override
	public void validate(Object target, Errors errors) {
		RegisterForm registerForm = (RegisterForm) target;
		String checkRadioCompany = registerForm.getRadioCompany();
		validateInsuranceNumber(errors, getInsuranceInternalId(registerForm), registerForm.getInsuranceNumber());
		validateUserFullName(errors, registerForm.getUserFullName());
		validateUserName(errors, registerForm.getUserName());
		validatePassWord(errors, registerForm.getPassWord(), registerForm.getConfirmPassWord());
		validateUserSexDivision(errors, registerForm.getRadioUserSexDivision());
		validateBirthDate(errors, registerForm.getDateBirth());
		if (checkRadioCompany.equals("old") == false) {
			validateCompanyName(errors, registerForm.getCompanyName());
			validateAddressCompany(errors, registerForm.getAddress());
			validateEmailCompany(errors, registerForm.getEmail());
			validatePhoneCompany(errors, registerForm.getTelephone());
		}
		validatePlaceOfRegister(errors, registerForm.getPlaceOfRegister());
		validateStartDate(errors, registerForm.getInsuranceStartDate());
		validateEndDate(errors, registerForm.getInsuranceStartDate(),
				registerForm.getInsuranceEndDate());
	}

    /**
     * get insurance internal id
     * @param registerForm
     * @return
     */
	public int getInsuranceInternalId (RegisterForm registerForm) {
        if (registerForm.getUserInternalId() != 0) {
            Integer userInternalId = registerForm.getUserInternalId();
            TblUser tblUser = tblUserService.findByUserInternalId(userInternalId);
            return tblUser.getInsuranceInternalId();
        }
        return 0;
    }
	
	/**
	 * validate insurance number
	 * @param errors all error
	 * @param insuranceInternalId
	 * @param insuranceNumber
	 */
	public void validateInsuranceNumber(Errors errors, int insuranceInternalId, String insuranceNumber) {
		if (common.isNullOrEmpty(insuranceNumber)) {
			errors.rejectValue("insuranceNumber", "not_empty.insurance_number");
		} else if (common.isRightFormatInsuranceNumber(insuranceNumber) == false) {
			errors.rejectValue("insuranceNumber", "format.insurance_number");
		} else if (tblInsuranceService.isExistsTblInsurance(insuranceInternalId, insuranceNumber)) {
			errors.rejectValue("insuranceNumber", "exists.insurance_number");
		}
	}
	
	/**
	 * validate user full name
	 * @param errors all error
	 * @param userFullName
	 */
	public void validateUserFullName(Errors errors, String userFullName) {
		if (common.isNullOrEmpty(userFullName)) {
			errors.rejectValue("userFullName", "not_empty.user_full_name");
		} else if (userFullName.trim().length() >= 50) {
			errors.rejectValue("userFullName", "length_max.user_full_name");
		}
	}
	
	/**
	 * validate user name
	 * @param errors all error
	 * @param userName
	 */
	public void validateUserName(Errors errors, String userName) {
		if (common.isNullOrEmpty(userName)) {
			errors.rejectValue("userName", "not_empty.user_name");
		} else if (userName.trim().length() > 16) {
			errors.rejectValue("userName", "length_max.user_name");
		}
	}
	
	/**
	 * validate password
	 * @param errors all error
	 * @param passWord
	 * @param confirmPassWord
	 */
	public void validatePassWord(Errors errors, String passWord, String confirmPassWord) {
		if (common.isNullOrEmpty(passWord)) {
			errors.rejectValue("passWord", "not_empty.password");
		} else if (passWord.trim().length() > 32) {
			errors.rejectValue("passWord", "length_max.password");
		} else if (confirmPassWord.equals(passWord) == false) {
			errors.rejectValue("passWord", "compare_password.password_confirm_password");
		}
	}
	
	/**
	 * validate user sex division
	 * @param errors all errors
	 * @param userSexDivision
	 */
	public void validateUserSexDivision(Errors errors, String userSexDivision) {
		if ((userSexDivision.equals("01") == false) && (userSexDivision.equals("02") == false)) {
			errors.rejectValue("user_sex_division", "format.user_sex_division");
		}
	}
	
	/**
	 * validate birth of date
	 * @param errors all errors
	 * @param dateBirth
	 */
	public void validateBirthDate(Errors errors, String dateBirth) {
		if (common.isNullOrEmpty(dateBirth) == false) {
			errors.rejectValue("dateBirth", "not_empty.date_birth");
		} else if (common.isRightFormatDate(dateBirth) == false) {
			errors.rejectValue("dateBirth", "format.date_birth");
		} else if (common.isDateExists(dateBirth) == false) {
			errors.rejectValue("dateBirth", "exists_date.date_birth");
		} else if (common.isParamDate2GreatThanParamDate1(common
			.getTodayDDMMYYYY(), dateBirth)) {
			errors.rejectValue("dateBirth", "compare_date._birth_date_and_today");
		}
	}
	
	/**
	 * validate company name
	 * @param errors all errors
	 * @param companyName
	 */
	public void validateCompanyName(Errors errors, String companyName) {
		if (common.isNullOrEmpty(companyName)) {
			errors.rejectValue("companyName", "not_empty.company_name");
		} else if (companyName.trim().length() > 50) {
			errors.rejectValue("companyName", "length_max.company_name");
		} else if (tblCompanyService.isExistsCompanyName(companyName) == true) {
			errors.rejectValue("companyName", "exists_company_name.company_name");
		}
	}
	
	/**
	 * validate address company
	 * @param errors all errors
	 * @param address
	 */
	public void validateAddressCompany(Errors errors, String address) {
		if (common.isNullOrEmpty(address)) {
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
	public void validateEmailCompany(Errors errors, String email) {
		if (common.isNullOrEmpty(email)) {
			errors.rejectValue("email", "not_empty.email");
		} else if (email.trim().length() > 50) {
			errors.rejectValue("email", "length_max.email");
		} else if (common.isFormatEmail(email) == false) {
			errors.rejectValue("email", "format.email");
		}
	}
	
	/**
	 * validate phone company
	 * @param errors all errors
	 * @param telephone
	 */
	public void validatePhoneCompany(Errors errors, String telephone) {
		if (common.isNullOrEmpty(telephone)) {
			errors.rejectValue("telephone", "not_empty.telephone");
		} else if (common.isFormatPhone(telephone) == false) {
			errors.rejectValue("telephone", "format.telephone");
		}
	}
	
	/**
	 * validate place of register
	 * @param errors all errors
	 * @param place
	 */
	public void validatePlaceOfRegister(Errors errors, String place) {
		if (common.isNullOrEmpty(place)) {
			errors.rejectValue("placeOfRegister", "not_empty.place_of_register");
		} else if (place.trim().length() > 50) {
			errors.rejectValue("placeOfRegister", "length_max.place_of_register");
		}
	}
	
	/**
	 * validate start date
	 * @param errors all errors
	 * @param startDate
	 */
	public void validateStartDate(Errors errors, String startDate) {
		if (common.isNullOrEmpty(startDate)) {
			errors.rejectValue("insuranceStartDate", "not_empty.insurance_start_date");
		} else if (common.isRightFormatDate(startDate) == false) {
			errors.rejectValue("insuranceStartDate", "format.insurance_start_date");
		} else if (common.isDateExists(startDate) == false) {
			errors.rejectValue("insuranceStartDate", "exists_date.insurance_start_date");
		}
	}
	
	/**
	 * validate end date
	 * @param errors all errors
	 * @param startDate
	 * @param endDate
	 */
	public void validateEndDate(Errors errors, String startDate, String endDate) {
		if (common.isNullOrEmpty(endDate)) {
			errors.rejectValue("insuranceEndDate", "not_empty.insurance_end_date");
		} else if (common.isRightFormatDate(endDate) == false) {
			errors.rejectValue("insuranceEndDate", "format.insurance_end_date");
		} else if (common.isDateExists(endDate) == false) {
			errors.rejectValue("insuranceEndDate", "exists_date.insurance_end_date");
		} else if (common.isParamDate2GreatThanParamDate1(startDate, endDate) == false) {
			errors.rejectValue("insuranceEndDate",
					"compare_date.insurance_start_date_insurance_and_end_date_insurance");
		}
	}
}

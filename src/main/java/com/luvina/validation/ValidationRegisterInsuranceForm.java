package com.luvina.validation;

import com.luvina.entities.TblUser;
import com.luvina.form.RegisterInsuranceForm;
import com.luvina.service.ITblInsuranceService;
import com.luvina.service.ITblUserService;
import com.luvina.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidationRegisterInsuranceForm implements Validator {

    @Autowired
    private ITblInsuranceService iTblInsuranceService;
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
		String numberInsurance = registerInsuranceForm.getNumberInsurance();
		String userFullName = registerInsuranceForm.getUserFullName();
		String userName = registerInsuranceForm.getUserName();
		String passWord = registerInsuranceForm.getPassWord();
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
		String check = registerInsuranceForm.getCheck();
        validateNumberInsurance(insuranceInternalId, numberInsurance, errors);
		
	}
	
	private void validateNumberInsurance(int idInsurance, String numberInsurance, Errors errors) {
		if (Common.checkNullAndEmpty(numberInsurance)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberInsurance",
					"NotEmpty.RegisterInsuranceForm.numberInsurance");
		} else if (Common.checkNumberFormatNumberInsurance(numberInsurance)) {
			errors.rejectValue("numberInsurance", "Format.RegisterInsuranceForm.numberInsurance");
		} else if (iTblInsuranceService.checkInsuranceIfNotExists(idInsurance, numberInsurance)) {
			errors.rejectValue("numberInsurance", "Exists.RegisterInsuranceForm.numberInsurance");
		}
	}
}

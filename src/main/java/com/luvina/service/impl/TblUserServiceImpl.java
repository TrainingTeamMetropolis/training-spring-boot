package com.luvina.service.impl;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblInsurance;
import com.luvina.entities.TblUser;
import com.luvina.form.RegisterInsuranceForm;
import com.luvina.repository.ITblCompanyRepository;
import com.luvina.repository.ITblInsuranceRepository;
import com.luvina.repository.ITblUserRepository;
import com.luvina.repository.customize.ITblUserRepositoryCustom;
import com.luvina.service.ITblUserService;
import com.luvina.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TblUserServiceImpl implements ITblUserService {
	
	@Autowired
	ITblUserRepository iTblUserRepository;
	
	@Autowired
	ITblUserRepositoryCustom iTblUserRepositoryCustom;
	
	@Autowired
	ITblCompanyRepository iTblCompanyRepository;
	
	@Autowired
	ITblInsuranceRepository iTblInsuranceRepository;
	
	
	@Override
	public List<TblUser> findAndSearchListData(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister) {
		return iTblUserRepositoryCustom.findAndSearchListData(offset, limit, typeSort, companyInternalId, userFullName,
				insuranceNumber, placeOfRegister);
	}
	
	@Override
	public Integer findTotalRecords(int offset, int limit, String typeSort, int companyInternalId, String userFullName,
			String insuranceNumber, String placeOfRegister) {
		return iTblUserRepositoryCustom.findTotalRecords(offset, limit, typeSort, companyInternalId, userFullName,
				insuranceNumber, placeOfRegister);
	}
	
	@Override
	public TblUser findByUserInternalId(int userInternalId) {
		return iTblUserRepository.findByUserInternalId(userInternalId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertInformationInsuranceOfUser(RegisterInsuranceForm registerInsuranceForm) {
		String checkRadioCompany = registerInsuranceForm.getRadioCompany();
		if (checkRadioCompany.equals("new")) {
			TblCompany tblCompany = saveTblCompany(registerInsuranceForm);
			int companyInternalIdInsert = tblCompany.getCompanyInternalId();
			TblInsurance tblInsurance = saveTblInsurance(registerInsuranceForm);
			int insuranceInternalIdInsert = tblInsurance.getInsuranceInternalId();
			saveTblUser(registerInsuranceForm, companyInternalIdInsert, insuranceInternalIdInsert);
		} else {
			TblInsurance tblInsurance = saveTblInsurance(registerInsuranceForm);
			int insuranceInternalIdInsert = tblInsurance.getInsuranceInternalId();
			saveTblUser(registerInsuranceForm, registerInsuranceForm.getCompanyInternalId(), insuranceInternalIdInsert);
		}
	}
	
	private TblCompany saveTblCompany(RegisterInsuranceForm registerInsuranceForm) {
		TblCompany tblCompany = new TblCompany();
		tblCompany.setAddressCompany(registerInsuranceForm.getAddress());
		tblCompany.setEmailCompany(registerInsuranceForm.getEmail());
		tblCompany.setPhoneCompany(registerInsuranceForm.getTelephone());
		tblCompany.setCompanyName(registerInsuranceForm.getCompanyName());
		iTblCompanyRepository.save(tblCompany);
		return tblCompany;
	}
	
	private TblInsurance saveTblInsurance(RegisterInsuranceForm registerInsuranceForm) {
		TblInsurance tblInsurance = new TblInsurance();
		tblInsurance
			.setInsuranceStartDate(Common.convertStringToDateSQL(registerInsuranceForm.getInsuranceStartDate()));
		tblInsurance.setInsuranceEndDate(Common.convertStringToDateSQL(registerInsuranceForm.getInsuranceEndDate()));
		tblInsurance.setPlaceOfRegister(registerInsuranceForm.getPlaceOfRegister());
		tblInsurance.setInsuranceNumber(registerInsuranceForm.getInsuranceNumber());
		return tblInsurance;
	}
	
	private void saveTblUser(RegisterInsuranceForm registerInsuranceForm, int companyInternalIdInsert,
			int insuranceInternalIdInsert) {
		TblUser tblUser = new TblUser();
		tblUser.setUserFullName(Common.handleString(registerInsuranceForm.getUserFullName()));
		tblUser.setUserName(registerInsuranceForm.getUserName());
		tblUser.setPassWord(Common.encodePassword(registerInsuranceForm.getPassWord()));
		tblUser.setUserSexDivision(registerInsuranceForm.getRadioUserSexDivision());
		if (registerInsuranceForm.getDateBirth().length() > 0) {
			tblUser.setBirthDate(Common.convertStringToDateSQL(registerInsuranceForm.getDateBirth()));
		}
		tblUser.setCompanyInternalId(companyInternalIdInsert);
		tblUser.setInsuranceInternalId(insuranceInternalIdInsert);
		iTblUserRepository.save(tblUser);
	}
	
}

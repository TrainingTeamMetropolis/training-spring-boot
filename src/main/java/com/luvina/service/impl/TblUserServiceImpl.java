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
		String userFullName = registerInsuranceForm.getUserFullName();
		String userName = registerInsuranceForm.getUserName();
		String passWord = registerInsuranceForm.getPassWord();
		String userSexDivision = registerInsuranceForm.getRadioUserSexDivision();
		String dateBirth = registerInsuranceForm.getDateBirth();
		String placeOfRegister = registerInsuranceForm.getPlaceOfRegister();
		String insuranceStartDate = registerInsuranceForm.getInsuranceStartDate();
		String insuranceEndDate = registerInsuranceForm.getInsuranceEndDate();
		String insuranceNumber = registerInsuranceForm.getInsuranceNumber();
		int companyInternalId = registerInsuranceForm.getCompanyInternalId();
		String address = registerInsuranceForm.getAddress();
		String companyName = registerInsuranceForm.getCompanyName();
		String email = registerInsuranceForm.getEmail();
		String telephone = registerInsuranceForm.getTelephone();
		
		TblInsurance tblInsurance = new TblInsurance();
		tblInsurance.setInsuranceStartDate(Common.convertStringToDateSQL(insuranceStartDate));
		tblInsurance.setInsuranceEndDate(Common.convertStringToDateSQL(insuranceEndDate));
		tblInsurance.setPlaceOfRegister(placeOfRegister);
		tblInsurance.setInsuranceNumber(insuranceNumber);
		
		if (checkRadioCompany.equals("new")) {
			TblCompany tblCompany = new TblCompany();
			tblCompany.setAddressCompany(address);
			tblCompany.setEmailCompany(email);
			tblCompany.setPhoneCompany(telephone);
			tblCompany.setCompanyName(companyName);
			iTblCompanyRepository.save(tblCompany);

			int companyInternalIdInsert = tblCompany.getCompanyInternalId();
			iTblInsuranceRepository.save(tblInsurance);

			TblInsurance tblInsuranceInsert = iTblInsuranceRepository.findByInsuranceNumber(insuranceNumber);
			int insuranceInternalIdInsert = tblInsuranceInsert.getInsuranceInternalId();
			
			TblUser tblUser = new TblUser();
			tblUser.setUserFullName(Common.handleString(userFullName));
			tblUser.setUserName(userName);
			tblUser.setPassWord(Common.encodePassword(passWord));
			tblUser.setUserSexDivision(userSexDivision);
			if (dateBirth.length() > 0) {
				tblUser.setBirthDate(Common.convertStringToDateSQL(dateBirth));
			}
			tblUser.setCompanyInternalId(companyInternalIdInsert);
			tblUser.setInsuranceInternalId(insuranceInternalIdInsert);
			iTblUserRepository.save(tblUser);
		} else {
			iTblInsuranceRepository.save(tblInsurance);
			TblInsurance tblInsuranceInsert = iTblInsuranceRepository.findByInsuranceNumber(insuranceNumber);
			int insuranceInternalId = tblInsuranceInsert.getInsuranceInternalId();
			TblUser tblUser = new TblUser();
			tblUser.setUserFullName(Common.handleString(userFullName));
			tblUser.setUserName(userName);
			tblUser.setPassWord(Common.encodePassword(passWord));
			
			tblUser.setUserSexDivision(userSexDivision);
			if (dateBirth.length() > 0) {
				tblUser.setBirthDate(Common.convertStringToDateSQL(dateBirth));
			}
			tblUser.setCompanyInternalId(companyInternalId);
			tblUser.setInsuranceInternalId(insuranceInternalId);
            iTblUserRepository.save(tblUser);
		}
	}
}

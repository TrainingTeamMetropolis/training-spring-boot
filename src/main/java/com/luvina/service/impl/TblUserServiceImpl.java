package com.luvina.service.impl;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblInsurance;
import com.luvina.entities.TblUser;
import com.luvina.form.RegisterForm;
import com.luvina.repository.TblCompanyRepository;
import com.luvina.repository.TblInsuranceRepository;
import com.luvina.repository.TblUserRepository;
import com.luvina.repository.customize.TblUserRepositoryCustom;
import com.luvina.service.TblUserService;
import com.luvina.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TblUserServiceImpl implements TblUserService {
	
	@Autowired
	TblUserRepository tblUserRepository;
	
	@Autowired
	TblUserRepositoryCustom tblUserRepositoryCustom;
	
	@Autowired
	TblCompanyRepository tblCompanyRepository;
	
	@Autowired
	TblInsuranceRepository tblInsuranceRepository;

    /**
     * Find all data {@link TblUser} by companyInternalId, userFullName, insuranceNumber, placeOfRegister
     * <p>find data by companyInternalId, userFullName, insuranceNumber, placeOfRegister <p/>
     * <p>after find then sort and paging</p>
     * @param offset
     * @param limit
     * @param typeSort
     * @param companyInternalId
     * @param userFullName
     * @param insuranceNumber
     * @param placeOfRegister
     * @return List of {@Link TblUser}
     */
	@Override
	public List<TblUser> findAndSearchListData(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister) {
		return tblUserRepositoryCustom.findAndSearchListData(offset, limit, typeSort, companyInternalId, userFullName,
				insuranceNumber, placeOfRegister);
	}
    /**
     * Find count data {@link TblUser} by companyInternalId, userFullName, insuranceNumber, placeOfRegister
     * <p>find count by companyInternalId, userFullName, insuranceNumber, placeOfRegister <p/>
     * @param offset
     * @param limit
     * @param typeSort
     * @param companyInternalId
     * @param userFullName
     * @param insuranceNumber
     * @param placeOfRegister
     * @return number of record {@Link TblUser}
     */
	@Override
	public Integer findTotalRecords(int offset, int limit, String typeSort, int companyInternalId, String userFullName,
			String insuranceNumber, String placeOfRegister) {
		return tblUserRepositoryCustom.findTotalRecords(offset, limit, typeSort, companyInternalId, userFullName,
				insuranceNumber, placeOfRegister);
	}
    /**
     * find data {@Link TblUser}
     * @param userInternalId
     * @return {@Link TblUser}
     */
	@Override
	public TblUser findByUserInternalId(int userInternalId) {
		return tblUserRepository.findByUserInternalId(userInternalId);
	}
    /**
     * insert request data to data base
     * @param registerForm
     */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertInformationInsuranceOfUser(RegisterForm registerForm) {
		String checkRadioCompany = registerForm.getRadioCompany();
		int companyInternalIdInsert = registerForm.getCompanyInternalId();
		if (checkRadioCompany.equals("new")) {
			TblCompany tblCompany = saveTblCompany(registerForm);
			companyInternalIdInsert = tblCompany.getCompanyInternalId();
		}
		TblInsurance tblInsurance = saveTblInsurance(registerForm);
		int insuranceInternalIdInsert = tblInsurance.getInsuranceInternalId();
		saveTblUser(registerForm, companyInternalIdInsert, insuranceInternalIdInsert);
	}

    /**
     * save data {@Link TblCompany} when check box new
     * @param registerForm
     * @return {@Link TblCompany}
     */
	private TblCompany saveTblCompany(RegisterForm registerForm) {
		TblCompany tblCompany = new TblCompany();
		tblCompany.setAddressCompany(registerForm.getAddress());
		tblCompany.setEmailCompany(registerForm.getEmail());
		tblCompany.setPhoneCompany(registerForm.getTelephone());
		tblCompany.setCompanyName(registerForm.getCompanyName());
		tblCompanyRepository.save(tblCompany);
		return tblCompany;
	}

    /**
     * save data {@Link TblInsurance}
     * @param registerForm
     * @return {@Link TblInsurance}
     */
	private TblInsurance saveTblInsurance(RegisterForm registerForm) {
		TblInsurance tblInsurance = new TblInsurance();
		tblInsurance
			.setInsuranceStartDate(Common.convertStringToDateSQL(registerForm.getInsuranceStartDate()));
		tblInsurance.setInsuranceEndDate(Common.convertStringToDateSQL(registerForm.getInsuranceEndDate()));
		tblInsurance.setPlaceOfRegister(registerForm.getPlaceOfRegister());
		tblInsurance.setInsuranceNumber(registerForm.getInsuranceNumber());
		return tblInsurance;
	}

    /**
     * save data {@Link TblUser}
     * @param registerForm
     * @param companyInternalIdInsert id after insert
     * @param insuranceInternalIdInsert
     */
	private void saveTblUser(RegisterForm registerForm, int companyInternalIdInsert,
                             int insuranceInternalIdInsert) {
		TblUser tblUser = new TblUser();
		tblUser.setUserFullName(Common.handleString(registerForm.getUserFullName()));
		tblUser.setUserName(registerForm.getUserName());
		tblUser.setPassWord(Common.encodePassword(registerForm.getPassWord()));
		tblUser.setUserSexDivision(registerForm.getRadioUserSexDivision());
		if (registerForm.getDateBirth().length() > 0) {
			tblUser.setBirthDate(Common.convertStringToDateSQL(registerForm.getDateBirth()));
		}
		tblUser.setCompanyInternalId(companyInternalIdInsert);
		tblUser.setInsuranceInternalId(insuranceInternalIdInsert);
		tblUserRepository.save(tblUser);
	}
}

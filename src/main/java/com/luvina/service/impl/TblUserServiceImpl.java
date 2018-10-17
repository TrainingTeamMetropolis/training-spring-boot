package com.luvina.service.impl;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblInsurance;
import com.luvina.entities.TblUser;
import com.luvina.form.RegisterForm;
import com.luvina.form.SearchForm;
import com.luvina.repository.TblCompanyRepository;
import com.luvina.repository.TblInsuranceRepository;
import com.luvina.repository.TblUserRepository;
import com.luvina.repository.customize.TblUserRepositoryCustom;
import com.luvina.service.TblCompanyService;
import com.luvina.service.TblUserService;
import com.luvina.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Autowired
	TblCompanyService tblCompanyService;

	@Autowired
    Common common;

    /**
     * Find all data {@link TblUser} by companyInternalId, userFullName, insuranceNumber, placeOfRegister
     *
     * <p>find data by companyInternalId, userFullName, insuranceNumber, placeOfRegister <p/>
     * <p>after find then sort and paging</p>
     * @param searchForm
     * @param requestParam
     * @param session
     * @return
     */
	@Override
	public Map findAndSearchListData(SearchForm searchForm, @RequestParam Map<String, String> requestParam,
			HttpSession session) {
		int offset = 0;
		int limit = common.getLimit();
		int endRange = 0;
		Integer currentPage = 0;
		String typeSort = "ASC";
		String userFullName = "";
		String insuranceNumber = "";
		String placeOfRegister = "";
		long id = new Date().getTime();
		String searchFormId = Long.toString(id);
		if (requestParam.get("searchFormId") != null) {
			searchFormId = requestParam.get("searchFormId");
		}
		List<TblCompany> tblCompanyList = tblCompanyService.findAllByOrderByCompanyNameAsc();
		Integer companyInternalId = searchForm.getSearchByCompanyInternalId();
		if (companyInternalId == null) {
			companyInternalId = tblCompanyList.get(0).getCompanyInternalId();
		}
		if (common.isNullOrEmpty(searchForm.getSearchByUserFullName()) == false) {
			userFullName = searchForm.getSearchByUserFullName();
		}
		if (common.isNullOrEmpty(searchForm.getSearchByInsuranceNumber()) == false) {
			insuranceNumber = searchForm.getSearchByInsuranceNumber();
		}
		if (common.isNullOrEmpty(searchForm.getSearchByPlaceOfRegister()) == false) {
			placeOfRegister = searchForm.getSearchByPlaceOfRegister();
		}
		if (common.isNullOrEmpty(requestParam.get("typeSort")) == false) {
			typeSort = requestParam.get("typeSort");
		}
		if (common.isNullOrEmpty(requestParam.get("page")) == false) {
			currentPage = Integer.parseInt(requestParam.get("page"));
		}
		if (session.getAttribute(searchFormId) != null) {
            SearchForm form = (SearchForm) session.getAttribute(searchFormId);
            session.removeAttribute(searchFormId);
            userFullName = form.getSearchByUserFullName();
            insuranceNumber = form.getSearchByInsuranceNumber();
            placeOfRegister = form.getSearchByPlaceOfRegister();
            companyInternalId = form.getSearchByCompanyInternalId();
            if (common.isNullOrEmpty(requestParam.get("page")) == false) {
                typeSort = form.getTypeSort();
            }
            if (common.isNullOrEmpty(requestParam.get("typeSort")) == false) {
                currentPage = form.getPage();
            }
        }
		if (currentPage == 0) {
			currentPage = 1;
		}
		String userFullNameEscape = common.escapeInjection(userFullName);
		String insuranceNumberEscape = common.escapeInjection(insuranceNumber);
		String placeOfRegisterEscape = common.escapeInjection(placeOfRegister);
		typeSort = common.handleSortType(typeSort);
		Integer totalRecord = findTotalRecords(offset, limit, typeSort, companyInternalId, userFullNameEscape,
				insuranceNumberEscape, placeOfRegisterEscape);
		List<Integer> listPaging = common.getListPaging(totalRecord, currentPage);
		if (listPaging.size() != 0) {
			endRange = listPaging.get(listPaging.size() - 1);
		}
		offset = common.getOffsetPaging(currentPage, limit);
		List<TblUser> tblUserList =
				tblUserRepositoryCustom.findAndSearchListData(offset, limit, typeSort, companyInternalId, userFullName,
						insuranceNumber, placeOfRegister);
		searchForm.setSearchByCompanyInternalId(companyInternalId);
		searchForm.setSearchByInsuranceNumber(insuranceNumber);
		searchForm.setSearchByUserFullName(userFullName);
		searchForm.setSearchByPlaceOfRegister(placeOfRegister);
		searchForm.setTypeSort(typeSort);
		searchForm.setPage(currentPage);
		
		Map mapData = new HashMap();
		mapData.put("tblUserList", tblUserList);
		mapData.put("totalPage", common.getTotalPage(totalRecord, limit));
		mapData.put("listPaging", listPaging);
		mapData.put("endRange", endRange);
		mapData.put("searchFormId", searchFormId);
		mapData.put("searchForm", searchForm);
		mapData.put("currentPage", currentPage);
		mapData.put("typeSort", typeSort);
		
		return mapData;
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
	public void insertInformationInsuranceOfUser(RegisterForm registerForm) throws NoSuchAlgorithmException {
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
	public TblCompany saveTblCompany(RegisterForm registerForm) {
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
	public TblInsurance saveTblInsurance(RegisterForm registerForm) {
		TblInsurance tblInsurance = new TblInsurance();
		tblInsurance
			.setInsuranceStartDate(common.convertStringToDateSQL(registerForm.getInsuranceStartDate()));
		tblInsurance.setInsuranceEndDate(common.convertStringToDateSQL(registerForm.getInsuranceEndDate()));
		tblInsurance.setPlaceOfRegister(registerForm.getPlaceOfRegister());
		tblInsurance.setInsuranceNumber(registerForm.getInsuranceNumber());
        tblInsuranceRepository.save(tblInsurance);
		return tblInsurance;
	}
	
	/**
	 * save data {@Link TblUser}
	 * @param registerForm
	 * @param companyInternalIdInsert id after insert
	 * @param insuranceInternalIdInsert
	 */
	public void saveTblUser(RegisterForm registerForm, int companyInternalIdInsert,
			int insuranceInternalIdInsert) throws NoSuchAlgorithmException {
		TblUser tblUser = new TblUser();
		tblUser.setUserFullName(common.handleString(registerForm.getUserFullName()));
		tblUser.setUserName(registerForm.getUserName());
		tblUser.setPassWord(common.encodePassword(registerForm.getPassWord()));
		tblUser.setUserSexDivision(registerForm.getRadioUserSexDivision());
		if (registerForm.getDateBirth().length() > 0) {
			tblUser.setBirthDate(common.convertStringToDateSQL(registerForm.getDateBirth()));
		}
		tblUser.setCompanyInternalId(companyInternalIdInsert);
		tblUser.setInsuranceInternalId(insuranceInternalIdInsert);
		tblUserRepository.save(tblUser);
	}
}

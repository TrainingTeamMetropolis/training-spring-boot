package com.luvina.service;

import com.luvina.entities.TblUser;
import com.luvina.form.RegisterForm;

import java.util.List;

public interface ITblUserService {
	
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
	List<TblUser> findAndSearchListData(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister);
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
	Integer findTotalRecords(int offset, int limit, String typeSort, int companyInternalId, String userFullName,
			String insuranceNumber, String placeOfRegister);

	/**
	 * find data {@Link TblUser}
	 * @param userInternalId
	 * @return {@Link TblUser}
	 */
	TblUser findByUserInternalId(int userInternalId);

	/**
	 * insert request data to data base
	 * @param registerForm data form from request
	 */
	void insertInformationInsuranceOfUser(RegisterForm registerForm);
}

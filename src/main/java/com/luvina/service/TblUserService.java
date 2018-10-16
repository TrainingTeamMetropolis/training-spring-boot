
package com.luvina.service;

import com.luvina.entities.TblUser;
import com.luvina.form.RegisterForm;
import com.luvina.form.SearchForm;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface TblUserService {
	
	/**
	 * Find all data {@link TblUser} by companyInternalId, userFullName, insuranceNumber, placeOfRegister
	 * <p>find data by companyInternalId, userFullName, insuranceNumber, placeOfRegister <p/>
	 * <p>after find then sort and paging</p>
	 * @param searchForm
	 * @param requestParam
	 * @param session
	 * @return
	 */
	Map findAndSearchListData(SearchForm searchForm, @RequestParam Map<String, String> requestParam,
			HttpSession session);
	
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
	void insertInformationInsuranceOfUser(RegisterForm registerForm) throws NoSuchAlgorithmException;
}

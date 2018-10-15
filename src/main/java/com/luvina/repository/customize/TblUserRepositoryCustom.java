package com.luvina.repository.customize;

import java.util.List;

import com.luvina.entities.TblUser;

public interface TblUserRepositoryCustom {

	/**
	 * search for dashboard screen
	 * <p>query data base and return result<p/>
	 * @param offset offset
	 * @param limit limit
	 * @param typeSort type sort
	 * @param companyInternalId company internal id
	 * @param userFullName user full name
	 * @param insuranceNumber insurance number
	 * @param placeOfRegister place of register
	 * @return List<TblUser> list data tbl_user
	 */
	List<TblUser> findAndSearchListData(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister);
    /**
     * get total record for dashboard screen
     * <p>query data base and return result<p/>
     * @param offset offset
     * @param limit limit
     * @param typeSort type sort
     * @param companyInternalId company internal id
     * @param userFullName user full name
     * @param insuranceNumber insurance number
     * @param placeOfRegister place of register
     * @return Integer number of records
     */
	Integer findTotalRecords(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister);
}

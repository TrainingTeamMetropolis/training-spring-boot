package com.luvina.repository.customize;

import java.util.List;

import com.luvina.entities.TblUser;

public interface ITblUserRepositoryCustom {
	
	List<TblUser> findAndSearchListData(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister);
	
	Integer findTotalRecords(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister);
}

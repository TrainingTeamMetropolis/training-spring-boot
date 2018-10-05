package com.luvina.repository.customize;

import com.luvina.entities.TblUser;

import java.util.List;

public interface ITblUserRepositoryCustom {
	
	List<TblUser> findAndSearchListData(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister);
	
	Integer findTotalRecords(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister);
}

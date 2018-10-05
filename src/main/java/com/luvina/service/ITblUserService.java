package com.luvina.service;

import com.luvina.entities.TblUser;

import java.util.List;

public interface ITblUserService {
	
	List<TblUser> findAndSearchListData(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister);
	
	Integer findTotalRecords(int offset, int limit, String typeSort, int companyInternalId, String userFullName,
			String insuranceNumber, String placeOfRegister);
}

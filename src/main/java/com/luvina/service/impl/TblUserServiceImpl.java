package com.luvina.service.impl;

import com.luvina.entities.TblUser;
import com.luvina.repository.ITblUserRepository;
import com.luvina.repository.customize.ITblUserRepositoryCustom;
import com.luvina.service.ITblUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TblUserServiceImpl implements ITblUserService {
	
	@Autowired
	ITblUserRepository iTblUserRepository;
	
	@Autowired
	ITblUserRepositoryCustom iTblUserRepositoryCustom;

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
}

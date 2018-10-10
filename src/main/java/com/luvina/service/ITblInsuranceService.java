package com.luvina.service;

import com.luvina.entities.TblInsurance;

public interface ITblInsuranceService {
	
	boolean isExistsTblInsurance(int insuranceInternalId, String insuranceNumber);

	TblInsurance findTblInsuranceByInsuranceNumber(String insuranceNumber);
	
}

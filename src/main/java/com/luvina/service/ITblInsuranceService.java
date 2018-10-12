package com.luvina.service;

public interface ITblInsuranceService {
	/**
	 * check true is exists {@Link TblInsurance}
	 * @param insuranceInternalId
	 * @param insuranceNumber
	 * @return boolean true when is exits and false when not exits
	 */
	boolean isExistsTblInsurance(int insuranceInternalId, String insuranceNumber);

}

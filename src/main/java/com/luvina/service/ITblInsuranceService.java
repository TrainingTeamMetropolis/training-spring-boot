package com.luvina.service;

public interface ITblInsuranceService {
	
	boolean checkInsuranceIfNotExists(int insuranceInternalId, String insuranceNumber);
	
}

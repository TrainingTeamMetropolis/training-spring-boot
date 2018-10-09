package com.luvina.service.impl;

import com.luvina.entities.TblInsurance;
import com.luvina.repository.ITblInsuranceRepository;
import com.luvina.service.ITblInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TblInsuranceServiceImpl implements ITblInsuranceService {
	
	@Autowired
	private ITblInsuranceRepository iTblInsuranceRepository;
	
	
	@Override
	public boolean checkInsuranceIfNotExists(int insuranceInternalId, String insuranceNumber) {
		TblInsurance tblInsurance =
				iTblInsuranceRepository.findByInsuranceNumberAndInsuranceInternalId(insuranceInternalId,
						insuranceNumber);
		if (tblInsurance.getInsuranceNumber() != null) {
			return true;
		}
		return false;
	}
}

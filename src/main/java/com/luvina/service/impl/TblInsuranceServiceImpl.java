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
	public boolean isExistsTblInsurance(int insuranceInternalId, String insuranceNumber) {
		TblInsurance tblInsurance =
				iTblInsuranceRepository.findByInsuranceInternalIdAndInsuranceNumberCustom(insuranceInternalId,
						insuranceNumber);
		if (tblInsurance != null) {
			return true;
		}
		return false;
	}

    @Override
    public TblInsurance findTblInsuranceByInsuranceNumber(String insuranceNumber) {
        return iTblInsuranceRepository.findTblInsuranceByInsuranceNumber(insuranceNumber);
    }
}

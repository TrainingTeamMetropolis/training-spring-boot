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

    /**
     * check true is exists {@Link TblInsurance}
     * @param insuranceInternalId
     * @param insuranceNumber
     * @return boolean true when is exits and false when not exits
     */
	@Override
	public boolean isExistsTblInsurance(int insuranceInternalId, String insuranceNumber) {
		TblInsurance tblInsurance =
				iTblInsuranceRepository.findByInsuranceInternalIdNotAndInsuranceNumber(insuranceInternalId,
						insuranceNumber);
		if (tblInsurance != null) {
			return true;
		}
		return false;
	}
}

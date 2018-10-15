package com.luvina.service.impl;

import com.luvina.entities.TblInsurance;
import com.luvina.repository.TblInsuranceRepository;
import com.luvina.service.TblInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TblInsuranceServiceImpl implements TblInsuranceService {
	
	@Autowired
	private TblInsuranceRepository tblInsuranceRepository;

    /**
     * check true is exists {@Link TblInsurance}
     * @param insuranceInternalId
     * @param insuranceNumber
     * @return boolean true when is exits and false when not exits
     */
	@Override
	public boolean isExistsTblInsurance(int insuranceInternalId, String insuranceNumber) {
		TblInsurance tblInsurance =
				tblInsuranceRepository.findByInsuranceInternalIdNotAndInsuranceNumber(insuranceInternalId,
						insuranceNumber);
		if (tblInsurance != null) {
			return true;
		}
		return false;
	}
}

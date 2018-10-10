package com.luvina.repository;

import com.luvina.entities.TblInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITblInsuranceRepository extends JpaRepository<TblInsurance, Long> {
	
	String SQL_GET_TBL_INSURANCE_BY_INSURANCE_INTERNAL_ID_AND_INSURANCE_NUMBER = "FROM TblInsurance WHERE"
			+ " insuranceInternalId!=:insuranceInternalId AND insuranceNumber=:insuranceNumber";

	@Query(SQL_GET_TBL_INSURANCE_BY_INSURANCE_INTERNAL_ID_AND_INSURANCE_NUMBER)
	TblInsurance findByInsuranceInternalIdAndInsuranceNumberCustom(int insuranceInternalId, String insuranceNumber);

    TblInsurance findTblInsuranceByInsuranceNumber(String insuranceNumber);
}

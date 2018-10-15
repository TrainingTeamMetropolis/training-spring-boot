package com.luvina.repository;

import com.luvina.entities.TblInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblInsuranceRepository extends JpaRepository<TblInsurance, Long> {
    /**
     * Find data {@Link TblInsurance} by not insurance internal id and insurance number
     * @param insuranceInternalId
     * @param insuranceNumber
     * @return {@Link TblInsurance} data tbl_insurance
     */
	TblInsurance findByInsuranceInternalIdNotAndInsuranceNumber(int insuranceInternalId, String insuranceNumber);
}

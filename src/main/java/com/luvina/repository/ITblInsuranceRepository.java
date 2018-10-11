package com.luvina.repository;

import com.luvina.entities.TblInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITblInsuranceRepository extends JpaRepository<TblInsurance, Long> {

	TblInsurance findByInsuranceInternalIdNotAndInsuranceNumber(int insuranceInternalId, String insuranceNumber);

    TblInsurance findByInsuranceNumber(String insuranceNumber);
}

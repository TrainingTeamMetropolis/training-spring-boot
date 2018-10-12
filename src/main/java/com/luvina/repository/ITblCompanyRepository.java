package com.luvina.repository;

import com.luvina.entities.TblCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ITblCompanyRepository extends JpaRepository<TblCompany, Integer> {
    /**
     * Find all data {@Link TblCompany} and order company name asc
     * @return List of {@Link TblCompany} List data
     */
    List<TblCompany> findAllByOrderByCompanyNameAsc();

    /**
     * Find data {@Link TblCompany} by company internal id
     * @param companyInternalId
     * @return {@Link TblCompany} data company
     */
    TblCompany findByCompanyInternalId(int companyInternalId);

    /**
     * Find data {@Link TblCompany} by company name
     * @param companyName
     * @return data of {@Link TblCompany}
     */
    TblCompany findByCompanyName(String companyName);

}

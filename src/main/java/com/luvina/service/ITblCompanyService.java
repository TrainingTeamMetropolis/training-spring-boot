package com.luvina.service;

import com.luvina.entities.TblCompany;

import java.util.List;

public interface ITblCompanyService {

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
     * @return boolean
     */
    boolean isExistsCompanyName(String companyName);
}

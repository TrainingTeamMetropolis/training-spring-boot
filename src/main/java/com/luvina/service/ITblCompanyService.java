package com.luvina.service;

import com.luvina.entities.TblCompany;

import java.util.List;

public interface ITblCompanyService {

    /**
     * method get list company from data base
     *
     * @return
     */
    List<TblCompany> findAllByOrderByCompanyNameAsc();

    /**
     *
     * @param companyInternalId id companyInternalId
     * @return
     */
    TblCompany findByCompanyInternalId(int companyInternalId);

}

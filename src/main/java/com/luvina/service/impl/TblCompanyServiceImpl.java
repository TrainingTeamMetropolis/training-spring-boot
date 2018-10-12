package com.luvina.service.impl;

import com.luvina.entities.TblCompany;
import com.luvina.repository.ITblCompanyRepository;
import com.luvina.service.ITblCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TblCompanyServiceImpl implements ITblCompanyService {
    @Autowired
    ITblCompanyRepository iTblCompanyRepository;
    /**
     * Find all data {@Link TblCompany} and order company name asc
     * @return List of {@Link TblCompany} List data
     */
    @Override
    public List<TblCompany> findAllByOrderByCompanyNameAsc() {
        return iTblCompanyRepository.findAllByOrderByCompanyNameAsc();
    }
    /**
     * Find data {@Link TblCompany} by company internal id
     * @param companyInternalId
     * @return {@Link TblCompany} data company
     */
    @Override
    public TblCompany findByCompanyInternalId(int companyInternalId) {
        return iTblCompanyRepository.findByCompanyInternalId(companyInternalId);
    }


}

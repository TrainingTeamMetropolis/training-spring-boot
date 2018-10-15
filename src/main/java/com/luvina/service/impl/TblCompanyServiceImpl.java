package com.luvina.service.impl;

import com.luvina.entities.TblCompany;
import com.luvina.repository.TblCompanyRepository;
import com.luvina.service.TblCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TblCompanyServiceImpl implements TblCompanyService {
	
	@Autowired
	TblCompanyRepository tblCompanyRepository;
	
	
	/**
	 * Find all data {@Link TblCompany} and order company name asc
	 * @return List of {@Link TblCompany} List data
	 */
	@Override
	public List<TblCompany> findAllByOrderByCompanyNameAsc() {
		return tblCompanyRepository.findAllByOrderByCompanyNameAsc();
	}
	
	/**
	 * Find data {@Link TblCompany} by company internal id
	 * @param companyInternalId
	 * @return {@Link TblCompany} data company
	 */
	@Override
	public TblCompany findByCompanyInternalId(int companyInternalId) {
		return tblCompanyRepository.findByCompanyInternalId(companyInternalId);
	}
	
	/**
	 * Find data {@Link TblCompany} by company name
	 * @param companyName
	 * @return boolean true when is exists company name
	 */
	@Override
	public boolean isExistsCompanyName(String companyName) {
	    if (tblCompanyRepository.findByCompanyName(companyName) != null) {
            return true;
        }
		return false;
	}
}

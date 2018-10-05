package com.luvina.service.impl;

import com.luvina.entities.TblCompany;
import com.luvina.repository.ICompanyRepository;
import com.luvina.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    ICompanyRepository iCompanyRepository;
    @Override
    public List<TblCompany> findAllDataTblCompanyAndOrder() {
        return iCompanyRepository.findAllByOrderByNameCompanyAsc();
    }

    @Override
    public TblCompany findCompanyById(int idCompany) {
        return null;
    }
}

package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.service.ITblCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoadAjaxCompanyFormController {
	
	@Autowired
	private ITblCompanyService iTblCompanyService;
	
	
	@RequestMapping(value = "/loadCompany")
	public TblCompany loadAjaxCompanyForm(@RequestParam Map<String, String> requestParam) {
		int companyInternalId = 0;
		if (requestParam.get("companyInternalId") != null) {
			companyInternalId = Integer.parseInt(requestParam.get("companyInternalId"));
		}
		TblCompany tblCompany = iTblCompanyService.findByCompanyInternalId(companyInternalId);
		return tblCompany;
	}
}

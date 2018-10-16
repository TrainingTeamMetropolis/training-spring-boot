package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.service.TblCompanyService;
import com.luvina.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoadAjaxFormController {
	
	@Autowired
	private TblCompanyService tblCompanyService;

	@Autowired
	Common common;



	/**
	 * load ajax for form inner
	 * @param requestParam param url
	 * @return data off {@Link TblCompany}
	 */
	@RequestMapping(value = "/loadCompany")
	public TblCompany loadAjaxCompanyForm(@RequestParam Map<String, String> requestParam) {
		int companyInternalId = 0;
		if (requestParam.get("companyInternalId") != null) {
			companyInternalId = Integer.parseInt(requestParam.get("companyInternalId"));
		}
		TblCompany tblCompany = tblCompanyService.findByCompanyInternalId(companyInternalId);
		return tblCompany;
	}

    /**
     * load ajax format name when has character special
     * @param name
     * @return String after handle
     */
    @RequestMapping(value="/formatName", method = RequestMethod.GET)
    @ResponseBody
    public String formatName(@RequestParam(value="name", defaultValue="") String name){
        return common.handleString(name);
    }
}

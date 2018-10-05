package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblUser;
import com.luvina.form.SearchForm;
import com.luvina.service.ITblCompanyService;
import com.luvina.service.ITblUserService;
import com.luvina.util.Common;
import com.luvina.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class DashBroadController {
	
	@Autowired
	private ITblCompanyService iTblCompanyService;
	
	@Autowired
	private ITblUserService iTblUserService;
	
	
	@RequestMapping(value = "/dashboard", method = GET)
	public ModelAndView dashboard(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("searchForm") SearchForm searchForm, HttpSession session) {
		showDataUser(modelAndView, requestParam, searchForm, session);
		return modelAndView;
	}
	
	@RequestMapping(value = "/dashboard", method = POST)
	public ModelAndView showDataUser(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("searchForm") SearchForm searchForm, HttpSession session) {
		int offset = 0;
		int limit = Constant.LIMIT;
		int companyInternalId = 0;
		int endRange = 0;
		Integer currentPage = 0;
		String typeSort = "ASC";
		String userFullName = "";
		String insuranceNumber = "";
		String placeOfRegister = "";
		
		companyInternalId = searchForm.getSearchByIdCompany();
		
		if (searchForm.getSearchByNameUser() != null) {
			userFullName = searchForm.getSearchByNameUser();
		}
		if (searchForm.getSearchByInsuranceNumber() != null) {
			insuranceNumber = searchForm.getSearchByInsuranceNumber();
		}
		if (searchForm.getSearchByPlaceOfRegister() != null) {
			placeOfRegister = searchForm.getSearchByPlaceOfRegister();
		}
		if (requestParam.get("type_sort") != null) {
			typeSort = requestParam.get("type_sort");
		}
		if (requestParam.get("current_page") != null) {
			currentPage = Integer.parseInt(requestParam.get("current_page"));
		}
		// Anti SQL Injection for order
		if (typeSort.equals("ASC")) {
			typeSort = "ASC";
		} else {
			typeSort = "DESC";
		}
		Integer totalRecord = iTblUserService.findTotalRecords(offset, limit, typeSort, companyInternalId, userFullName,
				insuranceNumber, placeOfRegister);
		List<TblCompany> tblCompanyList = iTblCompanyService.findAllDataTblCompanyAndOrder();
		List<TblUser> tblUserList = iTblUserService.findAndSearchListData(offset, limit, typeSort, companyInternalId,
				userFullName, insuranceNumber, placeOfRegister);
		List<Integer> listPaging = Common.getListPaging(totalRecord, currentPage);
		if (listPaging.size() != 0) {
			endRange = listPaging.get(listPaging.size() - 1);
		}
		modelAndView.addObject("tblCompanyList", tblCompanyList);
		modelAndView.addObject("listPaging", listPaging);
		modelAndView.addObject("tblUserList", tblUserList);
		modelAndView.addObject("endRange", endRange);
		modelAndView.setViewName("dashboard");
		return modelAndView;
	}
}

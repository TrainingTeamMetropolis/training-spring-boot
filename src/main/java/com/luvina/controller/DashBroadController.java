package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblUser;
import com.luvina.form.SearchForm;
import com.luvina.service.ITblCompanyService;
import com.luvina.service.ITblUserService;
import com.luvina.util.Common;
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
		int limit = Common.getLimit();
		int companyInternalId = 0;
		int endRange = 0;
		int totalPage = 0;
		long id;
		Integer currentPage = 0;
		String typeSort = "ASC";
		String userFullName = "";
		String insuranceNumber = "";
		String placeOfRegister = "";
		String searchFormId = "";
		List<TblCompany> tblCompanyList = iTblCompanyService.findAllByOrderByCompanyNameAsc();
		if (requestParam.get("searchFormId") != null) {
			searchFormId = requestParam.get("searchFormId");
		} else {
			id = Common.getMiniSecondRandom();
			searchFormId = Long.toString(id);
		}
		companyInternalId = searchForm.getSearchByCompanyInternalId();
		if (companyInternalId == 0) {
			companyInternalId = tblCompanyList.get(0).getCompanyInternalId();
		}
		
		if (Common.isNullOrEmpty(searchForm.getSearchByUserFullName()) == false) {
			userFullName = searchForm.getSearchByUserFullName();
		}
		if (Common.isNullOrEmpty(searchForm.getSearchByInsuranceNumber()) == false) {
			insuranceNumber = searchForm.getSearchByInsuranceNumber();
		}
		if (Common.isNullOrEmpty(searchForm.getSearchByPlaceOfRegister()) == false) {
			placeOfRegister = searchForm.getSearchByPlaceOfRegister();
		}
		if (Common.isNullOrEmpty(requestParam.get("typeSort")) == false) {
			typeSort = requestParam.get("typeSort");
		}
		if (Common.isNullOrEmpty(requestParam.get("page")) == false) {
			currentPage = Integer.parseInt(requestParam.get("page"));
		}
		if (session.getAttribute(searchFormId) != null) {
			SearchForm form = (SearchForm) session.getAttribute(searchFormId);
			session.removeAttribute(searchFormId);
			userFullName = form.getSearchByUserFullName();
			insuranceNumber = form.getSearchByInsuranceNumber();
			placeOfRegister = form.getSearchByPlaceOfRegister();
			companyInternalId = form.getSearchByCompanyInternalId();
		}
		if (currentPage == 0) {
			currentPage = 1;
		}
		String userFullNameEscape = Common.escapeInjection(userFullName);
		String insuranceNumberEscape = Common.escapeInjection(insuranceNumber);
		String placeOfRegisterEscape = Common.escapeInjection(placeOfRegister);
		typeSort = Common.handleSortType(typeSort);
		
		Integer totalRecord =
				iTblUserService.findTotalRecords(offset, limit, typeSort, companyInternalId, userFullNameEscape,
						insuranceNumberEscape, placeOfRegisterEscape);
		List<Integer> listPaging = Common.getListPaging(totalRecord, currentPage);
		if (listPaging.size() != 0) {
			endRange = listPaging.get(listPaging.size() - 1);
		}
		totalPage = Common.getTotalPage(totalRecord, limit);
		offset = Common.getOffsetPaging(currentPage, limit);
		
		List<TblUser> tblUserList = iTblUserService.findAndSearchListData(offset, limit, typeSort, companyInternalId,
				userFullNameEscape, insuranceNumberEscape, placeOfRegisterEscape);
		session.setAttribute("typeSort", typeSort);
		modelAndView.addObject("tblCompanyList", tblCompanyList);
		modelAndView.addObject("listPaging", listPaging);
		modelAndView.addObject("tblUserList", tblUserList);
		modelAndView.addObject("endRange", endRange);
		modelAndView.addObject("totalPage", totalPage);
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("searchFormId", searchFormId);
		
		searchForm.setSearchByCompanyInternalId(companyInternalId);
		searchForm.setSearchByInsuranceNumber(insuranceNumber);
		searchForm.setSearchByUserFullName(userFullName);
		searchForm.setSearchByPlaceOfRegister(placeOfRegister);
		searchForm.setTypeSort(typeSort);
		searchForm.setPage(currentPage);
		session.setAttribute(searchFormId, searchForm);

		modelAndView.setViewName("dashboard");
		return modelAndView;
	}
}

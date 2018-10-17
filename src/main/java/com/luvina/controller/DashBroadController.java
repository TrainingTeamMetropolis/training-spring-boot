package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblUser;
import com.luvina.form.SearchForm;
import com.luvina.service.TblCompanyService;
import com.luvina.service.TblUserService;
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
	private TblCompanyService tblCompanyService;
	
	@Autowired
	private TblUserService tblUserService;
	
	
	/**
	 * method get handle param search when load url dashboard
	 * @param modelAndView model and view
	 * @param requestParam param get field
	 * @param searchForm object form
	 * @param session session
	 * @return modelAndView
	 */
	@RequestMapping(value = "/dashboard", method = GET)
	public ModelAndView dashboard(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("searchForm") SearchForm searchForm, HttpSession session) {
		showDataUser(modelAndView, requestParam, searchForm, session);
		return modelAndView;
	}
	
	/**
	 * method post handle param search when load url dashboard
	 * @param modelAndView model and view
	 * @param requestParam param get field
	 * @param searchForm object form
	 * @param session session
	 * @return modelAndView
	 */
	@RequestMapping(value = "/dashboard", method = POST)
	public ModelAndView showDataUser(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("searchForm") SearchForm searchForm, HttpSession session) {
		List<TblCompany> tblCompanyList = tblCompanyService.findAllByOrderByCompanyNameAsc();
		Map mapData = tblUserService.findAndSearchListData(searchForm, requestParam, session);
		String searchFormId = (String) mapData.get("searchFormId");

		modelAndView.addObject("tblCompanyList", tblCompanyList);
		modelAndView.addObject("listPaging", mapData.get("listPaging"));
		modelAndView.addObject("tblUserList", mapData.get("tblUserList"));
		modelAndView.addObject("endRange", mapData.get("endRange"));
		modelAndView.addObject("totalPage", mapData.get("totalPage"));
		modelAndView.addObject("currentPage", mapData.get("currentPage"));
		modelAndView.addObject("searchFormId", searchFormId);

        session.setAttribute("typeSort", mapData.get("typeSort"));
		session.setAttribute(searchFormId, mapData.get("searchForm"));
		modelAndView.setViewName("dashboard");
		return modelAndView;
	}
}

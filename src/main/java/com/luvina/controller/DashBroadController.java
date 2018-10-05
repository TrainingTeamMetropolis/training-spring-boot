package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.form.SearchForm;
import com.luvina.service.ICompanyService;
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
	private ICompanyService iCompanyService;
	
	
	@RequestMapping(value = "/dashboard", method = GET)
	public ModelAndView dashboard(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("searchForm") SearchForm searchForm, HttpSession session) {
		showUser(modelAndView, requestParam, searchForm, session);
		return modelAndView;
	}
	
	@RequestMapping(value = "/showUser", method = POST)
	public ModelAndView showUser(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("searchForm") SearchForm searchForm, HttpSession session) {
		List<TblCompany> tblCompanyList = iCompanyService.findAllDataTblCompanyAndOrder();
		modelAndView.addObject("tblCompanyList", tblCompanyList);
		modelAndView.setViewName("dashboard");
		return modelAndView;
	}
}

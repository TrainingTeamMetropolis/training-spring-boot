package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.form.RegisterInsuranceForm;
import com.luvina.service.ITblCompanyService;
import com.luvina.service.ITblUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class RegisterInsuranceController {
	
	@Autowired
	private ITblCompanyService iTblCompanyService;
	
	private ITblUserService iTblUserService;
	
	
	@RequestMapping(value = "/register", method = GET)
	public ModelAndView register(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("registerInsuranceForm") RegisterInsuranceForm registerInsuranceForm, BindingResult result,
			HttpSession session) {
		List<TblCompany> tblCompanyList = iTblCompanyService.findAllByOrderByCompanyNameAsc();
		modelAndView.addObject("tblCompanyList", tblCompanyList);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
}

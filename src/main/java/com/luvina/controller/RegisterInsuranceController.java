package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.form.RegisterInsuranceForm;
import com.luvina.service.ITblCompanyService;
import com.luvina.service.ITblUserService;
import com.luvina.validation.ValidationRegisterInsuranceForm;
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
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class RegisterInsuranceController {
	
	@Autowired
	private ITblCompanyService iTblCompanyService;
	
	@Autowired
	private ITblUserService iTblUserService;
	
	@Autowired
	private ValidationRegisterInsuranceForm validationRegisterInsuranceForm;
	
	
	@RequestMapping(value = "/register", method = GET)
	public ModelAndView registration(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("registerInsuranceForm") RegisterInsuranceForm registerInsuranceForm, BindingResult result,
			HttpSession session) {
		setDataToView(modelAndView);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = POST)
	public ModelAndView register(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("registerInsuranceForm") RegisterInsuranceForm registerInsuranceForm, BindingResult result,
			HttpSession session) {
		String searchFormId = "";
		setDataToView(modelAndView);
		validationRegisterInsuranceForm.validate(registerInsuranceForm, result);
		if (requestParam.get("searchFormId") != null) {
			searchFormId = requestParam.get("searchFormId");
		}
		if (result.hasErrors()) {
			modelAndView.addObject("searchFormId", searchFormId);
            modelAndView.setViewName("register");
		} else {
			iTblUserService.insertInformationInsuranceOfUser(registerInsuranceForm);
			modelAndView.setViewName("redirect:dashboard");
		}
		return modelAndView;
	}
	
	private void setDataToView(ModelAndView modelAndView) {
		List<TblCompany> tblCompanyList = iTblCompanyService.findAllByOrderByCompanyNameAsc();
		modelAndView.addObject("tblCompanyList", tblCompanyList);
	}
}

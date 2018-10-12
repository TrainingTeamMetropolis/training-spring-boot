package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.form.RegisterForm;
import com.luvina.service.ITblCompanyService;
import com.luvina.service.ITblUserService;
import com.luvina.util.Common;
import com.luvina.validation.ValidationRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class RegisterController {
	
	@Autowired
	private ITblCompanyService iTblCompanyService;
	
	@Autowired
	private ITblUserService iTblUserService;
	
	@Autowired
	private ValidationRegisterForm validationregisterForm;

    /**
     * registration data to data base method get
     * @param modelAndView modelAndView
     * @param requestParam param request
     * @param registerForm form get data
     * @return modelAndView data set to view after handle
     */
	@RequestMapping(value = "/register", method = GET)
	public ModelAndView registration(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("registerForm") RegisterForm registerForm) {
		setDataToView(modelAndView);
		String searchFormId = "";
		if (Common.isNullOrEmpty(requestParam.get("searchFormId")) == false) {
			searchFormId = requestParam.get("searchFormId");
		}
		modelAndView.addObject("searchFormId", searchFormId);
		modelAndView.setViewName("register");
		return modelAndView;
	}

    /**
     * registration data to data base method post
     * @param modelAndView modelAndView
     * @param requestParam param request
     * @param registerForm form get data
     * @return modelAndView data set to view after handle
     */
	@RequestMapping(value = "/register", method = POST)
	public ModelAndView register(ModelAndView modelAndView, @RequestParam Map<String, String> requestParam,
			@ModelAttribute("registerForm") RegisterForm registerForm,
			BindingResult result) {
		String searchFormId = "";
		setDataToView(modelAndView);
		validationregisterForm.validate(registerForm, result);
		if (Common.isNullOrEmpty(requestParam.get("searchFormId")) == false) {
			searchFormId = requestParam.get("searchFormId");
		}
		if (result.hasErrors()) {
			modelAndView.addObject("searchFormId", searchFormId);
			modelAndView.setViewName("register");
		} else {
			iTblUserService.insertInformationInsuranceOfUser(registerForm);
			modelAndView.setViewName("redirect:dashboard");
		}
		return modelAndView;
	}

    /**
     * set data to view
     * <p>set data after load url register<p/>
     * @param modelAndView
     */
	private void setDataToView(ModelAndView modelAndView) {
		List<TblCompany> tblCompanyList = iTblCompanyService.findAllByOrderByCompanyNameAsc();
		modelAndView.addObject("tblCompanyList", tblCompanyList);
	}
}

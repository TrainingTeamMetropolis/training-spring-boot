package com.luvina.controller;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblUser;
import com.luvina.form.SearchForm;
import com.luvina.service.TblCompanyService;
import com.luvina.service.TblUserService;
import com.luvina.util.CSVFile;
import com.luvina.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ExportCSVController {
	
	@Autowired
	private TblUserService tblUserService;
	
	@Autowired
	private TblCompanyService tblCompanyService;
	
	@Autowired
	private CSVFile csvFile;

    /**
     *  handle export csv file after search
     * @param requestParam param get from screen
     * @param session session
     * @param response response
     */
	@RequestMapping(value = "exportCSV", method = RequestMethod.GET)
	public void exportCSVFile(@RequestParam Map<String, String> requestParam, HttpSession session,
			HttpServletResponse response) {
		String searchFormId = "";
		int companyInternalId = 0;
		int limit = 0;
		int offset = 0;
		String typeSort = "ASC";
		String userFullName = "";
		String insuranceNumber = "";
		String placeOfRegister = "";
		
		List<TblUser> tblUserList;
		TblCompany tblCompany;
		if (Common.isNullOrEmpty(requestParam.get("searchFormId")) == false) {
			searchFormId = requestParam.get("searchFormId");
			SearchForm searchForm = (SearchForm) session.getAttribute(searchFormId);
            companyInternalId = searchForm.getSearchByCompanyInternalId();
			userFullName = searchForm.getSearchByUserFullName();
			insuranceNumber = searchForm.getSearchByInsuranceNumber();
			placeOfRegister = searchForm.getSearchByPlaceOfRegister();
		}
		String userFullNameEscape = Common.escapeInjection(userFullName.trim());
		String insuranceNumberEscape = Common.escapeInjection(insuranceNumber.trim());
		String placeOfRegisterEscape = Common.escapeInjection(placeOfRegister.trim());
		tblCompany = tblCompanyService.findByCompanyInternalId(companyInternalId);
		tblUserList = tblUserService.findAndSearchListData(offset, limit, typeSort, companyInternalId, userFullNameEscape,
						insuranceNumberEscape, placeOfRegisterEscape);
		String header = csvFile.createHeader();
		String body = csvFile.createBody(tblUserList);
		String infoCompany = csvFile.createCompany(tblCompany);
		csvFile.exportCSV(response, header, body, infoCompany);
	}
}

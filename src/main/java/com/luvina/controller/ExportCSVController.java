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
		int companyInternalId = 0;
		SearchForm searchForm = null;
		if (Common.isNullOrEmpty(requestParam.get("searchFormId")) == false) {
            String searchFormId  = requestParam.get("searchFormId");
			searchForm = (SearchForm) session.getAttribute(searchFormId);
            companyInternalId = searchForm.getSearchByCompanyInternalId();
		}
        TblCompany tblCompany = tblCompanyService.findByCompanyInternalId(companyInternalId);
        Map map = tblUserService.findAndSearchListData(searchForm, requestParam, session);
		String header = csvFile.createHeader();
		String body = csvFile.createBody((List<TblUser>) map.get("tblUserList"));
		String infoCompany = csvFile.createCompany(tblCompany);
		csvFile.exportCSV(response, header, body, infoCompany);
	}
}

package com.luvina.util;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
@Component
public class CSVFile {
	
	@Autowired
	private Environment environment;
	
	public String createCompany(TblCompany tblCompany) {
		StringBuilder informationCompany = new StringBuilder();
		informationCompany.append(environment.getProperty("nameCompany") + "," + tblCompany.getCompanyName() + "\n");
		informationCompany.append(
				environment.getProperty("address") + "," + Common.handleCSVFile(tblCompany.getAddressCompany()) + "\n");
		informationCompany.append(environment.getProperty("email") + "," + tblCompany.getEmailCompany() + "\n");
		informationCompany.append(environment.getProperty("phone") + "," + "\t" + tblCompany.getPhoneCompany() + "\n");
		return informationCompany.toString();
	}
	
	public String createHeader() {
		return environment.getProperty("header");
	}
	
	public String createBody(List<TblUser> tblUsers) {
		
		StringBuilder body = new StringBuilder();
		for (TblUser tblUser : tblUsers) {
			body.append(tblUser.getUserFullName() + ", ");
			if (tblUser.getUserSexDivision().equals("1")) {
				body.append("Nam" + ", ");
			} else {
				body.append("Nữ" + ", ");
			}
			if (tblUser.getBirthDate() != null) {
				body.append(Common.convertDateToString(tblUser.getBirthDate()) + ", ");
			} else {
				body.append(", ");
			}
			body.append("\t" + tblUser.getTblInsurance().getInsuranceNumber() + ", ");
			body.append(Common.convertDateToString(tblUser.getTblInsurance().getInsuranceStartDate()) + ", ");
			body.append(Common.convertDateToString(tblUser.getTblInsurance().getInsuranceEndDate()) + ", ");
			body.append(tblUser.getTblInsurance().getPlaceOfRegister() + ", " + "\n ");
		}
		
		return body.toString();
		
	}
	
	public void exportCSV(HttpServletResponse response, String header, String body, String infoCompany) {
		StringBuilder content = new StringBuilder();
		
		response.setHeader("Content-type", "application/csv");
		response.setHeader("Content-disposition", "inline; filename=listUser.csv");
		content.append(environment.getProperty("title") + " " + "\n");
		content.append(infoCompany + "\n");
		content.append(header + "\n");
		content.append(body);
		try {
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(239);
			outputStream.write(187);
			outputStream.write(191);
			outputStream.write(content.toString().getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

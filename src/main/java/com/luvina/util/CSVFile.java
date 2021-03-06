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

	@Autowired
	Common common;
	/**
	 * create data {@Link TblCompany} csv file
	 * @param tblCompany
	 * @return String data {@Link TblCompany}
	 */
	public String createCompany(TblCompany tblCompany) {
		StringBuilder informationCompany = new StringBuilder();
		informationCompany.append(environment.getProperty("name_company") + "," + tblCompany.getCompanyName() + "\n");
		informationCompany.append(
				environment.getProperty("address") + "," + common.handleCSVFile(tblCompany.getAddressCompany())  + "\n");
		informationCompany.append(environment.getProperty("email") + "," + tblCompany.getEmailCompany() + "\n");
		informationCompany.append(environment.getProperty("phone") + "," + "\t" + tblCompany.getPhoneCompany() + "\n");
		return informationCompany.toString();
	}

	/**
	 * create deader get from property file
	 * @return String Header
	 */
	public String createHeader() {
		return environment.getProperty("header");
	}

	/**
	 * create body List of {@Link TblUser}
	 * @param tblUsers {@Link TblUser}
	 * @return List of {@Link TblUser}
	 */
	public String createBody(List<TblUser> tblUsers) {
		StringBuilder body = new StringBuilder();
		if (tblUsers.size() != 0) {
			for (TblUser tblUser : tblUsers) {
				body.append(tblUser.getUserFullName() + ", ");
				if (tblUser.getUserSexDivision().equals("01")) {
					body.append("Nam" + ", ");
				} else {
					body.append("Nữ" + ", ");
				}
				if (tblUser.getBirthDate() != null) {
					body.append(common.convertDateToString(tblUser.getBirthDate()) + ", ");
				} else {
					body.append(", ");
				}
				body.append("\t" + tblUser.getTblInsurance().getInsuranceNumber() + ", ");
				body.append(common.convertDateToString(tblUser.getTblInsurance().getInsuranceStartDate()) + ", ");
				body.append(common.convertDateToString(tblUser.getTblInsurance().getInsuranceEndDate()) + ", ");
				body.append(tblUser.getTblInsurance().getPlaceOfRegister() + ", " + "\n ");
			}
		} else {
			body.append("");
		}
		return body.toString();
	}

	/**
	 * function export csv file
	 * <p>add header file is csv file<p/>
	 * <p>append data and convert to string<p/>
	 * @param response response data string
	 * @param header header data string
	 * @param body body data string
	 * @param infoCompany company data string
	 */
	public void exportCSV(HttpServletResponse response, String header, String body, String infoCompany) {
		StringBuilder content = new StringBuilder();
		response.setHeader("Content-type", "application/csv");
		response.setHeader("Content-disposition", "inline; filename=insuranceList.csv");
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

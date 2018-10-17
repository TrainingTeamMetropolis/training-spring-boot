package com.luvina.util;

import com.luvina.entities.TblCompany;
import com.luvina.entities.TblUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CSVFileTest {
	
	@InjectMocks
	CSVFile sut;
	
	@Mock
	private Environment environment;
	
	@Mock
	Common common;
	
	
	@Test
	public void testCreateCompany() {
		// set up
		TblCompany tblCompany = new TblCompany();
		tblCompany.setCompanyInternalId(1);
		tblCompany.setPhoneCompany("22-3333-4444");
		tblCompany.setEmailCompany("usa@usa.com");
		tblCompany.setCompanyName("Dell Coporation");
		tblCompany.setAddressCompany("USA, ff");
		
		String dataCompanyExpected = "Tên công ty,Dell Coporation" + "\n" +
				"Địa chỉ,\"USA, ff\"" + "\n" +
				"Email,usa@usa.com" + "\n" +
				"Số điện thoại,\t22-3333-4444" + "\n";
		when(environment.getProperty("name_company")).thenReturn("Tên công ty");
		when(environment.getProperty("address")).thenReturn("Địa chỉ");
		when(environment.getProperty("email")).thenReturn("Email");
		when(environment.getProperty("phone")).thenReturn("Số điện thoại");
		when(common.handleCSVFile(tblCompany.getAddressCompany()))
			.thenReturn("\"" + tblCompany.getAddressCompany() + "\"");
		// exercise
		String dataCompanyActual = sut.createCompany(tblCompany);
		
		// verify
		Assert.assertEquals(dataCompanyExpected, dataCompanyActual);
	}
	
	@Test
	public void testCreateHeader() {
		// set up
		String headerExpected =
				"Họ và tên, Giới tính, Ngày sinh, Mã số thẻ bảo hiểm, Ngày bắt đầu, Ngày kết thúc, Nơi đăng ký KCB";
		when(environment.getProperty("header")).thenReturn(headerExpected);
		
		// exercise
		String headerActual = sut.createHeader();
		
		// verify
		Assert.assertEquals(headerExpected, headerActual);
	}

	@Test
	public void testCreateBody() {
		// set up
		String headerExpected = "";
		List<TblUser> tblUsers = new ArrayList<>();

		// exercise
		String bodyActual = sut.createBody(tblUsers);

		// verify
		Assert.assertEquals(headerExpected, bodyActual);


	}
	
}

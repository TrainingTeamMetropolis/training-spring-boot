package com.luvina.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tbl_company")
public class TblCompany implements Serializable {
	
	@Id
	@Column(name = "company_internal_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCompany;
	
	@Column(name = "company_name")
	private String nameCompany;
	
	@Column(name = "address")
	private String addressCompany;
	
	@Column(name = "email")
	private String emailCompany;
	
	@Column(name = "telephone")
	private String phoneCompany;
	
	
	public int getIdCompany() {
		return idCompany;
	}
	
	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}
	
	public String getNameCompany() {
		return nameCompany;
	}
	
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	
	public String getAddressCompany() {
		return addressCompany;
	}
	
	public void setAddressCompany(String addressCompany) {
		this.addressCompany = addressCompany;
	}
	
	public String getEmailCompany() {
		return emailCompany;
	}
	
	public void setEmailCompany(String emailCompany) {
		this.emailCompany = emailCompany;
	}
	
	public String getPhoneCompany() {
		return phoneCompany;
	}
	
	public void setPhoneCompany(String phoneCompany) {
		this.phoneCompany = phoneCompany;
	}
}

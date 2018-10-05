package com.luvina.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_user")
public class TblUser implements Serializable {
	
	@Id
	@Column(name = "user_internal_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String passWord;
	
	@Column(name = "company_internal_id")
	private int companyInternalId;
	
	@Column(name = "insurance_internal_id")
	private int insuranceInternalId;
	
	@Column(name = "user_full_name")
	private String userFullName;
	
	@Column(name = "user_sex_division")
	private String sexDivision;
	
	@Column(name = "birthdate")
	private Date birthDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "insurance_internal_id", insertable = false, updatable = false)
	private TblInsurance tblInsurance;
	
	@ManyToOne
	@JoinColumn(name = "company_internal_id", insertable = false, updatable = false)
	private TblCompany tblCompany;
	
	public TblUser() {
		
	}
	
	public TblUser(String userName, String passWord, int companyInternalId, int insuranceInternalId,
			String userFullName, String sexDivision, Date birthDate, TblInsurance tblInsurance, TblCompany tblCompany) {
		this.userName = userName;
		this.passWord = passWord;
		this.companyInternalId = companyInternalId;
		this.insuranceInternalId = insuranceInternalId;
		this.userFullName = userFullName;
		this.sexDivision = sexDivision;
		this.birthDate = birthDate;
		this.tblInsurance = tblInsurance;
		this.tblCompany = tblCompany;
	}
	
	public int getCompanyInternalId() {
		return companyInternalId;
	}
	
	public void setCompanyInternalId(int companyInternalId) {
		this.companyInternalId = companyInternalId;
	}
	
	public int getInsuranceInternalId() {
		return insuranceInternalId;
	}
	
	public void setInsuranceInternalId(int insuranceInternalId) {
		this.insuranceInternalId = insuranceInternalId;
	}
	
	public String getUserFullName() {
		return userFullName;
	}
	
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getSexDivision() {
		return sexDivision;
	}
	
	public void setSexDivision(String sexDivision) {
		this.sexDivision = sexDivision;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public TblCompany getTblCompany() {
		return tblCompany;
	}
	
	public TblInsurance getTblInsurance() {
		return tblInsurance;
	}
	
	public void setTblInsurance(TblInsurance tblInsurance) {
		this.tblInsurance = tblInsurance;
	}
	
	public void setTblCompany(TblCompany tblCompany) {
		this.tblCompany = tblCompany;
	}
}

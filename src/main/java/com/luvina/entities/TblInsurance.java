package com.luvina.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "tbl_insurance")
public class TblInsurance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "insurance_internal_id")
	@JoinColumn(name = "insurance_internal_id")
	private int insuranceInternalId;
	
	@Column(name = "insurance_number")
	private String insuranceNumber;
	
	@Column(name = "insurance_start_date")
	private Date insuranceStartDate;
	
	@Column(name = "insurance_end_date")
	private Date insuranceEndDate;
	
	@Column(name = "place_of_register")
	private String placeOfRegister;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private TblUser tblUser;
	
	
	public TblInsurance() {
		
	}
	
	public TblInsurance(int insuranceInternalId, String insuranceNumber, Date insuranceStartDate, Date insuranceEndDate,
			String placeOfRegister,
			TblUser tblUser) {
		this.insuranceInternalId = insuranceInternalId;
		this.insuranceNumber = insuranceNumber;
		this.insuranceStartDate = insuranceStartDate;
		this.insuranceEndDate = insuranceEndDate;
		this.placeOfRegister = placeOfRegister;
		this.tblUser = tblUser;
	}

	public int getInsuranceInternalId() {
		return insuranceInternalId;
	}

	public void setInsuranceInternalId(int insuranceInternalId) {
		this.insuranceInternalId = insuranceInternalId;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	
	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}
	
	public void setInsuranceStartDate(Date insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}
	
	public Date getInsuranceEndDate() {
		return insuranceEndDate;
	}
	
	public void setInsuranceEndDate(Date insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}
	
	public String getPlaceOfRegister() {
		return placeOfRegister;
	}
	
	public void setPlaceOfRegister(String placeOfRegister) {
		this.placeOfRegister = placeOfRegister;
	}
	
	public TblUser getTblUser() {
		return tblUser;
	}
	
	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}
}

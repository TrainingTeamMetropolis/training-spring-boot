package com.luvina.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.sql.Date;

public class TblInsurance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "insurance_internal_id")
	@JoinColumn(name = "insurance_internal_id")
	private int idInsurrance;
	
	@Column(name = "insurance_number")
	private String numberInsurance;
	
	@Column(name = "insurance_start_date")
	private Date startDate;
	
	@Column(name = "insurance_end_date")
	private Date endDate;
	
	@Column(name = "place_of_register")
	private String place;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private TblUser tblUser;
	
	public TblInsurance() {
		
	}
	
	public TblInsurance(int idInsurrance, String numberInsurance, Date startDate, Date endDate, String place,
			TblUser tblUser) {
		this.idInsurrance = idInsurrance;
		this.numberInsurance = numberInsurance;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
		this.tblUser = tblUser;
	}
	
	public int getIdInsurrance() {
		return idInsurrance;
	}
	
	public void setIdInsurrance(int idInsurrance) {
		this.idInsurrance = idInsurrance;
	}
	
	public String getNumberInsurance() {
		return numberInsurance;
	}
	
	public void setNumberInsurance(String numberInsurance) {
		this.numberInsurance = numberInsurance;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public TblUser getTblUser() {
		return tblUser;
	}
	
	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}
}

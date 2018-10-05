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
	private int idInsurrance;
	
	@Column(name = "insurance_number")
	private String insuranceNumber;
	
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
	
	public TblInsurance(int idInsurrance, String insuranceNumber, Date startDate, Date endDate, String place,
			TblUser tblUser) {
		this.idInsurrance = idInsurrance;
		this.insuranceNumber = insuranceNumber;
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
	
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
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

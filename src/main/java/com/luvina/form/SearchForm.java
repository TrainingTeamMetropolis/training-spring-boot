package com.luvina.form;

public class SearchForm {
	
	private Integer searchByCompanyInternalId;
	
	private String searchByUserFullName;
	
	private String searchByInsuranceNumber;
	
	private String searchByPlaceOfRegister;
	
	private String typeSort;
	
	private int page;

	public Integer getSearchByCompanyInternalId() {
		return searchByCompanyInternalId;
	}

	public void setSearchByCompanyInternalId(Integer searchByCompanyInternalId) {
		this.searchByCompanyInternalId = searchByCompanyInternalId;
	}

	public String getSearchByUserFullName() {
		return searchByUserFullName;
	}
	
	public void setSearchByUserFullName(String searchByUserFullName) {
		this.searchByUserFullName = searchByUserFullName;
	}
	
	public String getSearchByInsuranceNumber() {
		return searchByInsuranceNumber;
	}
	
	public void setSearchByInsuranceNumber(String searchByInsuranceNumber) {
		this.searchByInsuranceNumber = searchByInsuranceNumber;
	}
	
	public String getSearchByPlaceOfRegister() {
		return searchByPlaceOfRegister;
	}
	
	public void setSearchByPlaceOfRegister(String searchByPlaceOfRegister) {
		this.searchByPlaceOfRegister = searchByPlaceOfRegister;
	}
	
	public String getTypeSort() {
		return typeSort;
	}
	
	public void setTypeSort(String typeSort) {
		this.typeSort = typeSort;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
}

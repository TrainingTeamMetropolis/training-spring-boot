package com.luvina.form;

public class SearchForm {
	
	private int searchByIdCompany;
	
	private String searchByNameUser;
	
	private String searchByInsurranceNumber;
	
	private String searchByPlaceOfRegister;
	
	private String typeSort;
	
	private int page;
	
	
	public SearchForm() {
	}
	
	public SearchForm(int searchByIdCompany, String searchByNameUser, String searchByInsurranceNumber,
			String searchByPlaceOfRegister, String typeSort, int page) {
		this.searchByIdCompany = searchByIdCompany;
		this.searchByNameUser = searchByNameUser;
		this.searchByInsurranceNumber = searchByInsurranceNumber;
		this.searchByPlaceOfRegister = searchByPlaceOfRegister;
		this.typeSort = typeSort;
		this.page = page;
	}
	
	public int getSearchByIdCompany() {
		return searchByIdCompany;
	}
	
	public void setSearchByIdCompany(int searchByIdCompany) {
		this.searchByIdCompany = searchByIdCompany;
	}
	
	public String getSearchByNameUser() {
		return searchByNameUser;
	}
	
	public void setSearchByNameUser(String searchByNameUser) {
		this.searchByNameUser = searchByNameUser;
	}
	
	public String getSearchByInsurranceNumber() {
		return searchByInsurranceNumber;
	}
	
	public void setSearchByInsurranceNumber(String searchByInsurranceNumber) {
		this.searchByInsurranceNumber = searchByInsurranceNumber;
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

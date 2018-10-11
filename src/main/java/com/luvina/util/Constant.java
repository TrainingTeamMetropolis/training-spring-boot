package com.luvina.util;

public class Constant {
    // paging range
	public static final int RANGE_OF_PAGE = 5;
	// paging limit
	public static final int LIMIT = 5;

    public static final String FORMAT_DATE = "dd/MM/yyyy";

	public static final String REGEX_FORMAT_NUMBER_INSURANCE = "^[0-9]{10}$";
	
	public static final String REGEX_FORMAT_DATE = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
	
	public static final String REGEX_FORMAT_EMAIL =
			"^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";
	
	public static final String REGEX_FORMAT_PHONE = "^[0-9]{10}$";
}

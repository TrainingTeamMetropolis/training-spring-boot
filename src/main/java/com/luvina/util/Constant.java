package com.luvina.util;

public class Constant {
    // paging range
	public static final int RANGE_OF_PAGE = 5;
	// paging limit
	public static final int LIMIT = 5;
	// format date common
    public static final String FORMAT_DATE = "dd/MM/yyyy";
	// regex number insurance
	public static final String REGEX_FORMAT_NUMBER_INSURANCE = "^[0-9]{10}$";
	// regex format date
	public static final String REGEX_FORMAT_DATE = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
	// regex format email
	public static final String REGEX_FORMAT_EMAIL =
			"^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";
	// regex format phone
	public static final String REGEX_FORMAT_PHONE_1 = "^[0-9]{2}-[0-9]{4}\\-[0-9]{4}$";
    public static final String REGEX_FORMAT_PHONE_2 = "^[0-9]{3}-[0-9]{3}\\-[0-9]{4}$";
    public static final String REGEX_FORMAT_PHONE_3 = "^[0-9]{3}-[0-9]{4}\\-[0-9]{3}$";
    public static final String REGEX_FORMAT_PHONE_4 = "^[0-9]{4}-[0-9]{2}\\-[0-9]{4}$";

}

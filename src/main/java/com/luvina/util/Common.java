package com.luvina.util;

import java.util.ArrayList;
import java.util.List;

public class Common {
	
	/**
	 * getOffsetPaging
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	public static int getOffsetPaging(int currentPage, int limit) {
		int offset = (currentPage - 1) * limit;
		if (currentPage == 0) {
			offset = 0;
		}
		return offset;
	}
	
	/**
	 * getLimit
	 * @return
	 */
	public static int getLimit() {
		return Constant.LIMIT;
	}
	
	/**
	 *
	 * @return
	 */
	public static int getRange() {
		return Constant.RANGE_OF_PAGE;
	}
	
	/**
	 *
	 * @param totalUser
	 * @param limit
	 * @return
	 */
	public static int getTotalPage(int totalUser, int limit) {
		return (int) Math.ceil((double) totalUser / (double) limit);
	}

	/**
	 *
	 * @param totalUser
	 * @param currentPage
	 * @return
	 */
	public static List<Integer> getListPaging(int totalUser, int currentPage) {
		List<Integer> listPage = new ArrayList<>();
		int limit = getLimit();
		int totalPage = getTotalPage(totalUser, limit);
		int range = getRange();
		int start = currentPage - (range / 2);
		int end = currentPage + (range / 2);
		if (start < 1) {
			end = end + (1 - start);
			start = 1;
		}
		if (end > totalPage) {
			start = start - (end - totalPage);
			end = totalPage;
		}
		if (start < 1) {
			start = 1;
			
		}
		for (int i = start; i < end + 1; i++) {
			listPage.add(i);
		}
		return listPage;
	}
	
}

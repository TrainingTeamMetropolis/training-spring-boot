package com.luvina.repository.customize.impl;

import java.util.List;

import com.luvina.entities.TblUser;
import com.luvina.repository.customize.ITblUserRepositoryCustom;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ITblUserRepositoryCustomImpl implements ITblUserRepositoryCustom {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * search for dashboard screen
	 * <p>query data base and return result<p/>
	 * @param offset offset
	 * @param limit limit
	 * @param typeSort type sort
	 * @param companyInternalId company internal id
	 * @param userFullName user full name
	 * @param insuranceNumber insurance number
	 * @param placeOfRegister place of register
	 * @return List<TblUser> list data tbl_user
	 */
	@Override
	public List<TblUser> findAndSearchListData(int offset, int limit, String typeSort, int companyInternalId,
			String userFullName, String insuranceNumber, String placeOfRegister) {
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append(" FROM TblUser AS u ");
		hqlCommand.append("WHERE companyInternalId= :companyInternalId ");
		
		if (userFullName.length() > 0) {
			hqlCommand.append("AND u.userFullName LIKE :userFullName ");
		}
		if (insuranceNumber.length() > 0) {
			hqlCommand.append("AND u.tblInsurance.insuranceNumber= :insuranceNumber ");
		}
		if (placeOfRegister.length() > 0) {
			hqlCommand.append("AND u.tblInsurance.placeOfRegister LIKE :placeOfRegister ");
		}
		hqlCommand.append("ORDER BY u.userFullName " + typeSort);
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("companyInternalId", companyInternalId);
		
		if (userFullName.length() > 0) {
			query.setParameter("userFullName", "%" + userFullName + "%");
		}
		if (insuranceNumber.length() > 0) {
			query.setParameter("insuranceNumber", insuranceNumber);
		}
		if (placeOfRegister.length() > 0) {
			query.setParameter("placeOfRegister", "%" + placeOfRegister + "%");
		}
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<TblUser> listUser = query.list();
		session.close();
		return listUser;
	}
	/**
	 * get total record for dashboard screen
	 * <p>query data base and return result<p/>
	 * @param offset offset
	 * @param limit limit
	 * @param typeSort type sort
	 * @param companyInternalId company internal id
	 * @param userFullName user full name
	 * @param insuranceNumber insurance number
	 * @param placeOfRegister place of register
	 * @return Integer number of records
	 */
	@Override
	public Integer findTotalRecords(int offset, int limit, String typeSort, int companyInternalId, String userFullName,
			String insuranceNumber, String placeOfRegister) {
		Integer totalUser = 0;
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append(" SELECT COUNT(*) FROM TblUser AS u ");
		hqlCommand.append("WHERE companyInternalId= :companyInternalId ");
		
		if (userFullName.length() > 0) {
			hqlCommand.append("AND u.userFullName LIKE :userFullName ");
		}
		if (insuranceNumber.length() > 0) {
			hqlCommand.append("AND u.tblInsurance.insuranceNumber= :insuranceNumber ");
		}
		if (placeOfRegister.length() > 0) {
			hqlCommand.append("AND u.tblInsurance.placeOfRegister LIKE :placeOfRegister ");
		}
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("companyInternalId", companyInternalId);
		
		if (userFullName.length() > 0) {
			query.setParameter("userFullName", "%" + userFullName + "%");
		}
		if (insuranceNumber.length() > 0) {
			query.setParameter("insuranceNumber", insuranceNumber);
		}
		if (placeOfRegister.length() > 0) {
			query.setParameter("placeOfRegister", "%" + placeOfRegister + "%");
		}
		Long totalUserLong = (Long) (query.uniqueResult());
		totalUser = (int) (long) totalUserLong;
		session.close();
		return totalUser;
	}
}

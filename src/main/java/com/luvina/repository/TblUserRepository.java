package com.luvina.repository;

import com.luvina.entities.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblUserRepository extends JpaRepository<TblUser, Long> {
	/**
	 * Find {@Link TblUser} by userInternalId
	 * @param userInternalId
	 * @return data {@Link TblUser}
	 */
	TblUser findByUserInternalId(int userInternalId);
	
}

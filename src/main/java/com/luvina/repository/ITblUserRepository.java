package com.luvina.repository;

import com.luvina.entities.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITblUserRepository extends JpaRepository<TblUser, Long> {
	
	TblUser findByUserInternalId(int userInternalId);
	
}

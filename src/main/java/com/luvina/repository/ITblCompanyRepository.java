package com.luvina.repository;

import com.luvina.entities.TblCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ITblCompanyRepository extends JpaRepository<TblCompany, Integer> {

    List<TblCompany> findAllByOrderByNameCompanyAsc();
}

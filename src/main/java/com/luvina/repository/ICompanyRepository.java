package com.luvina.repository;

import com.luvina.entities.TblCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICompanyRepository extends JpaRepository<TblCompany, Integer> {
    /**
     *
     * @return
     */
    List<TblCompany> findAllByOrderByNameCompanyAsc();

    /**
     *
     * @param idCompany
     * @return
     */
    TblCompany findTblCompanyByIdCompany(int idCompany);

    /**
     *
     * @param idCompany
     */
    void deleteTblCompanyByIdCompany(int idCompany);

    /**
     *
     * @param nameCompany
     * @return
     */
    TblCompany findTblCompanyByNameCompany(String nameCompany);
}

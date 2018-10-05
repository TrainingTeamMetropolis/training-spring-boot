package com.luvina.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

public class TblUser implements Serializable {
    @Id
    @Column(name = "user_internal_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;

    @Column(name = "company_internal_id")
    private int idCompany;

    @Column(name = "insurance_internal_id")
    private int idInsurrance;

    @Column(name = "user_full_name")
    private String fullnameUser;

    @Column(name = "user_sex_division")
    private String sexDivision;

    @Column(name = "birthdate")
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_internal_id", insertable = false, updatable = false)
    private TblInsurance tblInsurrance;

    @ManyToOne
    @JoinColumn(name = "company_internal_id", insertable = false, updatable = false)
    private TblCompany tblCompany;
}

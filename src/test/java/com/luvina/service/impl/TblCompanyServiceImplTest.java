package com.luvina.service.impl;

import com.luvina.entities.TblCompany;
import com.luvina.repository.TblCompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TblCompanyServiceImplTest {

    @InjectMocks
    TblCompanyServiceImpl sut;

    @Mock
    TblCompanyRepository tblCompanyRepository;

    /**
     * Test find all by order by company name and oder company name asc
     * input
     * output
     *  List data after find from repository
     */
    @Test
    public void testFindAllByOrderByCompanyNameAsc() {
        // set up
        List<TblCompany> tblCompanyList =  new ArrayList<>();
        when(tblCompanyRepository.findAllByOrderByCompanyNameAsc()).thenReturn(tblCompanyList);

        // exercise
        List<TblCompany> tblCompanyListActual = sut.findAllByOrderByCompanyNameAsc();

        //verify
        assertThat(tblCompanyListActual).isEqualTo(tblCompanyList);
        verify(tblCompanyRepository, times(1)).findAllByOrderByCompanyNameAsc();
    }

    /**
     * Test Find By Company Internal Id
     * input
     *  Company Internal Id = anyInt()
     * output
     * TblCompany data model TblCompany
     */
    @Test
    public void testFindByCompanyInternalId1() {
        // set up
        TblCompany tblCompany = new TblCompany();
        when(tblCompanyRepository.findByCompanyInternalId(anyInt())).thenReturn(tblCompany);

        // exercise
        TblCompany tblCompanyActual = sut.findByCompanyInternalId(1);
        //verify
        assertThat(tblCompanyActual).isEqualTo(tblCompany);
        verify(tblCompanyRepository, times(1)).findByCompanyInternalId(anyInt());
    }

    /**
     * Test, check Is Exists Company Name when find from data base
     * input
     *  company name = anyString()
     * output
     *  check TblCompany if Is Exists TblCompany then return true and not Exists then return false
     */
    @Test
    public void testIsExistsCompanyName1() {
        // set up
        when(tblCompanyRepository.findByCompanyName(anyString())).thenReturn(null);

        // exercise
        boolean actual = sut.isExistsCompanyName(anyString());

        //verify
        assertFalse(actual);
    }

    /**
     * Test, check Is Exists Company Name when find from data base
     * input
     *  company name = anyString()
     * output
     *  check TblCompany if Is Exists TblCompany then return true and not Exists then return false
     */
    @Test
    public void testIsExistsCompanyName2() {
        // set up
        TblCompany tblCompany =  new TblCompany();
        when(tblCompanyRepository.findByCompanyName(anyString())).thenReturn(tblCompany);

        // exercise
        boolean actual = sut.isExistsCompanyName(anyString());

        //verify
        assertTrue(actual);
    }

}

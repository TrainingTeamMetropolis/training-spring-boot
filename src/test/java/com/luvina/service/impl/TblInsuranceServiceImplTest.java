package com.luvina.service.impl;

import com.luvina.entities.TblInsurance;
import com.luvina.repository.TblInsuranceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TblInsuranceServiceImplTest {

    @InjectMocks
    TblInsuranceServiceImpl sut;

    @Mock
    TblInsuranceRepository tblInsuranceRepository;

    /**
     * Test Is Exists Tbl Insurance
     * input
     *  insuranceInternalId = 1000000003
     *  numberInsurance = 3134567534
     * output
     *  check exists TblInsurance when return true and not exists return false
     */
    @Test
    public void testIsExistsTblInsurance1() {
        // set up
        TblInsurance tblInsurance = new TblInsurance();
        int insuranceInternalId = 1000000003;
        String numberInsurance = "3134567534";
        when(tblInsuranceRepository.findByInsuranceInternalIdNotAndInsuranceNumber(anyInt(), anyString())).thenReturn(tblInsurance);

        // exercise
        boolean actual = sut.isExistsTblInsurance(insuranceInternalId, numberInsurance);

        //verify
        assertTrue(actual);
    }

    /**
     * Test Is Exists Tbl Insurance
     * input
     *  insuranceInternalId = 1000000003
     *  numberInsurance = 3134567534
     * output
     *  check exists TblInsurance when return true and not exists return false
     */
    @Test
    public void testIsExistsTblInsurance2() {
        // set up
        int insuranceInternalId = 1000000003;
        String numberInsurance = "3134567534";
        when(tblInsuranceRepository.findByInsuranceInternalIdNotAndInsuranceNumber(anyInt(), anyString())).thenReturn(null);

        // exercise
        boolean actual = sut.isExistsTblInsurance(insuranceInternalId, numberInsurance);

        //verify
        assertFalse(actual);
    }
}

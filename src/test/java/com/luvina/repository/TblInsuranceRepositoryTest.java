package com.luvina.repository;

import com.luvina.entities.TblInsurance;
import com.luvina.util.Common;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TblInsuranceRepositoryTest {
	
	@Autowired
	TblInsuranceRepository sut;
	
	@Autowired
	TestEntityManager testEntityManager;

    /**
     * Test Find By Insurance InternalId Not And Insurance Number
     */
	@Test
	public void testFindByInsuranceInternalIdNotAndInsuranceNumber() {
		Common common = new Common();
		TblInsurance tblInsurance = new TblInsurance();
        tblInsurance.setInsuranceInternalId(1000);
		tblInsurance.setInsuranceNumber("1234567891");
		tblInsurance.setPlaceOfRegister("Hung Yen");
		tblInsurance.setInsuranceEndDate(common.convertStringToDateSQL("10/10/2018"));
		tblInsurance.setInsuranceEndDate(common.convertStringToDateSQL("10/10/2019"));
        testEntityManager.persist(tblInsurance);
        testEntityManager.flush();

		TblInsurance expected = new TblInsurance();
        expected.setInsuranceInternalId(1001);
        expected.setInsuranceNumber("1234567891");
        expected.setPlaceOfRegister("Hung Yen A");
        expected.setInsuranceEndDate(common.convertStringToDateSQL("10/10/2018"));
        expected.setInsuranceEndDate(common.convertStringToDateSQL("10/10/2019"));
        testEntityManager.persist(expected);
        testEntityManager.flush();

		TblInsurance actual = sut.findByInsuranceInternalIdNotAndInsuranceNumber(tblInsurance.getInsuranceInternalId(),
				tblInsurance.getInsuranceNumber());

        assertThat(actual).isEqualTo(expected);
		
	}
	
}

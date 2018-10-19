package com.luvina.repository;

import com.luvina.entities.TblCompany;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TblCompanyRepositoryTest {
	
	@Autowired
	TblCompanyRepository sut;
	
	@Autowired
	TestEntityManager testEntityManager;

    /**
     * Test Find All By Order By Company Name Asc
     * input
     *  tblCompany1, tblCompany2
     * output
     *
     */
	@Test
	public void testFindAllByOrderByCompanyNameAsc() {
		// set up
		TblCompany tblCompany1 = new TblCompany();
        tblCompany1.setAddressCompany("106 Hoang Quoc Viet");
        tblCompany1.setCompanyName("FPT");
        tblCompany1.setEmailCompany("chelsea@gmail.com");
        tblCompany1.setPhoneCompany("22-3655-6985");

        TblCompany tblCompany2 = new TblCompany();
        tblCompany2.setAddressCompany("106 Hoang Quoc Viet");
        tblCompany2.setCompanyName("APT");
        tblCompany2.setEmailCompany("chelsea@gmail.com");
        tblCompany2.setPhoneCompany("22-3655-6985");

		testEntityManager.persist(tblCompany1);
        testEntityManager.persist(tblCompany2);
		testEntityManager.flush();
        List<TblCompany> tblCompanyList = new ArrayList<>();
        tblCompanyList.add(tblCompany2);
        tblCompanyList.add(tblCompany1);

		// exercise
		List<TblCompany> actual = sut.findAllByOrderByCompanyNameAsc();

		// verify
		assertThat(actual).isEqualTo(tblCompanyList);
	}

	@Test
    public void testFindByCompanyInternalId() {
        // set up
        TblCompany tblCompany = new TblCompany();
        tblCompany.setAddressCompany("106 Hoang Quoc Viet");
        tblCompany.setCompanyName("FPT");
        tblCompany.setEmailCompany("chelsea@gmail.com");
        tblCompany.setPhoneCompany("22-3655-6985");

        testEntityManager.persist(tblCompany);
        testEntityManager.flush();

        // exercise
        TblCompany actual = sut.findByCompanyInternalId(tblCompany.getCompanyInternalId());

        // verify
        assertThat(actual).isEqualTo(tblCompany);
    }

    @Test
    public void testFindByCompanyName(){
        // set up
        TblCompany tblCompany = new TblCompany();
        tblCompany.setAddressCompany("106 Hoang Quoc Viet");
        tblCompany.setCompanyName("FPT");
        tblCompany.setEmailCompany("chelsea@gmail.com");
        tblCompany.setPhoneCompany("22-3655-6985");

        testEntityManager.persist(tblCompany);
        testEntityManager.flush();

        // exercise
        TblCompany actual = sut.findByCompanyName(tblCompany.getCompanyName());

        // verify
        assertThat(actual).isEqualTo(tblCompany);
    }
}

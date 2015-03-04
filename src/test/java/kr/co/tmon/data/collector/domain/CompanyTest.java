package kr.co.tmon.data.collector.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*.xml")
public class CompanyTest {
	private static final int COMPANY_ID = 1;
	private static final int ANOTHER_COMPANY_ID = 2;
	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void testCompanyEquals() {
		Company company = new Company();
		company.setId(COMPANY_ID);

		Company anotherCompany = new Company();
		company.setId(ANOTHER_COMPANY_ID);

		assertTrue(company.equals(company));
		assertFalse(company.equals(anotherCompany));

		assertFalse(company.equals(null));
	}

	@Test
	@Transactional
	public void testCompany() {
		Session currentSession = sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		List<Company> list = currentSession.createQuery("from Company").list();

		System.out.println(list);
	}
}

package kr.co.tmon.data.collector.dao;

import java.util.List;

import kr.co.tmon.data.collector.domain.Company;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*.xml")
public class CompanyDaoTest {

	@Autowired
	private CompanyDao companyDao;

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void testGetCompanyList() throws Exception {
		List<Company> companyList = companyDao.getCompanyList();
		System.out.println(companyList);
	}
}

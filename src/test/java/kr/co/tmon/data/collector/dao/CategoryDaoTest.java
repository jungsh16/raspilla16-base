package kr.co.tmon.data.collector.dao;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.data.collector.domain.Category;
import kr.co.tmon.data.collector.domain.Company;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*.xml")
public class CategoryDaoTest {

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private CategoryDao categoryDao;

	@Test
	public void testSaveCategoryList() throws Exception {
		List<Category> categoryEntityList = new ArrayList<Category>();

		Company company = new Company();
		company.setName("테스트 회사");
		company.setHost("http://test.com");
		companyDao.saveCompany(company);

		Category rootCategory = new Category();
		rootCategory.setCompany(company);
		rootCategory.setCategoryId("root");
		rootCategory.setName("루트");

		Category firstCategory = new Category();
		firstCategory.setCompany(company);
		firstCategory.setParentCategory(rootCategory);
		firstCategory.setCategoryId("first");
		firstCategory.setName("퍼스트");

		Category secondCategory = new Category();
		secondCategory.setCompany(company);
		secondCategory.setParentCategory(firstCategory);
		secondCategory.setCategoryId("second");
		secondCategory.setName("세컨드");

		categoryEntityList.add(rootCategory);
		categoryEntityList.add(firstCategory);
		categoryEntityList.add(secondCategory);

		categoryDao.saveCategoryList(categoryEntityList);
	}
}

package kr.co.tmon.data.collector.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class CategoryTest {

	private static final Company COMPANY;
	private static final int COMPANY_ID = 1;
	private static final String COMPANY_NAME = "쿠팡";
	private static final String CATEGORY = "Category";
	private static final String PARENT_CATEGORY = "ParentCategory";
	private static final String NAME = "테스트 카테고리";

	private static final Company ANOTHER_COMPANY;
	private static final int ANOTHER_COMPANY_ID = 2;
	private static final String ANOTHER_COMPANY_NAME = "티몬";
	private static final String ANOTHER_CATEGORY = "AnotherCategory";
	private static final String ANOTHER_PARENT_CATEGORY = "AnotherParentCategory";
	private static final String ANOTHER_NAME = "다른 테스트 카테고리";

	static {
		COMPANY = new Company();
		COMPANY.setId(COMPANY_ID);
		COMPANY.setName(COMPANY_NAME);

		ANOTHER_COMPANY = new Company();
		ANOTHER_COMPANY.setId(ANOTHER_COMPANY_ID);
		ANOTHER_COMPANY.setName(ANOTHER_COMPANY_NAME);
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void testCategoryEquals() {
		Category category = new Category();
		category.setCompany(COMPANY);
		category.setCategoryId(CATEGORY);
		category.setParentCategoryId(PARENT_CATEGORY);
		category.setName(NAME);

		Category anotherCategory = new Category();
		anotherCategory.setCompany(COMPANY);
		anotherCategory.setCategoryId(CATEGORY);
		anotherCategory.setParentCategoryId(PARENT_CATEGORY);
		anotherCategory.setName(NAME);

		System.out.println(category);
		System.out.println(anotherCategory);

		assertTrue(category.equals(category));
		assertTrue(category.equals(anotherCategory));
		assertFalse(category.equals(null));

		category.setCompany(null);
		assertFalse(category.equals(anotherCategory));
		category.setCompany(COMPANY);

		anotherCategory.setCompany(ANOTHER_COMPANY);
		assertFalse(category.equals(anotherCategory));

		anotherCategory.setCompany(COMPANY);
		anotherCategory.setCategoryId(ANOTHER_CATEGORY);
		assertFalse(category.equals(anotherCategory));

		anotherCategory.setCategoryId(CATEGORY);
		anotherCategory.setParentCategoryId(ANOTHER_PARENT_CATEGORY);
		assertFalse(category.equals(anotherCategory));

		anotherCategory.setParentCategoryId(PARENT_CATEGORY);
		anotherCategory.setName(ANOTHER_NAME);
		assertFalse(category.equals(anotherCategory));

		anotherCategory.setName(NAME);
		assertTrue(category.equals(anotherCategory));
	}

	@Test
	@Transactional
	public void testCategory() {
		Category category = new Category();
		category.setCompany(COMPANY);
		category.setCategoryId(CATEGORY);
		category.setParentCategoryId(PARENT_CATEGORY);
		category.setName(NAME);

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(category);

		currentSession.get(Category.class, category);

		currentSession.delete(category);
	}
}

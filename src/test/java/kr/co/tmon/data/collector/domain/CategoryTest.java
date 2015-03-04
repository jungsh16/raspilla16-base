package kr.co.tmon.data.collector.domain;

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

	private static final int DEPTH_1 = 1;
	private static final int DEPTH_2 = 2;
	private static final int DEPTH_3 = 3;

	private Company company;

	private Category root;
	private Category depth1;
	private Category depth1A;
	private Category depth1B;
	private Category depth1C;
	private Category depth2;
	private Category depth2A;
	private Category depth2B;

	{
		company = new Company();
		company.setId(1);
		company.setName("쿠팡");
		company.setHost("http://capi.coupang.com");

		root = new Category();
		root.setCategoryId("0");
		root.setCompany(company);
		root.setName("name0");
		root.setDepth(DEPTH_1);

		depth1 = new Category();
		depth1.setCategoryId("1");
		depth1.setCompany(company);
		depth1.setName("name1");
		depth1.setDepth(DEPTH_2);

		depth1A = new Category();
		depth1A.setCategoryId("1A");
		depth1A.setCompany(company);
		depth1A.setName("name1A");
		depth1A.setDepth(DEPTH_3);

		depth1B = new Category();
		depth1B.setCategoryId("1B");
		depth1B.setCompany(company);
		depth1B.setName("name1B");
		depth1B.setDepth(DEPTH_3);

		depth1C = new Category();
		depth1C.setCategoryId("1C");
		depth1C.setCompany(company);
		depth1C.setName("name1C");
		depth1C.setDepth(DEPTH_3);

		depth2 = new Category();
		depth2.setCategoryId("2");
		depth2.setCompany(company);
		depth2.setName("name2");
		depth2.setDepth(DEPTH_2);

		depth2A = new Category();
		depth2A.setCategoryId("2A");
		depth2A.setCompany(company);
		depth2A.setName("name2A");
		depth2A.setDepth(DEPTH_3);

		depth2B = new Category();
		depth2B.setCategoryId("2B");
		depth2B.setCompany(company);
		depth2B.setName("name2B");
		depth2B.setDepth(DEPTH_3);
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	@Transactional
	public void testCategory() {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(root);

		currentSession.get(Category.class, root);

		currentSession.delete(root);
	}
}

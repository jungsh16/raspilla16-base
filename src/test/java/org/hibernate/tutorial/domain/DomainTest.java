package org.hibernate.tutorial.domain;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext.xml", "classpath:spring/dispatcher-servlet.xml" })
public class DomainTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	@Transactional
	public void cityTest() {
		System.out.println("테스트 시작");
		System.out.println(sessionFactory.getClass().getName());
		City city = new City();
		city.setId(1);
		city.setName("test");
		city.setPopulation(100);
		System.out.println(sessionFactory.getCurrentSession().save(city));
		System.out.println(sessionFactory.getCurrentSession().get(City.class, 1));
	}
}

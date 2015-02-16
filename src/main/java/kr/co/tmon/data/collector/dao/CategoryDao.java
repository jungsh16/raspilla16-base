package kr.co.tmon.data.collector.dao;

import java.util.List;

import kr.co.tmon.data.collector.domain.Category;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Rollback(false)
	@Transactional(rollbackFor = { Exception.class })
	public void saveCategoryList(List<Category> categoryEntityList) {
		Session session = sessionFactory.getCurrentSession();

		categoryEntityList.forEach((category) -> {
			session.saveOrUpdate(category);
		});
	}
}

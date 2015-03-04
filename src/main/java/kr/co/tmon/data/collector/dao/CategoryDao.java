package kr.co.tmon.data.collector.dao;

import java.util.List;

import kr.co.tmon.data.collector.domain.Category;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = { Exception.class })
public class CategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveCategoryList(List<Category> categoryEntityList) {
		Session session = sessionFactory.getCurrentSession();

		categoryEntityList.forEach((category) -> {
			session.saveOrUpdate(category);
		});
	}
}

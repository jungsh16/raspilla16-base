package kr.co.tmon.data.collector.dao;

import java.util.List;

import kr.co.tmon.data.collector.domain.Company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Company> getCompanyList() {
		Session session = sessionFactory.openSession();

		return session.createQuery("from " + Company.class.getSimpleName()).list();
	}

}

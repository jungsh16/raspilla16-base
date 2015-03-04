package kr.co.tmon.data.collector.dao;

import java.util.List;

import kr.co.tmon.data.collector.domain.Company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = { Exception.class })
public class CompanyDao {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Company> getCompanyList() {
		Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Company.class).list();
	}

	public void saveCompany(Company company) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(company);
	}
}

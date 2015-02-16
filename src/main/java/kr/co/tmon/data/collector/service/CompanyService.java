package kr.co.tmon.data.collector.service;

import java.util.List;

import kr.co.tmon.data.collector.dao.CompanyDao;
import kr.co.tmon.data.collector.domain.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
	@Autowired
	private CompanyDao companyDao;

	public List<Company> getCompanyList() {
		return companyDao.getCompanyList();
	}
}

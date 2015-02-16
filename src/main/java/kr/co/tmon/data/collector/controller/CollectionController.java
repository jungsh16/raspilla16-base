package kr.co.tmon.data.collector.controller;

import java.util.List;

import kr.co.tmon.data.collector.domain.Company;
import kr.co.tmon.data.collector.service.CompanyService;
import kr.co.tmon.data.collector.task.coupang.CoupangCategoryTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

@Controller
public class CollectionController {

	public static final String COUPANG = "쿠팡";

	@Autowired
	private ApplicationContext context;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ThreadPoolTaskExecutor executor;

	public void collect() {
		List<Company> companyList = companyService.getCompanyList();

		companyList.forEach((company) -> {
			switch (company.getName()) {
				case COUPANG :
					CoupangCategoryTask coupangCategoryTask = context.getBean(CoupangCategoryTask.class, company);
					executor.execute(coupangCategoryTask);
					break;
				default :
					break;
			}
		});
	}
}

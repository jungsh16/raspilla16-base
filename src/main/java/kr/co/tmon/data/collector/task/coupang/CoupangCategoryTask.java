package kr.co.tmon.data.collector.task.coupang;

import java.util.List;

import kr.co.tmon.data.collector.domain.Company;
import kr.co.tmon.data.collector.task.coupang.service.CoupangCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class CoupangCategoryTask implements Runnable {

	private static final String COUPANG_CATEGORY_URI = "/v3/intro?executeType=N";

	private final Company company;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ThreadPoolTaskExecutor executor;

	@Autowired
	private CoupangCategoryService coupangCategoryService;

	public CoupangCategoryTask(Company company) {
		this.company = company;
	}

	@Override
	public void run() {
		try {
			List<ProductListTask> productListTaskList = coupangCategoryService.getProductListTaskList(company, COUPANG_CATEGORY_URI);

			if (productListTaskList == null) {
				return;
			}

			for (ProductListTask productListTask : productListTaskList) {
				executor.execute(productListTask);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

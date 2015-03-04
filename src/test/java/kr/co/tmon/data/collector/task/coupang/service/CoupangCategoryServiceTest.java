package kr.co.tmon.data.collector.task.coupang.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import kr.co.tmon.data.collector.dao.CategoryDao;
import kr.co.tmon.data.collector.domain.Company;
import kr.co.tmon.data.collector.task.util.JsonUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonUtil.class)
public class CoupangCategoryServiceTest {

	@Mock
	private CategoryDao categoryDao;

	@InjectMocks
	private CoupangCategoryService coupangCategoryService;

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void testGetProductListTaskList() throws Exception {
		JsonNode jsonNode = new ObjectMapper().readTree(getClass().getResource("/json/coupang_category.json"));

		mockStatic(JsonUtil.class);
		when(JsonUtil.getJsonNode(any(Company.class), anyString())).thenReturn(jsonNode);

		coupangCategoryService.getProductListTaskList(any(Company.class), anyString());
	}
}

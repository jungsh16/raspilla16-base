package kr.co.tmon.data.collector.task.coupang.service;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.data.collector.dao.CategoryDao;
import kr.co.tmon.data.collector.domain.Category;
import kr.co.tmon.data.collector.domain.Company;
import kr.co.tmon.data.collector.task.coupang.ProductListTask;
import kr.co.tmon.data.collector.task.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class CoupangCategoryService {

	private static final String CATEGORY_LIST = "categoryList";
	private static final String CATEGORY_ID = "id";
	private static final String CATEGORY_NAME = "name";
	private static final String CATEGORY_CHILDREN = "children";

	private static final String VALUE_ALL = "전체";

	@Autowired
	private CategoryDao categoryDao;

	public List<ProductListTask> getProductListTaskList(Company company, String requestUri) throws Exception {
		JsonNode coupangJsonNode = JsonUtil.getJsonNode(company, requestUri);

		JsonNode categoryList = coupangJsonNode.findValue(CATEGORY_LIST);
		List<Category> categoryEntityList = getCategoryEntityList(company, null, categoryList);

		categoryDao.saveCategoryList(categoryEntityList);

		return null;
	}

	private List<Category> getCategoryEntityList(Company company, String parentCategoryId, JsonNode categoryList) {
		List<Category> resultList = new ArrayList<Category>();

		for (JsonNode node : categoryList) {
			String name = node.get(CATEGORY_NAME).textValue();

			if (VALUE_ALL.equals(name)) {
				continue;
			}

			final Category category = new Category();
			category.setCompany(company);
			category.setName(name);

			String categoryId = node.get(CATEGORY_ID).textValue();
			category.setCategoryId(categoryId);

			if (parentCategoryId == null) {
				category.setParentCategoryId(categoryId);
			} else {
				category.setParentCategoryId(parentCategoryId);
			}

			resultList.add(category);

			if (node.has(CATEGORY_CHILDREN)) {
				resultList.addAll(getCategoryEntityList(company, categoryId, node.findValue(CATEGORY_CHILDREN)));
			}
		}

		return resultList;
	}
}
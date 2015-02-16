package kr.co.tmon.data.collector.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(schema = "product_comparison", name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JoinColumn(name = "c_id")
	@ManyToOne(targetEntity = Company.class)
	private Company company;

	@Id
	@Column(name = "cat_id")
	private String categoryId;

	@Id
	@Column(name = "p_cat_id")
	private String parentCategoryId;

	@Column(name = "name")
	private String name;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode()
		        + this.company.hashCode()
		        + this.categoryId.hashCode()
		        + this.parentCategoryId.hashCode()
		        + this.name.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Category == false) {
			return false;
		}

		Category category = (Category) object;

		return category.company.equals(this.company)
		        && StringUtils.equals(this.categoryId, category.categoryId)
		        && StringUtils.equals(this.parentCategoryId, category.parentCategoryId)
		        && StringUtils.equals(this.name, category.name);
	}

	@Override
	public String toString() {
		return "Category [company=" + company + ", categoryId=" + categoryId + ", parentCategoryId=" + parentCategoryId + ", name=" + name + "]";
	}
}

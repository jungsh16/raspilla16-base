package kr.co.tmon.data.collector.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@Column(name = "name")
	private String name;

	@Column(name = "depth")
	private int depth;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumns({
	        @JoinColumn(name = "p_c_id"),
	        @JoinColumn(name = "p_cat_id")
	})
	private Category parentCategory;

	@OneToMany(mappedBy = "parentCategory")
	private Set<Category> childCategories = new HashSet<Category>();

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<Category> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<Category> childCategories) {
		this.childCategories = childCategories;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode()
		        + this.company.hashCode()
		        + this.categoryId.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Category == false) {
			return false;
		}

		Category category = (Category) object;

		return category.company.equals(this.company)
		        && StringUtils.equals(this.categoryId, category.categoryId);
	}

	@Override
	public String toString() {
		return "Category [company=" + company + ", categoryId=" + categoryId + ", name=" + name + ", depth=" + depth + ", parentCategory=" + parentCategory
		        + ", childCategories=" + childCategories + "]";
	}
}

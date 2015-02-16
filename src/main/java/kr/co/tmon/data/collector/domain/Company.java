package kr.co.tmon.data.collector.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "product_comparison", name = "company")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "name", updatable = false)
	private String name;

	@Column(name = "host", updatable = false)
	private String host;

	@Column(name = "header", updatable = false)
	private String header;

	@Override
	public int hashCode() {
		return getClass().hashCode()
		        + this.id;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Company == false) {
			return false;
		}

		Company company = (Company) object;

		return this.id == company.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", host=" + host + ", header=" + header + "]";
	}
}

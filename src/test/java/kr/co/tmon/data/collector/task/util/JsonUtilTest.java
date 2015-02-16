package kr.co.tmon.data.collector.task.util;

import kr.co.tmon.data.collector.domain.Company;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtilTest {

	@Test
	public void testGetJsonNodeForCoupang() throws Exception {
		Company company = new Company();
		company.setId(1);
		company.setName("쿠팡");
		company.setHost("http://capi.coupang.com");
		company.setHeader("{'Accept-Encoding':'gzip','Content-Type':'application/x-www-form-urlencoded',"
		        + "'Accept':'*/*, application/json','coupang-app':'COUPANG|Android|5.0.1|3.7.2||"
		        + "APA91bERL_jlCplq5m6_kTO1SS83oZgcnQWFSXKA0R8ym12hUHY4Twp5_iW8Lv-er7KwAKYmmnEUGhSbAC9egQge3hpkrjmk"
		        + "_BAh0-49Y5a2Xhh7JUfwEcsfsfiRkPq8B8Pepfih1vtVaCFYp6s2hLtnxCW-yeHyww|"
		        + "ffffffff-8b9c-fd8d-ffff-ffffb0b66971|Y|LG-F460K|649bcaa59a9d7ff57430e5a543f5c0a5|"
		        + "e66c8acb-4d23-4c5e-a36b-4cce8d2f7a3e|XXHDPI|14140675049401984058601',"
		        + "'Host':'capi.coupang.com','Connection':'Keep-Alive','User-Agent':'Apache-HttpClient/UNAVAILABLE (java 1.4)'}");

		JsonNode jsonNode = JsonUtil.getJsonNode(company, "/v3/intro?executeType=Y");
		System.out.println(jsonNode);
	}
}

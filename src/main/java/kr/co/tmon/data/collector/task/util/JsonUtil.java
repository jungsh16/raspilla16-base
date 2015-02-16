package kr.co.tmon.data.collector.task.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import kr.co.tmon.data.collector.domain.Company;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {

	private static final int TIMEOUT = 5000;

	private JsonUtil() {
	}

	public static JsonNode getJsonNode(Company company, String requestUri) throws Exception {
		HttpURLConnection connection = getConnection(company, requestUri);
		InputStream inputStream = getInputStream(connection);
		JsonNode jsonNode = new ObjectMapper().readTree(inputStream);

		inputStream.close();
		connection.disconnect();

		return jsonNode;
	}

	private static InputStream getInputStream(HttpURLConnection connection) throws IOException {
		InputStream inputStream = connection.getInputStream();

		String contentEncoding = connection.getContentEncoding();

		if ("gzip".equals(contentEncoding)) {
			inputStream = new GZIPInputStream(inputStream);
		}

		return inputStream;
	}

	private static HttpURLConnection getConnection(Company company, String requestUri) throws IOException, MalformedURLException {
		HttpURLConnection connection = (HttpURLConnection) new URL(company.getHost() + requestUri).openConnection();
		connection.setConnectTimeout(TIMEOUT);

		JSONObject header = new JSONObject(company.getHeader());
		for (String key : header.keySet()) {
			connection.setRequestProperty(key, header.getString(key));
		}

		return connection;
	}
}

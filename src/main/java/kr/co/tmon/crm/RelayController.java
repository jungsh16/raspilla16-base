package kr.co.tmon.crm;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RelayController {
	@RequestMapping(value = "/relay.tmon")
	public ResponseEntity<String> getHelloMessage(String url, String keys) throws Exception {
		System.out.println(url);
		System.out.println(keys);

		List<String> keyList = Arrays.asList(keys.split(","));

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(new URL(url), new TypeReference<ConcurrentMap<String, Object>>() {
		});

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(mapper.writeValueAsString(getFilteredMap(keyList, jsonMap)), headers, HttpStatus.OK);

		return responseEntity;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getFilteredMap(List<String> keyList, Map<String, Object> jsonMap) {
		Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

		Set<String> keySet = jsonMap.keySet();

		for (String key : keySet) {
			if (keyList.contains(key)) {
				resultMap.put(key, jsonMap.get(key));
			} else if (jsonMap.get(key) instanceof Map) {
				Map<String, Object> filteredMap = getFilteredMap(keyList, (Map<String, Object>) jsonMap.get(key));

				if (filteredMap.size() > 0)
					resultMap.put(key, filteredMap);
			} else if (jsonMap.get(key) instanceof List) {
				List<Object> filteredList = getFilteredList(keyList, (List<Object>) jsonMap.get(key));

				if (filteredList.size() > 0)
					resultMap.put(key, filteredList);
			}
		}

		return resultMap;
	}

	@SuppressWarnings("unchecked")
	private List<Object> getFilteredList(List<String> keyList, List<Object> list) {
		List<Object> resultList = new ArrayList<Object>();

		for (Object object : list) {
			if (object instanceof Map) {
				resultList.add(getFilteredMap(keyList, (Map<String, Object>) object));
			} else if (object instanceof List) {
				resultList.add(getFilteredList(keyList, (List<Object>) object));
			}
		}

		return resultList;
	}
}

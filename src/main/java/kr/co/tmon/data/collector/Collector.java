package kr.co.tmon.data.collector;

import kr.co.tmon.data.collector.controller.CollectionController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Collector {

	private Collector() {
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		CollectionController collectionController = context.getBean(CollectionController.class);
		collectionController.collect();
	}
}

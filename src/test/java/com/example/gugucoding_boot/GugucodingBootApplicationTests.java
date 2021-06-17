package com.example.gugucoding_boot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GugucodingBootApplicationTests {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Test
	void contextLoads() {
		log.info("Context Loads...");
	}

}

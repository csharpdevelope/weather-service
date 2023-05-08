package com.example.weather;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class WeatherApplicationTests {

	@Test
	@Disabled("Disabled method")
	void contextLoads() {

		log.info("Context Loads");
	}

	@Test
	@BeforeEach
	@DisplayName("First Test")
	void beforeEach() {
		String someString = "Just a string";
		assumingThat(
				someString.equals("Just a string"),
				() -> assertEquals(2 + 2, 4)
		);
	}
}

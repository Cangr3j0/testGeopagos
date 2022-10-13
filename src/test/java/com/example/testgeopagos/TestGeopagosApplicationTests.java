package com.example.testgeopagos;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestGeopagosApplicationTests {

	@Autowired
	DataSource dataSource;
	@Test
	void contextLoads() {
	}

}

package com.example.demo;

import com.example.security.jwt.JwtUtil; // Import JwtUtil
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean; // Import MockBean

@SpringBootTest
class DemoApplicationTests {

	@MockBean // Mock the JwtUtil bean
	private JwtUtil jwtUtil;

	@Test
	void contextLoads() {
		// No direct assertions needed here, just checking if context loads
	}

}
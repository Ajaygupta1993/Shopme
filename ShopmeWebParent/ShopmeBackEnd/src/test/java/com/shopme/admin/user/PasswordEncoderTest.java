package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	@Test
	public void testEncodePassword() {
		BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
		String rawPassword="12345";
		String encodePassword = passwordEncoder.encode(rawPassword);
		System.out.println(encodePassword);
		boolean matchPassword = passwordEncoder.matches(rawPassword, encodePassword);
		assertThat(matchPassword).isTrue();
	}

}

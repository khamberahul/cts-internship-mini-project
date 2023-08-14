package com.cts.user.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

class UserTest {

	User user = null;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		user = new User();
		user.setFullName("Prathamesh");
		user.setUserId(1);
		user.setAadharNumber(123456789861L);
		user.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2001"));
		user.setAge(18);
		user.setGender("Male");
		user.setPhoneNumber("1234567892");
		user.setAddress("mumbai");
		user.setUsername("prathamesh");
		user.setUserEmail("prathamesh@gmail.com");
		user.setUserPassword("abc");
	}

	@Test
	void fullNameTest() {
		assertThat(user, hasProperty("fullName"));
		assertNotNull(user);
	}

	@Test
	void userIdTest() {
		assertThat(user, hasProperty("userId"));
//		assertNotNull(user);

	}

	@Test
	void aadharNumberTest() {
		assertThat(user, hasProperty("aadharNumber"));
//		assertNotNull(user);
	}

	@Test
	void dobTest() {
		assertThat(user, hasProperty("dob"));
//		assertNotNull(user);
	}

	@Test
	void ageTest() {
		assertThat(user, hasProperty("age"));
//		assertNotNull(user);

	}

	@Test
	void genderTest() {
		assertThat(user, hasProperty("gender"));
//		assertNotNull(user);
	}

	@Test
	void phoneNumberTest() {
		assertThat(user, hasProperty("phoneNumber"));
//		assertNotNull(user);
	}

	@Test
	void addressTest() {
		assertThat(user, hasProperty("address"));
//		assertNotNull(user);
	}

	@Test
	void usernameTest() {
		assertThat(user, hasProperty("username"));
//		assertNotNull(user);
	}

	@Test
	void userEmailTest() {
		assertThat(user, hasProperty("userEmail"));
//		assertNotNull(user);
	}

	@Test
	void userPasswordTest() {
		assertThat(user, hasProperty("userPassword"));
//		assertNotNull(user);
	}

}

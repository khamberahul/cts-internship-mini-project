package com.cts.center.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VaccineDetailsTest {

	VaccineDetails vaccineDetails = null;

	@BeforeEach
	void setUp() throws Exception {
	vaccineDetails = new VaccineDetails();
	vaccineDetails.setVaccineId(1);
	vaccineDetails.setVaccineName("Covaxin");
	vaccineDetails.setVaccinePrice(140.5);
	vaccineDetails.setVaccineQuantity(13);
	vaccineDetails.setVaccineCenter(null);
	
	}

	@Test
	void vaccineIdTest() {
		assertThat(vaccineDetails, hasProperty("vaccineId"));
		assertNotNull(vaccineDetails.getVaccineId());
	}

	@Test
	void vaccineNameTest() {
		assertThat(vaccineDetails, hasProperty("vaccineName"));
		assertNotNull(vaccineDetails.getVaccineName());
	}

	@Test
	void vaccinePriceTest() {
		assertThat(vaccineDetails, hasProperty("vaccinePrice"));
		assertNotNull(vaccineDetails.getVaccinePrice());
	}

	@Test
	void vaccineQuantityTest() {
		assertThat(vaccineDetails, hasProperty("vaccineQuantity"));
		assertNotNull(vaccineDetails.getVaccineQuantity());
	}
	
	@Test
	void vaccineCenterTest() {
		assertThat(vaccineDetails, hasProperty("vaccineCenter"));
//		assertNotNull(vaccineDetails.getVaccineCenter());
	}

	

}

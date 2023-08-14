package com.cts.center.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VaccineCenterTest {
	VaccineCenter vaccineCenter = null;

	@BeforeEach
	void setUp() throws Exception {
	vaccineCenter = new VaccineCenter();
	vaccineCenter.setCenterId(1);
	vaccineCenter.setCenterName("My lab");
	vaccineCenter.setCenterAddress("mumbai");
	vaccineCenter.setCenterDistrict("Mumbai");
	vaccineCenter.setCenterPinCode(400080);
	vaccineCenter.setDetailsList(new ArrayList<VaccineDetails>());
	}

	@Test
	void centerIdTest() {
		assertThat(vaccineCenter, hasProperty("centerId"));
		assertNotNull(vaccineCenter.getCenterId());
	}

	@Test
	void centerNameTest() {
		assertThat(vaccineCenter, hasProperty("centerName"));
		assertNotNull(vaccineCenter.getCenterName());
	}

	@Test
	void centerAddressTest() {
		assertThat(vaccineCenter, hasProperty("centerAddress"));
		assertNotNull(vaccineCenter.getCenterAddress());
	}

	@Test
	void centerDistrictTest() {
		assertThat(vaccineCenter, hasProperty("centerDistrict"));
		assertNotNull(vaccineCenter.getCenterDistrict());
	}

	@Test
	void centerPinCodeTest() {
		assertThat(vaccineCenter, hasProperty("centerPinCode"));
		assertNotNull(vaccineCenter.getCenterPinCode());
	}
	
	@Test
	void detailsListTest() {
		assertThat(vaccineCenter, hasProperty("detailsList"));
		assertNotNull(vaccineCenter.getDetailsList());
	}
	

}

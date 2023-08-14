package com.cts.center.service;

import java.lang.module.Configuration;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.service.ServiceRegistry;

import com.cts.center.exception.VaccineCenterNotFoundException;
import com.cts.center.model.VaccineCenter;
import com.cts.center.model.VaccineDetails;
import com.cts.center.repository.VaccineCenterRepo;
import com.mysql.cj.xdevapi.SessionFactory;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul VaccineCenterService Implementation
 */
@Slf4j
@Service
public class VaccineCenterServiceImpl implements VaccineCenterService {

	@Autowired
	VaccineCenterRepo vaccineRepo;
	private Logger logger = (Logger) LoggerFactory.getLogger(VaccineCenterServiceImpl.class);

	/**
	 * @author your Sakshi 
	 * Save Vaccine Center Details Implementation
	 */
	@Override
	public void addVaccineCenter(VaccineCenter vaccineCenter) {
		// TODO Auto-generated method stub
//		log.info(vaccineCenter.toString());
		vaccineRepo.save(vaccineCenter);
		log.info("saved details:{}");
	}

	
	/**
	 * Edit vaccination center with new info and centerId
	 * 
	 * @author AtharvaKamble
	 * @param editInfo {VaccineCenter}
	 * @param centerId {int}
	 * @return boolean
	 */
	@Override
	public boolean editVaccineCenter(VaccineCenter editInfo, int centerId) {
		boolean flag = false;

		log.info("Updating vaccine center with ID: {}", centerId);

		try {
			VaccineCenter temp = vaccineRepo.findById(centerId)
					.orElseThrow(() -> new VaccineCenterNotFoundException("No center found : ("));

			temp.setCenterName(editInfo.getCenterName());
			temp.setCenterAddress(editInfo.getCenterAddress());
			temp.setCenterDistrict(editInfo.getCenterDistrict());
			temp.setCenterPinCode(editInfo.getCenterPinCode());
			temp.setDetailsList(editInfo.getDetailsList());

			vaccineRepo.save(temp);
			flag = true;
			log.info("Vaccine center with ID: {} updated successfully!", centerId);

		} catch (Exception e) {
			log.info("There was an error while updating center with ID: {}.", centerId);
			log.error(e.toString());
		}

		return flag;
	}

	/**
	 * Delete vaccination center with centerId provided
	 * 
	 * @author AtharvaKamble
	 * @param centerid {int}
	 * @return boolean
	 */
	@Override
	public boolean deleteVaccineCenter(int centerId) {
		boolean flag = false;

		try {
			// Raise error if no vaccine center with given ID is found
			vaccineRepo.findById(centerId).orElseThrow(() -> new RuntimeException("No center found : ("));

			vaccineRepo.deleteById(centerId);
			flag = true;
			log.info("Vaccine center with ID: {} delete successfully!", centerId);

		} catch (Exception e) {
			flag=false;
			log.info("There was an error while deleting center with ID: {}.", centerId);
			log.error(e.toString());
		}

		return flag;

	}

	/**
	 * 
	 * @author Shridhar
	 * @return List<VaccineCenter>
	 */
	@Override
	public List<VaccineCenter> getVaccineCenters() {
		List<VaccineCenter> vaccinecenter = null;

		log.info("Fetching vaccine centers.");

		try {
			vaccinecenter = vaccineRepo.fetchAllVaccineCenter();
		} catch (Exception e) {
			log.error("There was an error in fetching vaccine centers.");
			log.error(e.toString());
		}

		return vaccinecenter;

	}

	/**
	 * @author rahul
	 * Get Vacccine center details using id
	 */
	@Override
	public VaccineCenter getVaccineCenterById(int vaccineCenter) {
		// TODO Auto-generated method stub
		VaccineCenter vc = vaccineRepo.findById(vaccineCenter).orElseThrow(() -> new VaccineCenterNotFoundException("No center found : ("));
		return vc;
	}
	
}

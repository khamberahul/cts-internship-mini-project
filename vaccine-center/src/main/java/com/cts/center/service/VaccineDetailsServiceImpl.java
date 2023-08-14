package com.cts.center.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.center.model.VaccineCenter;
import com.cts.center.model.VaccineDetails;
import com.cts.center.repository.VaccineCenterRepo;
import com.cts.center.repository.VaccineDetailsRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 * VaccineDetailsService Implementation
 */
@Slf4j
@Service
public class VaccineDetailsServiceImpl implements VaccineDetailsService{
	@Autowired
	VaccineDetailsRepo vaccineDetailsRepo;
	/**
	 * @author Sakshi
	 * Save Vaccine Details Implementation
	 */
	@Override
	public void addVaccineDetails(VaccineDetails vaccineDetails) {
		// TODO Auto-generated method stub
		vaccineDetailsRepo.save(vaccineDetails);
		log.info("saved details:{}");

	}
	@Override
	public List<VaccineDetails> getVaccineDetails() {
		// TODO Auto-generated method stub
		return vaccineDetailsRepo.findAll();
	}
	
	@Override
	public boolean deleteVaccineDetailById(int vaccineId) {
		boolean flag = false;
		
		log.info("Deleting vaccine details.");
		try {
			vaccineDetailsRepo.deleteById(vaccineId);
			flag = true;
		} catch (Exception e) {
			flag = false;
			log.warn(e.toString());
		}
		
		return flag;
	}
	
	
}

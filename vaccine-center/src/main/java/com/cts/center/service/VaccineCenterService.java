package com.cts.center.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.center.model.VaccineCenter;
import com.cts.center.model.VaccineDetails;
import com.cts.center.repository.VaccineCenterRepo;

/**
 * VaccineCenterService Interface for the vaccine center functionality
 */

public interface VaccineCenterService {
	/**
	 * For Saving the VaccineCenter
	 * @param user
	 * @author sakshi
	 */
	public void addVaccineCenter(VaccineCenter vaccineCenter);
	
	
	/**
	 * Edit current vaccine center details and return true if update was successful
	 * @author AtharvaKamble
	 * @param editInfo {VaccineCenter} 
	 * @return String 
	 */
	public boolean editVaccineCenter(VaccineCenter editInfo, int centerId);
	
	
	/**
	 * Delete a vaccine center and return true if deletion was successful
	 * @author AtharvaKamble
	 * @param centerId {int}
	 * @return boolean
	 */
	public boolean deleteVaccineCenter(int centerId);

	
	/**
	 * Fetch all vaccine centers
	 * @author Shridhar
	 * @return List<VaccineCenter>
	 */
	public List<VaccineCenter> getVaccineCenters();
	/**
	 * @author rahul
	 * @param vaccineCenter
	 * @return
	 */
	public VaccineCenter getVaccineCenterById(int vaccineCenter);
}

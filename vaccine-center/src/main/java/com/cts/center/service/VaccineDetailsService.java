package com.cts.center.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.center.model.VaccineCenter;
import com.cts.center.model.VaccineDetails;
import com.cts.center.repository.VaccineCenterRepo;
import com.cts.center.repository.VaccineDetailsRepo;

/**
 * VaccineDetailsService Interface for the vaccine details functionality
 */

public interface VaccineDetailsService {
	/**
	 * For Saving the VaccineDetails
	 * @param user
	 * @author Sakshi
	 */
	public void addVaccineDetails(VaccineDetails vaccineDetails);
	public List<VaccineDetails> getVaccineDetails();
	
	
	/**
	 * @author AtharvaKamble
	 * @param vaccineId
	 * @return boolean
	 */
	public boolean deleteVaccineDetailById(int vaccineId);
}

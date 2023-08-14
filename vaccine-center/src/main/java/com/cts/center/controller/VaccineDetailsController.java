package com.cts.center.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cts.center.exception.VaccineCenterNotFoundException;
import com.cts.center.exception.VaccineDetailsNotFoundException;
import com.cts.center.feign.AuthorisationClient;
import com.cts.center.model.VaccineCenter;
import com.cts.center.model.VaccineDetails;
import com.cts.center.service.VaccineCenterService;
import com.cts.center.service.VaccineDetailsService;
import com.cts.center.service.VaccineDetailsServiceImpl;

/**
 * This Class is marked as Rest Controller VaccineDetailsController
 * Default context path for this microservice is /vaccine-center
 * Here, You can add your @ResquestMapping
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vaccine")
public class VaccineDetailsController {
	@Autowired
	VaccineDetailsServiceImpl vaccineDetailsService;
	
	@Autowired
	AuthorisationClient authorisationClient;
	
	/**
	 * This method healthCheck used to check RestController Path
	 * @return HttpStatus
	 */
	@GetMapping("/health-check-1")
	public ResponseEntity<String> vaccineDetailsControllerHealthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}
	
	/**
	 * This method addVaccineDetailsEntity based on VaccineDetails Object as parameter
	 * 
	 * @param token
	 * @param VaccineDetails
	 * @return
	 * @throws VaccineDetailsNotFoundException
	 */
	@PostMapping
	public ResponseEntity<String> addVaccineDetails(@RequestHeader(name = "Authorization") String token,
			@RequestBody VaccineDetails vaccineDetails)throws VaccineCenterNotFoundException{
		if (authorisationClient.validate(token)) {
			vaccineDetailsService.addVaccineDetails(vaccineDetails);
			return new ResponseEntity<String>("Vaccine Added",HttpStatus.CREATED);
		}else {
			throw new VaccineDetailsNotFoundException("Token Issue");
		}
		
	}
	@GetMapping
	public ResponseEntity<List<VaccineDetails>> getVaccineDetails(){
		return new ResponseEntity<List<VaccineDetails>>(vaccineDetailsService.getVaccineDetails(),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{vaccineId}")
	public ResponseEntity<?> deleteVaccineDetail(@RequestHeader(name = "Authorization") String token,
			@PathVariable int vaccineId) {
		ResponseEntity<?> responsePayload = null;

		if (authorisationClient.validate(token)) {
			
			vaccineDetailsService.deleteVaccineDetailById(vaccineId);
			
			responsePayload = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			responsePayload = new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
		}

		return responsePayload;

	}
}

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.center.exception.VaccineCenterNotFoundException;
import com.cts.center.feign.AuthorisationClient;
import com.cts.center.model.VaccineCenter;
import com.cts.center.service.VaccineCenterService;
import com.cts.center.service.VaccineCenterServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * This Class is marked as Rest Controller VaccineCenterController Default
 * context path for this microservice is /vaccine-center Here, You can add
 * your @ResquestMapping
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/center")
public class VaccineCenterController {

	@Autowired
	VaccineCenterServiceImpl vaccineCenterService;

	@Autowired
	AuthorisationClient authorisationClient;

	/**
	 * This method healthCheck used to check RestController Path
	 * 
	 * @return HttpStatus
	 */
	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}

	/**
	 * This method addVaccineCenter based on VaccineCenter Object as parameter
	 * 
	 * @author Sakshi
	 * @param token
	 * @param VaccineCenter
	 * @return
	 * @throws VaccineCenterNotFoundException
	 */
	@PostMapping
	public ResponseEntity<String> addVaccineCenter(@RequestHeader(name = "Authorization") String token,
			@RequestBody VaccineCenter vaccine) throws VaccineCenterNotFoundException {
		if (authorisationClient.validate(token)) {
			vaccineCenterService.addVaccineCenter(vaccine);
			return new ResponseEntity<>("Vaccine Center Created", HttpStatus.CREATED);
		} else {
			throw new VaccineCenterNotFoundException("Token Issue");
		}

	}

	/**
	 * @author AtharvaKamble
	 * @param token    {String}
	 * @param editInfo {VaccineCenter}
	 * @param centerId {int}
	 * @return
	 */
	@PutMapping("/edit/{centerId}")
	public ResponseEntity<?> editVaccineCenter(@RequestHeader(name = "Authorization") String token,
			@RequestBody VaccineCenter editInfo, @PathVariable int centerId) {

		ResponseEntity<?> responsePayload = null;
		if (authorisationClient.validate(token)) {
			if (vaccineCenterService.editVaccineCenter(editInfo, centerId)) {
				responsePayload = new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				responsePayload = new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		} else {
			responsePayload = new ResponseEntity<>("Token Invalid", HttpStatus.FORBIDDEN);
		}

		return responsePayload;
	}

	/**
	 * @author AtharvaKamble
	 * @param token    {String}
	 * @param centerId {int}
	 * @return ResponseEntity
	 */
	@DeleteMapping("/delete/{centerId}")
	public ResponseEntity<?> deleteVaccineCenter(@RequestHeader(name = "Authorization") String token,
			@PathVariable int centerId) {

		ResponseEntity<?> responsePayload = null;

		if (authorisationClient.validate(token)) {
			if (vaccineCenterService.deleteVaccineCenter(centerId)) {
				responsePayload = new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				responsePayload = new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
			}
		} else {
			responsePayload = new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
		}
		return responsePayload;

	}

	/**
	 * @author Shridhar
	 * @return ResponseEntity<List<VaccineCenter>>
	 */
	@GetMapping
	public ResponseEntity<List<VaccineCenter>> getAllVaccineCenter() {
		ResponseEntity<List<VaccineCenter>> res = null;
		List<VaccineCenter> vaccinecenter = vaccineCenterService.getVaccineCenters();

		res = new ResponseEntity<List<VaccineCenter>>(vaccinecenter, HttpStatus.OK);

		return res;
	}

	/**
	 * @author rahul
	 * @param centerId
	 * @return
	 */
	@GetMapping("/{centerId}")
	public ResponseEntity<VaccineCenter> getVaccineCenterById(@PathVariable int centerId) {
		ResponseEntity<VaccineCenter> res = null;
		VaccineCenter vaccinecenter = vaccineCenterService.getVaccineCenterById(centerId);

		res = new ResponseEntity<VaccineCenter>(vaccinecenter, HttpStatus.OK);

		return res;
	}
}

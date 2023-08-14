package com.cts.center.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.center.model.VaccineCenter;

@Repository
public interface VaccineCenterRepo extends JpaRepository<VaccineCenter, Integer> {

	/**
	 * Query to fetch all vaccine centers
	 * @author Shridhar
	 * @return List<VaccineCenter>
	 */
	@Transactional
	@Query(value = "SELECT * FROM vaccine_center;", nativeQuery = true)
	public List<VaccineCenter> fetchAllVaccineCenter();
}

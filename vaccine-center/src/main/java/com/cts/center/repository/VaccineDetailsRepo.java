package com.cts.center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.center.model.VaccineDetails;

/**
 * 
 * @author your name
 * VaccineDetailsRepo Repository
 */
@Repository
public interface VaccineDetailsRepo extends JpaRepository<VaccineDetails, Integer>{

}

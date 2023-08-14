package com.cts.center.model;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vaccine_center")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int centerId;
	private String centerName;
	private String centerAddress;
	private String centerDistrict;
	private int centerPinCode;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "vaccineCenter", cascade = CascadeType.ALL)
	private List<VaccineDetails> detailsList = new ArrayList<>();
}

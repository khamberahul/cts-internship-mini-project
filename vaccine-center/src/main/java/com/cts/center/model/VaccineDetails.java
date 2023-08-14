package com.cts.center.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="vaccine_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vaccineId;
	private String vaccineName;
	private double vaccinePrice;
	private int vaccineQuantity;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="center_id")
	private VaccineCenter vaccineCenter;
}

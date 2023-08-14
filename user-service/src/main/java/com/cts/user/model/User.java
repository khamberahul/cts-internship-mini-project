package com.cts.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiOperation(value = "Model class for Vaccines.")
@ApiModel(value = "Model class for Vaccines.")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int userId;
	@NotNull(message = "Invalid Name: Name is NULL")
	@ApiModelProperty(value = "Name of the User.")
	@Column(name="name")
	private String fullName; 
	@NotNull(message = "Invalid Aadhar Number: Aadhar Number is NULL")
	@ApiModelProperty(value = "Aadhar Number of the User.")
	@Column(name="aadhar_number")
	private Long aadharNumber;
//	@NotNull(message = "Invalid DOB: DOB is NULL")
	@ApiModelProperty(value = "Date of Birth of the User.")
	@Column(name="dob")
	private Date dob;
	@Min(value = 18, message = "Invalid Age: Equals to 18 or Less than 18")
    @Max(value = 100, message = "Invalid Age: Exceeds 100 years")
	@ApiModelProperty(value = "Age of the User.")
	@Column(name="age")
	private int age;
	@NotNull(message = "Invalid Gender: Gender is NULL")
	@Column(name="gender")
	private String gender; 
	@Column(name="phoneNumber")
	private String phoneNumber;
	@NotNull(message = "Invalid address: address is NULL")
	@Column(name="address")
	private String address;
	@NotNull(message = "Invalid username: username is NULL")
	@Column(name="username")
	private String username;
	@NotNull(message = "Invalid userEmail: userEmail is NULL")
	@Column(name = "usermail")
	private String userEmail;
	@NotNull(message = "Invalid userPassword: userPassword is NULL")
	@Column(name="password")
	private String userPassword;
	@Column(name="userType")
	private String userType;
	@Column(name="vaccination_date")
	private Date vaccinationDate;
	@Column(name="vaccination_status")
	private String vaccinationStatus = "pending";
	@Column(name="userStatus")
	private String userStatus = "pending";
	@Column(name="center_id")
	private String centerId;
	
	private String vaccineId;
}

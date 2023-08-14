package com.cts.user.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class is for customizing the exception handler
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

	private String message;
	private LocalDateTime dateTime;
}

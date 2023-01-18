package com.rent.car.security.models;

import com.rent.car.custom.validator.ValidPassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {

	private String oldPassword;
	@ValidPassword
	private String newPassword;
	private String confirmNewPassword;
}

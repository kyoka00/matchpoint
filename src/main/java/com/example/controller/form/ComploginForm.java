package com.example.controller.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ComploginForm {
	@NotBlank
	private String compLoginId;

}

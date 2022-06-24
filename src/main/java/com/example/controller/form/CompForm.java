package com.example.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CompForm{
	@NotBlank
	private String compName;
	@NotBlank
	private String compDate;
	@NotBlank
	private String compPlace;
	@NotBlank
	private String compLoginId;
	@NotNull
	private Integer tournamentNum;
	
	private Integer gameType;
	
	private String gameTypeStr;
	
	private String memo;
}


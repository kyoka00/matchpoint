package com.example.controller.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CompForm{
	private Integer compId;
	@NotNull
	private String compName;
	@NotNull
	private String compDate;
	@NotNull
	private String compPlace;
	private String compLoginId;
	@NotNull
	private Integer tournamentNum;
	@NotNull
	private Integer gameType;
	private String gameTypeStr;
	private String memo;
}
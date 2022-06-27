package com.example.controller.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GameInfoForm{
	@NotNull
	private Integer gameNo;
	private Integer maxPoint;
	private Integer gameCount;
	
	private Integer coatNo;
	private String judgeName;
}
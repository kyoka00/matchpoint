package com.example.controller.form;

import lombok.Data;

@Data
public class CreateCompForm{
	private String compName;
	private String compDate;
	private String compPlace;
	private String compLoginId;
	private Integer rank;
	private Integer gameType;
	private String memo;
}
package com.example.controller.form;

import lombok.Data;

@Data
public class CompForm{

	private Integer compId;
	private String compName;
	private String compDate;
	private String compPlace;
	private String compLoginId;
	private Integer tournamentCount;
	private Integer gameType;
	
	private String gameTypeStr;
	
	private String memo;
}


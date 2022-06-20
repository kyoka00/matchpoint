package com.example.entity;

import lombok.Data;

@Data
public class Comp{
	private String compName;
	private String compDate;
	private String compPlace;
	private String compLoginId;
	private Integer tournamentNum;
	private Integer gameType;
	private String gameTypeStr;
	private String memo;
}
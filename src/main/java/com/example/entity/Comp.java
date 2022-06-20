package com.example.entity;

import lombok.Data;
import lombok.Getter;

@Data
public class Comp{
	private Integer compId;
	private String compName;
	private String compDate;
	private String compPlace;
	private String compLoginId;
	private Integer tournamentNum;
	private Integer gameType;
	private String gameTypeStr;
	private String memo;
}

package com.example.entity;

import java.util.Date;

import lombok.Data;

@Data

public class Comp{
	private Integer compId;
	private String compName;
	private Date compDate;
	private String compPlace;
	private String compLoginId;
	private Integer tournamentCount;
	private Integer gameType;
	private String gameTypeStr;
	private Integer tournamentEditStatus;
	private String memo;
}


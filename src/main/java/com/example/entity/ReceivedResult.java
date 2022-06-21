package com.example.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ReceivedResult {
	
	private Integer matchId;
	private Integer compId;
	private Integer gameInfoId;
	private Integer gameNo;
	private Integer coatNo;
	private String judgeName;
	private Integer recordStatus;
	private Date recordDate;
	private Integer maxPoint;
	private Integer gameCount;
	private Integer tournamentNo;
	private Integer teamIdA;
	private String teamAPlayer1;
	private String teamAPlayer2;
	private Integer teamIdB;
	private String teamBPlayer1;
	private String teamBPlayer2;
	
}

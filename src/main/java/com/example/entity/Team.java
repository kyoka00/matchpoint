package com.example.entity;

import lombok.Data;

@Data
public class Team{

	private Integer teamId;
	private Integer compId;
	private String playerAName;
	private String playerBName;
	private Integer tournamentNo;
}
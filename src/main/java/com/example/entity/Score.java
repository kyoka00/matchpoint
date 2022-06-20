package com.example.entity;

import lombok.Data;

@Data
public class Score{
	private Integer scoreId;
	private Integer gameInfoId;
	private Integer setNo;
	private Integer teamAScore;
	private Integer teamBScore;
}
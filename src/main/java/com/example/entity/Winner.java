package com.example.entity;

import lombok.Data;
@Data
public class Winner {
	
	private Integer gameNo;
	private Integer winnerTeamId;
	
	public Winner() {
		
	}
	public Winner(Integer gameNo, Integer winnerTeamId) {
		this.gameNo = gameNo;
		this.winnerTeamId = winnerTeamId;
	}
}

package com.example.controller.form;

import lombok.Data;

@Data
public class GamePlayerForm{
	private String playerA;
	private String playerB;
	private String playerC;
	private String playerD;
	private Integer team1Point;
	private Integer team2Point;
}
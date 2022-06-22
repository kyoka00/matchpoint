package com.example.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PlayerForm{
	
//	//プレイヤー１
//	@NotEmpty(message = "名前を入力してください")
//	@Size(max = 100, message = "名前は50桁以内で入力してください")
//	private String player1;
//	
//	//プレイヤ―「
//	@NotEmpty(message = "名前を入力してください")
//	@Size(max = 100, message = "名前は50桁以内で入力してください")
//	private String player2;
//	
//	@NotNull(message = "トーナメント番号を入力してください")
//	private Integer tournamentNum;
	
	@NotBlank
	private String player1;
	@NotBlank
	private String player2;
	@NotNull
	private Integer tournamentNum;
	
}
package com.example.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PlayerForm{
	
	//プレイヤー１	
	@NotBlank
	@NotEmpty(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50桁以内で入力してください")
	private String player1;
	
	//プレイヤー２
	@NotBlank
	@NotEmpty(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50桁以内で入力してください")
	private String player2;
	
	//トーナメント番号
	@NotNull(message = "トーナメント番号を入力してください")
	private Integer tournamentNum;
	
}
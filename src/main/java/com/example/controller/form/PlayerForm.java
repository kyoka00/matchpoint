package com.example.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PlayerForm{
	
	//チームID
	private Integer teamId;
	
	//大会ID
	private Integer compId;
	
	//プレイヤー１	
	@NotBlank
	@Size(max = 25, message = "名前は25文字以内で入力してください")
	private String playerAName;
	
	//プレイヤー２
	@NotBlank
	@Size(max = 25, message = "名前は25文字以内で入力してください")
	private String playerBName;
	
	//トーナメント番号
	@NotNull(message = "トーナメント番号を入力してください")
	private Integer tournamentNo;
	
	//キーワード検索
	private String keyword;

	
}
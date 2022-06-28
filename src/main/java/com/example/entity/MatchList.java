package com.example.entity;

import java.util.List;

import lombok.Data;

@Data
public class MatchList {
	private List<ReceivedResult> matchList;

	public List<ReceivedResult> getMatchList() {
		// TODO 自動生成されたメソッド・スタブ
		return this.matchList;
	}
}

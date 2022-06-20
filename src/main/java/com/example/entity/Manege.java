package com.example.entity;

import lombok.Data;

@Data
public class Manege{
	private String loginId;
	private String password;
	
	public static String select(String[] column) {
		String where = "";
		if(column.length >= 1) {
			where = " WHERE " + column[0] + "= :" + column[0];
			for(int i = 1; i < column.length; i++) {
				where += " AND " + column[i] + " = :" + column[i];
			}
		}
		return where;
	}
}
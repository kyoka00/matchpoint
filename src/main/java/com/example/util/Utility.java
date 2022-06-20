package com.example.util;

public class Utility{
	public static boolean notIsEmptyNull(String str) {
		if(str == null) {
			return false;
		}else if(str.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	public static boolean notIsEmptyNull(Integer i) {
		if(i == null) {
			return false;
		}else {
			return true;
		}
	}
}
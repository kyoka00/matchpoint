package com.example.dao;

import java.util.List;

import com.example.entity.Manege;

public interface ManegeDao{
	public List<Manege> selectAll(Manege manege);
	public void insertManege(Manege manege);
	public void deleteManege(Manege manege);
}
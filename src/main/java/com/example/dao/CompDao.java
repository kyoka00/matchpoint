package com.example.Dao;

import java.util.List;

import com.example.Enitity.Comp;

public interface CompDao {
	
	public List<Comp> find();
	
	public Comp compLoginId(String CompLoginId, int id);
	
	public int insert(Comp c);

}

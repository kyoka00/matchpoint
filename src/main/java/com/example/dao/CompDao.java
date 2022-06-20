package com.example.dao;

import java.util.List;

import com.example.entity.Comp;
import com.example.entity.Manege;

public interface CompDao{
	public List<Comp> selectAll(Comp comp);
	public void insertComp(Comp comp);
	public void deleteComp(Comp comp);
	public void updateComp(Comp comp);
}
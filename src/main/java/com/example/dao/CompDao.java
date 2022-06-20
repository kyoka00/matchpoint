package com.example.dao;

import java.util.List;

import com.example.entity.Comp;

public interface CompDao{
	public List<Comp> findBycompName(Comp compName);
}
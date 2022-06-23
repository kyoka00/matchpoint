package com.example.dao;

import java.util.List;

import com.example.entity.GameResultAll;

public interface GameResultAllDao{
	public List<GameResultAll> selectAll(GameResultAll gameResultAll);
	public List<GameResultAll> find(String keyword);
}
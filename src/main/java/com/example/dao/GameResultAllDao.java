package com.example.dao;

import java.util.List;

import com.example.entity.GameResultAll;

public interface GameResultAllDao{
	public List<GameResultAll> selectAll(GameResultAll gameResultAll, String keyword);
	public List<GameResultAll> find(Integer keyword);
}
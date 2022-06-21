package com.example.dao;

import java.util.List;

import com.example.entity.Team;

public interface TeamDao{
	public List<Team> selectAll(Team team);
	public void insertScore(Team team);
	public void deleteScore(Team team);
	public void updateScore(Team team);
}
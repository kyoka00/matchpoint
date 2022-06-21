package com.example.dao;

import java.util.List;

import com.example.entity.Team;

public interface TeamDao{
	public List<Team> selectAll(Team team);
	public void insertTeam(Team team);
	public void deleteTeam(Team team);
	public void updateTeam(Team team);
}
package com.example.dao;

import java.util.List;

import com.example.entity.Team;

public interface TeamDao{
	public List<Team> selectAll(Team team, String keyword);
	public int insertTeam(Team team);
	public int deleteTeam(Team team);
	public int updateTeam(Team team);
}
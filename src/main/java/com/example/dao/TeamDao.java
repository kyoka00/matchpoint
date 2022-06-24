package com.example.dao;

import java.util.List;

import com.example.entity.Team;

public interface TeamDao{
	public List<Team> selectAll(Team team, String keyword);
<<<<<<< HEAD
	public List<Team> gameTeam(Integer team1, Integer team2);
	public void insertTeam(Team team);
	public void deleteTeam(Team team);
	public void updateTeam(Team team);
=======
	public int insertTeam(Team team);
	public int deleteTeam(Team team);
	public int updateTeam(Team team);
>>>>>>> 83c24d2977421235b9f05d8bde0f290581a76ee9
}
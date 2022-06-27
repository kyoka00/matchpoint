package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.CompDao;
import com.example.dao.ManageDao;
import com.example.dao.ReceivedResultDao;
import com.example.dao.ScoreDao;
import com.example.dao.TeamDao;
import com.example.entity.ReceivedResult;
import com.example.entity.Team;

@RestController
public class TournamentRestController {
	@Autowired
	HttpSession session;
	
	@Autowired
	ManageDao manageDao;
	
	@Autowired
	ScoreDao scoreDao;

	@Autowired
	CompDao compDao;
	
	@Autowired
	TeamDao teamDao;

	@Autowired
	ReceivedResultDao receivedResultDao;
	
	@RequestMapping("getTeamList")
	public List<Team> getTeamLIst(){
		
		Integer compId = (Integer)session.getAttribute("compId");
		Team team = new Team();
		team.setCompId(compId);
		return teamDao.selectAll(team, null);
	}
	
	@RequestMapping("getMatchList")
	public List<ReceivedResult> getMatchLIst(){
		Integer compId =  (Integer)session.getAttribute("compId");
		ReceivedResult result= new ReceivedResult();
		result.setCompId(compId);
		List<ReceivedResult> resultList = receivedResultDao.searchMatchTeam(result);
		return resultList;
	}
	
	@RequestMapping("searchMatchByGameNo")
	public List<ReceivedResult> getGameInfo(@RequestParam("gameNo") Integer gameNo){
		Integer compId = (Integer)session.getAttribute("compId");
		ReceivedResult result = new ReceivedResult();
		result.setCompId(compId);
		result.setGameNo(gameNo);
		result.setRecordStatus(1);
		return receivedResultDao.search(result, null);
	}
	
//	@RequestMapping("")
//	public int insertMatch(Integer gameNo, Integer teamIdA, Integer teamIdB) {
//		Integer compId = (Integer)session.getAttribute("compId");
//		ReceivedResult result= new ReceivedResult();
//		result.setCompId(compId);
//		result.setGameNo(gameNo);
//		result.setTeamIdA(teamIdA);
//		result.setTeamIdB(teamIdB);
//		return receivedResultDao.insertMatch(result);
//	}
//	
//	@RequestMapping("")
//	public int updateMatch(Integer gameNo, Integer teamIdA, Integer teamIdB) {
//		ReceivedResult result= new ReceivedResult();
//		result.setGameNo(gameNo);
//		result.setTeamIdA(teamIdA);
//		result.setTeamIdB(teamIdB);
//		return receivedResultDao.updateMatch(result);
//	}
//	
	@RequestMapping("getTournamentEditStatus")
	public int getComp() {
		Integer compId = (Integer)session.getAttribute("compId");
		Comp comp = new Comp();
		comp.setCompId(compId);
		List<Comp> compList = compDao.selectAll(comp);
		return compList.isEmpty()? null: compList.get(0).getTournamentEditStatus();
	}
//	
//	@RequestMapping("")
//	public void updateComp(Integer tournamentEditStatus) {
//		Integer compId = (Integer)session.getAttribute("compId");
//		Comp comp = new Comp();
//		comp.setCompId(compId);
//		comp.setTournamentEditStatus(tournamentEditStatus);
//		compDao.updateComp(comp);
//		//return compDao.updateComp(comp);
//	}

}


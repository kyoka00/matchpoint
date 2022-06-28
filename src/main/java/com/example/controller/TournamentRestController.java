package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.CompDao;
import com.example.dao.ManageDao;
import com.example.dao.ReceivedResultDao;
import com.example.dao.ScoreDao;
import com.example.dao.TeamDao;
import com.example.entity.Comp;
import com.example.entity.MatchList;
import com.example.entity.ReceivedResult;
import com.example.entity.Score;
import com.example.entity.Team;
import com.example.entity.Winner;

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
	
	@RequestMapping("insertMatch")
	public int insertMatch(@RequestBody MatchList matchList) {
		System.out.println("insertMatch");
		int insertNum = 0;
		Integer compId = (Integer)session.getAttribute("compId");
		for(ReceivedResult result : matchList.getMatchList()) {
			result.setCompId(compId);
			insertNum += receivedResultDao.insertMatch(result);
		}
		System.out.println("insert is finished");
		return insertNum;
	}
	
	@RequestMapping("updateMatch")
	public int updateMatch(@RequestBody MatchList matchList) {
		System.out.println("updateMatch");
		int updateNum = 0;
		Integer compId = (Integer)session.getAttribute("compId");
		for(ReceivedResult result : matchList.getMatchList()) {
			result.setCompId(compId);
			updateNum += receivedResultDao.updateMatch(result);
		}
		System.out.println("update is finished");
		return updateNum;
	}
	
	@RequestMapping("getTournamentEditStatus")
	public int getComp() {
		int insertNum = 0;
		Integer compId = (Integer)session.getAttribute("compId");
		Comp comp = new Comp();
		comp.setCompId(compId);
		List<Comp> compList = compDao.selectAll(comp);
		return compList.isEmpty()? null: compList.get(0).getTournamentEditStatus();
	}
	
	@RequestMapping("changeEditStatus")
	public void updateComp(Integer tournamentEditStatus) {
		Integer compId = (Integer)session.getAttribute("compId");
		Comp comp = new Comp();
		comp.setCompId(compId);
		comp.setTournamentEditStatus(tournamentEditStatus);
		compDao.updateComp(comp);
		//return compDao.updateComp(comp);
	}
	
	@RequestMapping("getWinner")
	public List<Winner> getWinner(){
		Integer compId = (Integer)session.getAttribute("compId");
		ReceivedResult result = new ReceivedResult();
		result.setCompId(compId);
		result.setRecordStatus(1);
		List<ReceivedResult> resultList = receivedResultDao.search(result,null);
		List<Winner> winnerTeam = new ArrayList<Winner>();

		for(ReceivedResult r: resultList) {
			Score score = new Score();
			score.setGameInfoId(r.getGameInfoId());
			List<Score> scoreList = scoreDao.selectAll(score);
			
			int winCountA = 0;
			int winCountB = 0;
			if(scoreList != null) {
				for(Score s : scoreList) {
					if(s.getTeamAScore() > s.getTeamBScore()) {
						winCountA ++;
					}else if(s.getTeamAScore() < s.getTeamBScore()){
						winCountB ++;
					}
				}
			}
			if(winCountA > winCountB) {
				winnerTeam.add(new Winner(r.getGameNo(), r.getTeamIdA()));
			}else {
				winnerTeam.add(new Winner(r.getGameNo(), r.getTeamIdB()));
			}
		}
		return winnerTeam;
	}
//	@RequestMapping("")
//	public List<Winner> getWinner(){
//		Integer compId = (Integer)session.getAttribute("compId");
//		ReceivedResult result = new ReceivedResult();
//		result.setCompId(compId);
//		result.setRecordStatus(1);
//		List<ReceivedResult> resultList = receivedResultDao.search(result,null);
//		List<Winner> winnerTeam = new ArrayList<Winner>();
//
//		for(ReceivedResult r: resultList) {
//			Score score = new Score();
//			score.setGameInfoId(r.getGameInfoId());
//			List<Score> scoreList = scoreDao.selectAll(score);
//			
//			int winCountA = 0;
//			int winCountB = 0;
//			if(scoreList != null) {
//				for(Score s : scoreList) {
//					if(s.getTeamAScore() > s.getTeamBScore()) {
//						winCountA ++;
//					}else if(s.getTeamAScore() < s.getTeamBScore()){
//						winCountB ++;
//					}
//				}
//			}
//			if(winCountA > winCountB) {
//				winnerTeam.add(new Winner(r.getGameNo(), r.getTeamIdA()));
//			}else {
//				winnerTeam.add(new Winner(r.getGameNo(), r.getTeamIdB()));
//			}
//		}
//		return winnerTeam;
//	}
}


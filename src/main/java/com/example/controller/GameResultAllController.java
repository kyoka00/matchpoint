package com.example.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.form.GamePlayerForm;
import com.example.controller.form.GameResultAllForm;
import com.example.dao.GameResultAllDao;
import com.example.dao.ReceivedResultDao;
import com.example.dao.ScoreDao;
import com.example.entity.GameResultAll;
import com.example.entity.ReceivedResult;
import com.example.entity.Score;
import com.example.util.Utility;

@Controller
public class GameResultAllController{
	@Autowired
	HttpSession session;
	
	@Autowired
	GameResultAllDao gameResultAllDao;
	
	@Autowired
	ReceivedResultDao receivedResultDao;
	
	@Autowired
	ScoreDao scoreDao;
	
	//試合結果受信box
	@RequestMapping(value="game_result_all")
	public String gameResultAll(@ModelAttribute("comp_detail") GameResultAllForm form, Model model) {
		if (session.getAttribute("loginId") == null && session.getAttribute("compLoginId") == null) {
			return "top";
		}

		List<GameResultAll> resultList = gameResultAllDao.select(0);
		form.setRecordStatus(0);
		model.addAttribute("resultList", resultList);
		return "game_result_all";
	}

	@RequestMapping(value = "game_result_registered")
	public String gameResultRegistered(@ModelAttribute("comp_detail") GameResultAllForm form, Model model) {
		if (session.getAttribute("loginId") == null) {
			return "top";
		}

		List<GameResultAll> resultList = gameResultAllDao.select(1);
		form.setRecordStatus(1);
		model.addAttribute("resultList", resultList);
		return "game_result_all";
	}

	@RequestMapping(value = "game_result_search")
	public String search(@ModelAttribute("comp_detail") GameResultAllForm form, Model model) {
		if (session.getAttribute("loginId") == null && session.getAttribute("compLoginId") == null) {
			return "top";
		}
	
		int recordStatus = form.getRecordStatus();
		String keyword = form.getKeyword();

		
		if(recordStatus == 0){
			List<GameResultAll> search = gameResultAllDao.search(keyword, 0);
			model.addAttribute("resultList", search);
			return "game_result_all";
		}
		
		if(recordStatus == 1){
			List<GameResultAll> search = gameResultAllDao.search(keyword, 1);
			model.addAttribute("resultList", search);
		}

		return "game_result_all";
	}

	@RequestMapping(value = "sort")

	public String sort(@RequestParam("orderBy")  String orderBy,
			@ModelAttribute("comp_detail") GameResultAllForm form,Model model) {
		
		int recordStatus = form.getRecordStatus();

		if(recordStatus == 0) {
			List<GameResultAll> gameResultList = gameResultAllDao.select(0);
			if ("record_date".equals(orderBy)) {
				gameResultList.sort((p1, p2) -> p1.getRecordDate().compareTo(p2.getRecordDate()));
			}else if ("game_no".equals(orderBy)) {
				gameResultList.sort((p1, p2) -> p1.getGameNo() >= p2.getGameNo() ? 1 : -1);
			} else if ("coat_no".equals(orderBy)) {
				gameResultList.sort((p1, p2) -> p1.getCoatNo() >= p2.getCoatNo() ? 1 : -1);	
			} else if ("tournament_no".equals(orderBy)) {
				gameResultList.sort((p1, p2) -> p1.getTournamentNo() >= p2.getTournamentNo() ? 1 : -1);
			}
			model.addAttribute("resultList", gameResultList);
			return "game_result_all";
		}
		
		if(recordStatus == 1) {
			List<GameResultAll> gameResultList = gameResultAllDao.select(1);
			if ("record_date".equals(orderBy)) {
				gameResultList.sort((p1, p2) -> p1.getRecordDate().compareTo(p2.getRecordDate()));
			}else if ("game_no".equals(orderBy)) {
				gameResultList.sort((p1, p2) -> p1.getGameNo() >= p2.getGameNo() ? 1 : -1);
			} else if ("coat_no".equals(orderBy)) {
				gameResultList.sort((p1, p2) -> p1.getCoatNo() >= p2.getCoatNo() ? 1 : -1);	
			} else if ("tournament_no".equals(orderBy)) {
				gameResultList.sort((p1, p2) -> p1.getTournamentNo() >= p2.getTournamentNo() ? 1 : -1);
			}
			model.addAttribute("resultList", gameResultList);
		}
		return "game_result_all";
	}

	// 試合結果登録へ
	@RequestMapping(value = "game_result_final")
	public String resultFinal(@ModelAttribute("comp_detail") GamePlayerForm form, Model model) {
		if (session.getAttribute("loginId") == null && session.getAttribute("compLoginId") == null) {
			return "top";
		}

		Score score = new Score();

		score.setGameInfoId((Integer) session.getAttribute("game_info_id"));
		List<Score> list = scoreDao.selectAll(score);
		if(list == null) {
			score.getSetNo();
			list = new ArrayList<Score>();
		}
		score.setTeamAScore(form.getTeam1Point());
		score.setTeamBScore(form.getTeam2Point());
		scoreDao.insertScore(score);
		//insert??
		int winCountA = score.getTeamAScore();
		int winCountB = score.getTeamBScore();
		List<String> scoreList = new ArrayList<String>();
		if(list != null) {
			for(Score s : list) {
				scoreList.add(s.getTeamAScore() + "対" + s.getTeamBScore());
			}
		}
		model.addAttribute("set", (winCountA + winCountB) + "/" + session.getAttribute("game_count"));
		model.addAttribute("playerA", form.getPlayerA());
		model.addAttribute("playerB", form.getPlayerB());
		model.addAttribute("playerC", form.getPlayerC());
		model.addAttribute("playerD", form.getPlayerD());
		model.addAttribute("score_list", scoreList);
		model.addAttribute("setNumA", winCountA);
		model.addAttribute("setNumB", winCountB);
		
		return "game_result_final";
	}
	
	@RequestMapping(value="tournament_register")
	public String tournamentRegister(@ModelAttribute("comp_detail") GamePlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null && session.getAttribute("compLoginId")== null) {
			return "top";
		}
		
		ReceivedResult receivedResult = new ReceivedResult();
		Integer recordStatus = form.getRecordStatus();
		receivedResult.setGameInfoId((Integer) session.getAttribute("game_info_id"));
		
		
		if(recordStatus == 0) {
			recordStatus = 1;
			receivedResult.setRecordStatus(recordStatus);
			receivedResultDao.update(receivedResult);
			return "tournament";
		}
		
		if(Utility.notIsEmptyNull(receivedResult.getMatchId()) && recordStatus == 1) {
			recordStatus = 0;
			receivedResult.setRecordStatus(recordStatus);
			receivedResultDao.update(receivedResult);
		}
		return "tournament";
	}
}
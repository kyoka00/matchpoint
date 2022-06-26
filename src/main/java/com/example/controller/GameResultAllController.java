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

import com.example.controller.form.CompForm;
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
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		GameResultAll gameResultAll = new GameResultAll();
		ReceivedResult receivedResult = new ReceivedResult();
		
		gameResultAll.setGameNo(form.getGameNo());
		receivedResult.setRecordStatus(0);
		
		if(receivedResult.getRecordStatus() == 0 || receivedResult.getRecordStatus() == 1) {
			List<GameResultAll> resultList = gameResultAllDao.selectAll(gameResultAll, "");
			model.addAttribute("resultList", resultList);
		}
		return "game_result_all";
	}
	
	@RequestMapping(value = "game_result_search")
	public String search(@ModelAttribute("comp_detail") GameResultAllForm form, Model model) {
		
		GameResultAll gameResultAll = new GameResultAll();
		
		gameResultAll.setGameNo(form.getGameNo());
		
		String keyword = form.getKeyword();
		System.out.println(keyword);
		List<GameResultAll> search = gameResultAllDao.selectAll(gameResultAll, keyword);
		model.addAttribute("resultList", search);
		return "game_result_all";
	}
	
	@RequestMapping(value = "sort")
	public String sort(@RequestParam("orderBy")  String orderBy, Model model) {
		System.out.println("コントロール" + orderBy);
		GameResultAll gameResultAll = new GameResultAll();

		List<GameResultAll> gameResultList = gameResultAllDao.selectAll(gameResultAll, "");
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
	
	//試合結果登録へ
	@RequestMapping(value="game_result_final")
	public String resultFinal(@ModelAttribute("comp_detail") GamePlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		Score score = new Score();

		score.setGameInfoId(1);
		List<Score> list = scoreDao.selectAll(score);
		score.setTeamAScore(11);
		score.setTeamBScore(8);
		if(list == null) {
			score.setSetNo(0);
			list = new ArrayList<Score>();
			list.add(score);
		}else {
			score.setSetNo(list.size() + 1);
			list.add(score);
		}
		scoreDao.insertScore(score);
		int winCountA = 2;
		int winCountB = 1;
		List<String> scoreList = new ArrayList<String>();
		
		scoreList.add(11 + "対" + 8);
		model.addAttribute("set", (winCountA + winCountB) + "/" + 3);
		model.addAttribute("playerA", "大城");
		model.addAttribute("playerB", "金城");
		model.addAttribute("playerC", "名嘉");
		model.addAttribute("playerD", "野原");
		model.addAttribute("score_list", scoreList);
		model.addAttribute("setNumA", winCountA);
		model.addAttribute("setNumB", winCountB);
		
		return "game_result_final";
	}
	
	@RequestMapping(value="tournament_register")
	public String tournamentRegister(@ModelAttribute("comp_detail") GamePlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		ReceivedResult receivedResult = new ReceivedResult();
		int recordStatus = 0;
		receivedResult.setGameInfoId(1);
		
		receivedResult.setRecordStatus(recordStatus);
		
		if(Utility.notIsEmptyNull(receivedResult.getMatchId()) && receivedResult.getRecordStatus() == 0) {
			recordStatus = 1;
			receivedResult.setRecordStatus(recordStatus);
			receivedResultDao.update(receivedResult);
			System.out.println(receivedResult.getRecordStatus());
		}
		if(Utility.notIsEmptyNull(receivedResult.getMatchId()) && receivedResult.getRecordStatus() == 1) {
			recordStatus = 0;
			receivedResult.setRecordStatus(recordStatus);
			receivedResultDao.update(receivedResult);
			System.out.println(receivedResult.getRecordStatus());
		}
		return "tournament";
	}
}
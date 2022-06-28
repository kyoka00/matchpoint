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
		Integer compId = (Integer)session.getAttribute("compId");
		List<ReceivedResult> list = receivedResultDao.box(compId, 0, "");
		form.setRecordStatus(0);
		model.addAttribute("list", list);
		return "game_result_all";
	}

	@RequestMapping(value = "game_result_registered")
	public String gameResultRegistered(@ModelAttribute("comp_detail") GameResultAllForm form, Model model) {
		if (session.getAttribute("loginId") == null) {
			return "top";
		}
		Integer compId = (Integer)session.getAttribute("compId");
		List<ReceivedResult> list = receivedResultDao.box(compId, 1, "");
		form.setRecordStatus(1);
		model.addAttribute("list", list);
		return "game_result_all";
	}

	@RequestMapping(value = "game_result_search")
	public String search(@ModelAttribute("comp_detail") GameResultAllForm form, Model model) {
		if (session.getAttribute("loginId") == null && session.getAttribute("compLoginId") == null) {
			return "top";
		}
		
		
		Integer compId = (Integer)session.getAttribute("compId");
		List<ReceivedResult> list = receivedResultDao.box(compId, form.getRecordStatus(), form.getKeyword());
		form.setRecordStatus(1);
		model.addAttribute("list", list);
		System.out.println(list.get(0).getGameInfoId());
		
		
//		int recordStatus = form.getRecordStatus();
//		String keyword = form.getKeyword();
//
//		if(Utility.notIsEmptyNull(keyword)){
//			List<GameResultAll> search = gameResultAllDao.search(keyword, recordStatus);
//			model.addAttribute("resultList", search);
//			return "game_result_all";
//		} else {
//			List<GameResultAll> search = gameResultAllDao.search("", recordStatus);
//			model.addAttribute("resultList", search);
//		}
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
	public String resultFinal(@ModelAttribute("team") GamePlayerForm form, Model model) {
		if (session.getAttribute("loginId") == null && session.getAttribute("compLoginId") == null) {
			return "top";
		}

		Integer gameInfoId = form.getGameInfoId();
		Score score = new Score();
		score.setGameInfoId(gameInfoId);
		List<Score> list = scoreDao.selectAll(score);
		
		ReceivedResult result = new ReceivedResult();
		result.setGameInfoId(gameInfoId);
		ReceivedResult searchResult = receivedResultDao.search(result, "").get(0);
		
		int winCountA = 0;
		int winCountB = 0;
		List<String> scoreList = new ArrayList<String>();

		
		if(list != null) {

			for(Score s : list) {
				scoreList.add(s.getTeamAScore() + "対" + s.getTeamBScore());
				
				if(s.getTeamAScore() > s.getTeamBScore()) {
					winCountA ++;
				}else if(s.getTeamAScore() < s.getTeamBScore()){
					winCountB ++;
				}
			}
		}
		System.out.println(searchResult);
		model.addAttribute("set", (winCountA + winCountB) + "/" + searchResult.getGameCount());
		model.addAttribute("team", searchResult);
		model.addAttribute("score_list", scoreList);
		model.addAttribute("setNumA", winCountA);
		model.addAttribute("setNumB", winCountB);
		
		return "game_result_final";
	}
	
	@RequestMapping(value = "tournament_register", params = "register")
	public String tournamentRegister(@ModelAttribute("team") GamePlayerForm form, Model model) {
		if (session.getAttribute("loginId") == null && session.getAttribute("compLoginId") == null) {
			return "top";
		}

		if (form.getRecordStatus() == 0) {
			ReceivedResult search = new ReceivedResult();
			search.setGameNo(form.getGameNo());
			search.setRecordStatus(1);
			List<ReceivedResult> check = receivedResultDao.search(search, null);
			if (check != null) {
				model.addAttribute("msg", "既に登録済みの情報があります。");
			} else {
				ReceivedResult receivedResult = new ReceivedResult();
				receivedResult.setGameInfoId(form.getGameInfoId());
				receivedResult.setRecordStatus(1);
				int count = receivedResultDao.update(receivedResult);
				if (count == 0) {
					model.addAttribute("msg", "登録できませんでした。");
				} else {
					model.addAttribute("msg", "登録しました");
				}
			}

		}
		return "tournament";
	}
	@RequestMapping(value = "tournament_register", params = "delete")
	public String tournamentDelete(@ModelAttribute("comp_detail") GamePlayerForm form, Model model) {
		if (session.getAttribute("loginId") == null && session.getAttribute("compLoginId") == null) {
			return "top";
		}

		if (form.getRecordStatus() == 0) {
				ReceivedResult receivedResult = new ReceivedResult();
				receivedResult.setGameInfoId(form.getGameInfoId());
				receivedResult.setRecordStatus(2);
				int count = receivedResultDao.update(receivedResult);
				if (count == 0) {
					model.addAttribute("msg", "削除できませんでした。");
				} else {
					model.addAttribute("msg", "削除しました");
				}
			}

		return "tournament";
	}
}
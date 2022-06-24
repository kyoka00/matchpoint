package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.form.GameInfoForm;
import com.example.controller.form.GamePlayerForm;
import com.example.dao.ReceivedResultDao;
import com.example.dao.ScoreDao;
import com.example.dao.TeamDao;
import com.example.entity.ReceivedResult;
import com.example.entity.Score;
import com.example.entity.Team;

@Controller
public class GameModeController{
	@Autowired
	HttpSession session;
	
	@Autowired
	ReceivedResultDao receivedResultDao;
	
	@Autowired
	TeamDao teamDao;
	
	@Autowired
	ScoreDao scoreDao;
	
	//試合設定画面
	@RequestMapping(value="match")
	public String gameSetting(@ModelAttribute("game_info") GameInfoForm form) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		form.setGameNo(10);
		form.setMaxPoint(21);
		form.setGameCount(3);
		return "game_setting";
	}
	
	@RequestMapping(value="server_setting")
	public String redirectServerSetting(@ModelAttribute("score_setting") GamePlayerForm playerForm) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		Integer gameInfoId = (Integer)session.getAttribute("game_info_id");
		ReceivedResult receivedResult = new ReceivedResult();
		receivedResult.setGameInfoId(gameInfoId);
		List<ReceivedResult> list = receivedResultDao.search(receivedResult, "");
		List<Team> resultList = teamDao.gameTeam(list.get(0).getTeamIdA(), list.get(0).getTeamIdB());
		playerForm.setPlayerA(resultList.get(0).getPlayerAName());
		playerForm.setPlayerB(resultList.get(0).getPlayerBName());
		playerForm.setPlayerC(resultList.get(1).getPlayerAName());
		playerForm.setPlayerD(resultList.get(1).getPlayerBName());
		playerForm.setServer("playerB");
		return "server_setting";
	}
	
	@RequestMapping(value="server_setting", params="server_setting")
	public String serverSetting(@Validated @ModelAttribute("game_info") GameInfoForm form,
			BindingResult bindingResult, @ModelAttribute("score_setting") GamePlayerForm playerForm) {
		if (bindingResult.hasErrors()) {
			form.setMaxPoint(form.getMaxPoint());
			form.setGameCount(form.getGameCount());
            return "game_setting";
        }
		ReceivedResult receivedResult = new ReceivedResult();
		receivedResult.setGameNo(form.getGameNo());
		//あとから変えるやつ
		//compIDはセッションに保存されているやつを使う
		receivedResult.setCompId(1);
		List<ReceivedResult> list = receivedResultDao.searchMatch(receivedResult);
		if(list == null) {
			return "game_setting";
		}
		receivedResult.setMatchId(list.get(0).getMatchId());
		receivedResult.setGameCount(form.getGameCount());
		receivedResult.setMaxPoint(form.getMaxPoint());
		Integer i = receivedResultDao.insertGameInfo(receivedResult);
		session.setAttribute("game_info_id", i);
		session.setAttribute("max_point", form.getMaxPoint());
		return "redirect:/server_setting";
	}
	
	@RequestMapping(value="score_setting")
	public String scoreSetting(@ModelAttribute("score_setting") GamePlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		Score score = new Score();
		score.setGameInfoId((Integer)session.getAttribute("game_info_id"));
		List<Score> list = scoreDao.selectAll(score);
		int winCountA = 0;
		int winCountB = 0;
		if(list != null) {
			for(Score s : list) {
				if(s.getTeamAScore() > s.getTeamBScore()) {
					winCountA ++;
				}else {
					winCountB ++;
				}
			}
		}
		form.setMaxPoint((Integer)session.getAttribute("max_point"));
		model.addAttribute("setNumA", winCountA);
		model.addAttribute("setNumB", winCountB);
		return "score_setting";
	}
	
	@RequestMapping(value="game_set_result")
	public String gameSetResult(@ModelAttribute("score_setting") GamePlayerForm form) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		return "game_set_result";
	}
	
	@RequestMapping(value="game_result", params="next")
	public String nextGame(@ModelAttribute("score_setting") GamePlayerForm form) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		return "score_setting";
	}
	
	@RequestMapping(value="game_result", params="finish")
	public String gameResult(@ModelAttribute("score_setting") GamePlayerForm form) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		return "game_result";
	}
}
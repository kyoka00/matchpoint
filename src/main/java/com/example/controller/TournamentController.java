package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.CompDao;
import com.example.dao.ManageDao;
import com.example.dao.ReceivedResultDao;
import com.example.dao.ScoreDao;
import com.example.dao.TeamDao;
import com.example.entity.ReceivedResult;
import com.example.entity.Score;
import com.example.entity.Team;

@Controller
public class TournamentController {
	
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
	
	
	//トーナメントへ
		@RequestMapping(value="tournament")
		public String tournament(Model model) {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			
			Integer compId = 1;
			Team team = new Team();
			team.setCompId(compId);
			List<Team> teamList = teamDao.selectAll(team);
			model.addAttribute("teamList", teamList);
			
			return "tournament";
			
		}
		//トーナメント表編集
		@RequestMapping(value="edit_tournament")
		public String editTournament() {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			return "edit_tournament";
		}
		
		//プレイヤーでログインをトーナメント
		@RequestMapping(value="tournament_player")
		public String tournamentPlayer(@RequestParam("compLoginId") String compLoginID) {
			session.setAttribute("loginId", compLoginID);
			return "tournament";
		}
		
		//トーナメント表のセーブ
		@RequestMapping(value="submit_edition")
		public String tournamentSave() {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			return "edit_tournament";
		}
		
		//トーナメント表からDBに更新をかける
		@RequestMapping(value="save_tournament")
		public String saveTournament(Model model) {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			return "edit_tournament";
		}
		//試合番号ボタンクリック
		@RequestMapping(value="")
		public String gameResult(Model model) {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			Integer matchId = 1;
			ReceivedResult result = new ReceivedResult();
			result.setMatchId(matchId);
			result.setRecordStatus(1);
			
			List<ReceivedResult> resultList = receivedResultDao.search(result, null);
			ReceivedResult matchInfo = resultList.get(0);
			model.addAttribute("matchInfo",resultList);
			
			Score score = new Score();
			score.setGameInfoId(matchInfo.getGameInfoId());
			List<Score> scoreList = scoreDao.selectAll(score);
			model.addAttribute("scoreList", scoreList);
			
			return "game_result_final_registered";		
		}
		
		//試合結果からトーナメントへ
		@RequestMapping(value="tournament_back")
		public String tournamentBack(Model model) {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			return "tournament";
		}
}

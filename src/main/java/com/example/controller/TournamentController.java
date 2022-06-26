package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.form.CompForm;
import com.example.dao.CompDao;
import com.example.dao.ManageDao;
import com.example.dao.ReceivedResultDao;
import com.example.dao.ScoreDao;
import com.example.dao.TeamDao;
import com.example.entity.Comp;
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
		public String tournament(@ModelAttribute("compInfo") CompForm form, Model model) {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			Integer compId =form.getCompId();
			if((Integer)session.getAttribute("compId") == null && compId != null) {
				session.setAttribute("compId", compId);
			}else if(compId != null) {
				session.setAttribute("compId", compId);
			}else {
				compId = (Integer)session.getAttribute("compInfo");
			}
			
			Team team = new Team();
			team.setCompId(compId);
			List<Team> teamList = teamDao.selectAll(team, "");
			model.addAttribute("teamList", teamList);
			return "tournament";
			
		}
		//トーナメント表編集
		@RequestMapping(value="edit_tournament")
		public String editTournament() {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			Integer compId = (Integer)session.getAttribute("compId");
			Comp comp = new Comp();
			comp.setCompId(compId);
			Comp compList = compDao.selectAll(comp).get(0);
			int status = compList.getTournamentEditStatus();
			switch(status) {
			case 0:
				
				break;
				
			case 1: 
				
				break;
				
			case 2: 
				
				break;
			}
			
			return "edit_tournament";
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
		//大会一覧へ戻る
		@RequestMapping(value="comp_list_back")
		public String compListBack(@ModelAttribute("compInfo") CompForm form, Model model) {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}

			Comp comp = new Comp();
			model.addAttribute("resultList", compDao.selectAll(comp));
			return "comp_list";
		}
}

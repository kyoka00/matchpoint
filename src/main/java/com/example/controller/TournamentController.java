package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.CompDao;
import com.example.dao.ManageDao;
import com.example.dao.ReceivedResultDao;
import com.example.dao.ScoreDao;
import com.example.dao.TeamDao;
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
}

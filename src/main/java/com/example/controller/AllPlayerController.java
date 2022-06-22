package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.TeamDao;
import com.example.entity.Team;

@Controller
public class AllPlayerController {	
	@Autowired
	HttpSession session;
	
//	@Autowired
//	HttpRequest request;
	
	@Autowired
	TeamDao teamDao;
	
	//選手一覧へ（全件取得）
	@RequestMapping(value="all_player")
	public String playerList(Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		//teamテーブルのcomp_idを参照して、選手一覧画面を表示
		Team team = new Team();
		Integer compId = 2; //doubles
		team.setCompId(compId);
		List<Team> resultList = teamDao.selectAll(team);
		model.addAttribute("allPlayer", resultList);
	return "all_player";
}
}
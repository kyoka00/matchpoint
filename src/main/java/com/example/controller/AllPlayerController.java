package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.form.PlayerForm;
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

	//検索機能作れるようにDao編集しといたから、
	//all_player.htmlで検索用のform, input, buttonを作って、
	//新しいRequestMapping作ってー。
	//また詳しいことは聞いてください。
	
	//検索button押下
	@RequestMapping(value="search")
	public String search(@ModelAttribute("edit_player") PlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		Team team = new Team();
		Integer compId = 2;
		team.setCompId(compId);
		String keyword = form.getKeyword();
		List<Team> search = teamDao.selectAll(team, keyword);
		model.addAttribute("allPlayer", search);
		return "all_player";
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////	
	//選手一覧へ（全件取得）
	@RequestMapping(value="all_player")
	public String playerList(Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		Team team = new Team();
		Integer compId = (Integer)session.getAttribute("compId"); //結合時に、comp_idをsession.getAttribute();で持たせる予定
		team.setCompId(compId);
		List<Team> resultList = teamDao.selectAll(team, "");
		model.addAttribute("allPlayer", resultList);
		return "all_player";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

	//選手編集へ(serialNumber押下時)
	@RequestMapping(value="edit_player")
	public String playerEditPage(@ModelAttribute("edit_player") PlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}

		//チーム情報を保持したまま、編集画面に遷移
		
		Team team = new Team();
<<<<<<< HEAD
		
		Integer compId = (Integer)session.getAttribute("compId");
=======
		Integer compId = 2;
>>>>>>> 83c24d2977421235b9f05d8bde0f290581a76ee9
		team.setTeamId(form.getTeamId());
		team.setCompId(compId);
		List<Team> teamList = teamDao.selectAll(team, "");
		model.addAttribute("edit_player", teamList.get(0));
//		System.out.println(2);
		return "edit_player";
	}
}
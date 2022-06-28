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

import com.example.controller.form.PlayerForm;
import com.example.dao.TeamDao;
import com.example.entity.Team;

@Controller
public class EditPlayerController {

	@Autowired
	HttpSession session;

	@Autowired
	TeamDao teamDao;

	/**
	 * 選手情報更新 update
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@RequestMapping(value="edit_player", params= "edit")
	public String playerEdit(@Validated @ModelAttribute("edit_player") PlayerForm form, BindingResult bindingResult,Model model) {
		if(session.getAttribute("loginId") == null && session.getAttribute("compLoginId")== null) {
			return "top";
		}
		if (bindingResult.hasErrors()) {

			return "edit_player";
		}


		//存在チェック
		String player1 = form.getPlayerAName();
		String player2 = form.getPlayerBName();
		Integer tournamentNum = form.getTournamentNo();
		if(player1.isEmpty()) {
			model.addAttribute("errorMsg1", "※プレイヤー名が記入されていません。");
		}
		if(player2.isEmpty()) {
			model.addAttribute("errorMsg2", "※プレイヤー名が記入されていません。");
		}
		if(tournamentNum == null) {
			model.addAttribute("errorMsg3", "※トーナメント番号を入力してください。");
		}



		Team team = new Team();
		Integer teamId = form.getTeamId();
		Integer compId = (Integer)session.getAttribute("compId"); //結合時に、comp_idをsession.getAttribute();で持たせる予定
		team.setTeamId(teamId);
		team.setCompId(compId);
		team.setPlayerAName(form.getPlayerAName());
		team.setPlayerBName(form.getPlayerBName());
		team.setTournamentNo(form.getTournamentNo());
		int result = teamDao.updateTeam(team);
		if(result == 0) {
			return "edit_player";
		}

		//大会一覧への表示処理
		Team showTeam = new Team();
		showTeam.setCompId(compId);
		List<Team> updateList = teamDao.selectAll(showTeam, "" ); 
		model.addAttribute("allPlayer", updateList);
		return "all_player";
	}


	//選手情報更新画面 delete
	@RequestMapping(value="edit_player", params= "delete")
	public String playerDelete1(@Validated @ModelAttribute("edit_player") PlayerForm form, BindingResult bindingResult,Model model){
		if(session.getAttribute("loginId") == null && session.getAttribute("compLoginId")== null) {
			return "top";
		}

		Team team = new Team();
		Integer teamId = form.getTeamId();
		team.setTeamId(teamId);
		int result = teamDao.deleteTeam(team);
		if(result == 0) {
			return "edit_player";
		}
		Integer compId =(Integer)session.getAttribute("compId");
		Team showTeam = new Team();
		showTeam.setCompId(compId);
		List<Team> deleteList = teamDao.selectAll(showTeam, ""); 
		model.addAttribute("allPlayer", deleteList);
	

	//存在チェック
	String player1 = form.getPlayerAName();
	String player2 = form.getPlayerBName();
	Integer tournamentNum = form.getTournamentNo();
	if(player1.isEmpty()) {
		model.addAttribute("errorMsg1", "※プレイヤー名が記入されていません。");
	}
	if(player2.isEmpty()) {
		model.addAttribute("errorMsg2", "※プレイヤー名が記入されていません。");
	}
	if(tournamentNum == null) {
		model.addAttribute("errorMsg3", "※トーナメント番号を入力してください。");
	}



	Team team1 = new Team();
	Integer teamId1 = form.getTeamId();
	Integer compId1 = (Integer)session.getAttribute("compId"); //結合時に、comp_idをsession.getAttribute();で持たせる予定
	team1.setTeamId(teamId1);
	team1.setCompId(compId1);
	team1.setPlayerAName(form.getPlayerAName());
	team1.setPlayerBName(form.getPlayerBName());
	team1.setTournamentNo(form.getTournamentNo());
	int result1 = teamDao.updateTeam(team1);
	if(result1 == 0) {
		return "edit_player";
	}

	//大会一覧への表示処理
	Team showTeam1 = new Team();
	showTeam1.setCompId(compId1);
	List<Team> updateList = teamDao.selectAll(showTeam1, "" ); 
	model.addAttribute("allPlayer", updateList);
	return "all_player";
}




//選手情報更新画面 delete
@RequestMapping(value="edit_player", params= "delete")
public String playerDelete(@Validated @ModelAttribute("edit_player") PlayerForm form, BindingResult bindingResult,Model model){
	if(session.getAttribute("loginId") == null) {
		return "top";
	}

	Team team = new Team();
	Integer teamId = form.getTeamId();
	team.setTeamId(teamId);

	int result = teamDao.deleteTeam(team);
	if(result == 0) {
		return "edit_player";
	}

	Integer compId =(Integer)session.getAttribute("compId");
	Team showTeam = new Team();
	showTeam.setCompId(compId);
	List<Team> deleteList = teamDao.selectAll(showTeam, "" ); 
	model.addAttribute("allPlayer", deleteList);

	return "all_player";
}
}
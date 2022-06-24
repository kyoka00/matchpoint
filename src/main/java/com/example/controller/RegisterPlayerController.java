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

//ユーザー情報 Controller

@Controller
public class RegisterPlayerController {
	@Autowired
	HttpSession session;

	@Autowired
	TeamDao teamDao;

	//選手登録へ
	@RequestMapping(value="insert_player")
	public String playerInsertPage(@ModelAttribute("insert_player") PlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "insert_player";
	}

	//選手登録 登録ボタン
	@RequestMapping(value="insert_player", params="insert")
	public String playerInsert(@Validated@ModelAttribute("insert_player")  PlayerForm form, BindingResult bindingResult,Model model) {

		//存在チェック
		if(bindingResult.hasErrors()) {
			
			String player1 = form.getPlayerAName();
			Integer tournamentNum = form.getTournamentNo();
			if(player1.isEmpty()) {
				model.addAttribute("errorMsg1", "※プレイヤー名が記入されていません。");
			}
			if(tournamentNum == null) {
				model.addAttribute("errorMsg2", "※トーナメント番号を入力してください。");
			}
			return "insert_player";
		}

		//選手の新規登録処理
		Team team = new Team();
		Integer compId = 2; //結合時に、comp_idをsession.getAttribute();で持たせる予定
		team.setCompId(compId);
		team.setPlayerAName(form.getPlayerAName());
		team.setPlayerBName(form.getPlayerBName());
		team.setTournamentNo(form.getTournamentNo());
		teamDao.insertTeam(team);///////////////////////////////////ここまでが個人の処理
		
		//大会一覧への表示処理
		team = new Team();
		team.setCompId(compId);
		List<Team> registList = teamDao.selectAll(team,"");//
		model.addAttribute("allPlayer", registList);
		return "all_player";
	}
}
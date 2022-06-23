package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.form.PlayerForm;
import com.example.dao.TeamDao;
import com.example.entity.Team;

public class EditPlayerController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	TeamDao teamDao;
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	@RequestMapping(value = "/update", params = "update", method = RequestMethod.POST)
//	public String update(@Validated @ModelAttribute("productForm") PlayerForm pForm, BindingResult bindingResult ,Model model) {
//		if(bindingResult.hasErrors()) {
//		// 存在チェック
//		var product = productDao.findByProductId(pForm.getProductId(), pForm.getId());
//		if(product != null) {
//			model.addAttribute("errorMsg", "商品IDは既に使用されています。");
//			model.addAttribute("categoryList", categoryDao.findAll());
//			return "/updateInput";
//		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	//選手情報更新 update
//	@RequestMapping(value="edit_player", params= "edit")
//	public String JumpToAllPlayer(@ModelAttribute("edit_player") PlayerForm form, Model model) {
//		if(session.getAttribute("loginId") == null) {
//			return "top";
//		}
//		Team team = new Team();
//		team.setPlayerAName(form.getPlayer1());
//		team.setPlayerBName(form.getPlayer2());
//		team.setTournamentNo(form.getTournamentNum());
//		teamDao.updateTeam(team);//個人の更新処理
//		
//		//大会一覧への表示処理
//		team = new Team();
//		List<Team> updateList = teamDao.selectAll(team);
//		model.addAttribute("all_player", updateList);
//		
//		return "all_player";
//	}
	
	
	/**
	   * 選手情報更新 update
	   * @param userRequest リクエストデータ
	   * @param model Model
	   * @return ユーザー情報詳細画面
	   */
	@RequestMapping(value="/edit_player", params= "edit", method = RequestMethod.POST)
	public String playerUpdate(@Validated @ModelAttribute("all_player") PlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
			Team team = new Team();
			Integer compId = 2; //結合時に、comp_idをsession.getAttribute();で持たせる予定
			team.setCompId(compId);
			team.setPlayerAName(form.getPlayer1());
			team.setPlayerBName(form.getPlayer2());
			team.setTournamentNo(form.getTournamentNum());
			teamDao.updateTeam(team);///////////////////////////////////ここまでが個人の処理
			
			//大会一覧への表示処理
			List<Team> updateList = teamDao.selectAll(team);//
			model.addAttribute("allPlayer", updateList);
			System.out.println("ABC");
			return "all_player";
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		//選手情報更新画面 delete
		@RequestMapping(value="delete_player", params = "delete")
		public String playerDelete(@RequestParam("id") PlayerForm form, Model model) {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			
			Team team = new Team();
//			team.setCompId(form.getCompId());
			team.setPlayerAName(form.getPlayer1());
			team.setPlayerBName(form.getPlayer2());
			team.setTournamentNo(form.getTournamentNum());
			teamDao.deleteTeam(team);
			
			//大会一覧への表示処理
			List<Team> deleteList = teamDao.selectAll(team);
			model.addAttribute("all_player", deleteList);
			System.out.println("DEF");
			return "all_player";
		}
}
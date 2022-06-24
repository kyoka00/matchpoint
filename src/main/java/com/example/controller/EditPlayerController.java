package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
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
	@RequestMapping(value="edit_player", params= "edit")
	public String playerEdit(@Validated @ModelAttribute("edit_player") PlayerForm form, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
            return "edit_player";
        }
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
			Team team = new Team();
			Integer teamId = form.getTeamId();
			Integer compId = 2; //結合時に、comp_idをsession.getAttribute();で持たせる予定
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
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		//選手情報更新画面 delete
		@RequestMapping(value="edit_player", params= "delete")
		public String playerDelete(@Validated @ModelAttribute("edit_player") PlayerForm form, BindingResult bindingResult,Model model){
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			Team team = new Team();
			Integer teamId = form.getTeamId();
			team.setTeamId(teamId);
			
<<<<<<< HEAD
			//大会一覧への表示処理
			List<Team> deleteList = teamDao.selectAll(team,"");
			//deleteも同じく、TeamId必要。それ以外のカラムの値は要らないよー。
			model.addAttribute("all_player", deleteList);
=======
			int result = teamDao.deleteTeam(team);
//			System.out.println("ABC");
			if(result == 0) {
				return "edit_player";
			}
			Integer compId = 2;
			Team showTeam = new Team();
			showTeam.setCompId(compId);
			List<Team> deleteList = teamDao.selectAll(showTeam, "" ); 
			model.addAttribute("allPlayer", deleteList);
>>>>>>> 83c24d2977421235b9f05d8bde0f290581a76ee9
			return "all_player";
		}
}

package com.example.controller.form;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.TeamDao;
import com.example.entity.Team;

//ユーザー情報 Controller


@Controller
public class RegisterPlayerController {


	@Autowired
	HttpSession session;
	
	@Autowired
	TeamDao teamDao;

//	//選手登録 入力エラー
//	@RequestMapping(value="/insert_player", method = RequestMethod.POST)
//	public String register(@ModelAttribute("insert_player") PlayerForm form, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			List<String> errorList = new ArrayList<String>();
//			for(ObjectError error : result.getAllErrors()) {
//				errorList.add(error.getDefaultMessage());
//			}
//			model.addAttribute("validationError", errorList);
//			return "insert_player";
//		}
//		return "all_player";
//	}
	
	//選手登録へ
	@RequestMapping(value="insert_player")
	public String playerInsertPage(@ModelAttribute("insert_player") PlayerForm form, Model model) {
		System.out.println("yes");
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "insert_player";
	}
	
	//選手登録 登録ボタン
	@RequestMapping(value="insert_player", params="insert")
	public String playerInsert(@Validated@ModelAttribute("insert_player")  PlayerForm form, BindingResult bindingResult,Model model) {
		System.out.println("ok");
		if(bindingResult.hasErrors()) {
			return "insert_player";
		}
		
		 Team team = new Team();
		 
		 Integer compId = 2; //doubles
		 team.setCompId(compId);
		 
		 team.setPlayerAName(form.getPlayer1());
		 team.setPlayerBName(form.getPlayer2());
		 team.setTournamentNo(form.getTournamentNum());
		 
		 teamDao.insertTeam(team);
		 
		 team = new Team();
		 List<Team> resultList = teamDao.selectAll(team);
		 
		 model.addAttribute("allPlayer", resultList);
		 return "all_player";
	}

		
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	    public void insertTeam(Team team) {
//
//	        MapSqlParameterSource param = new MapSqlParameterSource();
//	        param.addValue("Comp_id", team.getCompId());
//	        param.addValue("Player_A_Name", team.getPlayerAName());
//	        param.addValue("Player_B_Name", team.getPlayerBName());
//	        param.addValue("TournamentNo", team.getTournamentNo());
	    
	



		
//		@RequestMapping(value="all_player", params="create")
//		public String createPlayer(@ModelAttribute("playerInfo") PlayerForm form, BindingResult bindingResult, Model model) {
//
//			if(bindingResult.hasErrors()) {
//				return "all_player";
//			}
//
//
//			Team team = new Team();
//			Team teamDao = new TeamDao();
//			List<Team> list = ((TeamDao) teamDao).selectAll(team);
//
//			team.setPlayerAName(form.getPlayer1());
//			team.setPlayerBName(form.getPlayer2());
//			team.setTournamentNo(form.getTournamentNum());	
//
//			if(list == null) {
//
//				teamDao.insertTeam(team);
//				team = new Team();
//
//				model.addAttribute("resultList", teamDao.selectAll(team));
//				return "comp_list";
//			}
//
//			model.addAttribute("errorMsg", "この選手は登録済みです。");
//
//			return "comp_info";
//		}
//		//				
//		//				
//		//				//大会一覧へ戻る
//		//				@RequestMapping(value="comp_list_back")
//		//				public String compListBack(@ModelAttribute("compInfo") CompForm form, Model model) {
//		//					if(session.getAttribute("loginId") == null) {
//		//						return "top";
//		//					}
//		//					Comp comp = new Comp();
//		//					model.addAttribute("resultList", compDao.selectAll(comp));
//		//					
//		//					return "comp_list";
//		//				}
//

		

	

}
package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	//選手情報更新 update
	@RequestMapping(value="edit_player", params= "edit") 
	public String playerUpdate(@ModelAttribute("all_player") PlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
			Team team = new Team();
//			Integer compId = 2; //結合時に、comp_idをsession.getAttribute();で持たせる予定
			team.setPlayerAName(form.getPlayer1());
			team.setPlayerBName(form.getPlayer2());
			team.setTournamentNo(form.getTournamentNum());
			teamDao.updateTeam(team);///////////////////////////////////ここまでが個人の処理
			//たぶん、teamIdもteamにセットしないと、SQLでWHEREが指定されないはず。
			//そうなると、たぶん全部の値が変わるはずよ

			//このコードのままだと、更新できたかどうか判定する方法がなさそう。
			//TeamDaoのupdate, delete, insertの戻り値をintにして、メソッドの戻り値をjdbcTemplate.update(sql, param);に変えた方がよさそう
			//そしたらSQLが行われた件数が取得できるから、1だったら正常に更新できてるし、0なら更新できてないってこと。
			//更新できてない場合に、エラーメッセージとか表示させた方がよきかも。
			
			//大会一覧への表示処理
			List<Team> updateList = teamDao.selectAll(team, ""); 
			
			
			model.addAttribute("allPlayer", updateList);
			System.out.println("ABC");
			return "all_player";
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		//選手情報更新画面 delete
		@RequestMapping(value="delete_player", params = "delete") //paramsいらないよ
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
			List<Team> deleteList = teamDao.selectAll(team,"");
			//deleteも同じく、TeamId必要。それ以外のカラムの値は要らないよー。
			model.addAttribute("all_player", deleteList);
			System.out.println("DEF");
			return "all_player";
		}
}
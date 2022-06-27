package com.example.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.form.CompForm;
import com.example.controller.form.LoginForm;
import com.example.dao.CompDao;
import com.example.dao.ManageDao;
import com.example.dao.ScoreDao;
import com.example.dao.TeamDao;
import com.example.entity.Comp;
import com.example.entity.Manage;

@Controller
public class ShomaController{
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
	
	//ログアウト
	@RequestMapping(value="logout")
	public String logout() {
		session.invalidate();
		return "top";
	}
	
	//トップページ
	@RequestMapping(value={"/","top"})
	public String top() {
		return "top";
	}
	
	//トップページから運営ログイン画面へ
	@RequestMapping(value="login")
	public String loginPage(@ModelAttribute("login") LoginForm form) {
		form.setLoginId("admin");
		return "login";
	}
	
	//プレイヤーにログイン
	@RequestMapping(value="comp_login")
	public String playerLoginPage() {
		return "comp_login";
	}
	
	//プレイヤーでログインをトーナメント
	@RequestMapping(value="tournament_player")
	public String tournamentPlayer(@RequestParam("compLoginId") String compLoginID,Model model) {
		
		
		Comp comp = new Comp();
		comp.setCompLoginId(compLoginID);
		List<Comp> list = compDao.selectAll(comp);
		
		if(list == null) {
			model.addAttribute("errorMsg", "大会ログインIDが違います。");
			return "comp_login";
		}else if(compLoginID.isEmpty()) {		
			model.addAttribute("errorMsg", "大会ログインIDは必須です。");
			return "comp_login";
		}
		session.setAttribute("loginId", compLoginID);
		return "tournament";
	}
	
	//運営ログイン画面から大会一覧へ
	@RequestMapping(value="comp_list")
	public String login(@Validated@ModelAttribute("login") LoginForm form,BindingResult bindingResult,Model model) {
		
		if(bindingResult.hasErrors()) {
			return "login";
		}
		
		Manage manage = new Manage();
		manage.setLoginId(form.getLoginId());//後でform.getLoginId()を入れる。
		manage.setPassword(form.getPassword());//後でform.getPassword()を入れる。
		List<Manage> list = manageDao.selectAll(manage);
		
		Comp comp = new Comp();
		
		if (list == null) {
			model.addAttribute("errorMsg", "IDまたはパスワードが不正です。");
			return "login";
		}
	
		if(session.getAttribute("loginId") == null && form.getLoginId() == null){
			return "top";
		}else if(session.getAttribute("loginId") == null) {
			if(list.get(0).getLoginId().equals(form.getLoginId())&&list.get(0).getPassword().equals(form.getPassword())) {
				session.setAttribute("loginId", form.getLoginId());
				model.addAttribute("resultList", compDao.selectAll(comp));
				return "comp_list";
			}else {
				return "login";
			}
		}else {
			model.addAttribute("resultList", compDao.selectAll(comp));
			session.setAttribute("loginId", form.getLoginId());
			return "comp_list";
		}
	}
	
	//大会一覧から大会作成画面へ
	@RequestMapping(value="comp_info")
	public String createCompPage(@ModelAttribute("compInfo") CompForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "comp_info";
	}
	
	//大会作成画面から作成ボタンで大会一覧へ移動
	@RequestMapping(value="comp_list", params="create")
	public String createComp(@Validated@ModelAttribute("compInfo") CompForm form, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "comp_info";
		}
		
		
		Comp comp = new Comp();
		comp.setCompLoginId(form.getCompLoginId());
		List<Comp> list = compDao.selectAll(comp);
		
		Date date= Date.valueOf(form.getCompDate());
		comp.setCompDate(date);
		
		comp.setCompName(form.getCompName());
		comp.setCompPlace(form.getCompPlace());
		comp.setTournamentCount(form.getTournamentNo());
		comp.setGameType(form.getGameType());
		comp.setMemo(form.getMemo());	
		
		
		if(list == null) {
			
			compDao.insertComp(comp);
			comp = new Comp();
			
			model.addAttribute("resultList", compDao.selectAll(comp));
			return "comp_list";
		}
		
		model.addAttribute("errorMsg", "この大会IDは使用済みです。");
				
		return "comp_info";
	}
	
}
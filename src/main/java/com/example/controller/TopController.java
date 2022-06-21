package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Dao.CompDao;
import com.example.Dao.ManegeDao;
import com.example.Enitity.Comp;
import com.example.controller.form.CompForm;
import com.example.controller.form.LoginForm;

@Controller
public class TopController {

	@Autowired
	ManegeDao manegeDao;

	@Autowired
	CompDao compDao;

	@Autowired
	HttpSession session;

	// ログアウト
	@RequestMapping(value = "logout")
	public String logout() {
		session.invalidate();
		return "top";
	}

	// 検索でトップページへ
	@RequestMapping(value = { "/", "/top" })
	public String top() {
		return "top";
	}
	
	// プレイヤーにログイン
	@RequestMapping(value = "comp_login")
	public String playerLoginPage() {
		return "comp_login";
	}

	//プレイヤーでログインをトーナメント
	@RequestMapping(value = "tournament_player")
	public String tournamentPlayer(@RequestParam("compLoginId") String compLoginID) {
		session.setAttribute("loginId", compLoginID);
		return "tournament";
	}

	// トップページから運営ログイン画面へ
	@RequestMapping(value = "login")
	public String loginPage(@ModelAttribute("login") LoginForm form) {
		return "login";
	}

	// 運営ログイン画面から大会一覧へ
	@RequestMapping(value = "comp_list")
	public String login(@Validated @ModelAttribute("login") LoginForm form, BindingResult bindingResult, Model model) {
		
	
		if(bindingResult.hasErrors()) {
			return "/login";
		}
		
		
		var manege = manegeDao.login(form.getLoginId(), form.getPassword());
		if (manege == null) {
			model.addAttribute("errorMsg", "IDまたはパスワードが不正です。");
			return "/login";
		}

		String loginId = manege.getLoginId();
		String password = manege.getPassword();

		if (session.getAttribute("loginId") == null && form.getLoginId() == null) {
			return "top";
		} else if (session.getAttribute("loginId") == null) {
			if (loginId.equals(form.getLoginId()) && password.equals(form.getPassword())) {
				session.setAttribute("loginId", form.getLoginId());
				model.addAttribute("compList", compDao.find());
				return "comp_list";
			} else {
				return "login";
			}
		} else {
			session.setAttribute("loginId", form.getLoginId());
			model.addAttribute("compList", compDao.find());
			return "comp_list";
		}
	}

	// 大会一覧から大会作成画面へ
		@RequestMapping(value="comp_info")
		public String createCompPage(@ModelAttribute("compInfo") CompForm form, Model model) {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			return "comp_info";
		}
		
		//大会作成画面から作成ボタンで大会一覧へ移動
		@RequestMapping(value="comp_list", params="create")
		public String createComp(@Validated@ModelAttribute("compInfo") CompForm form, BindingResult bindingResult ,Model model) {
			
			if(bindingResult.hasErrors()) {
				return "comp_info";
			}
			
			// 存在チェック
			var comp = compDao.compLoginId(form.getCompLoginId(), -1);
			if(comp != null) {
				model.addAttribute("errorMsg", "商品IDは既に使用されています。");
				return "/insert";
			}
			
			comp = new Comp();
			this.FormToComp(form,comp);
			var count = compDao.insert(comp);
			this.setMsg(model, "登録", count);
			
			model.addAttribute("compList", compDao.find());
			
			return "comp_list";
		}
		
		//大会一覧へ戻る
		@RequestMapping(value="comp_list_back")
		public String compListBack(@ModelAttribute("compInfo") CompForm form, Model model) {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			model.addAttribute("compList", compDao.find());
			return "comp_list";
		}
		
	
		
		private void FormToComp(CompForm form, Comp comp) {
			comp.setCompLoginId(form.getCompLoginId());
			comp.setCompName(form.getCompName());
			comp.setCompDate(form.getCompDate());
			comp.setCompPlace(form.getCompPlace());
			comp.setGameType(form.getGameType());
			comp.setTournamentNum(form.getTournamentNum());
			comp.setMemo(form.getMemo());
		}
		
		private void setMsg(Model model, String mode, int count) {
			if(count == 0) {
				model.addAttribute("msg", mode + "に失敗しました。");
			} else {
				model.addAttribute("msg", mode + "に成功しました。");
			}
		}
}
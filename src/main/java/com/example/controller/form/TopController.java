//package com.example.controller.form;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//
//import com.example.controller.form.CompForm;
//import com.example.controller.form.LoginForm;
//import com.example.controller.form.PlayerForm;
//import com.example.dao.CompDao;
//import com.example.dao.ManageDao;
//import com.example.dao.ReceivedResultDao;
//import com.example.dao.ScoreDao;
//import com.example.dao.TeamDao;
//
//
//public class TopController{
//	@Autowired
//	HttpSession session;
//	
//	@Autowired
//	ManageDao manageDao;
//	
//	@Autowired
//	ScoreDao scoreDao;
//
//	@Autowired
//	CompDao compDao;
//	
//	@Autowired
//	TeamDao teamDao;
//
//	@Autowired
//	ReceivedResultDao receivedResultDao;
//	
//	//ログアウト
//	@RequestMapping(value="logout")
//
//	public String logout() {
//		session.invalidate();
//		return "top";
//	}
//
//	
//	//トップページ
//	@RequestMapping(value={"/","top"})
//	public String top() {
//		return "top";
//	}
//	
//	//トップページから運営ログイン画面へ
//	@RequestMapping(value="login")
//	public String loginPage(@ModelAttribute("login") LoginForm form) {
//		form.setLoginId("admin");
//		return "login";
//	}
//
//	// 運営ログイン画面から大会一覧へ
//	@RequestMapping(value = "comp_list")
//	public String login(@Validated @ModelAttribute("login") LoginForm form, BindingResult bindingResult, Model model) {
//		
//	
//	//プレイヤーにログイン
//	@RequestMapping(value="comp_login")
//	public String playerLoginPage() {
//		return "comp_login";
//	}
//	
//	//プレイヤーでログインをトーナメント
//	@RequestMapping(value="tournament_player")
//	public String tournamentPlayer(@RequestParam("compLoginId") String compLoginID) {
//		session.setAttribute("loginId", compLoginID);
//		return "tournament";
//	}
//	
//	//運営ログイン画面から大会一覧へ
//	@RequestMapping(value="comp_list")
//	public String login(@ModelAttribute("login") LoginForm form) {
//		
//		if(session.getAttribute("loginId") == null && form.getLoginId() == null){
//			return "top";
//		}else if(session.getAttribute("loginId") == null) {
//			if("admin".equals(form.getLoginId())) {
//				session.setAttribute("loginId", form.getLoginId());
//				return "comp_list";
//			}else {
//				return "login";
//			}
//		}else {
//			session.setAttribute("loginId", form.getLoginId());
//
//			return "comp_list";
//		}
//	}
//
//	// 大会一覧から大会作成画面へ
//		@RequestMapping(value="comp_info")
//		public String createCompPage(@ModelAttribute("compInfo") CompForm form, Model model) {
//			if(session.getAttribute("loginId") == null) {
//				return "top";
//			}
//			return "comp_info";
//		}
//		
//		//大会作成画面から作成ボタンで大会一覧へ移動
//		@RequestMapping(value="comp_list", params="create")
//		public String createComp(@Validated@ModelAttribute("compInfo") CompForm form, BindingResult bindingResult ,Model model) {
//			
//			if(bindingResult.hasErrors()) {
//				return "comp_info";
//			}
//			
//			// 存在チェック
//			var comp = compDao.compLoginId(form.getCompLoginId(), -1);
//			if(comp != null) {
//				model.addAttribute("errorMsg", "商品IDは既に使用されています。");
//				return "/insert";
//			}
//			
//			comp = new Comp();
//			this.FormToComp(form,comp);
//			var count = compDao.insert(comp);
//			this.setMsg(model, "登録", count);
//			
//			model.addAttribute("compList", compDao.find());
//			
//			return "comp_list";
//		}
//		
//		//大会一覧へ戻る
//		@RequestMapping(value="comp_list_back")
//		public String compListBack(@ModelAttribute("compInfo") CompForm form, Model model) {
//			if(session.getAttribute("loginId") == null) {
//				return "top";
//			}
//			model.addAttribute("compList", compDao.find());
//			return "comp_list";
//		}
//		
//
//	
//	//大会作成画面から作成ボタンで大会一覧へ移動
//	@RequestMapping(value="comp_list", params="create")
//	public String createComp(@ModelAttribute("compInfo") CompForm form, Model model) {
//		return "comp_list";
//	}
//
//	
//	
//	
//	//試合結果受信box
//	@RequestMapping(value="game_result_all")
//	public String gameResultAll() {
//		if(session.getAttribute("loginId") == null) {
//			return "top";
//		}
//		return "game_result_all";
//	}
//	
//	//試合結果登録へ
//	@RequestMapping(value="game_result_final")
//	public String resultFinal() {
//		if(session.getAttribute("loginId") == null) {
//			return "top";
//		}
//		return "game_result_final";
//	}
//	
//	//トーナメント表編集
//	@RequestMapping(value="edit_tournament")
//	public String editTournament() {
//		if(session.getAttribute("loginId") == null) {
//			return "top";
//		}
//		return "edit_tournament";
//	}
//	
//	//試合設定画面
////	@RequestMapping(value="match")
////	public String gameSetting() {
////		if(session.getAttribute("loginId") == null) {
////			return "top";
////		}
////		return "game_setting";
////	}
//	
//	//サーブ権設定
////	@RequestMapping(value="server_setting", params="server_setting")
////	public String serverSetting(@RequestParam("game_num") String gameNum, @ModelAttribute("score_setting") GamePlayerForm form) {
////		form.setServer("playerB");
////		return "server_setting";
////	}
//	
//	//スコア登録画面
////	@RequestMapping(value="score_setting")
////	public String scoreSetting(@ModelAttribute("score_setting") GamePlayerForm form) {
////		if(session.getAttribute("loginId") == null) {
////			return "top";
////		}
////		return "score_setting";
////	}
//	
//	//ゲームセット結果
////	@RequestMapping(value="game_set_result")
////	public String gameSetResult(@ModelAttribute("score_setting") GamePlayerForm form) {
////		if(session.getAttribute("loginId") == null) {
////			return "top";
////		}
////		return "game_set_result";
////	}
//	
////	@RequestMapping(value="game_result", params="next")
////	public String nextGame() {
////		if(session.getAttribute("loginId") == null) {
////			return "top";
////		}
////		
////		return "score_setting";
////	}
//	
////	@RequestMapping(value="game_result", params="finish")
////	public String gameResult() {
////		if(session.getAttribute("loginId") == null) {
////			return "top";
////		}
////		
////		return "game_result";
////	}
//	
//	@RequestMapping(value="box")
//	public String box() {
//		
//		return "game_result_all";
//	}
//}
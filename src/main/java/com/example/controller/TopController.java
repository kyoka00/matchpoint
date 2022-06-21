package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.form.CompForm;
import com.example.controller.form.GamePlayerForm;
import com.example.controller.form.LoginForm;
import com.example.controller.form.PlayerForm;
import com.example.dao.CompDao;
import com.example.dao.ManageDao;
import com.example.dao.ScoreDao;
import com.example.dao.TeamDao;

@Controller
public class TopController{
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
	public String tournamentPlayer(@RequestParam("compLoginId") String compLoginID) {
		session.setAttribute("loginId", compLoginID);
		return "tournament";
	}
	
	//運営ログイン画面から大会一覧へ
	@RequestMapping(value="comp_list")
	public String login(@ModelAttribute("login") LoginForm form) {
		
		if(session.getAttribute("loginId") == null && form.getLoginId() == null){
			return "top";
		}else if(session.getAttribute("loginId") == null) {
			if("admin".equals(form.getLoginId())) {
				session.setAttribute("loginId", form.getLoginId());
				return "comp_list";
			}else {
				return "login";
			}
		}else {
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
	public String createComp(@ModelAttribute("compInfo") CompForm form, Model model) {
		return "comp_list";
	}
	
	//大会一覧へ戻る
	@RequestMapping(value="comp_list_back")
	public String compListBack(@ModelAttribute("compInfo") CompForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "comp_list";
	}
	
	//トーナメントへ
	@RequestMapping(value="tournament")
	public String tournament() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "tournament";
	}
	
	//選手一覧へ
	@RequestMapping(value="all_player")
	public String playerList() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "all_player";
	}
	
	//選手編集へ
	@RequestMapping(value="edit_player")
	public String playerEditPage(@ModelAttribute("edit_player") PlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "edit_player";
	}
	
	//選手情報更新 選手一覧へ
	@RequestMapping(value="edit_player", params= "edit")
	public String playerEdit(@ModelAttribute("edit_player") PlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "all_player";
	}
	
	//選手情報更新画面 delete
	@RequestMapping(value="delete_player")
	public String playerDelete() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "all_player";
	}
	
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
	public String playerInsert(@ModelAttribute("insert_player") PlayerForm form, Model model) {
		return "all_player";
	}
	
	//大会詳細画面へ
	@RequestMapping(value="comp_detail")
	public String compDetail(@ModelAttribute("comp_detail") CompForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		//初期値を入力
		form.setCompName("第1回");
		form.setCompDate("6/6");
		form.setCompPlace("okinawa");
		form.setTournamentNum(2);
		form.setGameTypeStr("シングルス");
		form.setMemo("aaa");
		return "comp_detail";
	}
	
	//大会削除
	@RequestMapping(value="comp_delete")
	public String compDelete() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "comp_list";
	}
	
	@RequestMapping(value="comp_detail_edit")
	public String compDetailUpdate(@ModelAttribute("comp_detail") CompForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "comp_detail_update";
	}
	
	//大会編集画面
	@RequestMapping(value="comp_detail_update", params="completion")
	public String compUpdatePage(@ModelAttribute("comp_detail") CompForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "comp_detail";
	}
	
	//大会編集画面から戻る
	@RequestMapping(value="comp_detail_update", params="back")
	public String compDetailBack(@ModelAttribute("comp_detail") CompForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "comp_detail";
	}
	
	//試合結果受信box
	@RequestMapping(value="game_result_all")
	public String gameResultAll() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "game_result_all";
	}
	
	//試合結果登録へ
	@RequestMapping(value="game_result_final")
	public String resultFinal() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "game_result_final";
	}
	
	//トーナメント表編集
	@RequestMapping(value="edit_tournament")
	public String editTournament() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "edit_tournament";
	}
	
	//試合設定画面
	@RequestMapping(value="match")
	public String gameSetting() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "game_setting";
	}
	
	//サーブ権設定
	@RequestMapping(value="server_setting", params="server_setting")
	public String serverSetting(@RequestParam("game_num") String gameNum, @ModelAttribute("score_setting") GamePlayerForm form) {
		return "server_setting";
	}
	
	//スコア登録画面
	@RequestMapping(value="score_setting")
	public String scoreSetting(@ModelAttribute("score_setting") GamePlayerForm form) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "score_setting";
	}
	
	//ゲームセット結果
	@RequestMapping(value="game_set_result")
	public String gameSetResult(@ModelAttribute("score_setting") GamePlayerForm form) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "game_set_result";
	}
	
	@RequestMapping(value="game_result", params="next")
	public String nextGame() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		return "score_setting";
	}
	
	@RequestMapping(value="game_result", params="finish")
	public String gameResult() {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		return "game_result";
	}
	
	@RequestMapping(value="box")
	public String box() {
		
		return "game_result_all";
	}
}
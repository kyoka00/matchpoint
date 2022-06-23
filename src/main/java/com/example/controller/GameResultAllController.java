package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.form.CompForm;
import com.example.controller.form.GameResultAllForm;
import com.example.dao.GameResultAllDao;
import com.example.dao.ReceivedResultDao;
import com.example.entity.GameResultAll;
import com.example.entity.ReceivedResult;

@Controller
public class GameResultAllController{
	@Autowired
	HttpSession session;
	
	@Autowired
	GameResultAllDao gameResultAllDao;
	
	@Autowired
	ReceivedResultDao receivedResultDao;
	
	//試合結果受信box
	@RequestMapping(value="game_result_all")
	public String gameResultAll(@ModelAttribute("comp_detail") GameResultAllForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		GameResultAll gameResultAll = new GameResultAll();
		
		gameResultAll.setGameNo(form.getGameNo());
		
		List<GameResultAll> resultList = gameResultAllDao.selectAll(gameResultAll);
		
		System.out.println(resultList);
		
		model.addAttribute("resultList", resultList);
		
		return "game_result_all";
	}
	
	@RequestMapping(value = "/game_result_search")
	public String search(@RequestParam("keyword") String keyword, ReceivedResult receivedResult ,Model model) {
		model.addAttribute("resultList", receivedResultDao.search(receivedResult , keyword));
		return "game_result_all";
	}
	
	//試合結果登録へ
	@RequestMapping(value="game_result_final")
	public String resultFinal(@ModelAttribute("comp_detail") GameResultAllForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		GameResultAll gameResultAll = new GameResultAll();
		
		gameResultAll.setGameNo(form.getGameNo());
		
		return "game_result_final";
	}
}
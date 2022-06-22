package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.form.CompForm;
import com.example.dao.CompDao;
import com.example.entity.Comp;

@Controller
public class CompDetailController{
	@Autowired
	HttpSession session;
	
	@Autowired
	CompDao compDao;
	
	//大会詳細画面へ
	@RequestMapping(value="comp_detail")
	public String compDetail(@ModelAttribute("comp_detail") CompForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		
		Comp comp = new Comp();
		
		//初期値を入力
		comp.setCompId(2);
//		form.setCompId(1);
//		form.setCompName("第1回");
//		form.setCompDate("6/6");
//		form.setCompPlace("okinawa");
//		form.setCompLoginId("1234");
//		form.setTournamentNum(2);
//		form.setGameTypeStr("シングルス");
//		form.setMemo("aaa");
		
		List<Comp> compDetail = compDao.selectAll(comp);
		
		System.out.println(compDetail);
		
		model.addAttribute("comp_detail", compDetail);
		
		return "comp_detail";
	}
	
	//大会削除
		@RequestMapping(value="comp_delete")
		public String compDelete() {
			if(session.getAttribute("loginId") == null) {
				return "top";
			}
			
			Comp comp = new Comp();
			
			compDao.deleteComp(comp);
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
			
//			Comp comp = new Comp();
//			
//			compDao.updateComp(comp);
			return "comp_detail";
		}
}
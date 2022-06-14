package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.form.CreateCompForm;
import com.example.controller.form.LoginForm;

@Controller
public class TopController{
	
	//検索でトップページへ
	@RequestMapping(value={"/","/top"})
	public String top() {
		return "top";
	}
	
	//トップページから運営ログイン画面へ
	@RequestMapping(value="management")
	public String loginPage(@ModelAttribute("login") LoginForm form) {
		return "login";
	}
	
	//運営ログイン画面から大会一覧へ
	@RequestMapping(value="login")
	public String login(@ModelAttribute("login") LoginForm form) {
		return "comp_list";
	}
	
	//大会一覧から大会作成画面へ
	@RequestMapping(value="comp_info")
	public String createComp(@ModelAttribute("compInfo") CreateCompForm form, Model model) {
		return "comp_info";
	}
}
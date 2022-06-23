package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.controller.form.PlayerForm;

public class EditPlayerController {
	/*
	 * 更新時
	 */
	//選手情報更新 選手一覧へ
	@RequestMapping(value="edit_player", params= "edit")
	public String playerEdit(@ModelAttribute("edit_player") PlayerForm form, Model model) {
		if(session.getAttribute("loginId") == null) {
			return "top";
		}
		return "all_player";
	}
	
	
	
	
	//
	@RequestMapping(value = "/update", params = "update", method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute("productForm") PlayerForm pForm, BindingResult bindingResult ,Model model) {
		if(bindingResult.hasErrors()) {
		// 存在チェック
		var product = productDao.findByProductId(pForm.getProductId(), pForm.getId());
		if(product != null) {
			model.addAttribute("errorMsg", "商品IDは既に使用されています。");
			model.addAttribute("categoryList", categoryDao.findAll());
			return "/updateInput";
		}
		
		//更新処理
		product = new Product();
		product.setId(pForm.getId());
		this.FormToProduct(pForm, product);
		var count = productDao.update(product);
		this.setMsg(model, "更新", count);
		
		model.addAttribute("productList", productDao.find(""));
		
		return "/menu";
	}
}

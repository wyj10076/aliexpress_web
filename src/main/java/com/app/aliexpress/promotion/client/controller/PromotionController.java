package com.app.aliexpress.promotion.client.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.aliexpress.promotion.client.service.SelectPromotionListService;

@Controller("clientPromotionController")
public class PromotionController {

	@Autowired
	private SelectPromotionListService selectPromotionListService;
	
	@RequestMapping("/promotion.ab")
	public String showPromotion(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		selectPromotionListService.selectPromotionList(map);
		model.addAllAttributes(map);
		return "client/promotion";
	}
	
	
}

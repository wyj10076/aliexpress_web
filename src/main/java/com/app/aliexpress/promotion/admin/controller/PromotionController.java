package com.app.aliexpress.promotion.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.aliexpress.promotion.admin.service.DeletePromotionService;
import com.app.aliexpress.promotion.admin.service.InsertPromotionService;
import com.app.aliexpress.promotion.admin.service.SelectPromotionListService;
import com.app.aliexpress.promotion.admin.service.SelectPromotionService;
import com.app.aliexpress.promotion.admin.service.UpdatePromotionService;

@Controller("adminPromotionController")
public class PromotionController {

	@Autowired
	private SelectPromotionListService selectPromotionListService;
	@Autowired
	private SelectPromotionService selectPromotionService;
	@Autowired
	private InsertPromotionService insertPromotionService;
	@Autowired
	private UpdatePromotionService updatePromotionService;
	@Autowired
	private DeletePromotionService deletePromotionService;
	
	@RequestMapping("/management/promotionList.aba")
	public String showPromotionList(HttpServletRequest req, Model model) {
		model.addAttribute("type", req.getParameter("type"));
		return "admin/promotionList";
	}
	
	@PostMapping("/management/selectPromotionList")
	@ResponseBody
	public Map<String, Object> selectPromotionList(@RequestBody Map<String, Object> map) {
		selectPromotionListService.selectPromotionList(map);
		return map;
	}
	
	@RequestMapping("/management/promotion.aba")
	public String showPromotion(HttpServletRequest req, Model model) {
		model.addAttribute("type", req.getParameter("type"));
		model.addAttribute("promotionId", req.getParameter("query"));
		return "admin/promotion";
	}
	
	@PostMapping("/management/selectPromotion")
	@ResponseBody
	public Map<String, Object> selectPromotion(@RequestBody Map<String, Object> map) {
		selectPromotionService.selectPromotion(map);
		return map;
	}
	
	@PostMapping("/management/insertPromotion")
	@ResponseBody
	public Map<String ,Object> insertPromotion(@RequestBody Map<String, Object> map) {
		insertPromotionService.insertPromotion(map);
		return map;
	}
	
	@PostMapping("/management/updatePromotion")
	@ResponseBody
	public Map<String, Object> updatePromotion(@RequestBody Map<String, Object> map) {
		updatePromotionService.updatePromotion(map);
		return map;
	}
	
	@PostMapping("/management/deletePromotion")
	@ResponseBody
	public Map<String, Object> deletePromotion(@RequestBody Map<String, Object> map) {
		deletePromotionService.deletePromotion(map);
		return map;
	}
}

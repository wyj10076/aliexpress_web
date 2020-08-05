package com.app.aliexpress.product.client.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.aliexpress.product.client.service.SelectProductService;
import com.app.aliexpress.product.client.service.SelectSimilarProductListService;

@Controller("clientProductController")
public class ProductController {

	@Autowired
	private SelectProductService selectProductService;
	@Autowired
	private SelectSimilarProductListService selectSimilarProductListService;
	
	@RequestMapping("/product.ab")
	public String showProduct(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", req.getParameter("query"));
		map.put("locale", session.getAttribute("locale"));
		
		selectProductService.selectProduct(map);
		
		model.addAllAttributes(map);
		return "client/product";
	}
	
	@PostMapping("/product/selectSimilarProductList")
	@ResponseBody
	public Map<String, Object> selectSimilarProductList(@RequestBody Map<String, Object> map) {
		return selectSimilarProductListService.selectSimilarProductList(map);
	}
}

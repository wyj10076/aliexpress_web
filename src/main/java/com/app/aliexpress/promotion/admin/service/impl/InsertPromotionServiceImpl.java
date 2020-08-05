package com.app.aliexpress.promotion.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.promotion.admin.service.InsertPromotionService;
import com.app.aliexpress.promotion.mapper.PromotionMapper;

@Service("adminInsertPromotionService")
public class InsertPromotionServiceImpl implements InsertPromotionService {

	@Autowired
	private PromotionMapper promotionMapper;
	
	@Override
	public void insertPromotion(Map<String, Object> map) {
		try {
			int result = promotionMapper.insertPromotion(map);
			
			if (result > 0) {
				map.put("result", "success");
				
			} else {
				map.put("result", "fail");
			}
			
		} catch (Exception e) {
			map.put("result", "fail");
		}

	}

}

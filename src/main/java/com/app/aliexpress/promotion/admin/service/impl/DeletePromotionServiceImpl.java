package com.app.aliexpress.promotion.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.promotion.admin.service.DeletePromotionService;
import com.app.aliexpress.promotion.mapper.PromotionMapper;

@Service("adminDeletePromotionService")
public class DeletePromotionServiceImpl implements DeletePromotionService {

	@Autowired
	private PromotionMapper promotionMapper;
	
	@Override
	public void deletePromotion(Map<String, Object> map) {
		try {
			int result = promotionMapper.deletePromotion(map);
			
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

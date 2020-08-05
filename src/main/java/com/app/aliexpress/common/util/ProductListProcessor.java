package com.app.aliexpress.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ProductListProcessor {

	public static List<Map<String, Object>> process(boolean isProducts, String productId, JSONArray jsonArray) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			String jsonProductId = String.valueOf((long) jsonObject.get("product_id"));
			
			// compare products에서 처음 검색한 product가 나온다면 continue
			if (isProducts && jsonProductId.equals(productId)) {
				continue;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			JSONArray jsonImages = (JSONArray) ((JSONObject) jsonObject.get("product_small_image_urls")).get("string");
			List<String> images = new ArrayList<String>();
			for (int j = 0; j < jsonImages.size(); j++) {
				if (j == 4) {
					break;
				}
				images.add((String) jsonImages.get(j));
			}
			
			map.put("productId", jsonProductId);
			map.put("link", jsonObject.get("promotion_link"));
			map.put("title", jsonObject.get("product_title"));
			map.put("mainImage", images.get(0));
			map.put("subImages", images);
			map.put("order", (long) jsonObject.get("lastest_volume"));
			map.put("shopLink", jsonObject.get("shop_url"));
			map.put("currency", jsonObject.get("target_original_price_currency"));
			map.put("firstCategoryId", jsonObject.get("first_level_category_id"));
			map.put("secondCategoryId", jsonObject.get("second_level_category_id"));
			
			String price = (String) jsonObject.get("target_sale_price");
			if (price == null) {
				map.put("price", Double.parseDouble((String) jsonObject.get("target_original_price")));
			} else {
				map.put("price", Double.parseDouble(price));
			}
			
			String rating = (String) jsonObject.get("evaluate_rate");
			if (rating == null) {
				map.put("rating", 0);
			} else {
				double doubleRating = Double.parseDouble(rating.split("%")[0]);
				map.put("rating", Float.parseFloat(String.format("%.1f", doubleRating / 20.0)));
			}
			
			list.add(map);
		}
		
		return list;
	}
}

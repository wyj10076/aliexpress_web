package com.app.aliexpress.common.util;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AliexpressAffiliateLinkGenerateRequest;
import com.taobao.api.request.AliexpressAffiliateProductQueryRequest;
import com.taobao.api.request.AliexpressAffiliateProductdetailGetRequest;
import com.taobao.api.response.AliexpressAffiliateLinkGenerateResponse;
import com.taobao.api.response.AliexpressAffiliateProductQueryResponse;
import com.taobao.api.response.AliexpressAffiliateProductdetailGetResponse;

public class Taobao {

	public static Map<String, Object> getProduct(Map<String, Object> map) throws Exception {
		String url = (String) map.get("SERVER_APP_URL");
		String appkey = (String) map.get("SERVER_APP_KEY");
		String secret = (String) map.get("SERVER_SECRET_KEY");
		String trackingId = (String) map.get("SERVER_TRACKING_ID");
		String locale = (String) map.get("locale");
		String productId = (String) map.get("productId");
		
		Map<String, Object> product = null;
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		
		AliexpressAffiliateProductdetailGetRequest req = new AliexpressAffiliateProductdetailGetRequest();
		
		req.setProductIds(productId);
		
		if (locale.equals("ko_KR")) {
			req.setTargetCurrency("KRW");
			req.setTargetLanguage("KO");
			
		} else {
			
			req.setTargetCurrency("USD");
			req.setTargetLanguage("EN");
		}
		
		req.setTrackingId(trackingId);
		
		AliexpressAffiliateProductdetailGetResponse rsp = client.execute(req);

		JSONParser parser = new JSONParser();
		JSONObject resp_result = (JSONObject) ((JSONObject) ((JSONObject) parser.parse(rsp.getBody()))
				.get("aliexpress_affiliate_productdetail_get_response")).get("resp_result");

		String resp_code = String.valueOf(resp_result.get("resp_code"));
		
		if (resp_code.equals("200")) {
			JSONArray jsonArray = (JSONArray) ((JSONObject) ((JSONObject) resp_result.get("result")).get("products"))
					.get("product");
			
			if (jsonArray != null) {
				product = ProductListProcessor.process(false, productId, jsonArray).get(0);
			}
		}

		return product;
	}
	
	public static List<Map<String, Object>> getProductList(Map<String, Object> map) throws Exception {
		String url = (String) map.get("SERVER_APP_URL");
		String appkey = (String) map.get("SERVER_APP_KEY");
		String secret = (String) map.get("SERVER_SECRET_KEY");
		String trackingId = (String) map.get("SERVER_TRACKING_ID");
		
		String locale = (String) map.get("locale");
		String productId = (String) map.get("productId");
		String productTitle = (String) map.get("title");
		String firstCategoryId = (String) map.get("firstCategoryId");
		String secondCategoryId = (String) map.get("secondCategoryId");

		String[] keywordArray = productTitle.split(" ");
		int keywordLength = keywordArray.length;
		int startIndex = keywordLength / 4;
		int endIndex = keywordLength - startIndex;

		StringBuilder keyword = new StringBuilder();
		for (int i = startIndex; i < endIndex; i++) {
			keyword.append(keywordArray[i] + " ");
		}

		List<Map<String, Object>> productList = null;

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		
		AliexpressAffiliateProductQueryRequest req = new AliexpressAffiliateProductQueryRequest();
		
		req.setCategoryIds(firstCategoryId + "," + secondCategoryId);
		req.setKeywords(keyword.toString());
		req.setPageNo(1L);
		req.setPageSize(10L);
		
		if (locale.equals("ko_KR")) {
			req.setTargetCurrency("KRW");
			req.setTargetLanguage("KO");
			
		} else {
			req.setTargetCurrency("USD");
			req.setTargetLanguage("EN");
		}
		
		req.setTrackingId(trackingId);
		
		AliexpressAffiliateProductQueryResponse rsp = client.execute(req);

		JSONParser parser = new JSONParser();
		JSONObject resp_result = (JSONObject) ((JSONObject) ((JSONObject) parser.parse(rsp.getBody()))
				.get("aliexpress_affiliate_product_query_response")).get("resp_result");

		String resp_code = String.valueOf(resp_result.get("resp_code"));

		if (resp_code.equals("200")) {
			JSONArray jsonArray = (JSONArray) ((JSONObject) ((JSONObject) resp_result.get("result")).get("products"))
					.get("product");
			productList = ProductListProcessor.process(true, productId, jsonArray);
		}

		return productList;
	}
	
	public static String linkGenerate(Map<String, Object> map) throws Exception {
		String url = (String) map.get("SERVER_APP_URL");
		String appkey = (String) map.get("SERVER_APP_KEY");
		String secret = (String) map.get("SERVER_SECRET_KEY");
		String trackingId = (String) map.get("SERVER_TRACKING_ID");
		String source = (String) map.get("hotProductLink");
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		
		AliexpressAffiliateLinkGenerateRequest req = new AliexpressAffiliateLinkGenerateRequest();
		
		req.setPromotionLinkType(0L);
		req.setSourceValues(source);
		req.setTrackingId(trackingId);
		
		AliexpressAffiliateLinkGenerateResponse rsp = client.execute(req);

		JSONParser parser = new JSONParser();
		JSONObject resp_result = (JSONObject) ((JSONObject) ((JSONObject) parser.parse(rsp.getBody()))
				.get("aliexpress_affiliate_link_generate_response")).get("resp_result");
		
		String resp_code = String.valueOf(resp_result.get("resp_code"));
		String promotionLink = null;
		
		if (resp_code.equals("200")) {
			promotionLink = (String) ((JSONObject) ((JSONArray) ((JSONObject) ((JSONObject) resp_result.get("result"))
					.get("promotion_links")).get("promotion_link")).get(0)).get("promotion_link");
		}
		
		return promotionLink;
	}
}

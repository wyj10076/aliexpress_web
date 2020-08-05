package com.app.aliexpress.hotproduct.client.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.common.util.Taobao;
import com.app.aliexpress.hotproduct.client.service.GenerateLinkService;
import com.app.aliexpress.server.mapper.ServerMapper;

@Service("clientGenerateLinkService")
public class GenerateLinkServiceImpl implements GenerateLinkService {
	
	@Autowired
	private ServerMapper serverMapper;
	
	@Override
	public void generateLink(Map<String, Object> map) {
		try {
			map.put("serverUseYN", "Y");
			map.putAll(serverMapper.selectServer(map));
			String promotionLink = Taobao.linkGenerate(map);
			
			if (promotionLink != null) {
				map.put("promotionLink", promotionLink);
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}
		} catch (Exception e) {
			map.put("result", "fail");
		}
	}

}

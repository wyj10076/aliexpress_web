package com.app.aliexpress.maincontent.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.maincontent.admin.service.SelectMainContentListService;
import com.app.aliexpress.maincontent.mapper.MainContentMapper;

@Service("adminSelectMainContentListService")
public class SelectMainContentListServiceImpl implements SelectMainContentListService {

	@Autowired
	private MainContentMapper mainContentMapper;
	
	@Override
	public void selectMainContentList(Map<String, Object> map) {
		String key = (String) map.get("mainContentType") + "ContentList";
		map.put(key, mainContentMapper.selectMainContentList(map));
	}

}

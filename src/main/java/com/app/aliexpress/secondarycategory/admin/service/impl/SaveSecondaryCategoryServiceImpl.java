package com.app.aliexpress.secondarycategory.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.app.aliexpress.common.util.FileUtils;
import com.app.aliexpress.secondarycategory.admin.service.SaveSecondaryCategoryService;
import com.app.aliexpress.secondarycategory.mapper.SecondaryCategoryMapper;

@Service("adminSaveSecondaryCategoryService")
public class SaveSecondaryCategoryServiceImpl implements SaveSecondaryCategoryService {

	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private TransactionDefinition definition;
	@Autowired
	private SecondaryCategoryMapper secondaryCategoryMapper;
	
	@Override
	public void saveSecondaryCategoryList(Map<String, Object> map) {
		TransactionStatus status = transactionManager.getTransaction(definition); 
		
		try {
			int result;

			if (map.get("insertList") != null) {
				
				result = secondaryCategoryMapper.insertSecondaryCategoryList(map);
				
				if (result > 0) {
					map.put("result" , "success");
				} else {
					map.put("result", "fail");
				}
			}
			if (map.get("updateList") != null) {
				result = secondaryCategoryMapper.updateSecondaryCategoryList(map);
				
				if (result > 0) {
					map.put("result", "success");
				} else {
					map.put("reuslt", "fail");
				}
			}
			if (map.get("deleteList") != null) {
				List<Map<String, Object>> deleteList = (List<Map<String, Object>>) map.get("deleteList");
				String realPath = (String) map.get("realPath");
				for (Map<String, Object> secondaryCategory : deleteList) {
					FileUtils.deleteFile(realPath, secondaryCategory);
				}
				
				result = secondaryCategoryMapper.deleteSecondaryCategoryList(map);
				
				if (result > 0) {
					map.put("result", "success");
				} else {
					map.put("reuslt", "fail");
				}
			}
			
			transactionManager.commit(status);
			
		} catch (Exception e) {
			map.put("result", "fail");
			transactionManager.rollback(status);
		}
	}

}

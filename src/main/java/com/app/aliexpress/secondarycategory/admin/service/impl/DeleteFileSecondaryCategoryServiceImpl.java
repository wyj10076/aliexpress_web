package com.app.aliexpress.secondarycategory.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.app.aliexpress.common.util.FileUtils;
import com.app.aliexpress.secondarycategory.admin.service.DeleteFileSecondaryCategoryService;
import com.app.aliexpress.secondarycategory.mapper.SecondaryCategoryMapper;

@Service("adminDeleteFileSecondaryCategoryService")
public class DeleteFileSecondaryCategoryServiceImpl implements DeleteFileSecondaryCategoryService {

	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private TransactionDefinition definition;
	@Autowired
	private SecondaryCategoryMapper secondaryCategoryMapper;
	
	@Override
	public void deleteFileSecondaryCategory(Map<String, Object> map) {
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			String realPath = (String) map.get("realPath");
			secondaryCategoryMapper.updateFileSecondaryCategory(map);
			
			FileUtils.deleteFile(realPath, map);
			
			map.put("result", "success");
			transactionManager.commit(status);
			
		} catch (Exception e) {
			map.put("result", "fail");
			transactionManager.rollback(status);
		}
	
	}

}

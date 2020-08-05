package com.app.aliexpress.primarycategory.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.app.aliexpress.common.util.FileUtils;
import com.app.aliexpress.primarycategory.admin.service.SavePrimaryCategoryService;
import com.app.aliexpress.primarycategory.mapper.PrimaryCategoryMapper;
import com.app.aliexpress.secondarycategory.mapper.SecondaryCategoryMapper;

@Service("adminSavePrimaryCategoryService")
public class SavePrimaryCategoryServiceImpl implements SavePrimaryCategoryService {

	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private TransactionDefinition definition;
	@Autowired
	private PrimaryCategoryMapper primaryCategoryMapper;
	@Autowired
	private SecondaryCategoryMapper secondaryCategoryMapper;
	
	@Override
	public void savePriamryCategoryList(Map<String, Object> map) {
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			int result;

			if (map.get("insertList") != null) {
				result = primaryCategoryMapper.insertPrimaryCategoryList(map);

				if (result > 0) {
					map.put("result", "success");
				} else {
					map.put("reuslt", "fail");
				}
			}
			if (map.get("updateList") != null) {
				result = primaryCategoryMapper.updatePrimaryCategoryList(map);

				if (result > 0) {
					map.put("result", "success");
				} else {
					map.put("reuslt", "fail");
				}
			}
			if (map.get("deleteList") != null) {
				String realPath = (String) map.get("realPath");
				
				List<Map<String, Object>> deleteList = (List<Map<String, Object>>) map.get("deleteList");
				for (int i = 0; i < deleteList.size(); i++) {
					Map<String, Object> tmpMap = new HashMap<String, Object>();
					tmpMap.put("primaryCategoryId", deleteList.get(i).get("categoryId"));
					List<Map<String, Object>> secondaryCategoryList = secondaryCategoryMapper.selectSecondaryCategoryList(tmpMap);
					
					for (Map<String, Object> secondaryCategory : secondaryCategoryList) {
						String originHotProductExcel = (String) secondaryCategory.get("SECONDARY_CATEGORY_FILE");
						secondaryCategory.put("originHotProductExcel", originHotProductExcel);
						FileUtils.deleteFile(realPath, secondaryCategory);
					}
				}
				
				result = primaryCategoryMapper.deletePrimaryCategoryList(map);

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

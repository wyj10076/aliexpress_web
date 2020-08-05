package com.app.aliexpress.hotproduct.admin.service.impl;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.app.aliexpress.common.util.FileUtils;
import com.app.aliexpress.hotproduct.admin.service.SaveHotProductService;
import com.app.aliexpress.hotproduct.mapper.HotProductMapper;
import com.app.aliexpress.secondarycategory.mapper.SecondaryCategoryMapper;

@Service("adminSaveHotProductService")
public class SaveHotProductServiceImpl implements SaveHotProductService {

	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private TransactionDefinition definition;
	@Autowired
	private HotProductMapper hotProductMapper;
	@Autowired
	private SecondaryCategoryMapper secondaryCategoryMapper;
	
	@Override
	public void saveHotProduct(Map<String, Object> map) {
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			String realPath = (String) map.get("realPath");
			MultipartFile hotProductExcel = (MultipartFile) map.get("hotProductExcel");
			String originHotProductExcel = (String) map.get("originHotProductExcel");
			
			List<Map<String, Object>> hotProductList;
			
			if (hotProductExcel != null) {
				hotProductList = FileUtils.loadMultipartExcelFile(realPath, map);
			} else {
				hotProductList = FileUtils.loadLocalExcelFile(realPath, map);
			}
			
			hotProductMapper.deleteHotProductList(map);
			
			if (hotProductList.size() != 0) {
				map.put("hotProductList", hotProductList);
				int result = hotProductMapper.insertHotProductList(map);
				
				if (result <= 0) {
					throw new Exception();
				}
			}
			
			if (hotProductExcel != null) {
				map.putAll(FileUtils.getFiles(realPath, map));
				secondaryCategoryMapper.updateFileSecondaryCategory(map);
				FileUtils.upload(map);
				if (!originHotProductExcel.equals("")) {
					FileUtils.deleteFile(realPath, map);
				}
			}
			
			map.clear();
			map.put("result", "success");
			transactionManager.commit(status);
			
		} catch (FileNotFoundException fnfe) {
			map.clear();
			map.put("result", "fileNotFound");
			
		} catch (Exception e) {
			map.clear();
			map.put("result", "fail");
			transactionManager.rollback(status);
		}

	}

}

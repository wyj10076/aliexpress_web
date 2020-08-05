package com.app.aliexpress.maincontent.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.app.aliexpress.common.util.FileUtils;
import com.app.aliexpress.maincontent.admin.service.SaveMainContentListService;
import com.app.aliexpress.maincontent.dto.MainContentListDto;
import com.app.aliexpress.maincontent.mapper.MainContentMapper;

@Service("saveMainContentListService")
public class SaveMainContentListServiceImpl implements SaveMainContentListService {

	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private TransactionDefinition definition;
	@Autowired
	private MainContentMapper mainContentMapper;
	
	@Override
	public void saveMainContentList(Map<String, Object> map) {
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			MainContentListDto dto = (MainContentListDto) map.get("dto");
			String realPath = (String) map.get("realPath");
			
			Map<String, List<Map<String, Object>>> result = proccessList(dto);
			
			List<Map<String, Object>> insertList = result.get("insertList");
			List<Map<String, Object>> updateList = result.get("updateList");
			List<Map<String, Object>> deleteList = result.get("deleteList");
			List<Map<String, Object>> orderList = result.get("orderList");
			List<Map<String, Object>> removeFileList = result.get("removeFileList");
			
			for (int i = 0; i < insertList.size(); i++) {
				Map<String, Object> files = FileUtils.getFiles(realPath, insertList.get(i));
				insertList.get(i).putAll(files);
			}
			
			for (int i = 0; i < updateList.size(); i++) {
				Map<String, Object> files = FileUtils.getFiles(realPath, updateList.get(i));
				updateList.get(i).putAll(files);
			}
			
			int resultCount;
			
			if (insertList.size() != 0) {
				resultCount = mainContentMapper.insertMainContentList(result);
				
				if (resultCount <= 0) {
					throw new Exception();
				}
			}
			if (updateList.size() != 0) {
				resultCount = mainContentMapper.updateMainContentList(result);
				
				if (resultCount <= 0) {
					throw new Exception();
				}
			} 
			if (orderList.size() != 0) {
				resultCount = mainContentMapper.updateOrderMainContentList(result);
				
				if (resultCount <= 0) {
					throw new Exception();
				}
			} 
			if (deleteList.size() != 0) {
				resultCount = mainContentMapper.deleteMainContentList(result);
				
				if (resultCount <= 0) {
					throw new Exception();
				}
			} 

			// 파일 업로드 및 삭제
			for (int i = 0; i < insertList.size(); i++) {
				FileUtils.upload(insertList.get(i));
			}
			
			for (int i = 0; i < updateList.size(); i++) {
				FileUtils.upload(updateList.get(i));
			}
			
			// 파일 삭제 코드 예정
			for (Map<String, Object> removeFile : removeFileList) {
				FileUtils.deleteFile(realPath, removeFile);
			}
			
			map.clear();
			map.put("result", "success");
			transactionManager.commit(status);
			
		} catch (Exception e) {
			map.put("result", "fail");
			transactionManager.rollback(status);
		}
	}
	
	private Map<String, List<Map<String, Object>>> proccessList(MainContentListDto dto) {
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		
		List<Integer> mainContentIdList = dto.getMainContentId();
		List<String> mainContentTypeList = dto.getMainContentType();
		List<MultipartFile> mainContentImageKoList = dto.getMainContentImageKo();
		List<String> originMainContentImageKoList = dto.getOriginMainContentImageKo();
		List<MultipartFile> mainContentImageEnList = dto.getMainContentImageEn();
		List<String> originMainContentImageEnList = dto.getOriginMainContentImageEn();
		List<String> mainContentDescriptionList = dto.getMainContentDescription();
		List<String> mainContentStatusList = dto.getStatus();
		List<Integer> deleteMainContentIdList = dto.getDeleteMainContentId();
		List<String> deleteMainContentTypeList = dto.getDeleteMainContentType();
		List<String> deleteMainContentImageKoList = dto.getDeleteMainContentImageKo();
		List<String> deleteMainContentImageEnList = dto.getDeleteMainContentImageEn();
		
		List<Map<String, Object>> insertList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> updateList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> deleteList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> removeFileList = new ArrayList<Map<String, Object>>();
		
		for (int i = 0; i < deleteMainContentIdList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mainContentId", deleteMainContentIdList.get(i));
			map.put("mainContentType", deleteMainContentTypeList.get(i));
			map.put("mainContentImageKo", deleteMainContentImageKoList.get(i));
			map.put("mainContentImageEn", deleteMainContentImageEnList.get(i));
			
			deleteList.add(map);
			removeFileList.add(map);
		}
		
		if (mainContentStatusList != null) {
			
			for (int i = 0; i < mainContentStatusList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Map<String, Object> removeFile;
				
				String status = mainContentStatusList.get(i);
				String mainContentDescription;
				Integer mainContentId;
				
				switch (status) {
				case "new" :
					mainContentDescription = mainContentDescriptionList.get(i);
					
					map.put("mainContentType", mainContentTypeList.get(i));
					map.put("mainContentImageKo", mainContentImageKoList.get(i));
					map.put("mainContentImageEn", mainContentImageEnList.get(i));
					
					if (!mainContentDescription.equals("null")) {
						map.put("mainContentDescription", mainContentDescription);
					}
					
					map.put("mainContentOrder", i);
					insertList.add(map);
					break;
					
				case "update" :
					mainContentId = mainContentIdList.get(i);
					mainContentDescription = mainContentDescriptionList.get(i);
					
					for (int j = 0; j < deleteList.size(); j++) {
						Integer id = (Integer) deleteList.get(j).get("mainContentId");
						
						if (mainContentId.intValue() == id.intValue()) {
							deleteList.remove(j);
							break;
						}
					}
					
					map.put("mainContentId", mainContentId);
					map.put("mainContentType", mainContentTypeList.get(i));
					
					if (!mainContentDescription.equals("null")) {
						map.put("mainContentDescription", mainContentDescription);
					}
					
					map.put("mainContentOrder", i);
					updateList.add(map);
					break;
					
				case "update-ko" :
					mainContentId = mainContentIdList.get(i);
					mainContentDescription = mainContentDescriptionList.get(i);
					
					for (int j = 0; j < deleteList.size(); j++) {
						Integer id = (Integer) deleteList.get(j).get("mainContentId");
						
						if (mainContentId.intValue() == id.intValue()) {
							deleteList.remove(j);
							break;
						}
					}
					
					map.put("mainContentId", mainContentId);
					map.put("mainContentType", mainContentTypeList.get(i));
					map.put("mainContentImageKo", mainContentImageKoList.get(i));
					
					if (!mainContentDescription.equals("null")) {
						map.put("mainContentDescription", mainContentDescription);
					}
					
					map.put("mainContentOrder", i);
					
					updateList.add(map);
					
					removeFile = new HashMap<String, Object>();
					removeFile.put("mainContentType", mainContentTypeList.get(i));
					removeFile.put("mainContentImageKo", originMainContentImageKoList.get(i));
					removeFileList.add(removeFile);
					break;
					
				case "update-en" :
					mainContentId = mainContentIdList.get(i);
					mainContentDescription = mainContentDescriptionList.get(i);
					
					for (int j = 0; j < deleteList.size(); j++) {
						Integer id = (Integer) deleteList.get(j).get("mainContentId");
						
						if (mainContentId.intValue() == id.intValue()) {
							deleteList.remove(j);
							break;
						}
					}
					
					map.put("mainContentId", mainContentId);
					map.put("mainContentType", mainContentTypeList.get(i));
					map.put("mainContentImageEn", mainContentImageEnList.get(i));
					
					if (!mainContentDescription.equals("null")) {
						map.put("mainContentDescription", mainContentDescription);
					}
					
					map.put("mainContentOrder", i);
					updateList.add(map);
					
					removeFile = new HashMap<String, Object>();
					removeFile.put("mainContentType", mainContentTypeList.get(i));
					removeFile.put("mainContentImageEn", originMainContentImageEnList.get(i));
					removeFileList.add(removeFile);
					break;
					
				case "update-all" :
					mainContentId = mainContentIdList.get(i);
					mainContentDescription = mainContentDescriptionList.get(i);
					
					for (int j = 0; j < deleteList.size(); j++) {
						Integer id = (Integer) deleteList.get(j).get("mainContentId");
						
						if (mainContentId.intValue() == id.intValue()) {
							deleteList.remove(j);
							break;
						}
					}
					
					map.put("mainContentId", mainContentId);
					map.put("mainContentType", mainContentTypeList.get(i));
					map.put("mainContentImageKo", mainContentImageKoList.get(i));
					map.put("mainContentImageEn", mainContentImageEnList.get(i));

					if (!mainContentDescription.equals("null")) {
						map.put("mainContentDescription", mainContentDescription);
					}
					
					map.put("mainContentOrder", i);
					updateList.add(map);
					
					removeFile = new HashMap<String, Object>();
					removeFile.put("mainContentType", mainContentTypeList.get(i));
					removeFile.put("mainContentImageKo", originMainContentImageKoList.get(i));
					removeFile.put("mainContentImageEn", originMainContentImageEnList.get(i));
					removeFileList.add(removeFile);
					break;
					
				case "origin" :
					mainContentId = mainContentIdList.get(i);
					
					for (int j = 0; j < deleteList.size(); j++) {
						Integer id = (Integer) deleteList.get(j).get("mainContentId");
						
						if (mainContentId.intValue() == id.intValue()) {
							deleteList.remove(j);
							break;
						}
					}
					
					map.put("mainContentId", mainContentId);
					map.put("mainContentOrder", i);
					orderList.add(map);
					break;
				}
			}
			
		}
		result.put("insertList", insertList);
		result.put("updateList", updateList);
		result.put("deleteList", deleteList);
		result.put("orderList", orderList);
		result.put("removeFileList", removeFileList);
		
		return result;
	}

}

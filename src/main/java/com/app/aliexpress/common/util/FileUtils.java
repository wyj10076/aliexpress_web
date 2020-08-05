package com.app.aliexpress.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	public static void upload(Map<String, Object> map) throws Exception {
		
		MultipartFile mainContentImageKo = (MultipartFile) map.get("mainContentImageKo");
		MultipartFile mainContentImageEn = (MultipartFile) map.get("mainContentImageEn");
		MultipartFile hotProductExcel = (MultipartFile) map.get("hotProductExcel");
		File mainContentImageKoFile = (File) map.get("mainContentImageKoFile");
		File mainContentImageEnFile = (File) map.get("mainContentImageEnFile");
		File hotProductExcelFile = (File) map.get("hotProductExcelFile");
		
		if (mainContentImageKo != null) {
			File dir = new File(mainContentImageKoFile.getParent());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			FileOutputStream fos = new FileOutputStream(mainContentImageKoFile);
			fos.write(mainContentImageKo.getBytes());
			fos.close();
		}
		
		if (mainContentImageEn != null) {
			File dir = new File(mainContentImageEnFile.getParent());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			FileOutputStream fos = new FileOutputStream(mainContentImageEnFile);
			fos.write(mainContentImageEn.getBytes());
			fos.close();
		}
		
		if (hotProductExcel != null) {
			File dir = new File(hotProductExcelFile.getParent());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			FileOutputStream fos = new FileOutputStream(hotProductExcelFile);
			fos.write(hotProductExcel.getBytes());
			fos.close();
		}
		
	}
	
	public static Map<String, Object> getFiles(String realPath, Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		StringBuilder commonPath = new StringBuilder("/upload/");
		
		String type = (String) map.get("mainContentType"); 
		MultipartFile mainContentImageKo = (MultipartFile) map.get("mainContentImageKo");
		MultipartFile mainContentImageEn = (MultipartFile) map.get("mainContentImageEn");
		MultipartFile hotProductExcel = (MultipartFile) map.get("hotProductExcel");
		
		if (mainContentImageKo != null) {
			StringBuilder filePath = new StringBuilder(commonPath.toString());
			filePath.append(type);
			filePath.append("/images/ko/");
			
			String fileName;
			File file;
			
			do {
				fileName = createRandomFileName(mainContentImageKo);
				file = new File(realPath + filePath.toString() + fileName);	
			} while (file.exists());
			
			filePath.append(fileName);
			
			result.put("mainContentImageKoFile", file);
			result.put("mainContentImageKoPath", filePath.toString());
		}
		
		if (mainContentImageEn != null) {
			StringBuilder filePath = new StringBuilder(commonPath.toString());
			filePath.append(type);
			filePath.append("/images/en/");
			
			String fileName;
			File file;
			
			do {
				fileName = createRandomFileName(mainContentImageEn);
				file = new File(realPath + filePath.toString() + fileName);	
			} while (file.exists());
			
			filePath.append(fileName);
			
			result.put("mainContentImageEnFile", file);
			result.put("mainContentImageEnPath", filePath.toString());
		}
		
		if (hotProductExcel != null) {
			StringBuilder filePath = new StringBuilder(commonPath.toString());
			filePath.append("hotproduct/");
			
			String fileName;
			File file;
			
			do {
				fileName = createRandomFileName(hotProductExcel);
				file = new File(realPath + filePath.toString() + fileName);	
			} while (file.exists());
			
			filePath.append(fileName);
			
			result.put("hotProductExcelFile", file);
			result.put("hotProductExcelPath", filePath.toString());
		}
		
		return result;
	}
	
	public static void deleteFile(String realPath, Map<String, Object> map) throws Exception {
		String mainContentImageKo = (String) map.get("mainContentImageKo");
		String mainContentImageEn = (String) map.get("mainContentImageEn");
		String originHotProductExcel = (String) map.get("originHotProductExcel");
		
		if (mainContentImageKo != null) {
			File file = new File(realPath + mainContentImageKo);
			
			if (file.exists()) {
				file.delete();
			}
		}
		
		if (mainContentImageEn != null) {
			File file = new File(realPath + mainContentImageEn);
			
			if (file.exists()) {
				file.delete();
			}
		}
		
		if (originHotProductExcel != null) {
			File file = new File(realPath + originHotProductExcel);
			
			if (file.exists()) {
				file.delete();
			}
		}
		
		if (map.get("tmpFile") != null) {
			File tmpFile = (File) map.get("tmpFile");
			
			if (tmpFile.exists()) {
				tmpFile.delete();
			}
		}
	}	
	
	public static List<Map<String, Object>> loadMultipartExcelFile(String realPath, Map<String, Object> map) throws Exception {
		MultipartFile hotProductFile = (MultipartFile) map.get("hotProductExcel");
		
		List<Map<String, Object>> hotProductList = new ArrayList<Map<String, Object>>();
		BufferedReader br;
		
		int imageCol = Integer.parseInt((String) map.get("imageCol"));
		int urlCol = Integer.parseInt((String) map.get("urlCol"));
		int originPriceCol = Integer.parseInt((String) map.get("originPriceCol"));
		int salePriceCol = Integer.parseInt((String) map.get("salePriceCol"));
		
		int primaryCategoryId = Integer.parseInt((String) map.get("primaryCategoryId"));
		int secondaryCategoryId = Integer.parseInt((String) map.get("secondaryCategoryId"));
		
		int hotProductCount = Integer.parseInt((String) map.get("hotProductCount"));
		
		File tmpDir = new File(realPath + "/tmp");
		if (!tmpDir.exists()) {
			tmpDir.mkdirs();
		}
		File tmpFile = File.createTempFile("upload_", ".tmp", tmpDir);
		copyInputStreamToFile(hotProductFile.getInputStream(), tmpFile);
		br = new BufferedReader(new FileReader(tmpFile));
		
		String line;
		int currentCount = 0;
		
		while((line = br.readLine()) != null) {
			currentCount++;
			
			if (currentCount == 1) {
				continue;
			}
			
			if (hotProductCount + 2 == currentCount) {
				break;
			}
			
			String[] cell = line.split(",");
			Map<String, Object> hotProduct = new HashMap<String, Object>();
			
			hotProduct.put("hotProductImage", cell[imageCol]);
			hotProduct.put("hotProductLink", cell[urlCol]);
			hotProduct.put("hotProductOriginPrice", cell[originPriceCol]);
			if (!cell[salePriceCol].equals("")) {
				hotProduct.put("hotProductSalePrice", cell[salePriceCol]);
			}
			
			hotProduct.put("hotProductPrimaryCategoryId", primaryCategoryId);
			hotProduct.put("hotProductSecondaryCategoryId", secondaryCategoryId);

			hotProductList.add(hotProduct);
		}
		
		if (br != null) {
			br.close();
		}
		
		tmpFile.delete();
		return hotProductList;
	}
	
	public static List<Map<String, Object>> loadLocalExcelFile(String realPath, Map<String, Object> map) throws Exception {
		File originHotProductFile = new File(realPath + (String) map.get("originHotProductExcel"));
		List<Map<String, Object>> hotProductList = new ArrayList<Map<String, Object>>();
		BufferedReader br;
		
		if (originHotProductFile.exists()) {
			int imageCol = Integer.parseInt((String) map.get("imageCol"));
			int urlCol = Integer.parseInt((String) map.get("urlCol"));
			int originPriceCol = Integer.parseInt((String) map.get("originPriceCol"));
			int salePriceCol = Integer.parseInt((String) map.get("salePriceCol"));
			
			int primaryCategoryId = Integer.parseInt((String) map.get("primaryCategoryId"));
			int secondaryCategoryId = Integer.parseInt((String) map.get("secondaryCategoryId"));
			
			int hotProductCount = Integer.parseInt((String) map.get("hotProductCount"));
			
			br = new BufferedReader(new FileReader(originHotProductFile));
			
			String line;
			int currentCount = 0;
			
			while((line = br.readLine()) != null) {
				currentCount++;
				
				if (currentCount == 1) {
					continue;
				}
				
				if (hotProductCount + 2 == currentCount) {
					break;
				}
				
				String[] cell = line.split(",");
				Map<String, Object> hotProduct = new HashMap<String, Object>();
				
				hotProduct.put("hotProductImage", cell[imageCol]);
				hotProduct.put("hotProductLink", cell[urlCol]);
				hotProduct.put("hotProductOriginPrice", cell[originPriceCol]);
				if (!cell[salePriceCol].equals("")) {
					hotProduct.put("hotProductSalePrice", cell[salePriceCol]);
				}
				
				hotProduct.put("hotProductPrimaryCategoryId", primaryCategoryId);
				hotProduct.put("hotProductSecondaryCategoryId", secondaryCategoryId);
				
				hotProductList.add(hotProduct);
			}
		} else {
			throw new FileNotFoundException("파일을 찾을 수 없습니다.");
		}
		
		if (br != null) {
			br.close();
		}
		
		return hotProductList;
	}
	
	private static String createRandomFileName(MultipartFile multipartFile) {
		String[] fileNames = multipartFile.getOriginalFilename().split("\\.");
		String suffix = fileNames[fileNames.length - 1];
		return UUID.randomUUID().toString().split("-")[0] + "." + suffix;
	}
	
	// InputStream -> File
    private static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {

    	FileOutputStream outputStream = new FileOutputStream(file);
        int read;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        
        if (outputStream != null) {
        	outputStream.close();
        }

    }
	
}

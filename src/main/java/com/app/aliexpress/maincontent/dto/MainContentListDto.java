package com.app.aliexpress.maincontent.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MainContentListDto {

	private List<Integer> mainContentId;
	private List<String> mainContentType;
	private List<MultipartFile> mainContentImageKo;
	private List<String> originMainContentImageKo;
	private List<MultipartFile> mainContentImageEn;
	private List<String> originMainContentImageEn;
	private List<String> mainContentDescription;
	private List<String> status;
	private List<Integer> deleteMainContentId;
	private List<String> deleteMainContentType;
	private List<String> deleteMainContentImageKo;
	private List<String> deleteMainContentImageEn;

	public List<Integer> getMainContentId() {
		return mainContentId;
	}

	public void setMainContentId(List<Integer> mainContentId) {
		this.mainContentId = mainContentId;
	}

	public List<String> getMainContentType() {
		return mainContentType;
	}

	public void setMainContentType(List<String> mainContentType) {
		this.mainContentType = mainContentType;
	}

	public List<MultipartFile> getMainContentImageKo() {
		return mainContentImageKo;
	}

	public void setMainContentImageKo(List<MultipartFile> mainContentImageKo) {
		this.mainContentImageKo = mainContentImageKo;
	}

	public List<String> getOriginMainContentImageKo() {
		return originMainContentImageKo;
	}

	public void setOriginMainContentImageKo(List<String> originMainContentImageKo) {
		this.originMainContentImageKo = originMainContentImageKo;
	}

	public List<MultipartFile> getMainContentImageEn() {
		return mainContentImageEn;
	}

	public void setMainContentImageEn(List<MultipartFile> mainContentImageEn) {
		this.mainContentImageEn = mainContentImageEn;
	}

	public List<String> getOriginMainContentImageEn() {
		return originMainContentImageEn;
	}

	public void setOriginMainContentImageEn(List<String> originMainContentImageEn) {
		this.originMainContentImageEn = originMainContentImageEn;
	}

	public List<String> getMainContentDescription() {
		return mainContentDescription;
	}

	public void setMainContentDescription(List<String> mainContentDescription) {
		this.mainContentDescription = mainContentDescription;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public List<Integer> getDeleteMainContentId() {
		return deleteMainContentId;
	}

	public void setDeleteMainContentId(List<Integer> deleteMainContentId) {
		this.deleteMainContentId = deleteMainContentId;
	}

	public List<String> getDeleteMainContentType() {
		return deleteMainContentType;
	}

	public void setDeleteMainContentType(List<String> deleteMainContentType) {
		this.deleteMainContentType = deleteMainContentType;
	}

	public List<String> getDeleteMainContentImageKo() {
		return deleteMainContentImageKo;
	}

	public void setDeleteMainContentImageKo(List<String> deleteMainContentImageKo) {
		this.deleteMainContentImageKo = deleteMainContentImageKo;
	}

	public List<String> getDeleteMainContentImageEn() {
		return deleteMainContentImageEn;
	}

	public void setDeleteMainContentImageEn(List<String> deleteMainContentImageEn) {
		this.deleteMainContentImageEn = deleteMainContentImageEn;
	}

	@Override
	public String toString() {
		return "MainContentListDto [mainContentId=" + mainContentId + ", mainContentType=" + mainContentType
				+ ", mainContentImageKo=" + mainContentImageKo + ", originMainContentImageKo="
				+ originMainContentImageKo + ", mainContentImageEn=" + mainContentImageEn
				+ ", originMainContentImageEn=" + originMainContentImageEn + ", mainContentDescription="
				+ mainContentDescription + ", status=" + status + ", deleteMainContentId=" + deleteMainContentId
				+ ", deleteMainContentType=" + deleteMainContentType + ", deleteMainContentImageKo="
				+ deleteMainContentImageKo + ", deleteMainContentImageEn=" + deleteMainContentImageEn + "]";
	}

}

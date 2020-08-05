<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/common/selectize/selectize.bootstrap3.css" />
<link rel="stylesheet" href="/admin/css/hotProduct.min.css" />
<title>Hot Product</title>
</head>
<body>
	<div class="hotProduct-wrap">
		<div class="hotProduct-box">
			<div class="menu-title">인기 제품 관리</div>
			<hr class="menu-division" />
			<div class="hotProduct-topside-button-box">
				<button type="button" class="topside-button hotProduct-delete-button">
					삭제</button>
				<button type="button" class="topside-button hotProduct-save-button">
					저장</button>
			</div>
			<form class="hotProduct-form">
				<div class="select-box-wrap">
					<select name="primaryCategoryId"
						class="primary-category-select form-control form-control-sm shadow-none">
						<option value=""></option>
						<c:forEach items="${primaryCategoryList}" var="primaryCategory">
							<option value="${primaryCategory.PRIMARY_CATEGORY_ID }">${primaryCategory.PRIMARY_CATEGORY_NAME_KO }</option>
						</c:forEach>
					</select>
					<div class="select-blank"></div>
					<select name="secondaryCategoryId"
						class="secondary-category-select form-control form-control-sm shadow-none">
					</select>
				</div>
				<div class="no-select">
					<div class="no-select-title">카테고리를 선택해 주세요.</div>
				</div>
				<div class="hotProduct-item-box">
					<div class="inner-item-box">
						<label class="item-label" for=""> 제품 개수 </label>
						<div class="item-count-radio-box">
							<div class="radio-wrap">
								<input type="radio" name="hotProductCount" id="first-radio"
									value="50" checked /> <label for="first-radio">50</label>
							</div>
							<div class="radio-wrap">
								<input type="radio" name="hotProductCount" id="second-radio"
									value="100" /> <label for="second-radio">100</label>
							</div>
							<div class="radio-wrap">
								<input type="radio" name="hotProductCount" id="third-radio"
									value="0" /> <label for="third-radio">최대</label>
							</div>
							<div class="radio-wrap">
								<input type="radio" name="hotProductCount" id="self-radio" /> <label
									for="fourth-radio">직접 입력</label> <input type="text"
									id="self-input" name="hotProductCount"
									class="form-control form-control-sm shadow-none"
									placeholder="1 이상" />
							</div>
						</div>
					</div>
					<div class="inner-item-box">
						<label for="" class="item-label">열 번호 </label>
						<div class="col-info-box">
							<div class="col-info-wrap">
								<label for="">이미지</label>
								<select
									id="imageCol" name="imageCol"
									class="form-control form-control-sm shadow-none">
								</select>
							</div>
							<div class="col-info-wrap">
								<label for="">URL</label>
								<select
									id="urlCol" name="urlCol"
									class="form-control form-control-sm shadow-none">
								</select>
							</div>
							<div class="col-info-wrap">
								<label for="">원가</label>
								<select
									id="originPriceCol" name="originPriceCol"
									class="form-control form-control-sm shadow-none">
								</select>
							</div>
							<div class="col-info-wrap">
								<label for="">할인가</label>
								<select
									id="salePriceCol" name="salePriceCol"
									class="form-control form-control-sm shadow-none">
								</select>
							</div>
						</div>
					</div>
					<div class="inner-item-box">
						<label class="item-label" for=""> 엑셀 파일 </label>
						<div class="custom-file item-file-box">
							<input data-type="none" type="file" class="custom-file-input"
								name="hotProductExcel" id="hotProductExcel"
								accept=".csv, application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />
							<input type="hidden" id="originHotProductExcel"
								name="originHotProductExcel" /> <label id="custom-file-label"
								class="custom-file-label shadow-none">엑셀 파일을 선택해 주세요.</label>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="/common/selectize/selectize.min.js"></script>
	<script type="text/javascript" src="/admin/js/hotProduct.js"></script>
</body>
</html>

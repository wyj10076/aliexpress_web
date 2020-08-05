<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="/common/bootstrap-datepicker-1.9.0/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet"
	href="/common/bootstrap-datepicker-1.9.0/css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" href="/admin/css/promotion.min.css" />
<title>Promotion</title>
</head>
<body>
	<div class="promotion-wrap">
		<div class="promotion-box">
			<div class="menu-title ${type }"></div>
			<input type="hidden" id="promotion-type" value="${type }" />
			<input type="hidden" id="promotion-id" value="${promotionId }" />
			<hr class="menu-division first" />
			<div class="language-title">한글 버전</div>
			<div class="promotion-form">
				<div class="promotion-info-box">
					<div class="promotion-title-box promotion-info">
						<label class="promotion-label" for="promotion-title-ko-input">제목</label>
						<input id="promotion-title-ko-input"
							class="promotion-title-input form-control form-control-sm shadow-none" />
					</div>
					<div class="promotion-html-box promotion-info">
						<label class="promotion-label" for="promotion-html-ko-textarea">HTML
							Code</label>
						<textarea id="promotion-html-ko-textarea"
							class="promotion-html-textarea form-control form-control-sm shadow-none"
							rows="6"></textarea>
					</div>
					<div class="promotion-link-box promotion-info">
						<label class="promotion-label" for="promotion-link-ko-input">링크</label>
						<div
							class="form-control form-control-sm shadow-none promotion-link-wrap">
							<a id="promotion-link-ko" class="promotion-link"></a>
						</div>
					</div>
				</div>
				<div class="promotion-img-box">
					<img data-type="default" id="promotion-img-ko" class="promotion-img"
						src="/common/image/no-image.png" />
				</div>
			</div>
			<hr class="menu-division" />
			<div class="language-title">영문 버전</div>
			<div class="promotion-form">
				<div class="promotion-info-box">
					<div class="promotion-title-box promotion-info">
						<label class="promotion-label" for="promotion-title-en-input">제목</label>
						<input id="promotion-title-en-input"
							class="promotion-title-input form-control form-control-sm shadow-none" />
					</div>
					<div class="promotion-html-box promotion-info">
						<label class="promotion-label" for="promotion-html-textarea">HTML
							Code</label>
						<textarea id="promotion-html-en-textarea"
							class="promotion-html-textarea form-control form-control-sm shadow-none"
							rows="6"></textarea>
					</div>
					<div class="promotion-link-box promotion-info">
						<label class="promotion-label" for="promotion-link-en-input">링크</label>
						<div
							class="form-control form-control-sm shadow-none promotion-link-wrap">
							<a id="promotion-link-en" class="promotion-link"></a>
						</div>
					</div>
				</div>
				<div class="promotion-img-box">
					<img data-type="default" id="promotion-img-en" class="promotion-img"
						src="/common/image/no-image.png" />
				</div>
			</div>
			<hr class="menu-division ${type}" />
			<div class="promotion-form ${type}">
				<div class="promotion-info-box promotion-date-box">
					<div class="promotion-info">
						<label class="promotion-label" for="promotion-date-picker">기간</label>
						<div class="input-daterange input-group"
							id="promotion-date-picker">
							<input type="text" id="start-ymd" class="form-control-sm form-control" name="start" />
							<span class="input-group-addon">~</span>
							<input type="text" id="end-ymd" class="form-control-sm  form-control" name="end" />
						</div>
					</div>
				</div>
			</div>
			<hr class="menu-division" />
			<div class="promotion-button-box">
				<button class="promotion-cancel-button promotion-button"
					type="button">취소</button>
				<button class="promotion-add-button promotion-button" type="button">
					등록</button>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	<script type="text/javascript"
		src="/common/bootstrap-datepicker-1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script
		src="/common/bootstrap-datepicker-1.9.0/locales/bootstrap-datepicker.ko.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript" src="/admin/js/promotion.js"></script>
</body>
</html>
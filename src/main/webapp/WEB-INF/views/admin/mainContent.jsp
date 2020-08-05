<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/admin/css/mainContent.min.css" />
<title>Main Content</title>
</head>
<body>
	<div data-type="${type }" class="mainContent-wrap">
		<div class="mainContent-box">
			<div class="menu-title ${type }"></div>
			<hr class="menu-division" />
			<div class="mainContent-topside-button-box">
				<button type="button" class="topside-button mainContent-save-button">
					저장</button>
			</div>
			<div class="no-data">
				<div class="no-data-title ${type }"></div>
				<button type="button" class="no-data-add-button">
					<i class="fas fa-plus"></i>추가하기
				</button>
			</div>
			<form id="main-content-form" class="mainContent-form-wrap" action="/management/saveMainContentList" method="post" enctype="multipart/form-data">
				<!-- 여기에 추가 -->
			</form>
		</div>
	</div>
	<script type="text/javascript" src="/admin/js/mainContent.js"></script>
</body>
</html>


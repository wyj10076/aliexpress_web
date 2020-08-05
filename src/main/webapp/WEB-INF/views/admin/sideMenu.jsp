<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/admin/css/sideMenu.min.css" />
<title>Side Menu</title>
</head>
<body>
	<div class="sideMenu-fixed-box">
		<div class="profile-box">
			<i class="fas fa-user-circle"></i>
			<div class="profile-id">${manager.MANAGER_ID } 님</div>
			<div class="logout-button">로그아웃</div>
		</div>
		<div class="sideMenu-box">
			<ul class="sideMenu-wrap">
				<li data-link="/management/dashboard.aba" class="sideMenu-button">
					대시 보드</li>
				<li data-type="main"
					class="sideMenu-button parent-extension-button extension-close">
					<span>메인 관리</span><i class="fas fa-plus"></i>
				</li>
				<li data-link="/management/mainContent.aba?query=banner"
					class="sideMenu-button children-extension-button main-extension">
					배너</li>
				<li data-link="/management/mainContent.aba?query=front"
					class="sideMenu-button children-extension-button main-extension">
					대문</li>
				<li data-type="promotion"
					class="sideMenu-button parent-extension-button extension-close">
					<span>프로모션 관리</span><i class="fas fa-plus"></i>
				</li>
				<li data-link="/management/promotionList.aba?type=ongoing"
					class="sideMenu-button children-extension-button promotion-extension">
					상시</li>
				<li data-link="/management/promotionList.aba?type=periodical"
					class="sideMenu-button children-extension-button promotion-extension">
					기간제</li>
				<li data-link="/management/hotProduct.aba" class="sideMenu-button">인기 제품 관리</li>
				<li data-link="/management/categoryList.aba" class="sideMenu-button">
					카테고리 관리</li>
				<li data-link="/management/contactList.aba" class="sideMenu-button">
					문의 관리</li>
				<li data-link="/management/configuration.aba"
					class="sideMenu-button">설정</li>
			</ul>
		</div>
	</div>
	<script type="text/javascript" src="/admin/js/sideMenu.js"></script>
</body>
</html>

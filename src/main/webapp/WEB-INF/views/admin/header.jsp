<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/admin/css/header.min.css" />
<title>Header</title>
</head>
<body>
	<div class="header-fixed-box">
		<div class="sideMenu-change-button sideMenu-close-button">
			<i class="fas fa-angle-double-left"></i>
		</div>
		<div class="header-title">
			<div class="user-page-ico-box"
				onclick="javascript:showUserPage('${sessionScope.server.SERVER_USER_SITE}');">
				<i class="fas fa-home"></i>
			</div>
			<div class="header-title-box">
				<span class="header-title-name">Ali-Bee</span>&nbsp;&nbsp;
				<span class="header-title-type">Management</span>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/admin/js/header.js"></script>
</body>
</html>


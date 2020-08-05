<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="/common/image/favicon.png">
<link rel="icon" href="/common/image/favicon.png">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="/common/OverlayScrollbars/css/OverlayScrollbars.min.css" />
<link rel="stylesheet" href="/admin/css/layout.min.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/common/OverlayScrollbars/js/OverlayScrollbars.min.js"></script>
<title>Ali-Bee::Admin</title>
</head>
<body id="admin-layout">
	<header class="header">
		<tiles:insertAttribute name="header" />
	</header>
	<nav class="sideMenu">
		<tiles:insertAttribute name="sideMenu" />
	</nav>
	<div class="content">
		<tiles:insertAttribute name="content" />
	</div>
	<script type="text/javascript" src="/admin/js/layout.js"></script>
</body>
</html>


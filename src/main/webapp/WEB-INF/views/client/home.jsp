<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="/common/slick/slick.css" />
    <link
      rel="stylesheet"
      type="text/css"
      href="/common/slick/slick-theme.css"
    />
    <link rel="stylesheet" href="/client/css/home.min.css" />
    <title>Home</title>
  </head>
  <body>
    <div class="content-box">
      <div class="content-wrap">
        <div class="slider-box">
        	<c:forEach items="${bannerContentList }" var="banner">
	          <div class="slider">
	          	<c:choose>
	          		<c:when test="${sessionScope.locale eq 'ko_KR' }">
			          	<img src="${banner.MAIN_CONTENT_IMAGE_KO }"/>
	          		</c:when>
	          		<c:otherwise>
	          			<img src="${banner.MAIN_CONTENT_IMAGE_EN }"/>
	          		</c:otherwise>
	          	</c:choose> 
	          </div>
        	</c:forEach>
        </div>
        <div class="front-box">
        	<c:forEach items="${frontContentList }" var="front">
          	<c:choose>
          		<c:when test="${sessionScope.locale eq 'ko_KR' }">
		          	<img class="front-title" src="${front.MAIN_CONTENT_IMAGE_KO }"/>
          		</c:when>
          		<c:otherwise>
          			<img class="front-title" src="${front.MAIN_CONTENT_IMAGE_EN }"/>
          		</c:otherwise>
          	</c:choose> 
        	</c:forEach>
        </div>
      </div>
    </div>
    <script type="text/javascript" src="/common/slick/slick.min.js"></script>
    <script type="text/javascript" src="/client/js/home.js"></script>
  </body>
</html>
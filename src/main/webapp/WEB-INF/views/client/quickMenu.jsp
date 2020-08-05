<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/client/css/quickMenu.min.css" />
    <title>Quick Menu</title>
  </head>
  <body>
    <div class="quickMenu-fixed-box">
    	<!-- 
    	<button data-link="#" class="quickMenu-button extension-button">
        <i class="fas fa-puzzle-piece"></i>
        <span class="quickMenu-span">
        	<spring:message code="quickMenu.extension"/>
        </span>
      </button>
       -->
      <button data-link="${sessionScope.clientServerYoutube }" class="quickMenu-button">
        <i class="fab fa-youtube"></i>
        <span class="quickMenu-span">Youtube</span>
      </button>
      <button data-link="/contact.ab" class="quickMenu-button">
        <i class="fas fa-comment"></i>
        <span class="quickMenu-span">
        	<spring:message code="quickMenu.contact"/>
        </span>
      </button>
      <button class="quickMenu-button top-button">
        <i class="fas fa-angle-double-up"></i>
        <span class="quickMenu-span">
        	<spring:message code="quickMenu.top"/>
        </span>
      </button>
    </div>
    <script type="text/javascript" src="/client/js/quickMenu.js"></script>
  </body>
</html>





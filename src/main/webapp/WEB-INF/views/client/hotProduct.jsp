<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/client/css/hotProduct.min.css" />
    <title>Hot Product</title>
  </head>
  <body>
    <div class="content-box">
      <div class="menu-title">
        <spring:message code="hotProduct.hotProduct" />
      </div>
      <div class="content-wrap">
        <div class="select-box-wrap">
          <select id="primary-category-select" class="form-control form-control-sm shadow-none">
            <option value=""><spring:message code="hotProduct.primaryCategory" /></option>
            <c:forEach items="${primaryCategoryList }" var="primaryCategory">
	            <c:choose>
	            	<c:when test="${sessionScope.locale eq 'ko_KR' }">
	            		<option value="${primaryCategory.PRIMARY_CATEGORY_ID }">${primaryCategory.PRIMARY_CATEGORY_NAME_KO }</option>
	            	</c:when>
	            	<c:otherwise>
	            		<option value="${primaryCategory.PRIMARY_CATEGORY_ID }">${primaryCategory.PRIMARY_CATEGORY_NAME_EN }</option>
	            	</c:otherwise>
	            </c:choose>
            </c:forEach>
          </select>
          <select id="secondary-category-select" class="form-control form-control-sm shadow-none">
            <option value=""><spring:message code="hotProduct.secondaryCategory" /></option>
          </select>
        </div>
        <div class="grid-box">
        <!-- 상품  -->
        </div>
      </div>
    </div>
    <script type="text/javascript" src="/client/js/hotProduct.js"></script>
  </body>
</html>

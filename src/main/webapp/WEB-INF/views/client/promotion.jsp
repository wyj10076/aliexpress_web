<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/client/css/promotion.min.css" />
<title>Promotion</title>
</head>
<body>
	<div class="content-box">
		<div class="menu-title">
			<spring:message code="promotion.promotion" />
		</div>
		<div class="content-wrap">
			<div class="grid-box">
				<c:forEach items="${promotionList }" var="promotion">
					<div class="promotion-item-box" data-link="${sessionScope.locale eq 'ko_KR' ? promotion.PROMOTION_LINK_KO : promotion.PROMOTION_LINK_EN}">
						<c:choose>
							<c:when test="${sessionScope.locale eq 'ko_KR' }">
								<img class="tag-img" src="/common/image/tag-ko.png" />
								<img class="promotion-img"
									src="${promotion.PROMOTION_IMAGE_KO }" />
							</c:when>
							<c:otherwise>
								<img class="tag-img" src="/common/image/tag-en.png" />
								<img class="promotion-img"
									src="${promotion.PROMOTION_IMAGE_EN }" />
							</c:otherwise>
						</c:choose>
						<div class="promotion-info-box">
							<div class="promotion-item-title">
								<c:choose>
									<c:when test="${sessionScope.locale eq 'ko_KR' }">
			                ${promotion.PROMOTION_TITLE_KO }
		              	</c:when>
									<c:otherwise>
		              		${promotion.PROMOTION_TITLE_EN }
		              	</c:otherwise>
								</c:choose>
							</div>
							<div class="promotion-item-period" data-date="${promotion.PROMOTION_END_YMD }">
								<c:choose>
									<c:when test="${promotion.PROMOTION_START_YMD eq null }">
										<spring:message code="promotion.ongoing" />
									</c:when>
									<c:otherwise>
										<fmt:parseDate value="${promotion.PROMOTION_START_YMD }"
											var="startYMD" pattern="yyyyMMdd" />
										<fmt:parseDate value="${promotion.PROMOTION_END_YMD}"
											var="endYMD" pattern="yyyyMMdd" />
										<fmt:formatDate value="${startYMD }" pattern="yyyy-MM-dd" />
			              	~ <fmt:formatDate value="${endYMD }"
											pattern="yyyy-MM-dd" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/client/js/promotion.js"></script>
</body>
</html>

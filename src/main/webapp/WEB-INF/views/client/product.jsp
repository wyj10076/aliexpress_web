<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/client/css/product.min.css" />
    <title>Product</title>
  </head>
  <body>
  	<input type="hidden" id="req-status" value="${message }"/>
    <div class="content-box">
    	<c:choose>
    		<c:when test="${message eq 'wrong' }">
    			<div class="exception-box">
    				Entered the wrong URL!!
    			</div>
    		</c:when>
    		<c:when test="${message eq 'error' }">
    			<div class="exception-box">
    				Error occurred!! Please try again later.
    			</div>
    		</c:when>
    		<c:otherwise>
    			<div class="product-wrap">
		        <div class="product-box">
		        	<input type="hidden" class="productId" value="${product.productId }" />
		        	<input type="hidden" class="firstCategoryId" value="${product.firstCategoryId }"/>
		        	<input type="hidden" class="secondCategoryId" value="${product.secondCategoryId }"/>
		          <img
		            class="main-image"
		            src="${product.mainImage }"
		            alt="product-image"
		          />
		          <div class="product-info">
		            <div class="title">
		              <a class="title-link" href="${product.link }"
		                ><span class="title-span"
		                  >${product.title }</span
		                ></a
		              >
		            </div>
		            <div class="rating-order">
		              <div class="rating">
		                <i class="fas fa-star"></i><span class="rating-span">${product.rating }</span>
		              </div>
		              <div class="order">
		                <i class="fas fa-truck"></i>
		                <span class="order-span">
		                	<fmt:formatNumber value="${product.order }" pattern="#,###"/>
		                </span>
		              </div>
		            </div>
		            <div class="store">
		              <a class="store-link" href="${product.shopLink }"
		                ><i class="fas fa-store"></i>
		                <span class="store-span">Store</span></a
		              >
		            </div>
		            <div class="price">${product.price }</div>
		          </div>
		        </div>
		        <div class="product-sub-box">
		          <div class="sub-image-box">
		          	<c:forEach items="${product.subImages }" var="image" varStatus="status">
			            <img
			              class="sub-image ${status.count eq 1 ? 'selected' : '' }"
			              src="${image }"
			              alt="sample"
			            />
		            </c:forEach>
		          </div>
		          <button class="purchase-button" onclick="handlePurchaseClick('${product.link }')">
		          	<spring:message code="similarProducts.purchase"/>
		          </button>
		        </div>
		      </div>	
		      <hr class="division" />
    		</c:otherwise>
	    </c:choose>
      <!-- end product-->
      <div class="similar-product-wrap">
      	<div class="sort-box">
     			<span class="sort-prefix">
     				<spring:message code="similarProducts.sort"/>:
     			</span>
     			<button data-type="price" class="sort-button">
       			<span>
       				<spring:message code="similarProducts.price"/>
       			</span>
       			<i class="fas fa-long-arrow-alt-up"></i>
     			</button>
     			<button data-type="rating" class="sort-button">
       			<span>
       				<spring:message code="similarProducts.rating"/>
       			</span>
       			<i class="fas fa-long-arrow-alt-up"></i>
     			</button>
     			<button data-type="order" class="sort-button">
       			<span>
       				<spring:message code="similarProducts.order"/>
       			</span>
       			<i class="fas fa-long-arrow-alt-up"></i>
     			</button>
   			</div>
      </div>
    </div>
    <script type="text/javascript" src="/client/js/product.js"></script>
  </body>
</html>

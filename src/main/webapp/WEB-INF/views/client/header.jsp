<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/client/css/header.min.css" />
    <title>Header</title>
  </head>
  <body>
    <div class="header-fixed-box">
      <button class="menu-button">
        <i class="fas fa-bars"></i>
        <div class="dropMenu-box">
          <ul>
            <li data-link="/home.ab" class="dropMenu">H O M E</li>
            <li data-link="/hotProduct.ab" class="dropMenu"><spring:message code="headerMenu.hotProduct"/></li>
            <li data-link="/promotion.ab" class="dropMenu"><spring:message code="headerMenu.promotion"/></li>
          </ul>
        </div>
      </button>
      <button class="back-button">
        <i class="fas fa-arrow-left"></i>
      </button>
      <a href="/home.ab">
      	<img class="logo" src="/common/image/logo.png" alt="Logo" />
      </a>
      <form class="search-box">
        <input
          class="search-input"
          type="text"
          placeholder="http://www.aliexpress.com/1234567890.html"
        />
        <button class="search-reset-button" type="reset">
          <i class="fas fa-times-circle"></i>
        </button>
        <button class="search-submit-button" type="submit">
          <i class="fas fa-search"></i>
        </button>
      </form>
      <button class="search-show-button">
        <i class="fas fa-search"></i>
      </button>
    </div>
    <script type="text/javascript" src="/client/js/header.js"></script>
  </body>
</html>


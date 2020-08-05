<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/admin/css/login.min.css" />
    <title>Login</title>
  </head>
  <body>
    <div class="content-box">
      <div class="logo-box">
        <img src="/common/image/logo.png" alt="logo" />
      </div>
      <form class="login-form">
        <div class="id-box info-box">
          <label for="id-input">아이디</label>
          <input id="id-input" class="login-input form-control shadow-none" name="id" type="text" />
        </div>
        <div class="password-box info-box">
          <label for="password-input">비밀번호</label>
          <input id="password-input" class="login-input form-control shadow-none" name="password" type="password" autocomplete="off"/>
        </div>
        <div class="button-box info-box">
	        <button class="login-button" type="submit">로그인</button>
        </div>
      </form>
    </div>
    <script type="text/javascript" src="/admin/js/login.js"></script>
  </body>
</html>

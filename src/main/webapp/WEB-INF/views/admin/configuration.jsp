<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/admin/css/configuration.min.css" />
<title>Configuration</title>
</head>
<body>
	<div class="configuration-wrap">
		<div class="configuration-box">
			<div class="menu-title">계정 설정</div>
			<hr class="menu-division" />
			<div class="config-wrap">
				<form data-type="password" class="password-box config-box">
					<div class="password-label config-label">비밀번호</div>
					<div class="before-password-form before-config-form">
						<button type="button" class="password-button config-change-button">
							변경</button>
					</div>
					<div class="after-password-form after-config-form">
						<input type="hidden" id="manager-id"
							value="${manager.MANAGER_ID }" /> <input type="password"
							id="current-password"
							class="form-control shadow-none config-input"
							placeholder="현재 비밀번호" autocomplete="off" /> <input
							type="password" id="new-password"
							class="form-control shadow-none config-input"
							placeholder="변경할 비밀번호 (30자 이내)" autocomplete="off" /> <input
							type="password" id="new-password-confirm"
							class="form-control shadow-none config-input"
							placeholder="비밀번호 확인" autocomplete="off" />
						<div class="config-button-box">
							<button type="button" class="config-cancel-button">취소</button>
						</div>
					</div>
				</form>
				<hr class="config-box-division last-division" />
				<div class="button-box">
					<button type="button"
						class="manager-submit-button config-submit-button">적용</button>
				</div>
			</div>
			<div class="blank"></div>
			<div class="menu-title">서버 설정</div>
			<hr class="menu-division" />
			<div class="config-wrap">
				<form data-type="server-id" class="server-id-box config-box">
					<div class="server-id-label config-label">서버 ID</div>
					<div class="before-config-id-form before-config-form">
						<select
							class="form-control form-control-sm shadow-none server-id-select"
							id="server-id-select">
						</select>
						<button type="button"
							class="server-id-button config-change-button">
							<i class="fas fa-plus"></i>
						</button>
						<button type="button"
							class="server-id-delete-button config-change-button">
							<i class="fas fa-minus"></i>
						</button>
					</div>
					<div class="after-config-id-form after-config-form">
						<input type="text" id="new-server-id"
							class="form-control shadow-none config-input server-id-input"
							placeholder="새 서버 ID (10자 이내)" />
						<div class="config-button-box">
							<button type="button"
								class="server-id-button config-check-button">중복 검사</button>
							<button type="button"
								class="server-id-button config-cancel-button">취소</button>
						</div>
					</div>
				</form>
				<hr class="config-box-division" />
				<form data-type="app" class="app-box config-box">
					<div class="app-label config-label">App Key</div>
					<div class="before-app-form before-config-form">
						<span class="app-yn-span config-yn-span"></span>
						<button type="button" class="app-button config-change-button">
							변경</button>
					</div>
					<div class="after-app-form after-config-form">
						<input type="text" id="app-url"
							class="form-control shadow-none config-input app-input"
							placeholder="App Url" />
						<input type="text" id="app-key"
							class="form-control shadow-none config-input app-input"
							placeholder="App Key" />
						<input type="text" id="secret-key"
							class="form-control shadow-none config-input app-input"
							placeholder="Secret Key"/>
						<input type="text" id="tracking-id"
							class="form-control shadow-none config-input app-input"
							placeholder="Tracking ID"/>
						<div class="config-button-box">
							<button type="button" class="app-button config-check-button">인증</button>
							<button type="button" class="config-cancel-button">취소</button>
						</div>
					</div>
				</form>
				<hr class="config-box-division" />
				<form data-type="email" class="email-box config-box">
					<div class="email-label config-label">이메일</div>
					<div class="before-email-form before-config-form">
						<span class="email-yn-span config-yn-span"></span>
						<button type="button" class="email-button config-change-button">
							변경</button>
					</div>
					<div class="after-email-form after-config-form">
						<div class="platform-radio-button-box">
							<div class="form-check form-check-inline">
								<input class="form-check-input email-platform" type="radio"
									name="platform" id="naver-button" value="naver" checked /> <label
									class="form-check-label" for="naver-button">naver</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input email-platform" type="radio"
									name="platform" id="gmail-button" value="gmail" /> <label
									class="form-check-label" for="gmail-button">gmail</label>
							</div>
						</div>
						<input type="text" id="email-id"
							class="form-control shadow-none config-input email-input"
							placeholder="아이디" /> <input type="password" id="email-password"
							class="form-control shadow-none config-input email-input"
							placeholder="비밀번호" autocomplete="off" />
						<div class="config-button-box">
							<button type="button" class="email-button config-check-button">인증</button>
							<button type="button" class="config-cancel-button">취소</button>
						</div>
					</div>
				</form>
				<hr class="config-box-division" />
				<form data-type="youtube" class="youtube-box config-box">
					<div class="yotube-label config-label">유튜브</div>
					<div class="before-youtube-form before-config-form">
						<span class="youtube-yn-span config-yn-span"></span>
						<button type="button" class="youtubue-button config-change-button">
							변경</button>
					</div>
					<div class="after-youtube-form after-config-form">
						<input type="text" id="youtube-url"
							class="form-control shadow-none config-input"
							placeholder="유튜브 채널 URL" />
						<div class="config-button-box">
							<button type="button" class="config-cancel-button">취소</button>
						</div>
					</div>
				</form>
				<hr class="config-box-division" />
				<form data-type="user-site" class="user-site-box config-box">
					<div class="user-site-label config-label">유저 사이트</div>
					<div class="before-user-site-form before-config-form">
						<span class="user-site-yn-span config-yn-span"></span>
						<button type="button"
							class="user-site-button config-change-button">
							변경</button>
					</div>
					<div class="after-user-site-form after-config-form">
						<input type="text" id="user-site-url"
							class="form-control shadow-none config-input"
							placeholder="유저 사이트 URL" />
						<div class="config-button-box">
							<button type="button" class="config-cancel-button">취소</button>
						</div>
					</div>
				</form>
				<hr class="config-box-division last-division" />
				<div class="button-box">
					<button type="button"
						class="server-submit-button config-submit-button">적용</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/admin/js/configuration.js"></script>
</body>
</html>
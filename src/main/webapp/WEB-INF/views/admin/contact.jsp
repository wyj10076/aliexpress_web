<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/admin/css/contact.min.css" />
<title>Contact</title>
</head>
<body>
	<div class="contact-wrap">
		<div class="contact-box">
			<div class="contact-user-box">
				<div class="menu-title">문의</div>
				<hr class="menu-division" />
				<form class="contact-form">
					<input type="hidden" id="contact-id" value="${contact.CONTACT_ID }" >
					<div class="contact-email-box contact-input-box">
						<label for="contact-email-input">Email</label> <input type="text"
							readonly id="contact-email-input"
							class="contact-email-input form-control shadow-none"
							value="${contact.CONTACT_EMAIL }" />
					</div>
					<div class="contact-title-box contact-input-box">
						<label for="contact-title-input">Title</label> <input type="text"
							readonly id="contact-title-input"
							class="contact-title-input form-control shadow-none"
							value="${contact.CONTACT_TITLE }" />
					</div>
					<div class="contact-content-box">
						<div class="contact-content-textarea form-control shadow-none">${contact.CONTACT_CONTENT }</div>
					</div>
					<div class="contact-button-box">
						<a href="/management/contactList.aba">
							<button type="button" class="contact-list-button">목록으로</button>
						</a>
						<button type="button"
							class="contact-reply-form-change-button ${reply eq true ? 'success' : '' }"></button>
					</div>
				</form>
			</div>

			<div class="blank"></div>
			
			<div class="contact-reply-box">
				<div class="menu-title">답변</div>
				<hr class="menu-division" />
				<form class="contact-form contact-reply-form">
					<div class="contact-reply-title-box contact-input-box">
						<label for="contact-reply-title-input">Title</label> <input
							type="text" id="contact-reply-title-input" ${reply eq true ? 'readonly' : '' }
							class="contact-reply-title-input form-control shadow-none"
							value="${contact.CONTACT_REPLY_TITLE }" />
					</div>
					<div class="contact-reply-content-box contact-content-box">
						<textarea id="contact-reply-content-textarea"
							class="contact-reply-content-textarea contact-content-textarea form-control shadow-none ${reply eq true ? '' : 'show'}"
							rows="10"></textarea>
						<div class="contact-reply-content-textarea contact-content-textarea form-control shadow-none ${reply eq true ? 'show' : ''}">
							${contact.CONTACT_REPLY_CONTENT }
						</div>
					</div>
					<div class="contact-reply-button-box contact-button-box">
						<button type="reset" class="contact-reply-reset-button">
							지우기</button>
						<button type="submit" class="contact-reply-submit-button">
							전송하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="https://cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
	<script type="text/javascript" src="/admin/js/contact.js"></script>
</body>
</html>

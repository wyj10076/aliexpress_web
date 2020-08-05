<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/client/css/contact.min.css" />
    <title>Contact</title>
  </head>
  <body>
    <div class="content-box">
      <div class="menu-title">
        <spring:message code="contact.contact" />
      </div>
      <div class="content-wrap">
        <form class="contact-form">
          <div class="contact-email-box contact-input-box">
            <label for="contact-email-input"><spring:message code="contact.email" /></label>
            <input
              type="text"
              id="contact-email-input"
              class="contact-email-input form-control form-control-sm shadow-none"
              name="contact-email"
              placeholder="<spring:message code="contact.email.placeholder" />"
            />
          </div>
          <div class="contact-title-box contact-input-box">
            <label for="contact-title-input"><spring:message code="contact.title" /></label>
            <input
              type="text"
              id="contact-title-input"
              class="contact-title-input form-control form-control-sm shadow-none"
              name="contact-title"
            />
          </div>
          <div class="contact-content-box">
            <textarea
              id="contact-content-textarea"
              class="contact-content-textarea"
            ></textarea>
          </div>
          <div class="contact-submit-box">
            <button class="submit-button" type="submit"><spring:message code="contact.submit" /></button>
          </div>
        </form>
      </div>
    </div>
    <script src="https://cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
    <script type="text/javascript" src="/client/js/contact.js"></script>
  </body>
</html>


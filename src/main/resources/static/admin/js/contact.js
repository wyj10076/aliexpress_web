$(document).ready(function () {
	if ($("#contact-reply-content-textarea").hasClass("show")) {
	  CKEDITOR.replace("contact-reply-content-textarea", {
			contentsCss: "/common/ckeditor/contents.css",
			resize_enabled: false,
	    toolbarGroups: [
	      { name: "styles" },
	      { name: "basicstyles", groups: ["basicstyles", "cleanup"] },
	      { name: "document", groups: ["document", "doctools"] },
	      { name: "clipboard", groups: ["clipboard", "undo"] },
	      { name: "editing", groups: ["find", "selection", "spellchecker"] },
	      { name: "forms" },
	    ],
	    height: 200,
	  });

	} else {
		$(".contact-reply-button-box").css("display", "none");
	}
	
}); // document ready end

// 답변보기 버튼 클릭 시
$(".contact-reply-form-change-button").click(function () {
  const $this = $(this);
  const $contactReplyBox = $(".contact-reply-box");
  const height = $contactReplyBox.height();

  if (!$this.hasClass("hide")) {
    $this.addClass("hide");

    $contactReplyBox
      .css({ display: "block", height: 0 })
      .animate({ height: height }, 300, function () {
        $("html, body").animate(
          {
            scrollTop: $contactReplyBox.offset().top,
          },
          300
        );
      });
  } else {
    $this.removeClass("hide");

    $contactReplyBox.animate({ height: 0 }, function () {
      $(this).css({ display: "none", height: height });
    });
  }
});

$(".contact-reply-form").submit(function (e) {
  e.preventDefault();
  const $contactForm = $(this);
  const $contactReplyTitle = $contactForm.find("#contact-reply-title-input");
  const $contactReplyContent = CKEDITOR.instances["contact-reply-content-textarea"];

	const contactId = $("#contact-id").val();
	const contactEmail = $("#contact-email-input").val();
  const contactReplyTitle = $contactReplyTitle.val().trim();
  const contactReplyContent = $contactReplyContent.getData().trim();

  if (contactReplyTitle === "") {
    alert("제목을 입력해 주세요.");
    $contactReplyTitle.focus();
    return;
  }

  if (contactReplyContent === "") {
    alert("내용을 입력해 주세요.");
    $contactReplyContent.focus();
    return;
  }

  const sendData = { contactId: contactId, contactEmail: contactEmail, contactReplyTitle: contactReplyTitle, contactReplyContent: contactReplyContent };
	
  $.ajax({
    url: "/management/replyContact",
    type: "post",
    dataType: "json",
		contentType: "application/json",
    data: JSON.stringify(sendData),
    success: function (data) {
      const result = data.result;

      if (result === "success") {
				alert("메일을 발송하였습니다.");
        location.href = "/management/contact.aba?query=" + contactId + "&reply=true";

      } else {
        alert("메일 발송 실패, 잠시 후 다시 시도해 주세요.");
      }
    },
  });
});

$(".contact-reply-reset-button").click(function () {
  const $content = CKEDITOR.instances["contact-reply-content-textarea"];
  $content.setData("");
});

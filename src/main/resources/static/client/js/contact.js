$(document).ready(function () {
  CKEDITOR.replace("contact-content-textarea", {
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
}); // document ready end

$(".contact-form").submit(function (e) {
  e.preventDefault();
  const $contactForm = $(this);
  const $contactEmail = $contactForm.find("#contact-email-input");
  const $contactTitle = $contactForm.find("#contact-title-input");
  const $contactContent = CKEDITOR.instances["contact-content-textarea"];

  const contactEmail = $contactEmail.val().trim();
  const contactTitle = $contactTitle.val().trim();
  const contactContent = $contactContent.getData().trim();

  if (contactEmail === "") {
    alert("Please enter your e-mail");
    $contactEmail.focus();
    return;
  } else {
    const emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    if (contactEmail.match(emailRegExp) == null) {
      alert("Not in email format.");
      $contactEmail.focus();
      return;
    }
  }

  if (contactTitle === "") {
    alert("Please enter the title");
    $contactTitle.focus();
    return;
  }

  if (contactContent === "") {
    alert("Please enter the content");
    $contactContent.focus();
    return;
  }

  const sendData = { contactEmail: contactEmail, contactTitle: contactTitle, contactContent: contactContent };

  $.ajax({
    url: "/insertContact",
    type: "post",
    dataType: "json",
		contentType: "application/json",
    data: JSON.stringify(sendData),
    success: function (data) {
      const result = data.result;

      if (result === "success") {
				alert("Success Submit");
        location.reload();

      } else {
        alert("Error occurred! Please try again later");
      }
    },
  });
});

// 서버 목록 받아놓기 위한 변수
let serverList;

// 현재 선택된 서버
let currentServer;

$(document).ready(function () {
  selectServerList();
});

// 서버 ID 변경 시
$(".server-id-select").change(function () {
  const $serverIdSelect = $(this);
  const serverId = $serverIdSelect.children("option:selected").val();
  $serverIdSelect.parent().parent().addClass("update");
  selectServer(serverId);
});

// input 입력 시
$(".config-input").on("input", function () {
  $(this).parent().find(".config-check-button").removeClass("success");
});

// email platform 변경 시
$(".email-platform").change(function () {
  $(this)
    .parent()
    .parent()
    .parent()
    .find(".config-check-button")
    .removeClass("success");
});

// 변경 버튼, + 버튼 클릭 시
$(".config-change-button").click(function () {
  const $configChangeButton = $(this);

  if ($configChangeButton.hasClass("server-id-delete-button")) {
    return;
  }

  const $beforeConfigForm = $configChangeButton.parent();
  formToggle("before", $beforeConfigForm);

  if ($configChangeButton.hasClass("server-id-button")) {
		const $appYNSpan = $(".app-yn-span");
    const $emailYNSpan = $(".email-yn-span");
    const $youtubeYNSpan = $(".youtube-yn-span");
    const $userSiteYNSpan = $(".user-site-yn-span");

    $appYNSpan.html("");
    $appYNSpan.addClass("warning");
    $emailYNSpan.html("");
    $emailYNSpan.addClass("warning");
    $youtubeYNSpan.html("");
    $youtubeYNSpan.addClass("warning");
    $userSiteYNSpan.html("");
    $userSiteYNSpan.addClass("warning");
  }
});

// 서버 삭제 버튼 클릭 시
$(".server-id-delete-button").click(function () {
  const sendData = {
    serverId: currentServer.SERVER_ID,
    serverUseYN: currentServer.SERVER_USE_YN,
  };

  if (confirm(currentServer.SERVER_ID + " 설정을 삭제하시겠습니까?")) {
    $.ajax({
      url: "/management/deleteServer",
      type: "post",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify(sendData),
      success: function (data) {
        const result = data.result;

        if (result === "success") {
          selectServerList();
        } else {
          alert("삭제 실패, 잠시 후 다시 시도해 주세요.");
        }
      },
    });
  }
});

// 중복 검사, 인증 버튼 클릭 시
$(".config-check-button").click(function () {
  const $configCheckButton = $(this);
  const $afterConfigForm = $configCheckButton.parent().parent();
  let hasBlank = false;
  let isValid = true;

  $afterConfigForm.children(".config-input").each(function () {
    const $configInput = $(this);
    const inputValue = $configInput.val().trim();
    const maxLength = $configInput.hasClass("server-id-input") ? 10 : 50;

    if (inputValue === "") {
      hasBlank = true;
      alert("공백이 존재합니다.");
      $configInput.focus();
      return false;
    } else if (inputValue.length > maxLength) {
      isValid = false;
      alert(maxLength + "자를 초과하였습니다.");
      $configInput.focus();
      return false;
    }
  });

  if (hasBlank || !isValid) {
    return;
  }

	let type;
  let url;
  const sendData = new Object();
  let successMessgae;
  let failMessage;

  if ($configCheckButton.hasClass("server-id-button")) {
		type = "serverId";
    url = "/management/checkServerId";
    sendData.serverId = $("#new-server-id").val().trim();
    successMessage = "사용 가능한 ID입니다.";
    failMessage = "이미 존재하는 ID입니다.";
  } else if ($configCheckButton.hasClass("email-button")) {
		type= "email";
    url = "/management/checkServerEmail";
    sendData.emailPlatform = $(".email-platform:checked").val();
    sendData.emailId = $("#email-id").val().trim();
    sendData.emailPassword = $("#email-password").val().trim();
    successMessage = "이메일이 인증되었습니다.";
    failMessage = "이메일 인증 실패, 계정을 다시 확인해 주세요.";
  } else if ($configCheckButton.hasClass("app-button")) {
		type = "app";
		url = "/management/checkServerApp";
		sendData.appUrl = $("#app-url").val().trim();
		sendData.appKey = $("#app-key").val().trim();
		sendData.secretKey = $("#secret-key").val().trim(); 
		sendData.trackingId = $("#tracking-id").val().trim();
		successMessage = "App이 인증되었습니다.";
		failMessage = "App 인증 실패, 정보를 다시 확인해 주세요.";
	}

  $.ajax({
    url: url,
    type: "post",
    dataType: "json",
    contentType: "application/json",
    data: JSON.stringify(sendData),
    success: function (data) {
      const result = data.result;

			if (type === "serverId") {
	      if (result !== "success") {
	        $configCheckButton.addClass("success");
	        alert(successMessage);
	      } else {
	        alert(failMessage);
	      }
							
			} else {
	      if (result === "success") {
	        $configCheckButton.addClass("success");
	        alert(successMessage);
	      } else {
	        alert(failMessage);
	      }
			}
    },
  });
});

// 취소 버튼 클릭 시
$(".config-cancel-button").click(function () {
  const $configCancelButton = $(this);
  const $afterConfigForm = $configCancelButton.parent().parent();
  formReset($afterConfigForm);
  formToggle("after", $afterConfigForm);

  if ($configCancelButton.hasClass("server-id-button")) {
    const serverId = $(".server-id-select").children("option:selected").val();
    selectServer(serverId);
  }
});

// 적용 버튼 클릭 시
$(".config-submit-button").click(function () {
  const $configSubmitButton = $(this);

  const $configBoxList = $(this).parent().prevAll(".config-box");
  let hasChanged = false;
  let validation = new Object();

  $($configBoxList.get().reverse()).each(function () {
    const $configBox = $(this);

    if ($configBox.hasClass("change")) {
      hasChanged = true;
      const type = $configBox.data("type");
      const $form = $configBox.find(".after-config-form");
      validation = validate(type, $form);

      if (!validation.result) {
        return false;
      }
    } else if ($configBox.hasClass("update")) {
      hasChanged = true;
      validation.result = true;
    }
  });

  if (!hasChanged) {
    alert("변경 사항이 없습니다.");
    return;
  }

  // 유효성 검사 통과 했을 시
  if (validation.result) {
    let url;
    const sendData = new Object();
    let successMessgae;
    let failMessage;

    if ($configSubmitButton.hasClass("manager-submit-button")) {
      url = "/management/updateManager";
      sendData.id = $configBoxList.find("#manager-id").val();
      sendData.password = validation.passwordList[1];
      successMessage = "비밀번호를 변경하였습니다.";
      failMessage = "비밀번호 변경에 실패하였습니다.";
    } else if ($configSubmitButton.hasClass("server-submit-button")) {
      url = "/management/updateServer";
      successMessage = "서버 설정을 변경하였습니다.";
      failMessage = "서버 설정 변경에 실패하였습니다.";
      sendData.serverId = currentServer.SERVER_ID;

      $configBoxList.each(function () {
        const $configBox = $(this);

        if ($configBox.hasClass("change")) {
          const type = $configBox.data("type");
          const $form = $configBox.find(".after-config-form");

          switch (type) {
            case "server-id":
              (url = "/management/insertServer"),
                (sendData.serverId = $form.find("#new-server-id").val().trim());
              break;

						case "app":
							sendData.serverAppUrl = $("#app-url").val().trim();
							sendData.serverAppKey = $("#app-key").val().trim();
							sendData.serverSecretKey = $("#secret-key").val().trim(); 
							sendData.serverTrackingId = $("#tracking-id").val().trim();
							break;

            case "email":
              sendData.serverEmailPlatform = $(".email-platform:checked").val();
              sendData.serverEmailId = $("#email-id").val().trim();
              sendData.serverEmailPassword = $("#email-password").val().trim();
              break;

            case "youtube":
              sendData.serverYoutube = $("#youtube-url").val().trim();
              break;

            case "user-site":
              sendData.serverUserSite = $("#user-site-url").val().trim();
          }
        }
      });
    }

    $.ajax({
      url: url,
      type: "post",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify(sendData),
      success: function (data) {
        const result = data.result;

        if (result === "success") {
          $configBoxList.each(function () {
            const $configBox = $(this);

            if ($configBox.hasClass("change")) {
              const $form = $configBox.find(".after-config-form");
              formReset($form);
              formToggle("after", $form);
            } else if ($configBox.hasClass("update")) {
              $configBox.removeClass("update");
            }
          });

          alert(successMessage);

          if ($configSubmitButton.hasClass("server-submit-button")) {
            selectServerList();
          }
        } else {
          alert(failMessage);
        }
      },
    });
  }
});

function validate(type, $form) {
  const validation = new Object();
	let $configInput;
	let inputValue;

  switch (type) {
    case "password":
      let hasBlank = false;
      let isCorrectLength = true;
      const passwordList = new Array();

      $form.find(".config-input").each(function () {
        const $configInput = $(this);
        const password = $configInput.val().trim();
        passwordList.push(password);

        if (password === "") {
          hasBlank = true;
          alert("공백이 존재합니다.");
          $configInput.focus();
          return false;
        }

        if (password.length > 30) {
          isCorrectLength = false;
          alert("30자를 초과하였습니다.");
          $configInput.focus();
          return false;
        }
      });

      if (hasBlank || !isCorrectLength) {
        validation.result = false;
      } else {
        if (passwordList[1] !== passwordList[2]) {
          validation.result = false;
          alert("비밀번호 확인을 다시 입력해 주세요.");
          $form.find("#new-password-confirm").focus();
        } else {
          const managerId = $form.find("#manager-id").val();
          const sendData = { id: managerId, password: passwordList[0] };

          $.ajax({
            url: "/management/checkPassword",
            type: "post",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(sendData),
            async: false,
            success: function (data) {
              const result = data.result;

              if (result === "success") {
                validation.result = true;
                validation.passwordList = passwordList;
              } else {
                validation.result = false;
                alert("현재 비밀번호를 다시 확인해 주세요.");
                $form.find("#current-password").focus();
              }
            },
          });
        }
      }
      return validation;

		case "app":
			validation.result = $form
        .find(".config-check-button")
        .hasClass("success");
      if (!validation.result) {
        alert("APP 인증을 해 주세요.");
      }
      return validation;

    case "server-id":
      validation.result = $form
        .find(".config-check-button")
        .hasClass("success");
      if (!validation.result) {
        alert("서버 ID 중복 검사를 해 주세요.");
      }
      return validation;

    case "email":
      validation.result = $form
        .find(".config-check-button")
        .hasClass("success");
      if (!validation.result) {
        alert("이메일 인증을 해 주세요.");
      }
      return validation;

    case "youtube":
      $configInput = $form.find(".config-input");
      inputValue = $configInput.val().trim();

      if (inputValue === "") {
        validation.result = false;
        alert("공백이 존재합니다.");
        $configInput.focus();
        return validation;
      }

      if (inputValue.length > 100) {
        validation.result = false;
        alert("100자를 초과하였습니다.");
        $configInput.focus();
        return validation;
      }

      validation.result = true;
      return validation;

    case "user-site":
      $configInput = $form.find(".config-input");
      inputValue = $configInput.val().trim();

      if (inputValue === "") {
        validation.result = false;
        alert("공백이 존재합니다.");
        $configInput.focus();
        return validation;
      }

      if (inputValue.length > 100) {
        validation.result = false;
        alert("100자를 초과하였습니다.");
        $configInput.focus();
        return validation;
      }

      validation.result = true;
      return validation;
  }
}

function formToggle(type, $form) {
  if (type === "before") {
    $form.parent().addClass("change");
    $form.css("display", "none");
    $form.nextAll(".after-config-form").css("display", "block");
  } else {
    $form.parent().removeClass("change");
    $form.css("display", "none");
    $form.prevAll(".before-config-form").css("display", "flex");
  }
}

function formReset($form) {
  $form.find(".email-platform:eq(0)").prop("checked", true);
  $form.find(".config-input").val("");
  $form.find(".config-check-button").removeClass("success");
}

function selectServerList() {
  $.ajax({
    url: "/management/selectServerList",
    type: "post",
    dataType: "json",
    success: function (data) {
      serverList = data.serverList;

      let optionList = "";
      let serverId;

      for (let server of serverList) {
        const selected = server.SERVER_USE_YN === "Y" ? "selected" : "";

        if (selected === "selected") {
          serverId = server.SERVER_ID;
        }

        optionList += `
						<option value="${server.SERVER_ID}" ${selected}>${server.SERVER_ID}</option>
					`;
      }

      $(".server-id-select").html(optionList);
      selectServer(serverId);
    },
  });
}

function selectServer(serverId) {
  for (let server of serverList) {
    if (server.SERVER_ID === serverId) {
      currentServer = server;

			const serverAppKey = server.SERVER_APP_KEY;
      const serverEmailId = server.SERVER_EMAIL_ID;
      const serverEmailPlatform = server.SERVER_EMAIL_PLATFORM;
      const serverYoutube = server.SERVER_YOUTUBE;
      const serverUserSite = server.SERVER_USER_SITE;
      const serverDeletableYN = server.SERVER_DELETABLE_YN;

			const $appYNSpan = $(".app-yn-span");
      const $emailYNSpan = $(".email-yn-span");
      const $youtubeYNSpan = $(".youtube-yn-span");
      const $userSiteYNSpan = $(".user-site-yn-span");

      if (serverDeletableYN === "Y") {
        $(".server-id-delete-button").css({ display: "block" });
      } else {
        $(".server-id-delete-button").css({ display: "none" });
      }

			if (!serverAppKey) {
        $appYNSpan.html("");
        $appYNSpan.addClass("warning");
      } else {
        $appYNSpan.removeClass("warning");
        $appYNSpan.html(serverAppKey);
      }

      if (!serverEmailId) {
        $emailYNSpan.html("");
        $emailYNSpan.addClass("warning");
      } else {
        $emailYNSpan.removeClass("warning");
        $emailYNSpan.html(serverEmailId + "@" + serverEmailPlatform + ".com");
      }

      if (!serverYoutube) {
        $youtubeYNSpan.html("");
        $youtubeYNSpan.addClass("warning");
      } else {
        $youtubeYNSpan.removeClass("warning");
        $youtubeYNSpan.html(serverYoutube);
      }

      if (!serverUserSite) {
        $userSiteYNSpan.html("");
        $userSiteYNSpan.addClass("warning");
      } else {
        $userSiteYNSpan.removeClass("warning");
        $userSiteYNSpan.html(serverUserSite);
      }

      break;
    }
  }
}

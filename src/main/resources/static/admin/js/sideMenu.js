$(document).ready(function () {
  $(".sideMenu-fixed-box").overlayScrollbars({
    className: "os-theme-dark",
    resize: "none",
    sizeAutoCapable: true,
    paddingAbsolute: true,
    scrollbars: {
      clickScrolling: true,
    },
  });
});

$(".logout-button").click(function () {
  $.ajax({
    url: "/management/logout",
    type: "post",
    success: function (data) {
      const result = data.result;

      if (result === "success") {
        location.href = "/management/login.aba";
      } else {
        alert("로그아웃 실패");
      }
    },
  });
});

$(".sideMenu-button").click(function () {
  const $sideMenuButton = $(this);
  $(".sideMenu-button.selected").removeClass("selected");
  $sideMenuButton.addClass("selected");

  if ($sideMenuButton.hasClass("parent-extension-button")) {
    if ($sideMenuButton.hasClass("extension-close")) {
      $sideMenuButton.removeClass("extension-close");
      $sideMenuButton.addClass("extension-open");
      $sideMenuButton.children("i").removeClass("fa-plus");
      $sideMenuButton.children("i").addClass("fa-minus");

      const type = $sideMenuButton.data("type");
      const height = $sideMenuButton.height();
      $sideMenuButton
        .nextAll("." + type + "-extension")
        .css({ display: "flex", height: 0, borderWidth: 3 })
        .animate({ height: height }, 300);
    } else {
      $sideMenuButton.removeClass("extension-open");
      $sideMenuButton.addClass("extension-close");
      $sideMenuButton.children("i").removeClass("fa-minus");
      $sideMenuButton.children("i").addClass("fa-plus");

      const type = $sideMenuButton.data("type");

      $sideMenuButton
        .nextAll("." + type + "-extension")
        .animate({ height: 0, borderWidth: 0 }, 300, function () {
          $(this).css("display", "none");
        });
    }
  } else {
    location.href = $(this).data("link");
  }
});

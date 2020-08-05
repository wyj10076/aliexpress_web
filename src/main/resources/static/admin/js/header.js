$(document).ready(function () {
  $(".sideMenu-change-button").click(function () {
    const $sideMenuButton = $(this);

    if ($sideMenuButton.hasClass("sideMenu-close-button")) {
      $sideMenuButton.removeClass("sideMenu-close-button");
      $sideMenuButton.addClass("sideMenu-open-button");
      $sideMenuButton.children("i").removeClass("fa-angle-double-left");
      $sideMenuButton.children("i").addClass("fa-angle-double-right");
      $(".header, .content").animate(
        {
          "padding-left": 0,
        },
        500
      );
    } else {
      $sideMenuButton.removeClass("sideMenu-open-button");
      $sideMenuButton.addClass("sideMenu-close-button");
      $sideMenuButton.children("i").removeClass("fa-angle-double-right");
      $sideMenuButton.children("i").addClass("fa-angle-double-left");
      $(".header, .content").animate(
        {
          "padding-left": 180,
        },
        500
      );
    }
    $(".sideMenu").animate(
      {
        width: "toggle",
      },
      500
    );
  });
});

function showUserPage(url) {
  window.open(url, "_blank");
}

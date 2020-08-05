const agent = navigator.userAgent.toLowerCase();

if (agent.indexOf("chrome") != -1) {
  $(".extension-button").css("display", "flex");
}

$(window).scroll(function () {
  if ($(this).scrollTop() > 100) {
    $(".top-button").css("visibility", "visible");
  } else {
    $(".top-button").css("visibility", "hidden");
  }
});

$(".quickMenu-button").click(function () {
  if ($(this).hasClass("top-button")) {
    $("html, body").animate(
      {
        scrollTop: 0,
      },
      300
    );
  } else if ($(this).hasClass("extension-button")) {
    alert("Not yet available");
  } else {
    const url = $(this).data("link");
    window.open(url, "_blank");
  }
});

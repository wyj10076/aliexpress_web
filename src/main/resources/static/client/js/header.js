$(".menu-button").click(function () {
  $(".dropMenu-box").toggle();
});

$(".dropMenu").click(function (e) {
  e.preventDefault();
  location.href = $(this).data("link");
});

$(".search-show-button ").click(function () {
  $(this).hide();
  $(".menu-button").hide();
  $(".logo").hide();
  $(".back-button").show();
  $(".search-box").css("display", "flex");
  $(".dropMenu").hide();
});

$(".back-button").click(function () {
  $(this).hide();
  $(".search-box").hide();
  $(".menu-button").show();
  $(".logo").show();
  $(".search-show-button").show();
});

$(".search-box").submit(function (e) {
  e.preventDefault();
  let inputValue = $(".search-input").val();
  const regEx = /\/[0-9]+.html/;
  const productIdRegEx = /[0-9]+/;

  inputValue = regEx.exec(inputValue);

  if (inputValue === null) {
    alert("Entered the wrong URL!!");
    return;
  }
  const productId = productIdRegEx.exec(inputValue);
  location.href = "/product.ab?query=" + productId;
});

$(window).resize(function () {
  const width = window.innerWidth;
  const $searchBox = $(".search-box");
  const $searchShowButton = $(".search-show-button");
  const status = $searchBox.css("display");

  if (width > 768 && status === "none") {
    $searchBox.css("display", "flex");
    $searchShowButton.hide();
  } else if (width <= 768 && status === "flex") {
    $searchBox.hide();
    $searchShowButton.show();
  }
});

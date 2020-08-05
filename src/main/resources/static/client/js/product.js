// 얻어온 제품
let productList;

$("document").ready(function () {
  const message = $("#req-status").val();

  if (message === "success") {
		const $price = $(".price");
		$price.html(currencyFormatting($price.text()));
	
    const productId = $(".productId").val();
    const firstCategoryId = $(".firstCategoryId").val();
    const secondCategoryId = $(".secondCategoryId").val();
    const title = $(".title-span").text().trim();

    const sendData = {
      locale: locale,
      productId: productId,
      title: title,
      firstCategoryId: firstCategoryId,
      secondCategoryId: secondCategoryId,
    };

    const $similarProductWrap = $(".similar-product-wrap");
    $similarProductWrap.append(
      `
			<div class="loader"></div>
			`
    );

    $.ajax({
      url: "/product/selectSimilarProductList",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(sendData),
      success: function (data) {
        productList = data.productList;

        let html = "";
        if (productList !== null) {
					$similarProductWrap.children(".loader").remove();
					$(".sort-box").css("display", "flex");
          appendSimilarProductList(productList, "down");
          changeButtonColor(productList);

        // productList.length === 0
        } else {
          $similarProductWrap.html(
            `
						<div class="no-search-product">
		          <span>No search product</span>
		        </div>
						`
          );
        }
      }, // success end
    }); // ajax end
  }
});

// sort button click
$(document).on("click", ".sort-button", function () {
  const $button = $(this);
  const $arrow = $button.children("i");
  const type = $button.data("type");

  if ($button.hasClass("selected")) {
    if ($arrow.hasClass("fa-long-arrow-alt-up")) {
      $arrow.prop("class", "fas fa-long-arrow-alt-down");
    } else {
      $arrow.prop("class", "fas fa-long-arrow-alt-up");
    }
  } else {
    $(".sort-button.selected").removeClass("selected");
    $button.addClass("selected");
    quickSort(productList, type, 0, productList.length - 1);
  }

  const direction = $arrow.hasClass("fa-long-arrow-alt-up") ? "up" : "down";

  appendSimilarProductList(productList, direction);
  changeButtonColor(productList, direction);
});


// sub image click
$(".sub-image").click(function () {
  const $subImage = $(this);

  $(".main-image").prop("src", $subImage.prop("src"));

  $(".sub-image.selected").removeClass("selected");
  $subImage.addClass("selected");
});

// append HTML after ajax
function appendSimilarProductList(productList, direction) {
  const $similarProductWrap = $(".similar-product-wrap");
	const purchaseButtonText = $(".purchase-button").text();
	
  const copyProductList = productList.slice();
  if (direction === "up") copyProductList.reverse();

  $(".similar-product-box").remove();

  for (let similarProduct of copyProductList) {
    const order = numberWithCommas(similarProduct.order);
    const price = currencyFormatting(similarProduct.price);
    $similarProductWrap.append(
      `
			<div class="similar-product-box">
    		<a class="similar-image-link" href="${similarProduct.link}">
          <img
            class="similar-product-image"
            src="${similarProduct.mainImage}"
            alt="sample"/>
    		</a>
    		<div class="similar-product-info">
      		<div class="similar-product-title">
        		<a class="similar-title-link" href="${similarProduct.link}">
            	<span class="similar-title-span">${similarProduct.title}</span>
            </a>
      		</div>
      		<div class="similar-product-rating-order">
        		<div class="similar-product-rating">
          		<i class="fas fa-star"></i>
          		<span class="similar-product-rating-span">${similarProduct.rating}</span>
        		</div>
        		<div class="similar-product-order">
          		<i class="fas fa-truck"></i>
          		<span class="similar-product-order-span">${order}</span>
        		</div>
      		</div>
      		<div class="similar-product-store">
        		<a class="similar-product-store-link" href="${similarProduct.shopLink}">
          		<i class="fas fa-store"></i>
          		<span class="similar-product-store-span">Store</span>
          	</a>
        	</div>
	    	</div>
  	  	<div class="similar-product-sub-info">
    	  	<div class="similar-product-price">${price}</div>
					<div class="similar-product-button-box">
	      		<button class="similar-purchase-button" onclick="handlePurchaseClick('${similarProduct.link}')">
							${purchaseButtonText}
						</button>
					</div>
    		</div>
  		</div>
			`
    );
  } // for end
}

// purchase button click
function handlePurchaseClick(link) {
  location.href = link;
}

// comma
function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// change number to currency
function currencyFormatting(price) {
  let currencyStyle = { style: "currency" };

  switch (locale) {
    case "ko_KR":
      currencyStyle.currency = "KRW";
      return new Intl.NumberFormat("ko-KR", currencyStyle).format(price);

    default:
      currencyStyle.currency = "USD";
      return new Intl.NumberFormat("en-US", currencyStyle).format(price);
  }
}

// max, min price
function changeButtonColor(productList, direction) {
  const copyProductList = productList.slice();
  if (direction === "up") copyProductList.reverse();

  let maxIndex = 0;
  let minIndex = 0;
  let maxPrice = copyProductList[0].price;
  let minPrice = copyProductList[0].price;

  if (copyProductList.length === 1) {
    return;
  }

  for (let i = 1; i < copyProductList.length; i++) {
    let price = parseInt(copyProductList[i].price);
    if (price > maxPrice) {
      maxIndex = i;
      maxPrice = price;
    }
    if (price < minPrice) {
      minIndex = i;
      minPrice = price;
    }
  }

  const onlyNumRegEx = /[^\.0-9]/g;
  const productPrice = parseInt($(".price").text().replace(onlyNumRegEx, ""));
  const $productPurchaseButton = $(".purchase-button");

  if (maxPrice > productPrice) {
    $(".similar-purchase-button:eq(" + maxIndex + ")").addClass("max-price");
  } else {
    $productPurchaseButton.addClass("max-price");
  }

  if (minPrice < productPrice) {
    $(".similar-purchase-button:eq(" + minIndex + ")").addClass("min-price");
  } else {
    $productPurchaseButton.addClass("min-price");
  }
}

function quickSort(productList, type, start, end) {
  if (start >= end) {
    return;
  }

  let key = start;
  let i = start + 1;
  let j = end;
  let temp;

  while (i <= j) {
    while (i <= end && productList[i][type] <= productList[key][type]) {
      i++;
    }
    while (j > start && productList[j][type] >= productList[key][type]) {
      j--;
    }
    if (i > j) {
      temp = productList[j];
      productList[j] = productList[key];
      productList[key] = temp;
    } else {
      temp = productList[i];
      productList[i] = productList[j];
      productList[j] = temp;
    }
  }

  quickSort(productList, type, start, j - 1);
  quickSort(productList, type, j + 1, end);
}

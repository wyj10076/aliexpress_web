$("#primary-category-select").change(function() {
	const primaryCategoryId = $(this).val();
	
	if (primaryCategoryId !== "") {
		
		const sendData = {primaryCategoryId: primaryCategoryId};
		
		$.ajax({
			url: "/selectSecondaryCategoryList",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(sendData),
			dataType: "json",
			success: function(data) {
				const secondaryCategoryList = data.secondaryCategoryList;
				resetSecondaryCategorySelect();
				
				for (let secondaryCategory of secondaryCategoryList) {
					let secondaryCategoryName;
					
					if (locale === "ko_KR") {
						secondaryCategoryName = secondaryCategory.SECONDARY_CATEGORY_NAME_KO; 
					} else {
						secondaryCategoryName = secondaryCategory.SECONDARY_CATEGORY_NAME_EN;
					} 
					
					$("#secondary-category-select").append(
						`
						<option value="${secondaryCategory.SECONDARY_CATEGORY_ID}">
							${secondaryCategoryName}
						</option>
						`
					); 
				}
			}
		})
	}
});

$("#secondary-category-select").change(function() {
	const secondaryCategoryId = $(this).val();
	
	if (secondaryCategoryId !== "") {
		
		const sendData = {secondaryCategoryId: secondaryCategoryId};
		
		$.ajax({
			url: "/selectHotProductList",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(sendData),
			dataType: "json",
			success: function(data) {
				const hotProductList = data.hotProductList;
				resetGridBox();
				
				if (hotProductList.length !== 0) {
					
					const purchase = locale === "ko_KR" ? "구매하기" : "Purchase";
					
					for (let hotProduct of hotProductList) {
						const salePrice = hotProduct.HOT_PRODUCT_SALE_PRICE;
						const isSale = salePrice !== undefined ? true : false;
						
						$(".grid-box").append(
							`
							<div class="product-item-box">
		            <img
		              src="${hotProduct.HOT_PRODUCT_IMAGE}"
		            />
		            <div class="product-info-box">
		              <div class="price-box">
		                <div class="sale-price ${isSale ? '' : 'none'}">
		                  US ${salePrice}
		                </div>
		                <div class="origin-price ${isSale ? 'through-line' : ''}">
		                  US ${hotProduct.HOT_PRODUCT_ORIGIN_PRICE}
		                </div>
		              </div>
		              <button data-link="${hotProduct.HOT_PRODUCT_LINK}" class="purchase-button" type="button">
										${purchase}
									</button>
		            </div>
	          	</div>
							`
						);
					}
				}
			}
		});
	}
});

$(document).on("click", ".purchase-button", function() {
	const hotProductLink = $(this).data("link");
	const sendData = {hotProductLink: hotProductLink};
	$.ajax({
		url: "/generateLink",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(sendData),
		dataType: "json",
		success: function(data) {
			const result = data.result;
			const url = data.promotionLink;
			
			if (result === "success") {
				window.open(url, "_blank");
			} else {
				window.open(hotProductLink, "_blank");
			}
		}
	})
});

function resetSecondaryCategorySelect() {
	$("#secondary-category-select").children(":not(:eq(0))").remove();
}

function resetGridBox() {
	$(".grid-box").children(".product-item-box").remove();
}
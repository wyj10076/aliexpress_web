$(document).ready(function() {
	$("#promotion-date-picker").datepicker({
    format: "yyyy-mm-dd",
    maxViewMode: 2,
    language: "ko",
    orientation: "top auto",
    autoclose: true,
    todayHighlight: true,
  });
	
	const promotionId = $("#promotion-id").val();
	const promotionType = $("#promotion-type").val();
	
	if (promotionId !== "") {
		const sendData = {promotionId: promotionId};
		 
		$.ajax({
			url: "/management/selectPromotion",
			type: "post",
			dataType: "json",
			contentType: "application/json",
			data: JSON.stringify(sendData),
			success: function(data) {
				const promotion = data.promotion;
				
				$("#promotion-title-ko-input").val(promotion.PROMOTION_TITLE_KO);
				$("#promotion-link-ko").text(promotion.PROMOTION_LINK_KO);
				$("#promotion-link-ko").prop("href", promotion.PROMOTION_LINK_KO);
				$("#promotion-img-ko").prop("src", promotion.PROMOTION_IMAGE_KO);
				$("#promotion-img-ko").data("type", "change");
				
				$("#promotion-title-en-input").val(promotion.PROMOTION_TITLE_EN);
				$("#promotion-link-en").text(promotion.PROMOTION_LINK_EN);
				$("#promotion-link-en").prop("href", promotion.PROMOTION_LINK_EN);
				$("#promotion-img-en").prop("src", promotion.PROMOTION_IMAGE_EN);
				$("#promotion-img-en").data("type", "change");
				
				if (promotionType === "periodical") {
					const startDate = convertStringToDate(promotion.PROMOTION_START_YMD);
					const endDate = convertStringToDate(promotion.PROMOTION_END_YMD);
					$("#start-ymd").datepicker("update", startDate);
					$("#end-ymd").datepicker("update", endDate);
				}
				
			}
		});
	}
});

$(".promotion-html-textarea").on("input", function () {
  const $promotionHtmlTextarea = $(this);
	const $form = $promotionHtmlTextarea.parent().parent().parent();
  const textareaValue = $promotionHtmlTextarea.val().trim();
  formReset($form);

  if (textareaValue === "") {
    return;
  }

  const valueArray = textareaValue.split('"');
  const findStr1 = "href=";
  const findStr2 = "src=";

  let findIndex1 = -1;
  let findIndex2 = -1;

  for (let i = 0; i < valueArray.length; i++) {
    const value = valueArray[i];
    if (value.indexOf(findStr1) != -1) {
      findIndex1 = i;
    } else if (value.indexOf(findStr2) != -1) {
      findIndex2 = i;
    }
  }

  const $promotionLink = $form.find(".promotion-link");
  const $promotionImg = $form.find(".promotion-img");

  if (findIndex1 === -1 || findIndex2 === -1) {
    formReset($form);
    $promotionHtmlTextarea.addClass("warning");

  } else {
    $promotionHtmlTextarea.addClass("success");

    const link = valueArray[findIndex1 + 1];
    const img = valueArray[findIndex2 + 1];

    $promotionLink.text(link);
    $promotionLink.prop("href", link);
    $promotionImg.prop("src", img);
		$promotionImg.data("type", "change");
  }
});

$(".promotion-link").click(function (e) {
  e.preventDefault();
  window.open($(this).prop("href"), "_blank");
});

$(".promotion-img").on("error", function () {
	const $form = $(this).parent().parent();
  formReset($form);
  $form.find(".promotion-html-textarea").addClass("warning");
});

$(".promotion-cancel-button").click(function() {
	if (confirm("목록으로 돌아가시겠습니까?")) {
		location.href = "/management/promotionList.aba?type=" + $("#promotion-type").val();
	}
});

$(".promotion-add-button").click(function() {
	let promotionId = $("#promotion-id").val();
	const promotionType = $("#promotion-type").val();
	const promotionTitleKo = $("#promotion-title-ko-input").val().trim();
	const promotionTitleEn = $("#promotion-title-en-input").val().trim();
	const promotionLinkKo = $("#promotion-link-ko").text().trim();
	const promotionLinkEn = $("#promotion-link-en").text().trim();
	const promotionImageKo = $("#promotion-img-ko").prop("src").trim();
	const promotionImageEn = $("#promotion-img-en").prop("src").trim();
	const imgTypeKo = $("#promotion-img-ko").data("type");
	const imgTypeEn = $("#promotion-img-en").data("type");
	let promotionStartYMD;
	let promotionEndYMD;
	
	if (promotionType === "periodical") {
		promotionStartYMD = $("#start-ymd").datepicker("getFormattedDate").replace(/-/gi, "");
		promotionEndYMD = $("#end-ymd").datepicker("getFormattedDate").replace(/-/gi, "");
		
		if (promotionStartYMD === "" || promotionEndYMD === "") {
			alert("기간을 선택해 주세요.");
			return;
		}
	}
	
	if (promotionTitleKo === "" || promotionLinkKo === ""
			|| promotionTitleEn === "" || promotionLinkEn === "") {
		alert("공백이 존재합니다.");
		return;
	}
	if (imgTypeKo === "default" || imgTypeEn === "default") {
		alert("기본 이미지는 저장할 수 없습니다.");
		return;
	}
	if (promotionTitleKo.length > 50 || promotionTitleEn.length > 50) {
		alert("제목은 50자를 초과할 수 없습니다.");
		return;
	}
	if (promotionLinkKo.legnth > 100 || promotionImageKo.length > 100
			|| promotionLinkEn.legnth > 100 || promotionImageEn.length > 100) {
		alert("링크 및 이미지 경로는 100자를 초과할 수 없습니다.");
		return;
	}
	
	
	let url;	
	const sendData = {promotionTitleKo: promotionTitleKo,
										promotionLinkKo: promotionLinkKo, promotionImageKo: promotionImageKo,
										promotionTitleEn: promotionTitleEn,
										promotionLinkEn: promotionLinkEn, promotionImageEn: promotionImageEn};
	let requestType; 
										
	if (promotionId === "") {
		url = "/management/insertPromotion";
		sendData.promotionType = promotionType;
		requestType = "insert";
	} else {
		url = "/management/updatePromotion";
		sendData.promotionId = promotionId;
		requestType = "update";
	}
	if (promotionType === "periodical") {
		sendData.promotionStartYMD = promotionStartYMD;
		sendData.promotionEndYMD = promotionEndYMD;
	}
	
	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType: "application/json",
		data: JSON.stringify(sendData),
		success: function(data) {
			const result = data.result;
			
			if (requestType === "insert") {
				promotionId = data.promotionId;
			}
			
			if (result === "success") {
				alert("등록되었습니다.");
				location.href = "/management/promotion.aba?query=" + promotionId + "&type=" + promotionType;
				
			} else {
				alert("등록 실패, 잠시 후 다시 시도해 주세요.");
			}
		}
	})
					
});

function formReset($form) {
  const $promotionHtmlTextarea = $form.find(".promotion-html-textarea");
  const $promotionLink = $form.find(".promotion-link");
  const $promotionImg = $form.find(".promotion-img");
  const noImage = "/common/image/no-image.png";

  $promotionHtmlTextarea.removeClass("success warning");
  $promotionLink.empty();
  $promotionLink.removeProp("href");
  $promotionImg.prop("src", noImage);
	$promotionImg.data("type", "default");
}

function convertStringToDate(str) {
	const y = str.substr(0, 4);
  const m = str.substr(4, 2);
	const d = str.substr(6, 2);
	return y + "-" + m + "-" + d;	
}

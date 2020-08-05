let $secondaryCategorySelect;
let secondaryCategoryList;

$(document).ready(function () {
  $(".primary-category-select").selectize({
		placeholder: "1차 카테고리"
	});
  $secondaryCategorySelect = $(".secondary-category-select").selectize({
		placeholder: "2차 카테고리"
	});
	
	const spellingList = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'];
	
	for (let i = 0; i < spellingList.length; i++) {
		$("#imageCol").append(
			`<option value=${i}>${spellingList[i]}</option>`
		);
		$("#urlCol").append(
			`<option value=${i}>${spellingList[i]}</option>`
		);
		$("#originPriceCol").append(
			`<option value=${i}>${spellingList[i]}</option>`
		);
		$("#salePriceCol").append(
			`<option value=${i}>${spellingList[i]}</option>`
		);
	}
	$("#imageCol").val(3);
	$("#urlCol").val(4);
	$("#originPriceCol").val(5);
	$("#salePriceCol").val(6);
});

$(".hotProduct-delete-button").click(function() {
	const primaryCategoryId = $(".primary-category-select").children("option:selected").val();
	const secondaryCategoryId = $(".secondary-category-select").children("option:selected").val();
	const originHotProductExcel = $("#originHotProductExcel").val();

	if (primaryCategoryId === "" || secondaryCategoryId === "") {
		alert("카테고리를 선택해 주세요.");
		return;
	}
	
	if (originHotProductExcel === "") {
		alert("삭제할 파일이 존재하지 않습니다.");
		return;
	}
	
	const sendData = {secondaryCategoryId: secondaryCategoryId, originHotProductExcel: originHotProductExcel};
	
	$.ajax({
		url: "/management/deleteFileSecondaryCategory",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(sendData),
		dataType: "json",
		success: function(data) {
			const result = data.result;
			
			if (result === "success") {
				alert("삭제되었습니다.");
				window.location.reload();
			} else {
				alert("삭제 실패, 잠시 후 다시 시도해 주세요.");
			}
		}
	});	
});

$(".hotProduct-save-button").click(function() {
	const primaryCategoryId = $(".primary-category-select").children("option:selected").val();
	const secondaryCategoryId = $(".secondary-category-select").children("option:selected").val();

	if (primaryCategoryId === "" || secondaryCategoryId === "") {
		alert("카테고리를 선택해 주세요.");
		return;
	}
	
	const $form = $(".hotProduct-form");
	const formData = new FormData($form[0]);
	
	if ($("#self-radio").prop("checked")) {
		formData.set("hotProductCount", $("#self-input").val().trim());
	}
	
	if ($("#hotProductExcel").data("type") === "none") {
		formData.set("hotProductExcel", null);
		
		if ($("#originHotProductExcel").val() === "") {
			alert("파일을 선택해 주세요.");
			return;
		}
	}
	
	$.ajax({
		url: "/management/saveHotProduct",
		type: "post",
		enctype: 'multipart/form-data', // 필수
		data: formData, // 필수
		processData: false, // 필수
	 	contentType: false, // 필수
		success: function(data) {
			const result = data.result;
			
			if (result === "success") {
				alert("저장 성공");
				window.location.reload();
			} else if (result === "fileNotFound") {
				alert("파일을 찾을 수 없습니다.");
			} else {
				alert("저장 실패, 잠시 후 다시 시도해 주세요.");
			}
		}
	});
});

$(".primary-category-select").change(function() {
	const secondaryCategorySelect = $secondaryCategorySelect[0].selectize;
	secondaryCategorySelect.clear();
	secondaryCategorySelect.clearOptions();
	
	const primaryCategoryId = $(this).children("option:checked").val();

	if (primaryCategoryId !== "") {
		const sendData = {primaryCategoryId: primaryCategoryId};
		
		$.ajax({
			url: "/management/selectSecondaryCategoryList",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(sendData),
			dataType: "json",
			success: function(data) {
				secondaryCategoryList = data.secondaryCategoryList;
				const optionList = new Array();
				
				for (let secondaryCategory of secondaryCategoryList) {
					const value = secondaryCategory.SECONDARY_CATEGORY_ID;
					const text = secondaryCategory.SECONDARY_CATEGORY_NAME_KO;
					optionList.push({value: value, text: text});
				}
				
				secondaryCategorySelect.addOption(optionList);
				
			}
		});
	}
});

$(".secondary-category-select").change(function() {
	resetForm();
	const secondaryCategoryId = $(this).children("option:checked").val();
	
	if (secondaryCategoryId === "") {
		$(".hotProduct-item-box").css("display", "none");
		$(".no-select").css("display", "flex");
	} else {
		$(".no-select").css("display", "none");
		$(".hotProduct-item-box").css("display", "block");
	
		const sendData = {secondaryCategoryId: secondaryCategoryId};
		
		$.ajax({
			url: "/management/selectSecondaryCategory",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(sendData),
			dataType: "json",
			success: function(data) {
				const secondaryCategory = data.secondaryCategory;
				const secondaryCategoryFile = secondaryCategory.SECONDARY_CATEGORY_FILE;
				
				if (secondaryCategoryFile !== undefined) {
					const fileName = secondaryCategoryFile.substring(secondaryCategoryFile.lastIndexOf("/") + 1, secondaryCategoryFile.length);
					
					$("#custom-file-label").addClass("bold");
					$("#custom-file-label").text(fileName);
					$("#originHotProductExcel").val(secondaryCategoryFile);
				}
			}
		});
	}
});

$(".custom-file-input").change(function() {
	const $fileInput = $(this);
	const fileName = $fileInput[0].files[0].name;
	$fileInput.data("type", "change");
  $fileInput.nextAll(".custom-file-label").text(fileName);
  $fileInput.nextAll(".custom-file-label").addClass("bold");
});

function resetForm() {
	$("#first-radio").prop("checked", true);
	$("#self-input").val("");
	$("#hotProductExcel").val("");
	$("#custom-file-label").removeClass("bold");
	$("#custom-file-label").text("엑셀 파일을 선택해 주세요.");
}

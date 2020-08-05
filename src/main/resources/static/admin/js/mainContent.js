let type;
let mainContentList;
let isChanged;
const deleteMainContentId = new Array();
const deleteMainContentType = new Array();
const deleteMainContentImageKo = new Array();
const deleteMainContentImageEn = new Array();

$(document).ready(function () {

	type = $(".mainContent-wrap").data("type");
	
	const sendData = {mainContentType: type};
	
	$.ajax({
		url: "/management/selectMainContentList",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(sendData),
		dataType: "json",
		success: function(data) {
			const key = type + "ContentList";
			mainContentList = data[key];
			
			if (mainContentList.length === 0) {
				$(".no-data").css({display: "flex"});
				
			} else {
				const $formWrap = $(".mainContent-form-wrap");
				$formWrap.css({display: "block"});
				
				for (let mainContent of mainContentList) {
					$formWrap.append(createForm(mainContent));
				}
			}
		} 
	});
});

$(document).on("change", ".custom-file-input", function () {
	isChanged = true;
  const $fileInput = $(this);
	const inputType = $fileInput.data("type");
	const $formWrap = $fileInput.parent().parent().parent().parent();
	const formWrapType = $formWrap.data("type");
  const fileName = $fileInput[0].files[0].name;
  $fileInput.nextAll(".custom-file-label").text(fileName);
  $fileInput.nextAll(".custom-file-label").addClass("bold");

	if (formWrapType === "origin" || formWrapType === "update") {
		if (inputType === "ko") {
			$formWrap.data("type", "update-ko");
			$formWrap.find(".status-input").val("update-ko");
			
		} else {
			$formWrap.data("type", "update-en");
			$formWrap.find(".status-input").val("update-en");
		}
	} else if (formWrapType === "update-ko") {
		if (inputType === "en") {
			$formWrap.data("type", "update-all");
			$formWrap.find(".status-input").val("update-all");
		}
	} else if (formWrapType === "update-en") {
		if (inputType === "ko") {
			$formWrap.data("type", "update-ko");
			$formWrap.find(".status-input").val("update-all");
		}
	}
});

$(document).on("input", ".description-input", function() {
	const $formWrap = $(this).parent().parent().parent().parent();
	
	isChanged = true;
	if ($formWrap.data("type") === "origin") {
		$formWrap.data("type", "update");
		$formWrap.find(".status-input").val("update");
	}
});

$(document).on("click", ".upper-button", function () {
	isChanged = true;
  const $formBox = $(this).parent().parent().parent();
  const $prevFormBox = $formBox.prev();
  $formBox.insertBefore($prevFormBox);
});

$(document).on("click", ".lower-button", function () {
	isChanged = true;
  const $formBox = $(this).parent().parent().parent();
  const $nextFormBox = $formBox.next();
  $formBox.insertAfter($nextFormBox);
});

$(document).on("click", ".form-add-button", function () {
	isChanged = true;
  const $formBox = $(this).parent().parent().parent();
  $formBox.after(createForm());
});

$(document).on("click", ".form-remove-button", function () {
	isChanged = true;
  const $formBox = $(this).parent().parent().parent();

	if ($formBox.data("type") !== "new") {
		const mainContentId = $formBox.find(".origin-id-input").val();
		const filteredList = mainContentList.filter(mainContent => mainContent.MAIN_CONTENT_ID === Number(mainContentId));
		
		deleteMainContentId.push(mainContentId);
		deleteMainContentType.push(filteredList[0].MAIN_CONTENT_TYPE);
		deleteMainContentImageKo.push(filteredList[0].MAIN_CONTENT_IMAGE_KO);
		deleteMainContentImageEn.push(filteredList[0].MAIN_CONTENT_IMAGE_EN);		
	}

  $formBox.remove();

	const $formWrap = $(".mainContent-form-wrap");
	
	if ($formWrap.children(".mainContent-form-box").length === 0) {
		$formWrap.css({display: "none"});
		$(".no-data").css({display: "flex"});
	}
});

$(".no-data-add-button").click(function() {
	isChanged = true;
	const $noDataDiv = $(this).parent();
	const $formWrap = $noDataDiv.nextAll(".mainContent-form-wrap");
	$noDataDiv.css({display: "none"});
	$formWrap.css({display: "block"});
	$formWrap.append(createForm());
});

$(".mainContent-save-button").click(function() {
	const $formWrap = $(".mainContent-form-wrap");
	const $mainContentList = $formWrap.children(".mainContent-form-box");
	
	if ((mainContentList.length === 0 && $mainContentList.length === 0)
	    || !isChanged) {
		alert("변경 사항이 없습니다.");
		return;
	}
	let isValid = true;
	
	$(".custom-file-input").each(function() {
		const $customFileInput = $(this);
		if ($customFileInput.val() === "" && $customFileInput.data("status") !== "attached") {
			alert("파일을 첨부해 주세요.");
			isValid = false;
			return false;
		}
	});
	
	if (!isValid) {
		return false;
	}
	
	$(".description-input").each(function() {
		const $descriptionInput = $(this);
		if ($descriptionInput.val() === "") {
			$descriptionInput.val("null");
		}
	});
	
	const form = document.getElementById("main-content-form");
	const formData = new FormData(form);
	
	formData.append("deleteMainContentId", deleteMainContentId);
	formData.append("deleteMainContentType", deleteMainContentType);
	formData.append("deleteMainContentImageKo", deleteMainContentImageKo);
	formData.append("deleteMainContentImageEn", deleteMainContentImageEn);
	
	$.ajax({
		url: "/management/saveMainContentList",
		type: "post",
		enctype: 'multipart/form-data', // 필수
		data: formData, // 필수
		processData: false, // 필수
	 	contentType: false, // 필수
		success: function(data) {
			const result = data.result;
			
			if (result === "success") {
				alert("저장되었습니다.");
				window.location.reload();
			} else {
				alert("저장 실패, 잠시 후 다시 시도해 주세요.");
			}
		}
	});
});

function createForm(mainContent) {
	let form = "";
		
	// 새로운 폼
	if (!mainContent) {
		form += `
    	<div data-type="new" class="mainContent-form-box">
        <div class="mainContent-form">
          <div class="order-button-box">
            <button class="upper-button" type="button">
              <i class="fas fa-angle-double-up"></i>
            </button>
            <button class="lower-button" type="button">
              <i class="fas fa-angle-double-down"></i>
            </button>
          </div>
          <div class="input-form">
            <input type="hidden" name="mainContentType" value="${type}" />
            <input type="hidden" name="mainContentId" value="0" />
            <input type="hidden" name="status" value="new" />
            <div class="input-wrap-box">
              <label class="input-label" for="input-form-wrap-ko"
                >한글 버전</label
              >
              <div
                id="input-form-wrap-ko"
                class="input-form-wrap custom-file"
              >
                <input
                  data-type="ko"
                  type="file"
                  class="custom-file-input"
                  name="mainContentImageKo"
                  accept="image/*"
                />
                <input
                  type="hidden"
                  name="originMainContentImageKo"
                  value="new"
                />
                <label class="custom-file-label shadow-none"
                  >이미지를 선택해 주세요.</label
                >
              </div>
            </div>
            <div class="input-wrap-box">
              <label class="input-label" for="input-form-wrap-en"
                >영문 버전</label
              >
              <div
                id="input-form-wrap-en"
                class="input-form-wrap custom-file"
              >
                <input
                  data-type="en"
                  type="file"
                  class="custom-file-input"
                  name="mainContentImageEn"
                  accept="image/*"
                />
                <input
                  type="hidden"
                  name="originMainContentImageEn"
                  value="new"
                />
                <label
                  class="custom-file-label shadow-none"
                  for="customFile"
                  >이미지를 선택해 주세요.</label
                >
              </div>
            </div>
            <div class="input-wrap-box">
              <label class="input-label" for="input-form-wrap-description"
                >설명</label
              >
              <div class="input-form-wrap">
                <input
                  type="text"
                  id="input-form-wrap-description"
                  class="form-control form-control-sm shadow-none description-input"
                  name="mainContentDescription"
                  placeholder="설명을 입력해 주세요."
                />
              </div>
            </div>
          </div>
          <div class="right-button-box">
            <button type="button" class="form-add-button">
              <i class="fas fa-plus-square"></i>
            </button>
            <button type="button" class="form-remove-button">
              <i class="fas fa-minus-square"></i>
            </button>
          </div>
        </div>
      </div>
    	`;
		
	} else {
		const mainContentId = mainContent.MAIN_CONTENT_ID;
		const mainContentImageKo = mainContent.MAIN_CONTENT_IMAGE_KO;
		const mainContentImageEn = mainContent.MAIN_CONTENT_IMAGE_EN;
		const mainContentDescription = mainContent.MAIN_CONTENT_DESCRIPTION ? mainContent.MAIN_CONTENT_DESCRIPTION : "";
		
		const mainContentImageKoFileName = mainContentImageKo.substring(mainContentImageKo.lastIndexOf("/") + 1, mainContentImageKo.length);
		const mainContentImageEnFileName = mainContentImageEn.substring(mainContentImageEn.lastIndexOf("/") + 1, mainContentImageEn.length);
		
		form += `
			<div data-type="origin" data-id=${mainContentId} class="mainContent-form-box">
				<div class="mainContent-form">
	        <div class="order-button-box">
	          <button class="upper-button" type="button">
	            <i class="fas fa-angle-double-up"></i>
	          </button>
	          <button class="lower-button" type="button">
	            <i class="fas fa-angle-double-down"></i>
	          </button>
	        </div>
	        <div class="input-form">
						<input type="hidden" class="origin-type-input" name="mainContentType" value="${type}"/>
						<input type="hidden" class="origin-id-input" name="mainContentId" value="${mainContentId}"/> 
						<input class="status-input" type="hidden" name="status" value="origin"/>
						<div class="input-wrap-box">
              <label class="input-label"
                >한글 버전</label
              >
		          <div class="input-form-wrap custom-file">
		            <input
									data-type="ko"
									data-status="attached"
		              type="file"
		              class="custom-file-input"
									name="mainContentImageKo"
		              accept="image/*"
		            />
								<input type="hidden"
									class="origin-file-input-ko"
									name="originMainContentImageKo"
									value="${mainContentImageKo}"
								/>
		            <label class="custom-file-label shadow-none bold" for="customFile"
		              >${mainContentImageKoFileName}</label
		            >
		          </div>
						</div>
						<div class="input-wrap-box">
              <label class="input-label"
                >영문 버전</label
              >
		          <div class="input-form-wrap custom-file">
		            <input
									data-type="en"
									data-status="attached"
		              type="file"
		              class="custom-file-input"
									name="mainContentImageEn"
		              accept="image/*"
		            />
								<input type="hidden"
									class="origin-file-input-en"
									name="originMainContentImageEn"
									value="${mainContentImageEn}"
								/>
		            <label class="custom-file-label shadow-none bold" for="customFile"
		              >${mainContentImageEnFileName}</label
		            >
		          </div>
						</div>
						<div class="input-wrap-box">
              <label class="input-label" for="input-form-wrap-description"
                >설명</label
              >
		          <div class="input-form-wrap">
		            <input
		              type="text"
		              class="form-control form-control-sm shadow-none description-input"
									name="mainContentDescription"
		              placeholder="설명을 입력해 주세요."
									value="${mainContentDescription}"
		            />
		          </div>
						</div>
	        </div>
	        <div class="right-button-box">
	          <button type="button" class="form-add-button">
	            <i class="fas fa-plus-square"></i>
	          </button>
	          <button type="button" class="form-remove-button">
	            <i class="fas fa-minus-square"></i>
	          </button>
	        </div>
	      </div>
	    </div>
			`;
	}
		
	return form;
}

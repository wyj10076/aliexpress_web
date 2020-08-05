let primaryCategoryScrollbar;
let secondaryCategoryScrollbar;
let primaryCategoryList;
let secondaryCategoryList;
let selectPrimaryCategoryId;
let insertObject = { type: "", insertCount: 0 };
let updateObject = { type: "", isUpdating: false };
let deleteObject = { type: "", isDeleting: false };

$(document).ready(function () {
  const scrollOptions = {
    className: "os-theme-dark",
    resize: "none",
    autoUpdate: true,
    sizeAutoCapable: true,
    paddingAbsolute: true,
    scrollbars: {
      clickScrolling: true,
    },
    overflowBehavior: {
      x: "hidden",
      y: "scroll",
    },
  };

  primaryCategoryScrollbar = $("#primary-category-tbody")
    .overlayScrollbars(scrollOptions)
    .overlayScrollbars();
  secondaryCategoryScrollbar = $("#secondary-category-tbody")
    .overlayScrollbars(scrollOptions)
    .overlayScrollbars();

  // select primary category
  $.ajax({
    url: "/management/selectPrimaryCategoryList",
    type: "post",
    dataType: "json",
    success: function (data) {
      primaryCategoryList = data.primaryCategoryList;
      let html = "";

      if (primaryCategoryList.length === 0) {
        html += `
					<tr>
						<td class="no-data">
							데이터가 없습니다.
						</td>
					</tr>
				`;
      } else {
        for (let primaryCategory of primaryCategoryList) {
          html += `
						<tr data-id="${primaryCategory.PRIMARY_CATEGORY_ID}" data-type="normal">
		          <td class="category-check-box">
		            <input data-type="primary" type="checkbox" />
		          </td>
		          <td class="category-name-ko">${primaryCategory.PRIMARY_CATEGORY_NAME_KO}</td>
		          <td class="category-name-en">${primaryCategory.PRIMARY_CATEGORY_NAME_EN}</td>
		        </tr>
					`;
        }
      }

      $("#primary-category-tbody").find(".os-content").html(html);
    },
  });
});

// 추가 버튼 클릭 시
$(".category-add-button").click(function () {
  const $buttonBox = $(this).parent();
  const type = $buttonBox.data("type");
  const $content = $buttonBox.nextAll(".category-table").find(".os-content");
  const isNoData = $content.find(".no-data").length === 1 ? true : false;

  if (isNoData) {
    $content.empty();
  }

  if (
    (insertObject.insertCount !== 0 && insertObject.type !== type) ||
    (updateObject.isUpdating && updateObject.type !== type) ||
    (deleteObject.isDeleting && deleteObject.type !== type)
  ) {
    if (type === "primary") {
      alert("2차 카테고리 작업 중입니다.");
    } else {
      alert("1차 카테고리 작업 중입니다.");
    }
    return;
  }

  if (type === "primary") {
    $content.append(
      `
			<tr data-type="insert">
	      <td class="category-check-box">
					<i class="remove-row fas fa-minus-square"></i>
	      </td>
	      <td class="category-name-ko"><input type="text" class="category-input form-control form-control-sm shadow-none"/></td>
	      <td class="category-name-en"><input type="text" class="category-input form-control form-control-sm shadow-none"/></td>
	    </tr>
			`
    );

    insertObject.type = type;
    insertObject.insertCount++;
  } else {
    if (!selectPrimaryCategoryId) {
      alert("1차 카테고리를 선택해 주세요.");
      return;
    }

    $content.append(
      `
			<tr data-primarycategoryid=${selectPrimaryCategoryId}
				data-type="insert">
	      <td class="category-check-box">
					<i class="remove-row fas fa-minus-square"></i>
	      </td>
	      <td class="category-name-ko"><input type="text" class="category-input form-control form-control-sm shadow-none"/></td>
	      <td class="category-name-en"><input type="text" class="category-input form-control form-control-sm shadow-none"/></td>
				<input type="hidden" class="secondary-category-file" value="none" />
	    </tr>
			`
    );

    insertObject.type = type;
    insertObject.insertCount++;
  }

  if (type === "primary") {
    primaryCategoryScrollbar.scroll({ y: "100%" }, 300);
  } else {
    secondaryCategoryScrollbar.scroll({ y: "100%" }, 300);
  }
});

// 수정 버튼 클릭 시
$(".category-update-button").click(function () {
  const $buttonBox = $(this).parent();
  const type = $buttonBox.data("type");
  const $content = $buttonBox.nextAll(".category-table").find(".os-content");
  const $trList = $content.children();

  // 수정 중일 경우
  if (updateObject.isUpdating) {
    if (
      updateObject.type === type &&
      confirm("수정 중인 작업을 취소하시겠습니까?")
    ) {
      $trList.each(function (index) {
        const $tr = $(this);
        const trType = $tr.data("type");

        if (trType !== "delete") {
          $tr.find("input[type=checkbox]").prop("checked", false);

          if (trType === "update") {
            $tr.data("type", "normal");

            const $categoryNameKo = $tr.children(".category-name-ko");
            const $categoryNameEn = $tr.children(".category-name-en");
            $categoryNameKo.empty();
            $categoryNameEn.empty();

            let categoryList =
              type === "primary" ? primaryCategoryList : secondaryCategoryList;
            let upperCaseType = type.toUpperCase();
            $categoryNameKo.html(
              categoryList[index][upperCaseType + "_CATEGORY_NAME_KO"]
            );
            $categoryNameEn.html(
              categoryList[index][upperCaseType + "_CATEGORY_NAME_EN"]
            );
          }
        }
      });

      // 수정 모드 전환
      updateObject.isUpdating = !updateObject.isUpdating;
    } else {
      if (updateObject.type === "primary") {
        alert("1차 카테고리 변경 중입니다.");
      } else {
        alert("2차 카테고리 변경 중입니다.");
      }
    }

    // 수정 중이 아닐 경우
  } else {
    if (
      (insertObject.insertCount !== 0 && insertObject.type !== type) ||
      (deleteObject.isDeleting && deleteObject.type !== type)
    ) {
      if (type === "primary") {
        alert("2차 카테고리 변경 중입니다.");
      } else {
        alert("1차 카테고리 변경 중입니다.");
      }
      return;
    }
    let hasChecked;

    $trList.each(function () {
      const $tr = $(this);
      const $checkBox = $tr.find("input[type=checkbox]");

      if ($tr.data("type") === "normal" && $checkBox.prop("checked")) {
        hasChecked = true;
        $tr.data("type", "update");

        const $categoryNameKo = $tr.children(".category-name-ko");
        const $categoryNameEn = $tr.children(".category-name-en");
        const categoryNameKo = $categoryNameKo.text();
        const categoryNameEn = $categoryNameEn.text();

        $categoryNameKo.html(
          `
					<input type="text" class="category-input form-control form-control-sm shadow-none" value="${categoryNameKo}" />
					`
        );
        $categoryNameEn.html(
          `
					<input type="text" class="category-input form-control form-control-sm shadow-none" value="${categoryNameEn}" />
					`
        );
      }
    });

    if (!hasChecked) {
      alert("체크된 항목이 없습니다.");
    } else {
      // 수정 모드 전환
      updateObject.type = type;
      updateObject.isUpdating = !updateObject.isUpdating;
    }
  }
});

// 삭제 버튼 클릭 시
$(".category-delete-button").click(function () {
  const $buttonBox = $(this).parent();
  const type = $buttonBox.data("type");
  const $content = $buttonBox.nextAll(".category-table").find(".os-content");
  const $trList = $content.children();

  // 삭제 중일 경우
  if (deleteObject.isDeleting) {
    if (
      deleteObject.type === type &&
      confirm("삭제 중인 작업을 취소하시겠습니까?")
    ) {
      $trList.each(function (index) {
        const $tr = $(this);
        const trType = $tr.data("type");

        if (trType !== "update") {
          $tr.find("input[type=checkbox]").prop("checked", false);

          if (trType === "delete") {
            $tr.data("type", "normal");
            $tr.removeClass("delete");
          }
        }
      });

      // 삭제 모드 전환
      deleteObject.isDeleting = !deleteObject.isDeleting;
    } else {
      if (deleteObject.type === "primary") {
        alert("1차 카테고리 변경 중입니다.");
      } else {
        alert("2차 카테고리 변경 중입니다.");
      }
    }

    // 삭제 중이 아닐 경우
  } else {
    if (
      (insertObject.insertCount !== 0 && insertObject.type !== type) ||
      (updateObject.isUpdating && updateObject.type !== type)
    ) {
      if (type === "primary") {
        alert("2차 카테고리 변경 중입니다.");
      } else {
        alert("1차 카테고리 변경 중입니다.");
      }
      return;
    }
    let hasChecked;

    $trList.each(function () {
      const $tr = $(this);
      const $checkBox = $tr.find("input[type=checkbox]");

      if ($tr.data("type") === "normal" && $checkBox.prop("checked")) {
        hasChecked = true;
        $tr.data("type", "delete");
        $tr.addClass("delete");
      }
    });

    if (!hasChecked) {
      alert("체크된 항목이 없습니다.");
    } else {
      // 삭제 모드 전환
      deleteObject.type = type;
      deleteObject.isDeleting = !deleteObject.isDeleting;
    }
  }
});

// 1차 카테고리 클릭 시
$(document).on("click", "#primary-category-tbody tr", function () {
  const $tr = $(this);
  const categoryNameKo = $tr.find(".category-name-ko").text();
  const type = $tr.data("type");
  if (type === "insert") {
    return;
  } else {
    selectPrimaryCategoryId = $tr.data("id");
    const sendData = { primaryCategoryId: selectPrimaryCategoryId };

    $.ajax({
      url: "/management/selectSecondaryCategoryList",
      type: "post",
      contentType: "application/json",
      data: JSON.stringify(sendData),
      dataType: "json",
      success: function (data) {
        secondaryCategoryList = data.secondaryCategoryList;
        let html = "";

        if (secondaryCategoryList.length === 0) {
          html += `
						<tr>
							<td class="no-data">
								데이터가 없습니다.
							</td>
						</tr>
					`;
        } else {
          for (let secondaryCategory of secondaryCategoryList) {
            html += `
							<tr data-id="${secondaryCategory.SECONDARY_CATEGORY_ID}"
									data-primarycategoryid="${selectPrimaryCategoryId}"
									data-type="normal">
			          <td class="category-check-box">
			            <input data-type="secondary" type="checkbox" />
			          </td>
			          <td class="category-name-ko">${secondaryCategory.SECONDARY_CATEGORY_NAME_KO}</td>
			          <td class="category-name-en">${secondaryCategory.SECONDARY_CATEGORY_NAME_EN}</td>
								<input type="hidden" class="secondary-category-file" value=${secondaryCategory.SECONDARY_CATEGORY_FILE} />
			        </tr>
						`;
          }
        }

        $("#secondary-category-tbody").find(".os-content").html(html);
        $(".select-primary-category-name").html(categoryNameKo);
      },
    });
  }
});

// 체크박스 주변 클릭 시
$(document).on("click", ".category-check-box", function (e) {
  e.stopPropagation();
  const $child = $(this).children();
  const tagName = $child.prop("tagName");
  if (tagName === "INPUT") {
    toggleCheckBox("around", $child);
  } else {
    $child.parent().parent().remove();
    insertObject.insertCount--;
  }
});

// 체크박스 직접 클릭 시
$(document).on("click", ".category-check-box input[type=checkbox]", function (
  e
) {
  e.stopPropagation();
  toggleCheckBox("direct", $(this));
});

$(document).on("click", ".remove-row", function (e) {
  e.stopPropagation();
  $(this).parent().parent().remove();
  insertObject.insertCount--;
});

// 인풋 태그 클릭 시
$(document).on("click", ".category-input", function (e) {
  e.stopPropagation();
});

// 저장버튼 클릭 시
$(".category-save-button").click(function () {
  const $buttonBox = $(this).parent();
  const type = $buttonBox.data("type");
  const $categoryTable = $buttonBox.nextAll(".category-table");
  const $trList = $categoryTable.find(".os-content").children();
  const insertList = new Array();
  const updateList = new Array();
  const deleteList = new Array();
  let isValid = true;

  $trList.each(function () {
    const $tr = $(this);

    if ($tr.data("type") === "insert") {
      const insert = new Object();

      $tr.find(".category-input").each(function () {
        const $input = $(this);
        isValid = validate(insert, $input);

        // input.each break
        if (!isValid) {
          return false;
        }
      });

      if (type === "secondary") {
        insert.primaryCategoryId = $tr.data("primarycategoryid");
      }
      insertList.push(insert);
    } else if ($tr.data("type") === "update") {
      const update = new Object();

      $tr.find(".category-input").each(function () {
        const $input = $(this);
        isValid = validate(update, $input);

        if (!isValid) {
          return false;
        }
      });

      update.categoryId = $tr.data("id");

      if (type === "secondary") {
        update.primaryCategoryId = $tr.data("primarycategoryid");
      }
      updateList.push(update);
    } else if ($tr.data("type") === "delete") {
      const deleteObj = new Object();

      deleteObj.categoryId = $tr.data("id");

      if (type === "secondary") {
        deleteObj.primaryCategoryId = $tr.data("primarycategoryid");
				deleteObj.originHotProductExcel = $tr.find(".secondary-category-file").val();
      }

      deleteList.push(deleteObj);
    }

    // trList.each break
    if (!isValid) {
      return false;
    }
  });

  if (!isValid) {
    return;
  } else if (
    insertList.length === 0 &&
    updateList.length === 0 &&
    deleteList.length === 0
  ) {
    alert("변경 사항이 없습니다.");
    return;
  }

  let url;
  const sendData = new Object();

  if (insertList.length !== 0) {
    sendData.insertList = insertList;
  }
  if (updateList.length !== 0) {
    sendData.updateList = updateList;
  }
  if (deleteList.length !== 0) {
    sendData.deleteList = deleteList;
  }

  if (type === "primary") {
    url = "/management/savePrimaryCategoryList";
  } else {
    url = "/management/saveSecondaryCategoryList";
  }

  $.ajax({
    url: url,
    type: "post",
    contentType: "application/json",
    data: JSON.stringify(sendData),
    dataType: "json",
    success: function (data) {
      const result = data.result;

      if (result === "success") {
        alert("저장되었습니다.");
        location.reload();
      } else {
        alert("저장 실패, 잠시 후 다시 시도해 주세요.");
      }
    },
  });
});

function validate(object, $input) {
  const inputValue = $input.val().trim();

  if (inputValue === "") {
    alert("공백이 존재합니다.");
    $input.focus();
    return false;
  } else if (inputValue.length > 80) {
    alert("80자를 초과할 수 없습니다.");
    $input.focus();
    return false;
  }

  if ($input.parent().hasClass("category-name-ko")) {
    object.categoryNameKo = inputValue;
  } else {
    object.categoryNameEn = inputValue;
  }

  return true;
}

function toggleCheckBox(point, $checkBox) {
  const type = $checkBox.data("type");
  const $categoryTable = $("#" + type + "-table");

  if (point !== "direct") {
    $checkBox.prop("checked", function () {
      return !$checkBox.prop("checked");
    });
  }

  if ($checkBox.parent().hasClass("category-check-all")) {
    const $trList = $categoryTable.find(".os-content").children();
    const isChecked = $checkBox.prop("checked");

    $trList.each(function () {
      const $tr = $(this);
      if ($tr.data("type") === "normal") {
        $tr.find("input[type=checkbox]").prop("checked", isChecked);
      }
    });
  } else {
    if ($checkBox.parent().parent().data("type") !== "normal") {
      alert("변경 중인 항목은 체크를 풀 수 없습니다.");
      $checkBox.prop("checked", true);
      return;
    }

    if (!$checkBox.prop("checked")) {
      $categoryTable
        .find(".category-check-all")
        .children("input[type=checkbox]")
        .prop("checked", false);
    }
  }
}

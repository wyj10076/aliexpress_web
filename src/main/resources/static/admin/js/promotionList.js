let promotionTable;

$(document).ready(function () {
	const $promotionTable = $("#promotion-table");
	const promotionType = $promotionTable.data("type");
	
  const language = {
    decimal: "",
    emptyTable: "데이터가 없습니다.",
    info: "Total : _TOTAL_ 건",
    infoEmpty: "",
    infoFiltered: "(전체 _MAX_ 건 중 필터링 됨)",
    infoPostFix: "",
    thousands: ",",
    lengthMenu: "목록 개수 : _MENU_",
    loadingRecords: "Loading...",
    processing: "Processing...",
    search: "Search : ",
    zeroRecords: "검색 결과가 없습니다.",
    paginate: {
      first: "처음",
      last: "마지막",
      next: "다음",
      previous: "이전",
    },
    aria: {
      sortAscending: ": activate to sort column ascending",
      sortDescending: ": activate to sort column descending",
    },
  };

  promotionTable = $promotionTable.DataTable({
		order: [[0,'desc']],
    pageLength: 5,
    language: language,
		searching: false,
		lengthChange: false,
		stateSave: true,
    ajax: {
      url: "/management/selectPromotionList",
			type: "POST",
			contentType: "application/json",
			data: function() {
				return JSON.stringify({promotionType: promotionType});
			},
			dataSrc: "promotionList"
    },
    columns: [
      { data: "PROMOTION_ID", width: "40px" },
      { data: "PROMOTION_IMAGE_KO", width: "120px", orderable: false,
				render: function (data) {
					return `<img class="banner-img" src="${data}"/>`;		
				}},
      { data: "PROMOTION_TITLE_KO" },
			{ data: "PROMOTION_END_YMD", width: "100px",
				render: function(data, type, row) {
					if (data === undefined) {
						return "상시";
					}
					const startYMD = convertStringToDate(row["PROMOTION_START_YMD"]); 
					const endYMD = convertStringToDate(data);
					return startYMD + "<br>~&nbsp;" + endYMD;
				}},
      { data: "PROMOTION_ID", width: "80px", orderable: false,
        render: function (data) {
          const html = `
						<div class="promotion-inner-button-box">
	            <button data-id="${data}" class="promotion-button update-button" type="button">수정</button>
	            <button data-id="${data}" class="promotion-button delete-button" type="button">삭제</button>
						<div class="promotion-inner-button-box">
          `;
          return html;
       	}},
    ],
  });
});

$(".add-button").click(function() {
	const type = $("#promotion-table").data("type");
	location.href = "/management/promotion.aba?type=" + type;
});

$(document).on("click", ".update-button", function () {
  const promotionId = $(this).data("id");
	const type = $("#promotion-table").data("type");
  location.href = "/management/promotion.aba?query=" + promotionId + "&type=" + type;
});

$(document).on("click", ".delete-button", function () {
	if (confirm("해당 프로모션을 삭제 하시겠습니까?")) {
		
	  const promotionId = $(this).data("id");
		const sendData = {promotionId: promotionId};
		
		$.ajax({
			url: "/management/deletePromotion",
			type: "post",
			dataType: "json",
			contentType: "application/json",
			data: JSON.stringify(sendData),
			success: function(data) {
				const result = data.result;
				
				if (result === "success") {
					promotionTable.ajax.reload(null, false);
				} else {
					alert("삭제 실패, 잠시 후 다시 시도해 주세요.");
				}
			} 
		})
	}
});

function convertStringToDate(str) {
	const y = str.substr(0, 4);
  const m = str.substr(4, 2);
	const d = str.substr(6, 2);
	return y + "-" + m + "-" + d;	
}

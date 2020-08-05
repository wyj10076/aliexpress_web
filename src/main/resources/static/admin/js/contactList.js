$(document).ready(function () {
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
	
  const contactTable = $("#contact-table").DataTable({
		order: [[0,'desc']],
		lengthMenu: [5, 10, 25, 50],
		language: language,
		ajax: {
			url: "/management/selectContactList",
			type: "POST",
			dataSrc: "contactList"
		},
		columns: [
				{data: "CONTACT_ID", width: "40px"},
				{data: "CONTACT_TITLE"},
				{data: "CONTACT_REG_YMD",
					render: function(data) {
					  return convertStringToDate(data);	
					},
					width: "60px"},
				{data: "CONTACT_READ_YMD",
					defaultContent: `<span>-</span>`,
					render: function(data) {
						if (data) {
					  	return convertStringToDate(data);
						}
					},
					width: "60px"},
				{data: "CONTACT_REPLY_YMD",
					defaultContent: `<span>-</span>`,
					render: function(data) {
						if (data) {
						  return convertStringToDate(data);
						}
					},
					width: "60px"}
			]
	});
	
	$("#contact-table tbody").on("click", "tr", function() {
		const contactId = contactTable.row(this).data()["CONTACT_ID"];
		const isReplied = contactTable.row(this).data()["CONTACT_REPLY_YMD"] !== undefined;
		location.href = "/management/contact.aba?query=" + contactId + "&reply=" + isReplied;
	});
	
});

function convertStringToDate(str) {
	const y = str.substr(0, 4);
  const m = str.substr(4, 2);
	const d = str.substr(6, 2);
	return y + "-" + m + "-" + d;	
}

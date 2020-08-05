$(document).ready(function() {
	let today = new Date();
	today = convertDateToString(today);
	
	$(".promotion-item-period").each(function() {
		const endYMD = $(this).data("date");
		if (endYMD !== "") {
			if (Number(endYMD) - Number(today) <= 30) {
				$(this).parent().parent().find(".tag-img").css("display", "block");
			}
		}
	});
});

$(".promotion-item-box").click(function() {
	window.open($(this).data("link"), "_blank");
});

function convertDateToString(date) {
	let year = String(date.getFullYear());
	let month = String(date.getMonth() + 1);
	let day = String(date.getDate());
	
	if (month.length < 2) {
		month = "0" + month;
	}
	if (day.length < 2) {
		day = "0" + day;
	}
	
	return year + month + day;
}
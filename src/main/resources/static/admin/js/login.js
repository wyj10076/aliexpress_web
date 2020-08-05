$(".login-form").submit(function(e) {
	e.preventDefault();
	const $loginForm = $(this);
	
	let hasBlank = false;
	
	$(".login-input").each(function() {
		const $loginInput = $(this);
		if ($loginInput.val().trim() === "") {
			hasBlank = true;
			$loginInput.focus();
			return false;
		}
	});
	
	if (hasBlank === true) {
		alert("아이디와 비밀번호를 입력해 주세요.");
		return;
	}
	
	const id = $("#id-input").val().trim();
	const password = $("#password-input").val().trim(); 
	const sendData = {id: id, password: password};
	
	$.ajax({
		url: "/management/login",
		type: "post",
		dataType: "json",
		contentType: "application/json",
		data: JSON.stringify(sendData),
		success: function(data) {
			const result = data.result;
			
			if (result === "success") {
				location.href = "/management";
				
			} else if (result === "fail"){
				alert("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
				
			} else {
				alert("에러 발생");
			}
		}
	});
	
});
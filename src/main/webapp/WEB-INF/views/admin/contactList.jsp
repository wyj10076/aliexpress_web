<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css" />
<link rel="stylesheet" href="/admin/css/contactList.min.css" />
<title>Contact List</title>
</head>
<body>
	<div class="contact-wrap">
		<div class="contact-box">
			<div class="menu-title">문의 관리</div>
			<hr class="menu-division" />
			<table id="contact-table"
				class="table table-hover text-nowrap table-bordered"
				style="width: 100%;">
				<thead class="thead-dark">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>문의 날짜</th>
						<th>읽은 날짜</th>
						<th>답변 날짜</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="/admin/js/contactList.js"></script>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css"
    />
    <link rel="stylesheet" href="/admin/css/promotionList.min.css" />
    <title>Promotion List</title>
  </head>
  <body>
    <div class="promotion-wrap">
      <div class="promotion-box">
        <div class="menu-title ${type}"></div>
        <hr class="menu-division" />
        <div class="upper-button-box">
        	<button class="add-button" type="button">추가</button>
        </div>
        <table
        	data-type="${type }"
          id="promotion-table"
          class="table table-hover text-nowrap table-bordered"
          style="width: 100%;"
        >
          <thead class="thead-dark">
            <tr>
              <th>번호</th>
              <th>배너</th>
              <th>제목</th>
              <th>기간</th>
              <th>관리</th>
            </tr>
          </thead>
        </table>
      </div>
    </div>
    <script
      type="text/javascript"
      charset="utf8"
      src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"
    ></script>
    <script
      type="text/javascript"
      charset="utf8"
      src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"
    ></script>
    <script type="text/javascript" src="/admin/js/promotionList.js"></script>
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/admin/css/categoryList.min.css" />
    <title>Category List</title>
  </head>
  <body>
    <div class="category-wrap">
      <div class="category-box">
        <div class="menu-title">카테고리 관리</div>
        <hr class="menu-division" />
        <div class="table-box">
          <div class="primary-category-box inner-category-box">
            <div class="category-title">1차 카테고리</div>
            <hr class="category-division" />
            <div data-type="primary" class="button-box">
              <button class="category-button category-add-button">추가</button>
              <button class="category-button category-update-button">수정</button>
              <button class="category-button category-delete-button">삭제</button>
              <button class="category-button category-save-button">저장</button>
            </div>
            <table id="primary-table" class="category-table table-hover">
              <thead>
                <tr>
                  <th class="category-check-box category-check-all">
                    <input data-type="primary" type="checkbox" />
                  </th>
                  <th>한글</th>
                  <th>영문</th>
                </tr>
              </thead>
              <tbody id="primary-category-tbody"></tbody>
            </table>
          </div>
          <div class="secondary-category-box inner-category-box">
            <div class="category-title">2차 카테고리</div>
            <hr class="category-division" />
            <div data-type="secondary" class="button-box">
              <button class="category-button category-add-button">추가</button>
              <button class="category-button category-update-button">수정</button>
              <button class="category-button category-delete-button">삭제</button>
              <button class="category-button category-save-button">저장</button>
            </div>
            <table id="secondary-table" class="category-table table-hover">
              <thead>
                <tr>
                  <th class="category-check-box category-check-all">
                    <input data-type="secondary" type="checkbox" />
                  </th>
                  <th>한글</th>
                  <th>영문</th>
                </tr>
              </thead>
              <tbody id="secondary-category-tbody">
              	<tr>
              		<td class="no-select">
              			1차 카테고리를 선택해 주세요.
              		</td>
              	</tr>
              </tbody>
            </table>
            <div class="select-primary-category-name"></div>
          </div>
        </div>
      </div>
    </div>
    <script type="text/javascript" src="/admin/js/categoryList.js"></script>
  </body>
</html>

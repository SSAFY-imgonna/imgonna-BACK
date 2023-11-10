<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<link rel="stylesheet" href="/static/assets/css/accompany.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp"%>

  <section class="py-4 text-center container">
    <div class="row">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">동행 글작성</h1>
      </div>
    </div>
  </section>
  
  <div class="album py-2 bg-body-tertiary">
    <div class="container col-11 col-md-8 col-lg-6">
      <div class="">
        <div class="row">
          <div class="card shadow-sm">
            <div class="card-body">
              <!-- Vertical Form -->
              <form id="form-register" class="row g-3" method="post" enctype="multipart/form-data">
	          	<input type="hidden" name="pgno" value="1">
			    <input type="hidden" name="key" value="">
			    <input type="hidden" name="word" value="">              
                <div class="col-12">
                  <label for="title" class="form-label">제목</label>
                  <input type="text" class="form-control" id="title" name="title">
                </div>
                <div class="col-12">
                  <label for="content" class="form-label">내용</label>
                  <textarea class="form-control" style="height: 100px" id="content" name="content"></textarea>
                </div>
                <div class="col-12">
                  <label for="addr" class="form-label">장소</label>
                  <input type="text" class="form-control" id="addr" name="addr">
                </div>
                <div class="col-6">
                  <label for="date" class="form-label">날짜</label>
                  <input type="date" class="form-control" id="date" name="date">
                </div>
                <div class="col-6">
                  <label for="time" class="form-label">시간</label>
                  <input type="time" class="form-control" id="time" name="time">             
                </div>
                <div class="col-12">
                  <label for="limitNum" class="form-label">모집인원</label>
                  <input type="number" class="form-control" min="1" max="10" value='1' id="limitNum" name="limitNum">
                </div>
                <div class="col-12">
                  <label for="upfile" class="form-label">사진</label>
                  <input class="form-control" type="file" id="upfile" name="upfile" >
                </div>
                <div class="col-12">
                  	<label for="status" class="form-label">모집상태</label>
                  	<div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="status" id="inlineRadio1" value="모집중" checked>
					  <label class="form-check-label" for="inlineRadio1">모집중</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="status" id="inlineRadio2" value="모집중단">
					  <label class="form-check-label" for="inlineRadio2">모집중단</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="status" id="inlineRadio3" value="모집종료">
					  <label class="form-check-label" for="inlineRadio3">모집종료</label>
					</div>
					</div>                  
                </div>  
                <div class="col-12">
                	<label for="status" class="form-label">테마분류</label>
					<select class="form-select" id="themeNo" name="themeNo" aria-label="Default select example">
					  <option selected>테마를 선택해주세요</option>
					  <option value="1">가족</option>
					  <option value="2">우정</option>
					  <option value="3">연인</option>
					  <option value="4">혼자</option>
					  <option value="5">MBTI</option>
					</select>            	
                </div>              
                <div class="text-center">
                  <button type="button" id="btn-register" class="btn btn-primary">전송</button>
                  <!-- <button type="reset" class="btn btn-secondary">초기화</button> -->
                  <button type="button" id="btn-list" class="btn btn-secondary">취소</button>
                </div>
              </form>
              <!-- Vertical Form -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <form id="form-param" method="get" action="">
    <input type="hidden" id="pgno" name="pgno" value="${pgno}">
    <input type="hidden" id="key" name="key" value="${key}">
    <input type="hidden" id="word" name="word" value="${word}">
  </form>
  <script>
    document.querySelector("#btn-register").addEventListener("click", function () {
      if (!document.querySelector("#title").value) {
        alert("제목 입력!!");
        return;
      } else if (!document.querySelector("#content").value) {
        alert("내용 입력!!");
        return;
      } else if (!document.querySelector("#addr").value) {
        alert("장소 입력!!");
        return;
      } else if (!document.querySelector("#date").value) {
        alert("날짜 입력!!");
        return;
      } else if (!document.querySelector("#time").value) {
        alert("시간 입력!!");
        return;
      } else if (!document.querySelector("#limitNum").value) {
        alert("모집인원 입력!!");
        return;
      } else if (!document.querySelector("#themeNo").value) {
         alert("테마분류 입력!!");
         return;
       } else {
        let form = document.querySelector("#form-register");
        form.setAttribute("action", "${root}/accompany/write");
        form.submit();
      }
    });
    
    document.querySelector("#btn-list").addEventListener("click", function () {
    	if(confirm("취소를 하시면 작성중인 글은 삭제됩니다.\n취소하시겠습니까?")) {
    		let form = document.querySelector("#form-param");
       		form.setAttribute("action", "${root}/accompany/list");
          	form.submit();
   		}
    });
  </script>  
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
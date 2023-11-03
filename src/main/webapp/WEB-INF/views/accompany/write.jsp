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
              <form id="form-register" class="row g-3" action="" method="post" enctype="multipart/form-data">
	          	<input type="hidden" name="pgno" value="1">
			    <input type="hidden" name="key" value="">
			    <input type="hidden" name="word" value="">              
                <div class="col-12">
                  <label for="accompanyTitle" class="form-label">제목</label>
                  <input type="text" class="form-control" id="accompanyTitle" name="accompanyTitle">
                </div>
                <div class="col-12">
                  <label for="accompanyContent" class="form-label">내용</label>
                  <textarea class="form-control" style="height: 100px" id="accompanyContent" name="accompanyContent"></textarea>
                </div>
                <div class="col-12">
                  <label for="accompanyLoc" class="form-label">장소</label>
                  <input type="text" class="form-control" id="accompanyLoc" name="accompanyLoc">
                </div>
                <div class="col-6">
                  <label for="accompanyDate" class="form-label">날짜</label>
                  <input type="date" class="form-control" id="accompanyDate" name="accompanyDate">
                </div>
                <div class="col-6">
                  <label for="accompanyTime" class="form-label">시간</label>
                  <input type="time" class="form-control" id="accompanyTime" name="accompanyTime">             
                </div>
                <div class="col-12">
                  <label for="accompanyTotal" class="form-label">모집인원</label>
                  <input type="number" class="form-control" min="1" max="10" value='1' id="accompanyTotal" name="accompanyTotal">
                </div>
                <div class="col-12">
                  <label for="accompanyPhoto" class="form-label">사진</label>
                  <input class="form-control" type="file" id="accompanyPhoto" name="accompanyPhoto">
                </div>
                <div class="text-center">
                  <button type="submit" id="btn-register" class="btn btn-primary">전송</button>
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
      if (!document.querySelector("#accompanyTitle").value) {
        alert("제목 입력!!");
        return;
      } else if (!document.querySelector("#accompanyContent").value) {
        alert("내용 입력!!");
        return;
      } else if (!document.querySelector("#accompanyLoc").value) {
        alert("장소 입력!!");
        return;
      } else if (!document.querySelector("#accompanyDate").value) {
        alert("날짜 입력!!");
        return;
      } else if (!document.querySelector("#accompanyTime").value) {
        alert("시간 입력!!");
        return;
      } else if (!document.querySelector("#accompanyTotal").value) {
        alert("모집인원 입력!!");
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
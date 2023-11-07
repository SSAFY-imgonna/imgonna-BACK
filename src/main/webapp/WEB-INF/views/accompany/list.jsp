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
<%-- <c:set var="list" value="${list}"/> --%>
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">동행 구하기</h1>
        <p class="lead text-body-secondary">
       	당신의 여정을 함께할 동행을 찾고 계시나요?<br>
       	외로운 여행을 더 즐겁고 안전하게 만들어 드립니다.<br>
       	다양한 취향과 관심을 공유할 친구를 찾고 싶다면 지금 구해보세요!<br> 
       	새로운 경험과 추억을 만들기 위해 우리와 함께 나아갑시다.<br>
        </p>
        <p class="lead">
       	#동행구하기 #새로운친구 #여행파트너   
        </p>
        <c:if test="${not empty memberDto}">
        <p>
          <a href="${root}/accompany/write" class="btn btn-primary my-2">동행 모집글 작성</a>
        </p>
        </c:if>
      </div>
    </div>
  </section>

  <div class="album py-5 bg-body-tertiary mx-auto">
  	<div class="container mb-4 col-6">
<!--       <div class="offset-3"> -->
        <form class="d-flex" method="GET" id="form-search" action="${root}/accompany/search">
        	<input type="hidden" name="action" value="searchList">
          <select id="keyfield1" class="form-select form-select-sm w-50"
            	aria-label="검색조건" name="keyfield">
            <c:if test="${empty keyfield1 || (not empty keyfield1 && keyfield1 == 'condition')}">
            	<option value="condition" selected>정렬조건</option>
            	<option value="hit">조회수</option>
            	<option value="regDate">작성일순</option>
            </c:if>
            <c:if test="${not empty keyfield1 && keyfield1 == 'hit'}">
            	<option value="condition">정렬조건</option>
            	<option value="hit" selected>조회수</option>
            	<option value="regDate">작성일순</option>
            </c:if>
            <c:if test="${not empty keyfield1 && keyfield1 == 'regDate'}">
            	<option value="condition">정렬조건</option>
            	<option value="hit">조회수</option>
            	<option value="regDate" selected>작성일순</option>
            </c:if>
          </select>
          <select id="keyfield2" class="form-select form-select-sm w-50"
            	aria-label="검색조건" name="keyfield">
            <option value="condition" selected>검색조건</option>
            <option value="title">제목</option>
            <option value="titleContent">제목+내용</option>
          </select>          
          <div class="input-group input-group-sm">
            <input type="text" class="form-control" name="keyword" placeholder="검색어..." />
            <button class="btn btn-dark" type="button" id="search-btn">검색</button>
          </div>
        </form>
<!--       </div> -->
    </div>

    <div class="container">
	  <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
	  	<%-- 제목 길어지거나 하면 truncate 처리해줘야될거같음!!!!!!!!!!!!!!!!!!!!!! --%>
	  	<c:forEach var="accompany" items="${list}">
			<div class="col">
			  <div class="card shadow-sm">
			  	<%-- 저장된 이미지가 없는 경우!!! --%>
			  	<c:if test="${empty accompany.fileInfos}">
					<img src="/static/assets/img/no_image.png" class="card-img-top" alt="...">	
				</c:if>    					
				<%-- 저장된 이미지가 있는 경우!!! --%>
	            <c:if test="${!empty accompany.fileInfos}">
					<c:set var="file" value="${accompany.fileInfos[0]}" />
					<img src="/upload/${file.saveFolder}/${file.saveFile}" class="card-img-top" alt="...">						
				</c:if>    
	            <div class="card-body">
	              <div class="card-text">
	              	<b>${accompany.title}</b>
	              </div>
	              <div class="d-flex align-items-center my-2">
		            <i class="bi bi-geo-alt-fill me-2"></i>${accompany.addr}
	              </div>
	              <div class="d-flex align-items-center my-2">
					<i class="bi bi-calendar2-week me-2"></i>${accompany.joinTime}
				  </div>
	              <div class="d-flex align-items-center my-2">
		            <i class="bi bi-people-fill me-2"></i>${accompany.currentNum} / ${accompany.limitNum}명 (<i class="bi bi-eye-fill me-1"></i>${accompany.hit})
	              </div>
		          <div class="d-flex justify-content-between align-items-center">
		            <div class="btn-group">
		              <button type="button" class="btn btn-sm btn-outline-secondary showDetail" 
		              		onclick="location.href='${root}/accompany/view?accompanyNo=${accompany.accompanyNo}'">자세히 보기</button>
		              <!-- <button type="button" class="btn btn-sm btn-outline-secondary">신청하기</button> -->
		            </div>
		            <div>
		              <c:if test="${sessionScope.memberDto.id == accompany.id}">
				        <span class="text-danger me-2"><b>내가 쓴 글</b></span>
		              </c:if>
			          <span class="text-body-secondary"><i class="bi bi-person-circle"></i> ${accompany.id}</span>		            
		            </div>
		          </div>
	            </div>
	          </div>
	      	</div>	  	
	  	</c:forEach>     	      	      	      	     	      	
      </div>
  	</div>
  </div>
  <%-- 사용자 정의 js --%>
  <script>
  	  var keyfield1 = document.querySelector("#keyfield1");
  	  keyfield1.addEventListener("change", function () {
        location.href = "${root}/accompany?action=sort&keyfield1=" + keyfield1.value;
      });
   </script> 
<%@ include file="/WEB-INF/views/include/footer.jsp"%>	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<link rel="stylesheet" href="/static/assets/css/accompany.css" />
  </head>
  <body>
  <%@ include file="/WEB-INF/views/include/nav.jsp"%>
    <div class="container">
        <div class="row justify-content-center mt-4 mt-lg-5">
          <div class="col-lg-8 col-md-10 col-sm-12">
            <div class="row my-2">
              <h2 class="px-2">${accompanyDto.accompanyTitle}</h2>
            </div>
            <div class="row">
              <hr class="hr-style col-12" size="1" width="100%">
              <div class="col-8 align-content-center">
                <div class="clearfix align-content-center">
                  <img
                    class="avatar me-2 float-md-start bg-light p-2"
                    src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                  />
                  <div>
                    <span class="fw-bold">${accompanyDto.id}</span> <br />
                    <span class="text-secondary fw-light"> 작성일 : ${accompanyDto.regDate}&nbsp;&nbsp;</span>
                    <span><i class="bi bi-eye-fill"></i> ${accompanyDto.hit} </span>
                  </div>
                </div>
              </div>
              <%-- 댓글 개수부분 나중에 동적으로 수정해야!!!!!!!!!!!!!!!!!!!!!! --%>
              <div class="col-4 align-self-center text-end">댓글 : <span id="commentCount">${cnt}</span></div>
              <div class="divider mb-3"></div>
              <hr class="hr-style col-12" size="1" width="100%">
              <div class="divider mb-3"></div>
              <div>
                <%-- 이미지가 있을 때만 보여지게 --%>                
	            <c:if test="${!empty accompanyDto.fileInfos}">
				<div class="mt-3">
					<c:forEach var="file" items="${accompanyDto.fileInfos}">
						<img src="/upload/${file.saveFolder}/${file.saveFile}" alt="..." class="col-8">						
						<%-- <li>${file.originalFile} <a href="${root}/file/download/${file.saveFolder}/${file.originalFile}/${file.saveFile}">[다운로드]</a> --%>
					</c:forEach>
				</div>
				</c:if>                
                <div class="mb-4">
	                <div class="d-flex align-items-center my-2">
		              <i class="bi bi-geo-alt-fill me-2"></i> 장소 : ${accompanyDto.accompanyLoc}
	                </div>
	                <div class="d-flex align-items-center my-2">
					  <i class="bi bi-calendar2-week me-2"></i> 시간 : ${accompanyDto.accompanyDate}
				    </div>
	                <div class="d-flex align-items-center my-2">
		              <i class="bi bi-people-fill me-2"></i> 현재 인원 : ${accompanyDto.accompanyNum} / ${accompanyDto.accompanyTotal}명
	                </div>                                
                </div>
                ${accompanyDto.accompanyContent}
              </div>
              <div class="divider mt-3 mb-3"></div>
              <div class="d-flex justify-content-end">
                <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
                  	글목록
                </button>
              <%-- 로그인 되어있을 때 --%>
              <c:if test="${not empty sessionScope.memberDto}">
              	<%-- 글 작성자일때 --%>
              	<c:if test="${sessionScope.memberDto.id == accompanyDto.id}">
                <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
                  	글수정
                </button>
                <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
                  	글삭제
                </button>
                </c:if>
              	<%-- 글 작성자가 아닐때 --%>
              	<c:if test="${sessionScope.memberDto.id != accompanyDto.id}">
              		<%-- 이미 신청 하였을때 --%>
              		<c:if test="${isJoin == true}">
		                <button type="button" id="btn-join" class="btn btn-outline-secondary mb-3 ms-1"
		                	onclick="location.href='${root}/accompany/joinCancel?accompanyNo=${accompanyDto.accompanyNo}'"
		               		>
		                  	신청취소
		                </button>                
              		</c:if>
              		<%-- 아직 신청하지 않았을때 --%>
              		<c:if test="${isJoin == false}">
              			<%-- 정원 아직 꽉 차지 않았다면 신청하기 버튼 --%>
              			<c:if test="${accompanyDto.accompanyNum != accompanyDto.accompanyTotal}">
			                <button type="button" id="btn-join" class="btn btn-outline-success mb-3 ms-1"
			                	onclick="location.href='${root}/accompany/join?accompanyNo=${accompanyDto.accompanyNo}'">
			                  	신청하기
			                </button>                
		                </c:if>
              			<%-- 정원 꽉 찼으면 모집마감 버튼 --%>
              			<c:if test="${accompanyDto.accompanyNum == accompanyDto.accompanyTotal}">
	              			<button type="button" id="btn-join" class="btn btn-outline-secondary mb-3 ms-1"
	              				onclick="return false;">
			                  	모집마감
			                </button> 
              			</c:if>
              		</c:if>
              	</c:if>
              </c:if>
              </div>
              <hr class="hr-style col-12 mt-3" size="1" width="100%">
              
              <%-- 댓글 폼 시작 --%>
	          <div id="comment-div" class='mt-0'>
	            <div class='mb-2 ps-1'>
	              <span class="comm-title" style="font-size: 15pt">댓글</span>&nbsp; 
	              <span id="comm-first"><span class="letter-count">50/50</span></span>
	            </div>
	            <%-- submit하면  ajax 통신하도록 만듦. accompany_comment.js에서 댓글 등록 부분 참고 --%>
	            <form id="form-comm">
		          <div class="inner-text">
	                <%-- 로그인한 경우 => 댓글 작성 및 submit 가능 --%>
	                <c:if test="${not empty sessionScope.memberDto}">
		                <textarea class="form-control comm-content inner-text" name="commContent" id="commContent"></textarea>
		                <button type="submit" class="btn btn-outline-primary" id="inner-submit"><i class="bi bi-send-fill"></i></button>	                
	                </c:if>
	                <%-- 로그아웃한 경우 => 댓글 작성 및 submit 불가능(disabled) --%>
	                <c:if test="${empty sessionScope.memberDto}">
		                <textarea class="form-control comm-content inner-text" name="commContent" id="commContent" disabled="disabled">로그인 후 작성가능합니다</textarea>
		                <button type="submit" class="btn btn-outline-primary" id="inner-submit"><i class="bi bi-send-fill" style="display:none;"></i></button>	                
	                </c:if>	                
	              </div>
	            </form>   
	          </div>             
              <%-- 댓글 폼 끝 --%> 

              <div class="divider mb-3"></div>
              
              <%-- 댓글 목록 시작 --%>
              <div id="output"></div>
              <%-- 나중에 더보기를 이용한 페이지네이션 처리 + 로딩중 이미지 처리 하면 좋을듯!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --%>
              <div class="paging-button" style="display: none;">
                <input class="btn btn-outline-secondary" type="button" value="더보기">
              </div>
              <div id="loading" style="display: none;">
                <img src="${root}/assets/img/loading.gif" width="50" height="50">
              </div>
              <%-- 댓글 목록 끝 --%>
              
            </div>
          </div>
        </div>
      </div>
      <div id="root-element" data-root="${root}"></div>
      <div id="accompanyNo" data-no="${accompanyDto.accompanyNo}"></div>
      <script>
        document.querySelector("#btn-list").addEventListener("click", function () {
          	location.href = "${root}/accompany/list";
        });
        document.querySelector("#btn-mv-modify").addEventListener("click", function () {
          	location.href = "${root}/accompany/modify?accompanyNo=${accompanyDto.accompanyNo}";
        });
        document.querySelector("#btn-delete").addEventListener("click", function () {
        	if(confirm("정말로 삭제하시겠습니까?")) {
	          	location.href = "${root}/accompany/delete?accompanyNo=${accompanyDto.accompanyNo}";
       		}          
        });
      </script>
      <%-- 댓글 작업과 관련된 js --%>    
	  <script src="$/static/assets/js/accompany_comment.js"></script>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>

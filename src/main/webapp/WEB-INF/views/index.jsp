<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>

	<%-- 페이지만의 내용 --%>
	<section class="banner" id="top">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-8">
					<div class="left-banner-content">
						<div class="text-content">
							<c:if test="${not empty sessionScope.memberDto}">
								<h6>${sessionScope.memberDto.nickname}(${sessionScope.memberDto.id})님
									반갑습니다!</h6>
							</c:if>
							<c:if test="${empty sessionScope.memberDto}">
								<h6>There you go</h6>
							</c:if>
							<div class="line-dec"></div>
							<h1>Enjoy Trip</h1>
							<div class="white-border-button">
								<a href="#" class="scroll-link" data-id="best-offer-section">자세히
									보기</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="right-banner-content">
						<div class="logo">
							<a href="${root}/index.jsp"> </a>
						</div>
						<h3>문의하기</h3>
						<span>이름과 연락처를 남겨주세요</span>
						<div class="line-dec"></div>
						<form>
							<input type='text' class="form-control" placeholder='이름'>
							<br> <input type='tel' class="form-control"
								placeholder='전화번호'> <br> <input type='checkbox'
								class="form-check-input mt-0"> <span> 정보제공동의함 </span> <br>
							<input type='submit' class="btn btn-outline-secondary mt-5"
								value='보내기'>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
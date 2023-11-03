<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid px-5">
		<a class="navbar-brand" href="${root}/"> <i
			class="fa-solid fa-plane-departure"></i> <span id='indexTitle'
			class='fw-bolder'> EnjoyTrip</span>
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<!-- <ul class="navbar-nav ms-auto mb-2 mb-lg-0 nnav"> -->
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 nnav">
				<li class="nav-item"><a class="nav-link"
					href="${root}/attraction?action=view">지역별여행지</a></li>
				<li class="nav-item"><a class="nav-link" href="#">나의여행계획</a></li>
				<li class="nav-item"><a class="nav-link" href="#">핫플자랑하기</a></li>
				<li class="nav-item"><a class="nav-link" href="#">여행정보공유</a></li>
				<li class="nav-item"><a class="nav-link" href="${root}/accompany/list">동행구하기</a></li>
			</ul>
			<ul class="navbar-nav mb-2 mb-lg-0 nnav">
				<c:if test="${empty sessionScope.memberDto}">
					<li class="nav-item" id="func1"><a class="nav-link"
						id='register' data-bs-toggle="modal"
						data-bs-target="#registerModal">회원가입</a></li>
					<li class="nav-item" id="func2"><a class="nav-link"
						data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a></li>
				</c:if>

				<c:if test="${not empty sessionScope.memberDto}">
					<li class="nav-item"><a class="nav-link"
						href="${root}/member/mypage}">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/member/logout">로그아웃</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>

<!-- Modal -->
<!-- 회원가입 -->
<div class="modal fade" id="registerModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">회원가입</h1>
			</div>
			<div class="modal-body">
				<div class="container mt-3">
					<form method="post" name="registerForm" id="registerForm">

						<%-- <div class="input-group mb-3">
							<span class="input-group-text">회원 유형</span> <span class="mx-auto">
								<input class="form-check-input" type="radio" name="role"
								value="general" id="general" checked="checked"> <label
								class="form-check-label" for="general"> 일반 회원 </label> <input
								class="form-check-input" type="radio" name="role" value="admin"
								id="admin"> <label class="form-check-label" for="admin">
									관리자 </label>
							</span>
						</div> --%>

						<div class="input-group mb-3">
							<span class="input-group-text">아이디</span> <input type="text"
								class="form-control" id="registerId" name="registerId">
							<button class="btn btn-outline-secondary" type="button"
								id="memberIdCheck">중복확인</button>
						</div>

						<div id="showMessageElementId" class="mb-3"></div>
						<div class="input-group mb-3">
							<span class="input-group-text">비밀번호</span> <input type="password"
								class="form-control" id="registerPw" name="registerPw">
						</div>

						<div class="input-group mb-3">
							<span class="input-group-text">비밀번호확인</span> <input
								type="password" class="form-control" id="memberPwCheck"
								name="memberPwCheck">
						</div>

						<div id="showMessageElementPw" class="mb-3"></div>

						<div class="input-group mb-3">
							<span class="input-group-text">이름</span> <input type="text"
								class="form-control" id="registerName" name="registerName">
						</div>

						<div class="input-group mb-3">
							<span class="input-group-text">닉네임</span> <input type="text"
								class="form-control" id="registerNickname"
								name="registerNickname">
						</div>

						<div class="input-group mb-3">
							<span class="input-group-text">연락처</span> <input type="text"
								class="form-control" id="registerPhone" name="registerPhone">
						</div>

						<div class="input-group mb-3">
                        <span class="input-group-text">MBTI</span> <input type="text"
                        	class="form-control" id="mbti" name="mbti">
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text">소개글</span>
                            <textarea class="form-control" id="introduction"
                            type="text" maxlength="255" rows="3" cols="30" name="introduction"></textarea>
                        </div>

						<div class="input-group mb-3" id='input-group'>
							<span class="input-group-text">이메일</span> <input type="text"
								class="form-control" placeholder="아이디" id="registerEmail"
								name="registerEmail"> <span class="input-group-text">@</span>
							<select class="form-select" id="registerEmailAdd"
								name="registerEmailAdd">
								<option value='' selected>선택</option>
								<option value="@naver.com">네이버</option>
								<option value="@samsung.com">삼성</option>
								<option value="@google.com">구글</option>
							</select>
						</div>
						<div id="showMessageElementEmail" class="mb-3"></div>

						<!-- <div class="form-check mb-3">
                  <label class="form-check-label">
                      <input class="form-check-input" type="checkbox" name="showMemberPw"> 비밀번호 보이기
                  </label>
              </div> -->

						<!-- <div class="input-group mb-3" id='select-group'>
                <span class="input-group-text">지역</span>
              <select class="form-select">
                  <option selected>시도선택</option>
                  <option value="@naver.com"></option>
                  <option value="@samsung.com">삼성</option>
                  <option value="@google.com">구글</option>
                </select>
              <select class="form-select">
                  <option selected>구군선택</option>
                  <option value="@naver.com">네이버</option>
                  <option value="@samsung.com">삼성</option>
                  <option value="@google.com">구글</option>
                </select>
            </div> -->
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-outline-primary"
					id="registerSubmit" value="회원가입">
				<!-- <input type="reset" class="btn btn-outline-success" value = "초기화" > -->
				<input type="button" class="btn btn-outline-secondary" value="닫기"
					data-bs-dismiss="modal" aria-label="close">
			</div>
		</div>
	</div>
</div>

<!-- 로그인 -->

<div class="modal fade" id="loginModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<h2 class='modal-title fs-5'>로그인</h2>
			</div>

			<div class="modal-body">
				<form action="" method='post' id="loginForm">
					<div class="input-group mb-1" id='input-group'>
						<span class="input-group-text">아이디</span> <input type="text"
							class="form-control" id="loginId" placeholder="아이디를 입력하세요"
							name="loginId" value=${cookie.memberId.value}>
					</div>
					<input type='checkbox' name='saveId' value="checked"> 아이디저장
					<div class="input-group mt-3" id='input-group'>
						<span class="input-group-text ">비밀번호</span> <input type="password"
							class="form-control" id="loginPw" placeholder="비밀번호를 입력하세요"
							name="loginPwd">
					</div>
				</form>
				<div class="modal-footer">
					<button type="button" id="loginSubmit"
						class="btn btn-outline-primary">로그인</button>
					<button type="button" class="btn btn-outline-warning">
						<a style="text-decoration: none;" data-bs-toggle="modal"
							data-bs-target="#findIdModal">아이디찾기</a>
					</button>
					<button type="button" class="btn btn-outline-warning">
						<a style="text-decoration: none;" data-bs-toggle="modal"
							data-bs-target="#findPwModal">비밀번호찾기</a>
					</button>
					<input type="button" class="btn btn-outline-secondary" value="닫기"
						data-bs-dismiss="modal" aria-label="close">
				</div>

			</div>
		</div>
	</div>
</div>


<!-- 아이디 찾기 -->
<div class="modal fade" id="findIdModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<h2 class='modal-title fs-5'>아이디 찾기</h2>
			</div>

			<div class="modal-body">
				<form action="" method='post' id="findIdForm">
					<div class="input-group mb-1" id='input-group'>
						<span class="input-group-text">이메일</span> <input type="text"
							class="form-control" id="findIdEmail" placeholder="이메일을 입력하세요"
							name="email">
					</div>
					<div class="input-group mb-1" id='input-group'>
						<span class="input-group-text">이름</span> <input type="text"
							class="form-control" id="findIdName" placeholder="이름을 입력하세요"
							name="name">
					</div>
					<div id="showMessageElementFindPw" class="mt-5 mb-3"></div>
				</form>
				<div class="modal-footer">
					<button type="button" id="btnFindId"
						class="btn btn-outline-primary">아이디 찾기</button>
					<!-- <button type="button" class="btn btn-outline-success">아이디찾기</button> -->
					<button type="button" class="btn btn-outline-success">
						<a style="text-decoration: none;" data-bs-toggle="modal"
							data-bs-target="#loginModal">로그인</a>
					</button>
					<input type="button" class="btn btn-outline-secondary" value="닫기"
						data-bs-dismiss="modal" aria-label="close">
				</div>

			</div>
		</div>
	</div>
</div>

<!-- 비밀번호 찾기 -->
<div class="modal fade" id="findPwModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<h2 class='modal-title fs-5'>비밀번호 찾기</h2>
			</div>

			<div class="modal-body">
				<form action="" method='post' id="findPasswordForm">
					<div class="input-group mb-1" id='input-group'>
						<span class="input-group-text">아이디</span> <input type="text"
							class="form-control" id="findPwId" placeholder="아이디를 입력하세요"
							name="id">
					</div>
					<div class="input-group mb-1" id='input-group'>
						<span class="input-group-text">이메일</span> <input type="text"
							class="form-control" id="findPwEmail" placeholder="이메일을 입력하세요"
							name="email">
					</div>
					<div class="input-group mb-1" id='input-group'>
						<span class="input-group-text">전화번호</span> <input type="text"
							class="form-control" id="findPwPhone" placeholder="전화번호를 입력하세요"
							name="password">
					</div>
					<div id="showMessageElementFindPw" class="mt-5 mb-3"></div>
				</form>
				<div class="modal-footer">
					<button type="button" id="btnFindPassword" class="btn btn-outline-warning">
						비밀번호 찾기</a>
					</button>
					<!-- <button type="button" class="btn btn-outline-success">아이디찾기</button> -->
					<button type="button" class="btn btn-outline-success">
						<a style="text-decoration: none;" data-bs-toggle="modal"
							data-bs-target="#loginModal">로그인</a>
					</button>
					<input type="button" class="btn btn-outline-secondary" value="닫기"
						data-bs-dismiss="modal" aria-label="close">
				</div>

			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	document.getElementById("loginSubmit").addEventListener("click",
			function() {
				let form = document.querySelector("#loginForm");
				form.setAttribute("action", "${root}/members/login");
				form.submit();
			});
	document.getElementById("registerSubmit").addEventListener("click",
			function() {
				let form = document.querySelector("#registerForm");
				form.setAttribute("action", "${root}/members/regist");
				form.submit();
			});
	document.getElementById("btnFindId").addEventListener("click",
			function() {
				let form = document.querySelector("#findIdForm");
				form.setAttribute("action", "${root}/members/find/id");
				form.submit();
			});
	document.getElementById("btnFindPassword").addEventListener("click",
			function() {
				let form = document.querySelector("#findPasswordForm");
				form.setAttribute("action", "${root}/members/find/pw");
				form.submit();
			});
	
</script>
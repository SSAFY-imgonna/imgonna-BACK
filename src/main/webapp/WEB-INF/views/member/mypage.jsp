<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
</head>
  <body class="bg-light">
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>


    <div class="container">
      <div class="py-5 text-center">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="100"
          height="100"
          fill="currentColor"
          class="bi bi-person-circle"
          viewBox="0 0 16 16"
        >
          <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
          <path
            fill-rule="evenodd"
            d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"
          />
        </svg>
        <h2 class="m-3">내 정보 조회</h2>

        <div class="d-flex justify-content-center align-items-center">
          <div class="order-md-1 mt-3">
            <form action="#" method="post" id="editForm">
             
              <input type="hidden" name="action" value="modify">
              <div class="form-floating mb-3">
                              <input
                                type="text"
                                class="form-control"
                                id="id"
                                name="id"
                                value="${member.id}"
                               readonly
                              />
                              <label for="id">아이디</label>
                            </div>

              <div class="form-floating mb-3">
                <input type="text" class="form-control" id="name" name="name" value="${member.name}"/>
                <label for="name" >이름</label>
              </div>
              
               <div class="form-floating mb-3">
                <input type="text" class="form-control" id="nickname" name="nickname" value="${member.nickname}"/>
                <label for="nickname" >닉네임</label>
              </div>
              <div class="form-floating mb-3">
                <input type="text" class="form-control" id="mbti" name="mbti" value="${member.mbti}"/>
                                <label for="mbti" >mbti</label>
              </div>
              

              <!-- <div class="form-floating mb-3">
                <input
                  type="password"
                  class="form-control"
                  id="memberPw"
                  name="memberPw"
                />
                <label for="memberPw">비밀번호</label>
              </div>

              <div class="form-floating mb-3">
                <input
                  type="password"
                  class="form-control"
                  id="memberPwCheck"
                  name="memberPwCheck"
                />
                <label for="memberPwCheck">비밀번호확인</label>
              </div> -->

              <div id="showMessageElementPw" class="mb-3"></div>

              <div class="input-group mb-3">
                <span class="input-group-text">연락처</span>
                <input
                  type="text"
                  class="form-control"
                  id="phone"
                  name="phone"
                  value="${member.phone}"
                />
              </div>



<div class="input-group mb-3" id="input-group">
                <span class="input-group-text">이메일</span>
                <input
                  type="text"
                  class="form-control"
                  placeholder="아이디"
                  id="email"
                  name="email"
                  readonly
                />
                <span class="input-group-text">@</span>
                <select class="form-select" id="emailAdd" name="emailAdd">
                  <option value='' selected>선택</option>
				<option value="@naver.com">네이버</option>
				<option value="@samsung.com">삼성</option>
				<option value="@google.com">구글</option>
                </select>
              </div>

<div class="input-group mb-3">
                            <span class="input-group-text">소개글</span>
                            <textarea class="form-control" id="introduction"
                            type="text" maxlength="255" rows="3" cols="30" name="introduction">${member.introduction}</textarea>
                        </div>
              <div>
                <button
                  type="button"
                  class="btn btn-outline-success"
                  id="editSubmit"
                >
                  수정
                </button>
                <button type="button" class="btn btn-outline-warning"
                >                   <a style="text-decoration: none;" data-bs-toggle="modal"
								data-bs-target="#changePwdModal">비밀번호변경</a>  </button>
                <button type="button" class="btn btn-outline-danger"> 
                  <a style="text-decoration: none;" data-bs-toggle="modal"
								data-bs-target="#leaveModal">회원탈퇴</a> </button>
              </div>
              <!-- <div class="form-check mb-3">
                          <label class="form-check-label">
                              <input class="form-check-input" type="checkbox" name="showMemberPw"> 비밀번호 보이기
                          </label>
                      </div> -->
            </form>
          </div>
        </div>

      </div>
    </div>
    
    <!-- 탈퇴 시 비밀번호 입력  -->
	<div class="modal fade" id="leaveModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h2 class='modal-title fs-5'>회원 탈퇴 </h2>
				</div>

				<div class="modal-body">
					<form action="" method='post' id="leaveForm">
					 <input type="hidden" name="action" value="delete">
						<div class="input-group mb-1" id='input-group'>
							<span class="input-group-text">비밀번호 </span> <input type="password"
								class="form-control" id=leaveConfirmPwd" placeholder="비밀번호를 입력하세요"
								name="leaveConfirmPwd">
						</div>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-outline-danger" id="btnLeave">탈퇴
						</button>
						<input type="button" class="btn btn-outline-secondary" value="닫기"
							data-bs-dismiss="modal" aria-label="close">
					</div>

				</div>
			</div>
		</div>
	</div>
    <!-- 비밀번호 변경 모달  -->
	<div class="modal fade" id="changePwdModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h2 class='modal-title fs-5'>비밀번호 변경 </h2>
				</div>

				<div class="modal-body">
					<form action="" method='post' id="modifyPwdForm">
					 <input type="hidden" name="action" value="modifyPassword">
						<div class="input-group mb-1" id='input-group'>
							<span class="input-group-text"> 기존 비밀번호 </span> <input type="password"
								class="form-control" id="oldPassword" placeholder="기존 비밀번호를 입력하세요"
								name="oldPassword" required>
						</div>
						<div class="input-group mb-1" id='input-group'>
							<span class="input-group-text"> 변경할 비밀번호 </span> <input type="password"
								class="form-control" id="newPassword" placeholder="변경할 비밀번호를 입력하세요"
								name="newPassword" required>
						</div>
                        <p id="showRes" style="color:red"></p>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-outline-danger" id="btnModifyPassword">변경
						</button>
						<input type="button" class="btn btn-outline-secondary" value="닫기"
							data-bs-dismiss="modal" aria-label="close">
					</div>

				</div>
			</div>
		</div>
	</div>
    
    <script>
    
    
     /*  <!-- 회원 기존 데이터 화면에 뿌려주기 -->
      let localUser = window.localStorage.getItem("user");
      console.log(localUser); */
      
      // 마이페이지 창이 열렸을 때
      window.onload = () => {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
        document.querySelector("#email").value = "${fn:split(member.email,'@')[0]}";

        const el = document.getElementById("emailAdd");
        const len = el.options.length;
        const str = "@${fn:split(member.email,'@')[1]}";

        for (let i = 0; i < len; i++) {
          if (el.options[i].value == str) {
            el.options[i].selected = true;
          }
        }
      }; 

      // 수정 버튼 클릭 시 
      document.getElementById("editSubmit").addEventListener("click",
				function() {
					let form = document.querySelector("#editForm");
					form.setAttribute("action", "${root}/members/modify");
					form.submit();
				});
      
      // 비밀번호 변경 클릭 시 
      document.getElementById("btnModifyPassword").addEventListener("click",
				function() {
    	  if (!document.querySelector("#oldPassword").value) {
            alert("기존 비밀번호를 입력해주세요");
            return;
    	  }
    	  else if (!document.querySelector("#newPassword").value) {
            alert("변경할 비밀번호를 입력해주세요");
            return;
		  } else {
    	    let form = document.querySelector("#modifyPwdForm");
			form.setAttribute("action", "${root}/members/modify/pw");
			form.submit();
		    }
		  });
      
      // 회원 탈퇴 클릭 시 
      document.getElementById("btnLeave").addEventListener("click",
				function() {
    	  let form = document.querySelector("#leaveForm");
			form.setAttribute("action", "${root}/members/delete");
			form.submit();
				});  
      

      
    </script>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
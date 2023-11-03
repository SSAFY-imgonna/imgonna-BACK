let localUser = window.localStorage.getItem("user"); 
console.log(localUser);
    
if (localUser !== null) {
    let func1Element = document.getElementById('func1');
    func1Element.innerHTML = 
    `<a class='nav-link' onclick="logout()">로그아웃</a>`;
    let func2Element = document.getElementById('func2');
    func2Element.innerHTML = 
    `<a class='nav-link' href="../mypage.html">마이페이지</a>`;
} else {
    let func1Element = document.getElementById('func1');
    func1Element.innerHTML = 
    `<a class="nav-link" id='register' data-bs-toggle="modal" data-bs-target="#registerModal">회원가입</a>`;
    let func2Element = document.getElementById('func2');
    func2Element.innerHTML = 
    `<a class="nav-link" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>`;
}

function logout() {

    window.localStorage.removeItem("user");
    location.href = 'index.html';
    alert("로그아웃 성공!");

}
// data-root 속성을 사용하여 root 값을 읽어옴
let root = document.getElementById('root-element').getAttribute('data-root');

/* 댓글 목록 */
let outputDiv = document.querySelector("#output");
let accompanyNo = document.querySelector("#accompanyNo").getAttribute('data-no');

function selectList(){
	//로딩 이미지 노출
	document.querySelector('#loading').style.display='';
	
	// ajax 통신 (댓글 개수 뽑아오기)
	let url = root + "/accompany?action=commentCount&accompanyNo=" + accompanyNo; 
	fetch(url)
	.then((response) => response.json())
	.then((data) => {
		// 현재 상세보기의 댓글 개수(#commentCount)을 찾음
		let commentCount = document.querySelector('#commentCount');
		if (commentCount) {
			console.log(commentCount);
			
			let cnt = data && data.cnt;
			
			if(typeof cnt !== 'undefined') {
				// commentCount의 innerHTML 내용을 변경
				console.log(data.cnt);
				commentCount.innerHTML = data.cnt;			  
			}
		}		
	});

	// ajax 통신 (댓글 목록 가져오기)
	url = root + "/accompany?action=commentList&accompanyNo=" + accompanyNo; 
	fetch(url)
	.then((response) => response.json())
	.then((data) => resultViewJSON(data));
}

function resultViewJSON(data) {
	// outputDiv 문서 객체를 초기화
	outputDiv.innerHTML = '';

	// 로딩 이미지 감추기
	document.querySelector('#loading').style.display='none';
	
	// 받은 데이터 View에 보여줌
	for(let idx=0; idx<data.list.length; idx++) {
		let output = '<div class="item">';
		output += '<b><h5>' + data.list[idx].id + '</h5></b>';
		output += '<div class="sub-item">';
		output += '<div class="mb-2 mContent">' + data.list[idx].commentContent + '</div>';
		output += '<span class="reg-date">' + data.list[idx].regDate + '</span>';
		
		// 로그인한 회원과 댓글 작성자의 회원번호 일치한다면 => 수정과 삭제 버튼 보이게
		if(data.id != null && data.id == data.list[idx].id) {
			output += '<div style="float:right; margin-bottom:10px;">';
			output += '<button type="button" class="modify-btn btn btn-outline-primary me-1" data-no="'+data.list[idx].commentNo+'">수정</button>';
			output += '<button type="button" class="delete-btn btn btn-outline-danger" data-no="'+data.list[idx].commentNo+'">삭제</button>';
			output += '</div>';
		}
		output += '<hr size="1" noshade="noshade" width="100%">';
		output += '</div>';
		output += '</div>';
		
		// outputDiv 문서 객체에 추가
		outputDiv.innerHTML += output;
	}
}

//초기 데이터(목록) 호출
selectList();

/* 댓글 등록 */
let form = document.querySelector('#form-comm');
let content = document.querySelector('#commContent');

form.addEventListener("submit", function() {
	// 기본 이벤트(페이지 새로고침) 제거
	event.preventDefault();
	
	// 내용 입력되지 않았을 경우 => alert 창 띄움!!
	if(content.value.trim()==''){
		alert('내용을 입력하세요!');
		initForm();
		content.focus();
		return false;
	}
	
	// 폼에서 입력받은 데이터를 JSON 객체로 만듦
	let data = {
		commentContent: content.value
	};
	
	// ajax 통신
	let url = root + "/accompany?action=commentAdd&accompanyNo=" + accompanyNo; 
	fetch(url, {
		method: "POST",
		headers: {'Content-Type' : 'application/json'},
		body: JSON.stringify(data) // JSON 객체를 문자열로 변환 -> 본문으로 설정
	})
	.then((response) => response.json())
	.then(initForm) // 폼 초기화
	.then(selectList); // 새로 추가된 댓글을 포함해서 댓글 목록 다시 호출
});

//댓글 작성 폼 초기화
function initForm(){
	content.value = '';
	// 글자수도 초기화
	//$('#re_first .letter-count').text('50/50');
}

/* 댓글 수정 */
// 1) 수정 버튼 클릭시 댓글 수정 폼 보여짐(SELECT)
// document.querySelectorAll을 사용하면 초기 페이지 로딩 시에만 존재하는 요소만 선택 가능 (동적으로 생성된 요소는 x)
// => 동적으로 생성된 요소의 부모 요소 중 동적으로 생성되지 않은 요소에 이벤트를 바인딩하여 해결!!
const parent = document.querySelector('#output');
// 클릭 이벤트를 부모 요소에 추가하고 
parent.addEventListener('click', function(event) {
  // 클릭된 요소가 '.modify-btn' 클래스를 가진 요소인 경우에만 처리함
  if (event.target.classList.contains('modify-btn')) {
	  // 댓글 번호
	  let commentNo = event.target.getAttribute('data-no');

	  // 댓글 내용
	  // 클릭된 수정 버튼 요소를 기준으로 가장 가까운 상위 '.sub-item' 요소를 찾음
	  let subItem = event.target.closest('.sub-item');
	  // 해당 'sub-item' 요소 내의 가장 첫 번째 'div' 요소(commentContent가 있음)를 찾음
	  let divElement = subItem.querySelector('div');
	  // 해당 'div' 요소의 내용을 가져오고, <br> 태그를 개행 문자로 변환함. g:지정문자열 모두, i:대소문자 구별없이 검색
	  let mCommContent = divElement.innerHTML.replace(/<br>/gi, '\n');
	  
	  // 댓글 수정폼UI(동적으로 생성) => 아래에서 '.item' 요소에 붙힘(139라인)
	  let modifyUI = '<form id="form-mcomm" class="form-mcomm" method="POST">';
	  modifyUI += '<input type="hidden" name="action" id="action" value="commentModify">';
	  modifyUI += '<input type="hidden" name="commentNo" id="commentNo" value="'+commentNo+'">';
	  modifyUI += '<div id="comm-first"><span class="letter-count">50/50</span></div>';
	  
	  modifyUI += '<div class="inner-text">';
	  modifyUI += '<textarea class="form-control mcomm-content inner-text" name="commentContent" id="commentContent">'+mCommContent+'</textarea>';
	  modifyUI += '</div>';
	  
	  modifyUI += '<div id="comm-second" class="align-right">';
	  modifyUI += '<div style="float:right; margin-bottom:10px;">';
	  modifyUI += '<button type="submit" class="comm-modify btn btn-outline-primary me-1">수정</button>';
	  modifyUI += '<button type="button" class="comm-reset btn btn-outline-secondary">취소</button>';
	  modifyUI += '</div>';
	  modifyUI += '</div>';
	  modifyUI += '<hr size="1" noshade width="100%">';
	  modifyUI += '</form>';
	  
	  // 이전에 이미 수정중인(수정폼이 활성화된) 댓글이 있을 경우 => 다시 숨긴 sub-item(136라인)을 보여지게 하고, 수정폼을 초기화함
	  initModifyForm();
	  
	  // 클릭된 수정 버튼을 감싸고 있는 댓글 목록의 데이터 감추기
	  subItem.style.display='none';
	  
	  // 클릭된 수정 버튼 요소를 기준으로 가장 가까운 상위 '.item' 요소를 찾음
	  let item = event.target.closest('.item');
	  // 해당 'item' 요소에 동적으로 생성한 댓글 수정폼 UI를 노출함
	  item.innerHTML += modifyUI;
	  
	  //입력한 글자수 셋팅!!!!!!!!!!!!!!!!나중에 필요
  }
});

// 수정폼에서 취소 버튼 클릭시 수정폼 초기화
//클릭 이벤트를 부모 요소에 추가하고 
parent.addEventListener('click', function(event) {
	// 클릭된 요소가 '.comm-reset' 클래스를 가진 요소인 경우에만 처리함
	if (event.target.classList.contains('comm-reset')) {
		initModifyForm();
	}
});

// 이미 수정중인(수정폼이 활성화된) 댓글이 있을 경우 댓글 수정폼 초기화
function initModifyForm(){
	// 숨긴 sub-item을 보여지게 함
	let subItems = document.querySelectorAll('.sub-item');
	// for~in은 객체의 열거 가능한 속성을 반복함   vs  for~of(또는 forEach 메서드)는 배열 요소를 반복함
	subItems.forEach(item =>{
		item.style.display=''; // 댓글을 보이게 설정
	});

	// 열려있는 해당 수정폼을 제거함 
	const mCommForm = document.querySelector("#form-mcomm");
	if(mCommForm) {
		mCommForm.parentNode.removeChild(mCommForm);
	}
}

// 2) 수정 버튼 클릭시 수정 작업(UPDATE)
parent.addEventListener('submit', function(event) {
    // 이벤트가 발생한 요소가 폼인지 확인
    if (event.target && event.target.id === 'form-mcomm') {
	    // 기본 이벤트(페이지 새로고침) 제거
	    event.preventDefault();

    // 폼 데이터 가져오기
    const formData = new FormData(event.target);
    let mContent = formData.get('commentContent');
    let commetnNo = formData.get('commentNo');
    
	// 내용 입력되지 않았을 경우 => alert 창 띄움!!
	if(mContent.trim()=== ''){
		alert('내용을 입력하세요!');
		mContent.value = '';
		mContent.focus();
		return false;
	}
	
    // 폼에서 입력받은 데이터를 JSON 객체로 만듦
	let data = {
		commentContent: mContent,
	};
	
	// ajax 통신
	let url = root + "/accompany?action=commentModify&commentNo=" +commentNo.value; 
	console.log(url);
	fetch(url, {
		method: "POST",
		headers: {'Content-Type' : 'application/json'},
		body: JSON.stringify(data) // JSON 객체를 문자열로 변환 -> 본문으로 설정
	})
	.then((response) => {
		if(!response.ok) {
			throw new Error('댓글 수정에 실패했습니다.');
		}
		// 수정이 성공적으로 완료되었을때의 처리
		response.json();
	})
	.then((data) => {
		// 현재 작성중인 댓글 수정폼(#form-mcomm)을 찾음
		let mreForm = document.querySelector('#form-mcomm');
		if (mreForm) {
		  // 현재 작성중인 댓글 수정폼(#form-mcomm)의 부모 요소 중에서 .mContent인거 찾음
		  let parent = mreForm.parentNode;
		  let pElement = parent.querySelector('.mContent');

		  // mContent 요소의 값을 가져와서 HTML 태그 처리
		  let mContent = document.querySelector('#commentContent').value;
		  mContent = mContent.replace(/</g, '&lt;');

		  // .mContent의 내용을 변경
		  pElement.innerHTML = mContent;
		}
	  }
	) // 수정된 내용 목록에 보여줌
	.then(initModifyForm) // 수정폼 삭제 및 초기화
	.catch(error => {
		console.error(error);
	})
  }	
});

/* 댓글 삭제 */
parent.addEventListener('click', function(event) {
	if (event.target.classList.contains('delete-btn')) {
		// 댓글 번호
		let commentNo = event.target.getAttribute('data-no');
		
		// ajax 통신
		let url = root + "/accompany?action=commentDelete&commentNo=" + commentNo; 
		fetch(url)
		.then((response) => {
			if(!response.ok) {
				throw new Error('댓글 삭제에 실패했습니다.');
			}
			// 수정이 성공적으로 완료되었을때의 처리
			alert('댓글 삭제 완료');
			response.json();
		})		
		.then(selectList); // 새로 추가된 댓글을 포함해서 댓글 목록 다시 호출
	}
});












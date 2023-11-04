<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<style>
#attraction_div {
	white-space: nowrap;
	overflow-x: auto;
	text-align: center
}

</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>

	<!-- 중앙 section start -->
	<section>
		<div class="container px-4 px-lg-5">
			<div class="col">
				<div class="alert alert-primary mt-3 text-center fw-bold"
					role="alert">전국 관광지 정보</div>
				<!-- 관광지 검색 start -->
				<form class="d-flex my-3" onsubmit="return false;" role="search">
					<select id="sido" class="form-select me-2">
						<option value="0" selected>검색 할 지역 선택</option>
						<c:forEach var="sido" items="${sidoList}">
							<option value="${sido.sidoCode}">${sido.sidoName}</option>
						</c:forEach>
					</select> <select id="gugun" class="form-select me-2">
						<option value="0" selected>검색 할 시군구 선택</option>
					</select> <select id="search-content-id" class="form-select me-2">
						<option value="0" selected>컨텐츠 선택</option>
						<option value="12">관광지</option>
						<option value="14">문화시설</option>
						<option value="15">축제공연행사</option>
						<option value="25">여행코스</option>
						<option value="28">레포츠</option>
						<option value="32">숙박</option>
						<option value="38">쇼핑</option>
						<option value="39">음식점</option>
					</select>
					<!-- <input
              id="search-keyword"
              class="form-control me-2"
              type="search"
              placeholder="검색어"
              aria-label="검색어"
            /> -->
					<button id="btn-search" class="btn btn-outline-success"
						type="button">검색</button>
				</form>
				<!-- kakao map start -->
				<div class="d-flex flex-row" id="map_attraction_div">
					<div id="map" class="mt-3 ml-5 p-2" style="width: 80%; height: 500px"></div>
					<!-- kakao map end -->
					<!-- 관광지 검색 end -->
					
				</div>
			</div>
		</div>
	</section>
	<!-- 중앙 section end -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3eab89cd66f75d0e804d4145a5ad7a27&libraries=services,clusterer,drawing"></script>
	<script type="text/javascript">
	
	let sel = document.querySelector("#gugun");

    // 시도가 바뀌면 구군정보 얻기.
    document.querySelector("#sido").addEventListener("change", function () {
       let sidoCode = this[this.selectedIndex].value;
       let url = `${root}/attraction/gugun?sidoCode=`+sidoCode;
       console.log(url);
       fetch(url, { method: "GET" })
         .then((response) => response.json())
         .then((data) => optionJSON(data));
    });
    
    function optionJSON(data) {

    	sel.innerHTML = `<option value="0" selected>검색 할 시군구 선택</option>`;
    	data.forEach((gugun) => {
    		let opt = document.createElement("option");
    		opt.setAttribute("value", gugun.gugunCode);
    		opt.appendChild(document.createTextNode(gugun.gugunName));
    		sel.appendChild(opt);
    	})
      }

    // 검색 버튼을 누르면..
    // 지역, 유형, 검색어 얻기.
    // 위 데이터를 가지고 공공데이터에 요청.
    // 받은 데이터를 이용하여 화면 구성.
    document.getElementById("btn-search").addEventListener("click", () => {
      let baseUrl = `https://apis.data.go.kr/B551011/KorService1/`;
      // let searchUrl = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
      // let searchUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

      let queryString = `serviceKey=${serviceKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
      let areaCode = document.getElementById("sido").value;
      let gugunCode = document.getElementById("gugun").value;
      let contentTypeId = document.getElementById("search-content-id").value;
      
      let url = "${root}/attraction/list?sidoCode=" + areaCode  + "&gugunCode=" + gugunCode + "&contentTypeId=" + contentTypeId;

      fetch(url)
        .then((response) => response.json())
        .then((data) => makeList(data));
    });

    var positions; // marker 배열.
    function makeList(data) {
      positions = [];
      attractionList = [];
      data.forEach((area) => {
    	  let markerInfo = {
    			  title: area.title,
    	          latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
    	          addr: area.addr1,
    	          image: area.firstImage,
    	          zipcode: area.zipcode,
    	  };
    	  
    	  let listInfo = {
    			  title: area.title,
    			  addr: area.addr1,
    			  tel: area.tel,
    	  }
    	  attractionList.push(listInfo);
	      positions.push(markerInfo);
      console.log(listInfo);
      })
      // displayList();
      displayMarker();
    }

    // 카카오지도
    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
      mapOption = {
        center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
        level: 5, // 지도의 확대 레벨
      };

    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    let markerList = [];
    let overlayList = [];

    function displayList() {
    	let div = document.querySelector("#map_attraction_div");
    	
    	let divHTML = '<div class="mt-3 p-2" style="width: 40%; height: 500px" id="attraction_div">'
						+'<table class="table table-hover" id="attraction_table" style="width: 1000px">'
						+'	<thead>'
						+ '		<tr class="text-center">'
						+ '			<th scope="col">관광지명</th>'
						+ '			<th scope="col">도로명주소</th>'
						+ '			<th scope="col">전화번호</th>'
						+ '		</tr>'
						+ '	</thead>'
						+ '</table>'
						+'</div>' ;	
    	
		div.innerHTML += divHTML;
	
    	attractionList.forEach((attraction) => {
    		let title = attraction.title;
    		let addr = attraction.addr;
    		let tel = attraction.tel;
    		
    		if(!tel) {
    			tel = "정보 없음";
    		}
    		
    		let table = document.querySelector("#attraction_table");
    		table.innerHTML += '<tbody id="attraction_tbody" ></tbody>';
    		let tr = '';
    		if(attractionList.length!=0) {
    		tr += '<td>'+title+'</td>' 
    		+'<td>'+addr+'</td>'
    		+'<td>'+tel+'</td>';
    		} else {
        		tr += '<td colspan=3>검색 결과가 없습니다</td>' ;
    		}
    		document.querySelector("#attraction_tbody").innerHTML += tr;
    	});
    }
    
    function displayMarker() {
      markerList.forEach((marker) => {
        marker.setMap(null);
      });
      markerList = [];
      overlayList.forEach((overlay) => {
        overlay.setMap(null);
      });
      overlayList = [];

      // 마커 이미지의 이미지 주소입니다
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });

        markerList.push(marker);

        // 커스텀 오버레이에 표시할 컨텐츠 입니다
        // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
        // 별도의 이벤트 메소드를 제공하지 않습니다
        let title = positions[i].title;
        let image = "./assets/img/no_image.png";
        if (positions[i].image) {
        	image = positions[i].image;
        }
        let addr = positions[i].addr;
        let zipcode = positions[i].zipcode;
        var content =
          '<div class="wrap">' +
          '    <div class="info">' +
          '        <div class="title">' +
          '            ' + title +
          "        </div>" +
          '        <div class="body">' +
          '            <div class="img">' +
          '                <img src="' + image + '" width="73" height="70">' +
          "           </div>" +
          '            <div class="desc">' +
          '                <div class="ellipsis">' + addr + '</div>' +
          '                <div class="jibun ellipsis">(우) ' + zipcode + '</div>' +
          "            </div>" +
          "        </div>" +
          "    </div>" +
          "</div>";

        // 마커에 표시할 인포윈도우를 생성합니다
        var overlay = new kakao.maps.CustomOverlay({
          content: content, // 인포윈도우에 표시할 내용
          map: map, // 마커를 표시할 지도
          position: marker.getPosition(),
        });

        overlay.setMap(null);

        overlayList.push(overlay);

        kakao.maps.event.addListener(marker, "mouseover", makeOverListener(map, marker, overlay));
        kakao.maps.event.addListener(marker, "mouseout", makeOutListener(overlay));

        // 커스텀오버레이를 표시하는 함수입니다
        function makeOverListener(map, marker, overlay) {
          return function () {
            overlay.setMap(map);
          };
        }

        // 커스텀오버레이를 닫는 함수입니다
        function makeOutListener(overlay) {
          return function () {
            overlay.setMap(null);
          };
        }
      }

      // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
      map.setCenter(positions[0].latlng);
    }

    function moveCenter(lat, lng) {
      map.setCenter(new kakao.maps.LatLng(lat, lng));
    }
  </script>
  <%@ include file="/WEB-INF/views/include/footer.jsp"%>
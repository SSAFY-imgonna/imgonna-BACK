# ✈ Enjoy Trip Project [Algorithm PJT] 
  
## method name convention
```
- [create]: create도메인(Dto);
- [read]
    - 전체: get도메인List(Map);
    - 단건: get도메인By프로퍼티명(프로퍼티);
- [update]: modify도메인(Dto);
- [delete]: delete도메인(pk);

* service, dao 메서드명 동일
```
<hr>
  
## commit convention
```
- [feat] : 새로운 기능 추가
- [fix] : 버그 수정
- [docs] : 문서 수정
- [style] : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- [refactor] : 코드 리펙토링
- [test] : 테스트 코드, 리펙토링 테스트 코드 추가
- [chore] : 빌드 업무 수정, 패키지 매니저 수정
```
  
## 프로젝트 개요
사용자에게 한국의 다양한 관광지, 먹거리, 축제, 행사 등을 소개하여 지역 관광 활성화를 위한 지역 관광 소개 페이지를 구축하려고 한다. 한국관광공사에서 제공하는 국문관광정보서비스_GW의 다양한 상세기능정보 API를 활용하여 지역별 관광지 data를 분석하고 화면에 표시한다. 또한 여행계획을 위한 스케줄과 여행경로 공유 등 사용자 편의 기능을 구현하고 나만의 숨은 관광지를 소개하는 페이지와 자유롭게 토론이 가능한 게시판 등을 구현해 본다. 이번 관통 프로젝트는 EnjoyTrip 프로젝트의 Back-End 부분을 작성한다. MVC 기반의 웹 프로젝트를 설계하고 구현.

  - GitLab을 활용하여 소스 관리
  - [공공데이터 포털의 한국관광공사_국문관광정보 서비스_GW 데이터](https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15101578)를 이용

### 팀원 
  서지현, 이하늬

### 진행일자
  2023.11.03

### 개발언어/프로그램
 - `HTML` `CSS` `JavaScript` `VS Code`
 - `Java` `Eclipse` `Tomcat` `MySQL`

### 기능
  `지역별`
  - 지역별 관광지 목록 전체 조회
  - 지역별 관광지 이름으로 검색

  `회원`
  - 등급: 일반회원, 관리자회원
  - 회원가입
  - 로그인
  - 회원 정보 수정
  - 회원 탈퇴

  `게시판`
  - 게시글 타입: 일반 글, 공지사항 글
  - 게시글 등록
  - 게시글 목록 전체 조회
  - 게시글 제목으로 검색
  - 게시글 번호로 조회
  - 게시글 수정
  - 게시글 삭제


## ERD

## 💻 실행 화면

<hr>

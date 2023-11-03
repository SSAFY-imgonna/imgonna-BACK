<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Enjoy Trip</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="/static/assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" />
<link href="/static/assets/css/styles.css" rel="stylesheet" />
<link href="/static/assets/css/indexstyles.css" rel="stylesheet" />
<link href="/static/assets/css/map.css" rel="stylesheet" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Roboto+Mono&display=swap" rel="stylesheet">
<link
  href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@500&family=Nanum+Gothic+Coding&display=swap"
  rel="stylesheet">
<script src="/static/assets/js/key.js"></script>
<script src="https://example.com/fontawesome/v6.4.0/js/all.js" data-auto-replace-svg="nest"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<%-- request 객체에 msg가 들어있을 때 해당 내용 알림창 띄우기 --%>
<script>
	<c:if test="${!empty msg}">
		alert("${msg}");
	</c:if>
</script>
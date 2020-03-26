<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ='/WEB-INF/views/includes/header.jsp'/>
		<div id="content">
			<div id="user">
			<c:choose>
				<c:when test="${bool eq false}">
					<p class="jr-success">
						성공적으로 수정되었습니다.
						<br><br>
						<a href="${pageContext.request.contextPath }">메인으로</a>
					</p>
				</c:when>
				<c:otherwise>
					<p class="jr-success">
						회원가입을 축하합니다.
						<br><br>
						<a href="${pageContext.request.contextPath }/user/login">로그인하기</a>
				</c:otherwise>
			</c:choose>			
			</div>
		</div>
		<c:import url ='/WEB-INF/views/includes/navigation.jsp'/>
		<c:import url ='/WEB-INF/views/includes/footer.jsp'/>
	</div>
</body>
</html>
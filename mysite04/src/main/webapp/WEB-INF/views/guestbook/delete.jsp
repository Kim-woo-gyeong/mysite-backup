<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="${pageContext.request.contextPath }/guestbook/delete">
					<input type='hidden' name="no" value="${no }">
					
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="submit" value="확인" onclick="return delchk();">
				</form>
				<a href="${pageContext.request.contextPath }/guestbook">방명록 리스트</a>
			</div>
			<script type="text/javascript">
				function delchk() {
					return confirm("삭제하시겠습니까?");
				}
			</script>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
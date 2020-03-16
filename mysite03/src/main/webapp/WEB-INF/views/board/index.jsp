<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="get">
					<input type="text" id="kwd" name="kwd" value="${kwd }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var='listcount' value='${fn:length(list) }'/>
					<c:forEach var='vo' items='${list }' varStatus='status'>
					<tr>
						<td>${total-(5*(currentPage))-status.index }</td>
						<td style="text-align:left; padding-left:${20*vo.depth }px">
						
						<c:if test="${vo.depth > 0 }">
							<img src ='${pageContext.request.contextPath }/assets/images/reply.png'>	
						</c:if>
						
						<a href="${pageContext.request.contextPath }/board/view/${vo.no }">${vo.title }</a></td>
						<td>${vo.userName }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						
						<td>
							<c:if test="${authUser.no == vo.userNo }">
								<a href="${pageContext.request.contextPath }/board/delete/${vo.no }" class="del" onclick="return delchk();">삭제</a>
								<script type="text/javascript">
									function delchk() {
									return confirm("삭제하시겠습니까?");
								}
								</script>
							</c:if>
						</td>
					</tr>
					</c:forEach>				
				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
					
					<c:choose>
						<c:when test="${currentPage == 0 }">
							<li style = "color:lightgrey;">◀</li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }/board?p=${prePage}&&kwd=${kwd}">◀</a></li>
						</c:otherwise>
					</c:choose>
					
					<c:forEach var="i" begin="${firstPage }" end="${endPage }" step="1">
						<c:choose>
							<c:when test="${currentPage == i }">
								<li style = "color:pink;" class="selected" >${i+1 }</li> 
							</c:when>
							<c:when test="${max >= i }">
								<li><a href="${pageContext.request.contextPath }/board?p=${i }&&kwd=${kwd}">${i+1 }</a></li> 
							</c:when>
						</c:choose>
					</c:forEach>
					
					<c:choose>
						<c:when test = "${nextPage <= max }">
							<li><a href="${pageContext.request.contextPath }/board?p=${nextPage }&&kwd=${kwd}">▶</a></li>
						</c:when> 
						<c:otherwise>
							<li style = "color:lightgrey;">▶</li>
						</c:otherwise>
					</c:choose>
				
					
					</ul>
				</div>					
				<!-- pager 추가 -->
				<c:if test="${not empty authUser }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board/write" id="new-book">글쓰기</a>
					</div>
				</c:if>
								
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
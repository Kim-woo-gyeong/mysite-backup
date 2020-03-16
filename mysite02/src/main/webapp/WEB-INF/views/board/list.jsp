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
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="hidden" name="a" value="search">
					<input type="submit" value="찾기">
				</form>
				
				<!-- 시작 수 조건문 -->
				<c:choose>
					<c:when test="${empty param.p }">
						<c:set var = 'start' value = '0'/>
						<c:set var ='page' value = '0'/>
					</c:when>
					<c:otherwise>
						<c:set var ='page' value = '${param.p}'/>
						<fmt:parseNumber var = 'start' integerOnly='true' type='number' value = '${param.p/5 }'></fmt:parseNumber>	
					</c:otherwise>
				</c:choose>
				
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
						<td>${total-(5*(page))-status.index }</td>
						<td style="text-align:left; padding-left:${20*vo.depth }px">
						
						<c:if test="${vo.depth > 0 }">
							<img src ='${pageContext.request.contextPath }/assets/images/reply.png'>	
						</c:if>
						
						<a href="${pageContext.request.contextPath }/board?a=view&&no=${vo.no }">${vo.title }</a></td>
						<td>${vo.userName }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						
						<td>
							<c:if test="${authUser.no == vo.userNo }">
								<a href="${pageContext.request.contextPath }/board?a=delete&&no=${vo.no }" class="del">삭제</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>				
				</table>
				<!-- pager 추가 -->
				
				<!-- 끝 수 조건문 -->
				<c:choose>
					<c:when test="${total%5 == 0 }">
						<fmt:parseNumber var = 'end' integerOnly='true' type='number' value = '${total/5-1 }'></fmt:parseNumber>
					</c:when>
					<c:otherwise>
						<fmt:parseNumber var = 'end' integerOnly='true' type='number' value = '${total/5 }'></fmt:parseNumber>
					</c:otherwise>
				</c:choose>
				<div class="pager">
					<ul>
					
					<c:choose>
						<c:when test="${page == 0 }">
							<li style = "color:lightgrey;">◀</li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }/board?p=${page-1}">◀</a></li>
						</c:otherwise>
					</c:choose>
					
					<c:forEach var="i" begin="${5*start }" end="${5*start + 4 }" step="1">
						<c:choose>
							<c:when test="${page == i }">
								<li style = "color:pink;" class="selected" >${i+1 }</li> 
							</c:when>
							<c:when test="${end >= i }">
								<li><a href="${pageContext.request.contextPath }/board?p=${i }">${i+1 }</a></li> 
							</c:when>
						</c:choose>
					</c:forEach>
					
					<c:choose>
						<c:when test = "${page + 1 <= end }">
							<li><a href="${pageContext.request.contextPath }/board?p=${page + 1 }">▶</a></li>
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
						<a href="${pageContext.request.contextPath }/board?a=write" id="new-book">글쓰기</a>
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
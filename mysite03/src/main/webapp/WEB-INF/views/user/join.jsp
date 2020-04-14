<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript">

	$(function(){
		$("#join-form").submit(function(e){
			e.preventDefault();
			
			if($("#name").val() == ''){
				alert('이름이 비어있습니다.');
				$("#name").focus();
				return;
			}
			
			if($("#email").val() == ''){
				alert('이메일이 비어있습니다.');
				$("#email").focus();
				return;
			}
			
			if($("#password").val()==''){
				alert('비밀번호가 비어있습니다.');
				$("#email").focus();
				return;
			}
			
			if($("#img-checkemail").is(":hidden")){
				alert('이메일 중복 체크를 하지 않았습니다.');
				return;
			}
			
			if($("#agree-prov").is(":checked")==false){
				alert('약관 동의가 필요합니다.');
				$("#agree-prov").focus();
				return;
			}
			
			this.submit();
		});
		
		$('#email').change(function(){
			$('#btn-checkemail').show();
			$('#img-checkemail').hide();
			console.log("changed!");
		})
		$('#btn-checkemail').click(function(){
			var email = $("#email").val();
			if(email==''){
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath }/api/user/checkemail?email=" + email,
				type: 'get',
				data: '',
				dataType: 'json',
				success:function(response){
					console.log(response);
					if(response.result == 'fail'){
						console.error(response.message);
						return;
					}
					if(response.result==true){
						alert('존재하는 이메일입니다.');
						$('email').val('').focus();
						return;
					}
					
					$('#btn-checkemail').hide();
					$('#img-checkemail').show();
				},
				/* XHR : Mapping하고 있는 xml 도출? */
				error: function(XHR, status, e){
					console.log(status+":" + e);
				}
			})
		})
	})
</script>
</head>
<body>
	<div id="container">
		<c:import url ='/WEB-INF/views/includes/header.jsp'/>
		<div id="content">
			<div id="user">

				<form:form 
					modelAttribute='userVo'
					id="join-form" 
					name="joinForm" 
					method="post" 
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
						<spring:hasBindErrors name="userVo">
							<c:if test='${errors.hasFieldErrors("name") }'>
								<spring:message code = '${errors.getFieldError("name").codes[0] }'/>
							</c:if>
						</spring:hasBindErrors>
					</p>
					
					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />
					<input type="button" id = 'btn-checkemail' value="이메일확인">
					<img id= 'img-checkemail' style='width:16px; display: none' src="${pageContext.request.contextPath }/assets/images/check.png">
					<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
						<form:errors path="email" />
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path = "password" />
					<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
						<form:errors path="password" />
					</p>
					
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="F" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="M">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		<c:import url ='/WEB-INF/views/includes/navigation.jsp'/>
		<c:import url ='/WEB-INF/views/includes/footer.jsp'/>
	</div>
</body>
</html>
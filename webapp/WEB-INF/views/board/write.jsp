<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet"
	type="text/css">
	
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
	
<script>
<!--
$(function(){
	
	$( "#board-form" ).submit(function(){
		//이름 체크
		if(  $( "#title" ).val()  == "" ){
			alert( "제목이 공백입니다." );
			$( "#title" ).focus();
			return false;
		}
		

	});
});
-->
	
</script>



<c:if test="${param.result == 'fail'}">
	<script>
		$(function() {
			alert("빈칸을 채워주세요.");
		});
	</script>
</c:if>


</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<!-- <form class="board-form" method="post" action="${pageContext.request.contextPath}/board/upload"> -->
				<form id = "board-form" class = "board-form" action="${pageContext.request.contextPath}/board/upload" method="post"
									enctype="multipart/form-data">

					<input type= "hidden" name="memberNo" value="${authUser.no}">
					<input type="hidden" name="groupNo" value="${vo.groupNo }">
					<input type="hidden" name="orderNo" value="${vo.orderNo }">
					<input type="hidden" name="depth" value="${vo.depth }">   
					<!-- 이게 없어서 계쏙 계층형 안됐음 -->
					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="" id = "title"></td>
						</tr>
						<tr>
							<td align="right" class="label">파일첨부</td>
							<td align="left" style="padding-left: 20; padding-right: 30">

								 
									<input type="file" 	name="uploadFile"	style="color: slategray; border: 1 solid silver; width: 300; height: 20">
								(최대 4M)
								
							</td>


						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="content"></textarea></td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board">취소</a> <input type="submit" value="등록">
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp" />
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>
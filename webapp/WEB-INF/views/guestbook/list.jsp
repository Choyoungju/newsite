<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.hanains.mysite.dao.GuestBookDao"%>
<%@ page import="com.hanains.mysite.vo.GuestBookVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	//object to down casting
	List<GuestBookVo> list = (List<GuestBookVo>)request.getAttribute("list");
pageContext.setAttribute("newLine","\n");
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>




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
			<div id="guestbook">
				<form id="guestbook-form"
					action="${pageContext.request.contextPath}/guestbook/insert"
					method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" id="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password" id="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"
									id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>


				<c:set var="count" value="${fn:length(list)}" />
				--${count}--
				<c:forEach items="${list }" var="vo" varStatus="status">


					<br>
					<table width=510 border=1>
						<tr>
							<td>${count-status.index }</td>
							<td>${vo.name}</td>
							<td>${vo.reg_date}</td>
							<td><a
								href="${pageContext.request.contextPath}/guestbook/deleteform?id=${vo.no}">삭제</a></td>
						</tr>
						<tr>
							<!-- <td colspan=4><!%=vo.getMessage().replaceAll("\n", "<br/>") %></td> -->
							<td colspan=4>${fn:replace(vo.message, newLine, "<br />")}</td>
						</tr>
					</table>
				</c:forEach>

			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp" />
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>
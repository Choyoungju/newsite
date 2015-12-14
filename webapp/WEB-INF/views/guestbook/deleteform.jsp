<%@ page contentType="text/html;charset=UTF-8" %>
<%
	String no = request.getParameter("id");
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
				<c:import url ="/WEB-INF/views/include/header.jsp"/>
	
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="${pageContext.request.contextPath}/guestbook">
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="no" value="<%=no%>">
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="submit" value="확인">
				</form>
				<a href="${pageContext.request.contextPath}/guestbook">방명록 리스트</a>
			</div>
		</div>
		<c:import url ="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url ="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="com.hanains.mysite.vo.UserVo"%>
<%
UserVo authUser = (UserVo) session.getAttribute("authUser");
	//jsp는 이렇게 쓰지만 서블릿에서는 request.getSession();
%>
<div id="header">
	<h1>MySite</h1>
	<ul>

		<c:choose>
			<c:when test="${empty authUser}">
				<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/joinform">회원가입</a></li>
			</c:when>

			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/modifyform">회원정보수정</a></li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				  <li><%=authUser.getName()%>님 안녕하세요 ^^;</li>
				<li><${authUser.name}님 안녕하심?jstlel</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
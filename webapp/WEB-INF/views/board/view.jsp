<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute( "newLine", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>

					<tr>
						<td class="label">제목</td>
						<td>${board.title }</td>
					</tr>
					<tr>
 
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace( board.content, newLine, '<br>' ) }	
							</div>
						</td>
					</tr>
					
					   <td align="right" td class = "label"> 파일첨부</td>
    <td align="left" style="padding-left: 20; padding-right: 30">
             <input type="file" name="attachFile" style="color:slategray;border:1 solid silver;width:300; height:20"> (최대 4M)</td>
</tr>


				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/board/">글목록</a>
					<a href="${pageContext.request.contextPath}/board/modify/${board.no }">글수정</a>
				</div>
			</div>
		</div>
	<c:import url ="/WEB-INF/views/include/navigation.jsp"/>
	<c:import url ="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>
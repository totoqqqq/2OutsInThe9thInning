<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Free Board</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<table border="1" align="center" width="800px">
        <tr>
            <th colspan="5">자유게시판</th>
        </tr>
        <tr>
            <td width="5%"></td>
            <td width="60%" align="center">제목</td>
            <td width="15%" align="center">작성자</td>
            <td width="15%" align="center">날짜</td>
            <td width="5%">조회</td>
        </tr>
        
           <c:choose>
           		<c:when test="${not empty list }">
           			<c:forEach var="i" items="${list }">
           			<tr>
           				<td>${i.seq }
           				<td><a href="/detail.board?seq=${i.seq }">${i.title }</a>
           				<td>${i.writer }
           				<td>${i.write_date }
           				<td>${i.view_count }
           			</tr>
           			</c:forEach>
           		</c:when>
           		<c:otherwise>
           			<td colspan=5 style="height:400px;line-height:400px;text-align:center;">출력할 내용이 없습니다.
           		</c:otherwise>
           </c:choose>
        
        <tr>
            <td colspan=5 align="center">
                ${navi }
            </td>
        </tr>
        <tr>
            <td colspan=5 align="right">
                <button>작성하기</button>
            </td>
        </tr>
    </table>
</body>
</html>
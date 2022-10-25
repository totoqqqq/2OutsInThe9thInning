<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
    <style>
        *{
            font-family: 'Dongle', sans-serif;
            font-size: 20px;
        }
        *{box-sizing:border-box;}
		table { 
            width: 700px;
            height: 600px;
            background: rgba(240,240,240,0.5);
            table-layout:fixed;
		}
		
		table #meta {
			font-size: 8px;
		}
		
		table #contents {
			height: 200px;
			overflow-y: auto;
		}
		table textarea{
			width:100%;
			height:100%;
		}
		#title{border:none;}
        [contenteditable="true"]:empty:before {
		   content: attr(placeholder);
		}
    </style>
</head>
<body>
	<form action="" id="detailFrm" method="post">
		<table border=1 align=center>
			<tr>
				<th id="title">${boardData.boardTitle}</th>
			</tr>
			<tr>
				<td id="meta">${boardData.memberID} ${boardData.createDate} ${boardData.viewcount}
			</tr>
			<tr>
				<td id="contents">${boardData.boardContent}</td>
			</tr>
			<tr>
				<td align=right><c:if test="${boardData.memberID == memberID}">
						<button type=button id="delete-btn">삭제</button>
						<button type=button id="edit-btn">수정</button>
					</c:if>
					<button type=button id="back-btn">목록으로</button>
			</tr>
		</table>
	</form>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="view.js"></script>
</body>
</html>
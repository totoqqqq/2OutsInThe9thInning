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
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
    <style>
        *{
            font-family: 'Dongle', sans-serif;
            font-size: 30px;
        }
        div{
            margin: 0px;
            padding: 0px;
        }
    </style>
</head>
<body>
    <div>
        ${nickname}님 환영합니다.<br>
    </div>
    <div>
	    <button id = "logout-btn">로그아웃</button>
	    <button id = "editid-btn">회원수정</button>
	    <button id = "removeid-btn">회원탈퇴</button>
    </div>
	<script src="/miniweb/mypage.js"></script>
</body>
</html>
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
        #login-item{
            width: 700px;
            height: auto;
        }
        #loginBox{
            width: 100%;
            height: 100%;
            display: flex;
        }
        #loginleft{
            width: 60%;
            height: 100%;
        }
        .leftDiv{
            width: 100%;
            height: 50%;
        }
        #loginright{
            width: 20%;
            height: 100%;
        }
        .loginInput{
            width: 90%;
            height: 90%;
            font-size: 20px;
            display: block;
        }
        .loginInputR{
            width: 100%;
            height: 100%;
            font-size: 20px;
            display: block;
        }
        #signUp-div{
            width: 20%;
            height: 100%;
        }
        div{
            margin: 0px;
            padding: 0px;
        }
    </style>
</head>
<body>
	<!--<a href="ExamServlet">페이지01</a> 서블릿 불러오기.-->
	<div id="login-item">
	    <form method="post" name="login" id="loginForm" action="login.mem">
	        <div id="loginBox">
	            <div id="loginLeft">
	                <div class="leftDiv">
	                    <input class="loginInput" type="text" id="id" name="id" placeholder="ID를 입력해주세요.">
	                </div>
	                <div class="leftDiv">
	                    <input class="loginInput" type="password" id="pw" name="pw" placeholder="PW를 입력해주세요.">
	                </div>
	            </div>
	            <div id="loginRight">
	                <input class="loginInputR" type="submit" value="로그인">
	            </div>
	        </div>
	    </form>
	    <div id="signUp-div">
	        <button id = "createid-btn">회원가입</button>
	    </div>
	</div>
    <div id="loginCheck">

    </div>
	<script src="/miniweb/index.js"></script>
</body>
</html>
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
        .signUp-div{
            width: 600px;
            display: none;
        }
        .text-input{
            width: 80%;
            height: 95%;
        }
        .text-input-noright{
            width: 93%;
        }
        .signUp-group>h2{
            text-align: center;
        }
        .sub-title{
            text-align: right;
            font-size: 23px;
            margin-right: 0px;
        }
        .sub-btn{
            margin-top: 40px;
            margin-left: 15px;
        }
    </style>
</head>
<body>
    <div>
        ${nickName}님 환영합니다.<br>
    </div>
    <div>
        <button id = "freeboard-btn">자유게시판</button>
	    <button id = "logout-btn">로그아웃</button>
	    <button id = "editid-btn">회원수정</button>
	    <button id = "removeid-btn">회원탈퇴</button>
    </div>
        <div class="signUp-div">
            <form role="signUp" id="signUp" name="signUp" method="post" action="update.mem">
                <div class="signUp-group">
                    <h2>회원 정보 수정</h2>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title col-2">
                        <label for="inputdefault">아이디 :</label>
                    </div>
                    <div class="sub-input col-10">
                        <input class="signUp-control text-input-noright" id="id-text" name="id" type="text" placeholder="아이디를 입력하세요.">
                    </div>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title col-2">
                        <label for="inputdefault">현재 암호 :</label>
                    </div>
                    <div class="sub-input col-10">
                        <input class="signUp-control text-input-noright" id="pw-text" name="oldpw" type="password" placeholder="현 암호를 입력하세요.">
                    </div>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title col-2">
                        <label for="inputdefault">갱신 할 암호 :</label>
                    </div>
                    <div class="sub-input col-10">
                        <input class="signUp-control text-input-noright" id="pwck-text" name="newpw" type="password" placeholder="갱신 할 암호를 재입력하세요.">
                    </div>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title col-2">
                        <label for="inputdefault">닉네임:</label>
                    </div>
                    <div class="sub-input col-10">
                        <input class="signUp-control text-input-noright" id="name-text" name="name" type="text" placeholder="닉네임을 입력해주세요.">
                    </div>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title col-2">
                        <label for="inputdefault">휴대전화 번호:</label>
                    </div>
                    <div class="sub-input col-10">
                        <input class="signUp-control text-input-noright" id="phone-text" name="phone" type="text" placeholder="휴대전화 번호를 입력해주세요.">
                    </div>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title col-2">
                        <label for="inputdefault">이메일:</label>
                    </div>
                    <div class="sub-input col-10">
                        <input class="signUp-control text-input-noright" id="email-text" name="email" type="text" placeholder="이메일을 입력해주세요.">
                    </div>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title col-2">
                        <label for="inputdefault">우편번호:</label>
                    </div>
                    <div class="sub-input col-6">
                        <input class="signUp-control text-input-right" id="zipcode-text" name="zipcode" type="text" placeholder="우편번호를 검색해주세요." readonly>
                        <input class="signUp-control btn btn-sm btn-outline-primary" type="button" id="zipcode-btn" value="우편번호">
                    </div>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title col-2">
                        <label for="inputdefault">주소:</label>
                    </div>
                    <div class="sub-input col-10">
                        <input class="signUp-control text-input-noright" id="address-text" name="address" type="text" placeholder="우편번호를 검색해주세요." readonly>
                    </div>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title col-2">
                        <label for="inputdefault">세부주소:</label>
                    </div>
                    <div class="sub-input col-10">
                        <input class="signUp-control text-input-noright" id="building-text" name="building" type="text" placeholder="세부주소를 입력해주세요.">
                    </div>
                </div>
                <div class="signUp-group row">
                    <div class="sub-title sub-btn col-7">
                        <input class="signUp-control btn btn-sm btn-outline-success" type="submit" value="회원수정">
                        <input class="signUp-control btn btn-sm btn-outline-danger" id="cancel-btn" type="button" value="수정취소">
                    </div>
                </div>
            </form>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="mypage.js"></script>
    <script>
        $("#id-text").val("${memberID}");
        $("#id-text").attr("readonly","true");
        $("#name-text").val("${nickName}");
        $("#phone-text").val("${phone}");
        $("#phone-text").attr("readonly","true");
        $("#email-text").val("${email}");
        $("#zipcode-text").val("${zipcode}");
        $("#address-text").val("${address}");
        $("#building-text").val("${building}");

        $("#cancel-btn").on("click",function(){
            $("#id-text").val("${memberID}");
            $("#name-text").val("${nickName}");
            $("#phone-text").val("${phone}");
            $("#email-text").val("${email}");
            $("#zipcode-text").val("${zipcode}");
            $("#address-text").val("${address}");
            $("#building-text").val("${building}");
            $(".signUp-div").prop("style","display:none");
    	    editId=true;
        })
    </script>
</body>
</html>
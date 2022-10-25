<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <div class="signUp-div">
        <form role="signUp" id="signUp" name="signUp" method="post" action="create.mem">
            <div class="signUp-group">
                <h2>회원 가입 정보 입력</h2>
            </div>
            <div class="signUp-group row">
                <div class="sub-title col-2">
                    <label for="inputdefault">아이디 :</label>
                </div>
                <div class="sub-input col-10">
                    <input class="signUp-control text-text" id="id-text" name="id" type="text" placeholder="아이디를 입력하세요.">
                    <input class="signUp-control id-btn btn-sm btn-outline-primary" type="button" id="id-btn" value="중복체크">
                    <span id="id-span">중복체크를 해주세요</span>
                </div>
            </div>
            <div class="signUp-group row">
                <div class="sub-title col-2">
                    <label for="inputdefault">비밀번호 :</label>
                </div>
                <div class="sub-input col-10">
                    <input class="signUp-control text-input-noright" id="pw-text" name="pw" type="password" placeholder="비밀번호를 입력하세요.">
                </div>
            </div>
            <div class="signUp-group row">
                <div class="sub-title col-2">
                    <label for="inputdefault">비밀번호 확인:</label>
                </div>
                <div class="sub-input col-10">
                    <input class="signUp-control text-input-noright" id="pwck-text" name="pwck" type="password" placeholder="비밀번호를 재입력하세요.">
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
                    <input class="signUp-control id-btn btn-sm btn-outline-primary" type="button" id="zipcode-btn" value="우편번호">
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
                    <input class="signUp-control id-btn btn-sm btn-outline-success" type="submit" value="회원가입">
                    <input class="signUp-control id-btn btn-sm btn-outline-danger" type="button" value="취소">
                </div>
            </div>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="signUp.js"></script>
</body>
</html>
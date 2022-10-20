//RegExp
//id : 8 ~ 14자 소문자,숫자,_ 사용가능.
let regId=/^(?=.*[a-z\d_])[a-z\d_]{8,14}$/;
//pw : 10 ~ 20자 대,소문자,숫자 혼용 필수.
let regPw=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-z\d]{10,20}$/;
//name : 2 ~ 5자 한글
let regName=/^(?=.*[가-힣a-zA-z\d])[가-힣a-zA-z\d]{4,15}$/;
//phone : 휴대전화만 받음.
let regPhone=/^01\d-\d{3,4}-\d{4}$|^01\d{8,9}/;
//email : [영어&숫자] n글자 @ [영어&숫자] n글자+ . +[영어] 2~3글자
let regEmail=/^[a-zA-Z\d]*@[a-zA-Z\d]*[.][a-zA-Z]{2,3}/;

//signUp
//kakao address
let zipcodeBtn=document.getElementById("zipcode-btn");
zipcodeBtn.onclick=function(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById("zipcode-text").value=data.zonecode;
            document.getElementById("address-text").value=data.address;
            document.getElementById("building-text").value=data.buildingName;
        }
    }).open();
}
let editId=true;
$("#logout-btn").on("click",function(){
    $.post("logout.mem");
    location.replace("/miniweb");
})
$("#removeid-btn").on("click",function(){
    $.post("delete.mem");
    location.replace("/miniweb");
})
$("#editid-btn").on("click",function(){
	if(editId){
    	$(".signUp-div").prop("style","display:inline-block");
    	editId=false;
    }else{
    	$(".signUp-div").prop("style","display:none");
    	editId=true;
    }
})
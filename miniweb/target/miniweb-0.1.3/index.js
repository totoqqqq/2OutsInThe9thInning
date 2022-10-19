$("#logout-btn").on("click",function(){
    $.post("LoginCheck",{order:"logout"});
    location.replace("/miniweb");
})
$("#createid-btn").on("click",function(){
    location.replace("/miniweb/signUp.jsp");
})
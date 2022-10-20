$("#logout-btn").on("click",function(){
    $.post("logout.mem");
    location.replace("/miniweb");
})
$("#createid-btn").on("click",function(){
    location.replace("/miniweb/signUp.jsp");
})
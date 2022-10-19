$("#logout-btn").on("click",function(){
    $.post("LoginCheck",{order:"logout"});
    location.replace("/miniweb");
})
$("#removeid-btn").on("click",function(){
    $.post("MemberCRUD",{order:"delete"});
    location.replace("/miniweb");
})
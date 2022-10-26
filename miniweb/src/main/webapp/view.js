let edited=true;
$(".delete-btn").on("click",function(){
    $.get("delete.board"+window.location.search);
    location.replace("/miniweb/board.jsp");
});
$(".edit-btn").on("click",function(){
    if(edited){
		alert("내용을 직접 수정해주세요.");
        edited=false;
        $("#title").attr({"contenteditable":"true","placeholder":"글 제목"});
        $("#contents").attr({"contenteditable":"true","placeholder":"내용을 입력해주세요."});
    }else{
        edited=true;
        $.get("update.board"+window.location.search,{boardTitle:$("#title").text(),boardContent:$("#contents").text()});
        location.reload();
    }
});

$("#back-btn").on("click",function(){
    location.replace("/miniweb/board.jsp");
});
$("#replyinput-btn").on("click",function(){
	$.get("replyinput.board"+window.location.search,{boardreplyinput:$("#replyinput-table").text()});
	location.reload();	
});
$(".download-btn").on("click",function(){
	$.get("download.board"+window.location.search,{realName:$(this).val(),vmName:$(this).attr("id")});
});
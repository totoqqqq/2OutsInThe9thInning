$("#create-btn").on("click",function(){
	$.post("create.board",{title:$("#title").text(),contents:$("#contents").text()});
	location.replace("board.jsp");
})
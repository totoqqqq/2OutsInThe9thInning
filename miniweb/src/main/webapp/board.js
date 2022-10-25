$("#boardwriter-btn").on("click",function(){
	location.replace("/miniweb/boardpost.jsp");
})
$(window).on("load",function(){
	$.post("list.board",function(list){
		let lists=JSON.parse(list);
		if(list!=null){
			for(var i=0;i<lists.length;i++){
			$("#checktable").after(
       			"<tr><td>"+lists[i].boardID+
       			"<td><a href='view.board?boardIDs="+lists[i].boardID+"'>"+lists[i].boardTitle+"</a>"+
       			"<td>"+lists[i].memberID+
       			"<td>"+lists[i].createDate.substring(0,12)+
       			"<td>"+lists[i].viewcount+
       			"</tr>"
			);}
		}else
			$("#checktable").append('<td colspan=5 style="height:400px;line-height:400px;text-align:center;">출력할 내용이 없습니다.')
	});
});
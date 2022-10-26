let lists, pages, selectpage;
$("#boardwriter-btn").on("click",function(){
	location.replace("/miniweb/boardpost.jsp");
})
$(window).on("load",function(){
	$.post("list.board",{page:"1"},function(list){
		selectpage=1;
		if(list!=null){
            lists=JSON.parse(list);
			for(var i=0;i<lists.length;i++){
			$("#checktable").after(
       			"<tr><td>"+lists[i].boardID+
       			"<td><a href='view.board?boardIDs="+lists[i].boardID+"'>"+lists[i].boardTitle+"</a>"+
       			"<td>"+lists[i].memberID+
       			"<td>"+lists[i].createDate.substring(0,12)+
       			"<td>"+lists[i].viewcount+
       			"</tr>"
			);}
			$("#navi-td").prepend("<button class='page-btn' id='leftlist-btn'>◀</button> <button class='page-btn' id='1-btn'>1</button>");
			for(var j=2;j<=pages;j++)
                $("#navi-td").append("<button class='page-btn' id='"+j+"-btn'>"+j+"</button> ");
			$("#navi-td").append("<button class='page-btn' id='rightlist-btn'>▶</button>");
		}else
			$("#checktable").append('<td colspan=5 style="height:400px;line-height:400px;text-align:center;">출력할 내용이 없습니다.')
	});
});


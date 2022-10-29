let lists, pages, selectpage;
$("#boardwriter-btn").on("click",function(){
	location.replace("/miniweb/boardpost.jsp");
})
$(window).on("load",function(){
	let listpage=parseInt(document.location.toString().substring(document.location.toString().lastIndexOf("?page=")+6));
	if(listpage<=0||listpage==null)
		listpage=1;
	$.post("list.board",{page:listpage},function(list){
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
		}else
			$("#checktable").append('<td colspan=5 style="height:400px;line-height:400px;text-align:center;">출력할 내용이 없습니다.')
	});
	$.post("listpage.board",function(countpage){
			$("#navi-td").prepend("<button class='page-btn' id='leftlist-btn'>◀</button> <button class='listpage-btn' id='1-btn'>1</button>");
			for(var j=2;j<=countpage;j++)
                $("#navi-td").append("<button class='listpage-btn' id='"+j+"-btn'>"+j+"</button> ");
			$("#navi-td").append("<button class='page-btn' id='rightlist-btn'>▶</button>");
	});
});

$(document).on("click",".listpage-btn",function(){
	location.replace("board.jsp?page="+$(this).attr("id").toString().substring(0,$(this).attr("id").toString().indexOf("-")));
});
$(document).on("click","#leftlist-btn",function(){
	let listpage=parseInt(document.location.toString().substring(document.location.toString().lastIndexOf("?page=")+6))-1;
	if (listpage<=0)
		listpage=1
	location.replace("board.jsp?page="+listpage);
});
$(document).on("click","#rightlist-btn",function(){
	let listpage=parseInt(document.location.toString().substring(document.location.toString().lastIndexOf("?page=")+6))+1;
	$.post("listpage.board",function(countpage){
		if (parseInt(listpage)>=parseInt(countpage.toString())){
			listpage=countpage;
			}
		location.replace("board.jsp?page="+listpage);
	});
});
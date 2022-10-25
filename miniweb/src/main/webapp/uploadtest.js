$("#listView-btn").on("click",function(){
    $.post("view.file",function(list) {
        let lists=JSON.parse(list)
        let listStr="";
        for(var i=0;i<lists.length;i++)
			listStr+=i+1+"."+lists[i].realName+"<br>";
        $("#list-div").append().html(
            listStr+"<br>"
        )
    })
});
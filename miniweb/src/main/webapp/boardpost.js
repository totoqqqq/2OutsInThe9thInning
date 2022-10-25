$("#create-btn").on("click",function(){
	$("#title-hidden").val($("#title").text());			
	$("#content-hidden").val($("#contents").text());
	$("#data-form").submit();
	});
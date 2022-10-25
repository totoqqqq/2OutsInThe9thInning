<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.seq}.${dto.title }</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<style>
*{box-sizing:border-box;}
table {
	width: 300px;
}

table #meta {
	font-size: 8px;
}

table #contents {
	height: 200px;
	overflow-y: auto;
}
table textarea{
	width:100%;
	height:100%;
}
#title{border:none;}
</style>
</head>
<body>
	<form action="" id="detailFrm" method="post">
		<input type=hidden name=seq value="${dto.seq }">
		<input type=hidden name=title id="input_title">
		<input type=hidden name=contents id="input_contents">
		
		<table border=1 align=center>
			<tr>
				<th id="title">${dto.title }</th>
			</tr>
			<tr>
				<td id="meta">${dto.writer }${dto.write_date } ${dto.view_count }
			</tr>
			<tr>
				<td id="contents">${dto.contents }</td>
			</tr>
			<tr>
				<td align=right><c:if test="${dto.writer == loginID }">
						<button type=button id="delete">삭제</button>
						<button type=button id="modify">수정</button>
					</c:if>
					<button type=button id="back">목록으로</button>
			</tr>
		</table>
	</form>
	<script>
		$("#delete").on("click", function() {
			$("#detailFrm").attr("action","/delete.board?seq=${dto.seq}");
			$("#detailFrm").submit();
		})
		
		$("#modify").on("click", function() {
			$("#delete,#modify").hide();
			
			$("#title,#contents").attr("contenteditable","true");
			
			let modifyOK = $("<button>");
			modifyOK.html("수정완료");
			modifyOK.on("click",function(){
				$("#detailFrm").attr("action","/update.board");
				
				$("#input_contents").val($("#contents").html());
				$("#input_title").val($("#title").html());
				
				$("#detailFrm").submit();
			})
			
			let modifyCancel = $("<button>");
			modifyCancel.attr("type","button");
			modifyCancel.html("취소");
			modifyCancel.on("click",function(){location.reload();});
			
			$("#back").before(modifyOK);
			$("#back").before(modifyCancel);
		})
		

		$("#back").on("click", function() {
			history.back();
		})
	</script>

</body>
</html>
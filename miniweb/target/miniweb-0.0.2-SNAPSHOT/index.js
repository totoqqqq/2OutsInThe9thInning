let info=document.cookie;
if(!(info.indexOf("nickname")==-1)){
	let id=info.substring(info.indexOf("id")+3,info.indexOf(";",info.indexOf("id")));
	let nickname=info.substring(info.indexOf("nickname")+9);
   	$("#loginCheck").html(nickname+"님 환영합니다.");
}
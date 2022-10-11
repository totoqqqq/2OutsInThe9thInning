<%@ page import="java.io.*"%>
<script>
let id =<%@(String)session.getAttribute("memberID");%>
alert(id);
$("#loginCheck").html(id+"님 환영합니다.");
</script>
<%@ page language="java" contentType="text/html;charset=GBK"%>


<script language = "javascript">
<%
String uri = request.getRequestURL().toString();
if(uri!=null && uri.indexOf("manmao.net")>-1){
	response.sendRedirect(request.getContextPath()+"/common/999.jsp");
}
%>

	top.location="${pageContext.request.contextPath}/common/login.zul";

</script>
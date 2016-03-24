<%@ page language="java"  session="false" %>
<%

	final String url = request.getContextPath() + "/security/login.do";
	response.sendRedirect(response.encodeURL(url));

%>
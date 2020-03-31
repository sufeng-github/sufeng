<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
	<title>My JSP 'index.jsp' starting page</title>
<%-- 	<script src="<%=basePath%>plugin/jquery.min1.11.1.js"></script> --%>
	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
	<script type="text/javascript">  
	 	$(function(){
	 	
	 		$("#pdfIframe").attr("src","../web/viewer.html?file="+ encodeURIComponent("${pageContext.request.contextPath}/fileop.htm/showPdf.do"));
	    });  
	</script>  
  </head>
  <body>
  	<iframe id="pdfIframe" width="100%" height="100%"></iframe>  
  </body>
</html>
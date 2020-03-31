<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>

		<mvc:form id="fmDataOne" modelAttribute="DataOne" method="post">
			           		
			<input name="productionOrderNum" class="easyui-textbox" style="width:75px;" data-options="validType:'text'">   
			<input name="purchaseDate" class="easyui-textbox" style="width:75px;" data-options="validType:'text'">   
			                                    	
		</mvc:form>
					


 	</body> 
</html> 
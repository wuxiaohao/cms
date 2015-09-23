<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/uploadify/uploadify.css"/>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#attach").uploadify({
				swf:$("#ctx").val()+"/resources/uploadify/uploadify.swf"
				
			});
			
		});
	</script>
  </head>
  
  <body>
  	<input type="hidden" id="ctx" value="<%=request.getContextPath() %>">
   	<input type="file" name="attach" id="attach" value="">
  </body>
</html>

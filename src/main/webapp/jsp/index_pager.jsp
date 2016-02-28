<%@page import="org.wxh.basic.model.SystemContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<pg:pager export="curPage=pageNumber" 
	items="${param.totalRecord }" 
	maxPageItems="<%=SystemContext.getPageSize() %>"
	url="${param.url }">
	<c:forEach items="${param.params }" var="p">
		<pg:param name="${p }"/>
	</c:forEach>
	<ul class="pagination pagination-sm">
		<pg:first>
			<li><a href="${param.url }" > &laquo;</a></li>
		</pg:first>
		<pg:pages>
			<li><a href="${param.url }">${pageNumber }</a></li>
		</pg:pages>
		<pg:last>
			<li><a href="${param.url }" > &raquo;</a></li>
		</pg:last>
	</ul>
</pg:pager>

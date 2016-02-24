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
			<li><a href="${pageUrl }" > &laquo;</a></li>
		</pg:first>
		<pg:pages>
			<%-- <c:if test="${curPage eq pageNumber }">
				[${pageNumber }]
			</c:if>
			<c:if test="${curPage != pageNumber }">
				<a href="${pageUrl }" class="pager_link">${pageNumber }</a>
			</c:if> --%>
			<li><a href="${pageUrl }">${pageNumber }</a></li>
		</pg:pages>
		<pg:last>
			<li><a href="${pageUrl }" > &raquo;</a></li>
		</pg:last>
	</ul>
</pg:pager>

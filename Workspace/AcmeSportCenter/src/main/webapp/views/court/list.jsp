<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="courts" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<spring:message code="court.name" var="nameHeader"/>
	<display:column property="name" title="${nameHeader}" sortable="true"/>
	
	<spring:message code="court.category" var="categoryHeader"/>
	<display:column property="category" title="${categoryHeader}" sortable="true"/>
	  	
	<display:column> <a href="court/administrator/edit.do?courtId=<jstl:out value="${row.id}"/>"><spring:message code="court.edit"/></a></display:column>
	
</display:table>

	<div>
		<a href="court/administrator/create.do"> <spring:message
				code="court.create" />
		</a>
	</div>
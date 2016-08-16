<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table name="courts" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<acme:column code="court.centre" property="centre.name" sortable="true"/>
	<acme:column code="court.name" property="name" sortable="true"/>
	<acme:column code="court.category" property="category" sortable="true"/>
	  	
	<acme:refColumn ref="court/administrator/edit.do?courtId=${row.id}" code="court.edit"/>
	
</display:table>

	<div>
		<a href="court/administrator/create.do"> <spring:message code="court.create" /></a>
	</div>
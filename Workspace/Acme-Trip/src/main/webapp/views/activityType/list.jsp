<%--
 * list.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="activityTypes" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="activityType.name" property="name" sortable="true"/>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:refColumn ref="activityType/administrator/edit.do?activityTypeId=${row.id}" code="activityType.edit"/>
	</security:authorize>

	<acme:refColumn ref="trip/listByActivityType.do?activityTypeId=${row.id}" code="activityType.trips"/>
			
</display:table>

<security:authorize access="hasRole('ADMIN')">
<a href="activityType/administrator/create.do?"><spring:message code="activityType.create"></spring:message> </a> <br/>
</security:authorize><br/>

<acme:cancel url="/" code="activityType.back"/>
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

<display:table name="activities" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="activity.title" property="title" sortable="true"/>
	
	<acme:column code="activity.description" property="description"/>	
	
	<acme:column code="activity.activityType" property="activityType.name"/>		
	
	<acme:pictureColumn items="${row.photos}" code="activity.photos" alt="${row.title}"/>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:column code="activity.inappropriate" property="inappropriate"/>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:refConditionColumn ref="activity/administrator/flagActivity.do?activityId=${row.id}" code="activity.flagActivity" condition="${row.inappropriate == false}"/>	
	</security:authorize>
	
	<security:authorize access="isAuthenticated()">
	<acme:refColumn ref="activityComment/actor/list.do?activityId=${row.id}" code="activity.comments"/>	
	</security:authorize>
	
</display:table>

<acme:cancel url="/trip/list.do" code="activity.back"/>
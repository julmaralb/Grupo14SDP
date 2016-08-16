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

<display:table name="slots" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="slot.title" property="title" sortable="true"/>
	
	<acme:column code="slot.description" property="description"/>
	
	<acme:column code="slot.activity" property="activity.title"/>		
	
	<acme:pictureColumn items="${row.photos}" code="slot.photos" alt="${row.title}"/>
	
	<acme:column code="slot.startTime" property="startTime" sortable="true"/>
	
	<acme:column code="slot.endTime" property="endTime" sortable="true"/>
	
	<acme:refColumn ref="activity/list.do?slotId=${row.id}" code="slot.activity"/>
	
	<security:authorize access="hasRole('USER')">
	<acme:refColumn ref="slot/user/edit.do?slotId=${row.id}&dailyPlanId=${param['dailyPlanId']}" code="dailyPlan.edit"/>
	</security:authorize>
				
</display:table>
<acme:cancel url="/trip/list.do" code="slot.back"/>
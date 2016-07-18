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

<display:table name="dailyPlans" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="dailyPlan.weekDay" var="weekDayHeader" />
	<display:column title="${weekDayHeader}">
		<jstl:choose>
    		<jstl:when test="${row.weekDay==1}">
    			<spring:message code="dailyPlan.weekDay.monday" var="weekDay" /><jstl:out value="${weekDay}"/>
    		</jstl:when>
    		<jstl:when test="${row.weekDay==2}">
    			<spring:message code="dailyPlan.weekDay.tuesday" var="weekDay" /><jstl:out value="${weekDay}"/>
    		</jstl:when>
    		<jstl:when test="${row.weekDay==3}">
    			<spring:message code="dailyPlan.weekDay.wednesday" var="weekDay" /><jstl:out value="${weekDay}"/>
    		</jstl:when>
    		<jstl:when test="${row.weekDay==4}">
    			<spring:message code="dailyPlan.weekDay.thursday" var="weekDay" /><jstl:out value="${weekDay}"/>
    		</jstl:when>
    		<jstl:when test="${row.weekDay==5}">
    			<spring:message code="dailyPlan.weekDay.friday" var="weekDay" /><jstl:out value="${weekDay}"/>
    		</jstl:when>
    		<jstl:when test="${row.weekDay==6}">
    			<spring:message code="dailyPlan.weekDay.saturday" var="weekDay" /><jstl:out value="${weekDay}"/>
    		</jstl:when>
    		<jstl:when test="${row.weekDay==7}">
    			<spring:message code="dailyPlan.weekDay.sunday" var="weekDay" /><jstl:out value="${weekDay}"/>
    		</jstl:when>
    	</jstl:choose>
	</display:column>
	
	<acme:column code="dailyPlan.title" property="title" sortable="true"/>
	
	<acme:column code="dailyPlan.description" property="description"/>
	
	<acme:pictureColumn items="${row.photos}" code="dailyPlan.photos" alt="${row.title}"/>
	
	<acme:refColumn ref="slot/list.do?dailyPlanId=${row.id}" code="dailyPlan.slots"/>	
	
	<security:authorize access="hasRole('USER')">
	<acme:refColumn ref="dailyPlan/user/edit.do?dailyPlanId=${row.id}&tripId=${param['tripId']}" code="dailyPlan.edit"/>
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
	<acme:refColumn ref="slot/user/create.do?dailyPlanId=${row.id}" code="dailyPlan.addSlot"/>
	</security:authorize>	
					
</display:table>
<acme:cancel url="/trip/list.do" code="dailyPlan.back"/>
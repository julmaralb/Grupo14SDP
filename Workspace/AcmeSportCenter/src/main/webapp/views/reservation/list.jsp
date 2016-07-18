<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="reservations" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="reservation.customer" var="customerHeader"/>
	<display:column property="customer.name" title="${customerHeader}" sortable="true"/>
	</security:authorize>
	
	<spring:message code="reservation.centre" var="centreHeader"/>
	<display:column property="court.centre.name" title="${centreHeader}" sortable="true"/>
	
	<spring:message code="reservation.court" var="courtHeader"/>
	<display:column property="court.name" title="${courtHeader}" sortable="true"/>
	
	<spring:message code="reservation.start" var="startHeader"/>
	<display:column property="start" title="${startHeader}" sortable="true"/>
	
	<spring:message code="reservation.end" var="endHeader"/>
	<display:column property="end" title="${endHeader}" sortable="false"/>
	
</display:table>
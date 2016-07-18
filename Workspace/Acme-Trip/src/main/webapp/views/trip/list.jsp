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

<form:form action="trip/listKeyword.do" modelAttribute="trip">

<input type="text" name="keyword">
&nbsp;
<input type="submit" name="search" value="<spring:message code="trip.search" />" />
<br></br>
</form:form>


<display:table name="trips" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="trip.title" property="title" sortable="true"/>
	
	<acme:column code="trip.startingDate" property="startingDate" sortable="true"/>
	
	<acme:column code="trip.endingDate" property="endingDate" sortable="true"/>
	
	<acme:column code="trip.description" property="description"/>		
	
	<acme:pictureColumn items="${row.photos}" code="trip.photos" alt="${row.title}"/>
	
	<security:authorize access="hasRole('USER')">
	<acme:refColumn ref="dailyPlan/user/create.do?tripId=${row.id}" code="trip.addDailyPlan"/>
	</security:authorize>
				
</display:table>
<acme:cancel url="/" code="trip.back"/>
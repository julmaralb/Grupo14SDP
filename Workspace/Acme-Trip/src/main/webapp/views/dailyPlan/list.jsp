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

<form:form action="dailyPlan/listKeyword.do" modelAttribute="dailyPlan">

<input type="text" name="keyword">
&nbsp;
<input type="submit" name="search" value="<spring:message code="dailyPlan.search" />" />
<br></br>
</form:form>


<display:table name="dailyPlans" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="dailyPlan.weekDay" property="weekDay" sortable="true"/>
	
	<acme:column code="dailyPlan.title" property="title" sortable="true"/>
	
	<acme:column code="dailyPlan.description" property="description"/>
	
	<acme:column code="dailyPlan.photos" property="photos"/>		
					
</display:table>
<acme:cancel url="/" code="dailyPlan.back"/>
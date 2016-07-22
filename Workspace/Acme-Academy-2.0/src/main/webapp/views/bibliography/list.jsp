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

<display:table name="bibliographies" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="bibliography.title" property="title" sortable="true"/>
	
	<acme:column code="bibliography.authors" property="authors"/>
	
	<acme:column code="bibliography.locator" property="locator"/>
	
	<acme:column code="bibliography.URL" property="URL"/>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:column code="bibliography.counter" property="counter" sortable="true"/>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('LECTURER')">
<a href="bibliography/lecturer/create.do"><spring:message code="bibliography.create"/></a>
</security:authorize><br/>

<security:authorize access="hasRole('LECTURER')">
<acme:cancel url="syllabus/lecturer/list.do" code="lecturer.back"/>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<acme:cancel url="/" code="lecturer.back"/>
</security:authorize>
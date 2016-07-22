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

<display:table name="assignments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="assignment.title" property="title"/>
	<acme:column code="assignment.description" property="description"/>
	<acme:column code="assignment.points" property="points"/>
	<acme:column code="assignment.opening" property="opening"/>
	<acme:column code="assignment.deadline" property="deadline"/>
	
	<security:authorize access="hasRole('LECTURER')">
	<acme:refColumn ref="deliverable/lecturer/list.do?assignmentId=${row.id}" code="assignment.deliverables"/>
	</security:authorize>
	
	<security:authorize access="hasRole('STUDENT')">
	<acme:refColumn ref="deliverable/student/list.do?assignmentId=${row.id}" code="assignment.deliverables"/>
	</security:authorize>
	
	<security:authorize access="hasRole('STUDENT')">
	<acme:refColumn ref="deliverable/student/create.do?assignmentId=${row.id}" code="assignment.uploadDeliverable"/>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('LECTURER')">
<a href="assignment/lecturer/create.do"><spring:message code="assignment.create"/></a>
</security:authorize><br/>

<security:authorize access="hasRole('STUDENT')">
<acme:cancel url="group/student/listMyGroups.do" code="lecturer.back"/>
</security:authorize>

<security:authorize access="hasRole('LECTURER')">
<acme:cancel url="group/lecturer/list.do" code="lecturer.back"/>
</security:authorize>
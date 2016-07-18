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

<display:table name="learningMaterials" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="learningMaterial.title" property="title" sortable="true"/>
	<acme:column code="learningMaterial.notes" property="notes"/>

	<spring:message code="learningMaterial.contents" var="contentsHeader" />
	<display:column title="${contentsHeader}" sortable="false">
	<jstl:out value="${row.contents}"></jstl:out> -
	<a href="${row.contents}"><spring:message code="learningMaterial.download" /></a>
	</display:column>
</display:table>

<security:authorize access="hasRole('LECTURER')">
<a href="learningMaterial/lecturer/create.do?"><spring:message code="learningMaterial.uploadlearningMaterial"/></a> 
</security:authorize>
<br></br>

<security:authorize access="hasRole('STUDENT')">
<acme:cancel url="group/student/listMyGroups.do" code="lecturer.back"/>
</security:authorize>

<security:authorize access="hasRole('LECTURER')">
<acme:cancel url="group/lecturer/list.do" code="lecturer.back"/>
</security:authorize>
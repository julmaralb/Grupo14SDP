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

<display:table name="subjects" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="subject.code" property="code" sortable="true"/>
	
	<acme:column code="subject.title" property="title" sortable="true"/>
	
	<display:column> <a href="subject/listLecturers.do?subjectId=<jstl:out value="${row.id}"/>"><spring:message code="subject.lecturer"/></a></display:column>
	
	<security:authorize access="hasRole('LECTURER')">
	<display:column> <a href="subject/lecturer/display.do?subjectId=<jstl:out value="${row.id}"/>"><spring:message code="subject.display"/></a></display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('LECTURER')">
	<display:column> <a href="subject/lecturer/listSyllabi.do?subjectId=<jstl:out value="${row.id}"/>"><spring:message code="subject.syllabi"/></a></display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column> <a href="subject/administrator/edit.do?subjectId=<jstl:out value="${row.id}"/>"><spring:message code="subject.edit"/></a></display:column>
	</security:authorize>
</display:table>
<acme:cancel url="/" code="lecturer.back"/>
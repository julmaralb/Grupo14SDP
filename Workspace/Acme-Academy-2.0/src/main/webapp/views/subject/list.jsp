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
	
	<acme:refColumn ref="subject/listLecturers.do?subjectId=${row.id}" code="subject.lecturer"/>
	
	<security:authorize access="hasRole('LECTURER')">
	<acme:refColumn ref="subject/lecturer/display.do?subjectId=${row.id}" code="subject.display"/>
	</security:authorize>
	
	<acme:refColumn ref="subject/listSyllabi.do?subjectId=${row.id}" code="subject.syllabi"/>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:refColumn ref="subject/administrator/edit.do?subjectId=${row.id}" code="subject.edit"/>
	</security:authorize>
</display:table>
<acme:cancel url="/" code="lecturer.back"/>
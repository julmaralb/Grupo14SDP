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

<display:table name="syllabi" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="syllabus.academicYear" property="academicYear" sortable="true"/>
	
	<acme:column code="syllabus.summary" property="summary"/>
	
	<acme:column code="syllabus.goals" property="goals"/>
	
	<acme:column code="syllabus.prerequisites" property="prerequisites"/>
	
	<acme:column code="syllabus.evalAndGradPolicies" property="evaluationAndGradingPolicies"/>
	
	<display:column> <a href="syllabus/lecturer/listBibliographies.do?syllabusId=<jstl:out value="${row.id}"/>"><spring:message code="syllabus.bibliographies"/></a></display:column>
	
	<display:column> <a href="syllabus/lecturer/edit.do?syllabusId=<jstl:out value="${row.id}"/>"><spring:message code="syllabus.edit"/></a></display:column>
	
</display:table>
<acme:cancel url="subject/lecturer/list.do" code="lecturer.back"/>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table name="groups" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="group.name" property="name" sortable="true"/>
	<acme:column code="group.description" property="description"/>
	<acme:column code="group.academicYear" property="academicYear"/>
	<acme:column code="group.subject" property="subject.title"/>
	
	<security:authorize access="hasRole('LECTURER')">
	<display:column>
	<a href="assignment/lecturer/listByGroup.do?groupId=<jstl:out value="${row.id}"/>"><spring:message code="group.assignments"/></a>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('LECTURER')">
	<display:column>
	<a href="learningMaterial/lecturer/list.do?groupId=<jstl:out value="${row.id}"/>"><spring:message code="group.learningMaterials"/></a>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('STUDENT')">
	<display:column>
	<jstl:if test="${fn:contains(row.students,principal)}">
	<a href="learningMaterial/student/list.do?groupId=<jstl:out value="${row.id}"/>"><spring:message code="group.learningMaterials"/></a>
	</jstl:if>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('STUDENT')">
	<display:column>
	<jstl:if test="${fn:contains(row.students,principal)}">
	<a href="assignment/student/listByGroup.do?groupId=<jstl:out value="${row.id}"/>"><spring:message code="group.assignments"/></a>
	</jstl:if>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('STUDENT')">
	<display:column>
	<jstl:if test="${!fn:contains(row.students,principal)}">
	<a href="group/student/join.do?groupId=<jstl:out value="${row.id}"/>"><spring:message code="group.join"/></a>
	</jstl:if>
	</display:column>  
	</security:authorize>
</display:table>

	<security:authorize access="hasRole('LECTURER')">
	<a href="group/lecturer/create.do?"><spring:message code="group.create"/></a> 
	</security:authorize>

	<br></br>
	<acme:cancel url="/" code="lecturer.back"/>
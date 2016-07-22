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
	<acme:refColumn ref="assignment/lecturer/listByGroup.do?groupId=${row.id}" code="group.assignments"/>
	</security:authorize>
	
	<security:authorize access="hasRole('LECTURER')">
	<acme:refColumn ref="learningMaterial/lecturer/list.do?groupId=${row.id}" code="group.learningMaterials"/>
	</security:authorize>
	
	<security:authorize access="hasRole('STUDENT')">
	<acme:refConditionColumn ref="learningMaterial/student/list.do?groupId=${row.id}" code="group.learningMaterials" condition="${fn:contains(row.students,principal)}"/>
	</security:authorize>
	
	<security:authorize access="hasRole('STUDENT')">
	<acme:refConditionColumn ref="assignment/student/listByGroup.do?groupId=${row.id}" code="group.assignments" condition="${fn:contains(row.students,principal)}"/>
	</security:authorize>
	
	<security:authorize access="hasRole('STUDENT')">
	<acme:refConditionColumn ref="group/student/join.do?groupId=${row.id}" code="group.join" condition="${!fn:contains(row.students,principal)}"/>
	</security:authorize>
</display:table>

	<security:authorize access="hasRole('LECTURER')">
	<a href="group/lecturer/create.do"><spring:message code="group.create"/></a> 
	</security:authorize>

	<br></br>
	<acme:cancel url="/" code="lecturer.back"/>
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

<display:table name="deliverables" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="deliverable.version" property="deliverableVersion" sortable="true"/>
	<acme:column code="deliverable.moment" property="moment" sortable="true"/>
	<acme:column code="deliverable.mark" property="mark" sortable="true"/>
	
	<spring:message code="deliverable.contents" var="contentsHeader" />
	<display:column title="${contentsHeader}" sortable="false">
	<jstl:out value="${row.contents}"></jstl:out> - 
	<a href="${row.contents}"><spring:message code="deliverable.download" /></a>
	</display:column>
	
	<acme:column code="deliverable.assignment" property="assignment.title" sortable="true"/>
	
	<spring:message code="deliverable.assessments" var="assessmentsHeader"/>
	<display:column title="${assessmentsHeader}">
	<jstl:choose>
		<jstl:when test="${allInstantiated}">
			<jstl:forEach items="${row.assessments}" var="a"><jstl:out value="${a.explanation}"/> - <jstl:out value="${a.points}"/>, </jstl:forEach>
		</jstl:when>
		<jstl:otherwise>
			<spring:message code="deliverable.currentlyUnderReview"/>
		</jstl:otherwise>
	</jstl:choose>
	</display:column>
	
	<spring:message code="deliverable.student" var="studentHeader"/>
	<display:column title="${studentHeader}" sortable="true"> <jstl:out value="${row.student.name}"> </jstl:out> <jstl:out value="${row.student.surname}"> </jstl:out></display:column>
	
	<security:authorize access="hasRole('LECTURER')">
	<acme:refColumn ref="assessment/lecturer/list.do?deliverableId=${row.id}" code="deliverable.assessments"/>
	</security:authorize>
	
	<security:authorize access="hasRole('LECTURER')">
	<acme:refColumn ref="assessment/lecturer/create.do?deliverableId=${row.id}" code="deliverable.assess"/>
	</security:authorize>

	
	<security:authorize access="hasRole('LECTURER')">
	<acme:refConditionColumn ref="deliverable/lecturer/computeMark.do?deliverableId=${row.id}" code="deliverable.computeMark" condition="${computable}"/>
	</security:authorize>
	
	
</display:table>

<security:authorize access="hasRole('STUDENT')">
<acme:cancel url="group/student/listMyGroups.do" code="lecturer.back"/>
</security:authorize>

<security:authorize access="hasRole('LECTURER')">
<acme:cancel url="group/lecturer/list.do" code="lecturer.back"/>
</security:authorize>
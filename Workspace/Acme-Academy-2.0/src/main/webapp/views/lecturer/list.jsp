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

<display:table name="lecturers" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="lecturer.name" property="name" sortable="true"/>
	<acme:column code="lecturer.surname" property="surname" sortable="true"/>
	<acme:column code="lecturer.email" property="email" />
	<acme:column code="lecturer.phone" property="phone" />

	
	<security:authorize access="hasRole('ADMIN')">
	<acme:column code="lecturer.username" property="userAccount.username" />
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:column code="lecturer.counter" property="counter" sortable="true"/>
	</security:authorize>
	
	<acme:refColumn ref="lecturer/listSubjects.do?lecturerId=${row.id}" code="lecturer.subjects"/>

</display:table>

<acme:cancel url="/" code="lecturer.back"/>
<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAnonymous()">
<p><spring:message code="welcome.greeting.prefix" /> <spring:message code="welcome.greeting.suffix" /></p>
</security:authorize>

<security:authorize access="isAuthenticated()">
	<p><spring:message code="welcome.greeting.prefix" /> <security:authentication property="principal.username" /> <spring:message code="welcome.greeting.suffix" /></p>
</security:authorize>


<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 

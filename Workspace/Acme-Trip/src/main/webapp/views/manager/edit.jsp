<%--
 * edit.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="manager/administrator/edit.do" modelAttribute="manager">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="folders"/>
	<form:hidden path="userAccount.authorities"/>

	<fieldset>
  	<legend><spring:message code="manager.personalInfo"/>:</legend>
	<acme:textbox code="manager.name" path="name"/>
	<acme:textbox code="manager.surname" path="surname"/>
	<acme:textbox code="manager.email" path="email"/>
	<acme:textbox code="manager.phone" path="phone"/>	
	</fieldset>

	<fieldset>
  	<legend><spring:message code="manager.accountInfo"/>:</legend>
  	<acme:textbox code="manager.username" path="userAccount.username"/>
  	<acme:password code="manager.password" path="userAccount.password"/>
	</fieldset>
	
	<br></br>
	<acme:submit name="save" code="manager.save"/>
	<acme:cancel code="manager.cancel" url="/security/login.do"/>
</form:form>
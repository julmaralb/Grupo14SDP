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

<form:form action="user/edit.do" modelAttribute="userForm">

	<fieldset>
  	<legend><spring:message code="user.personalInfo"/>:</legend>
	<acme:textbox code="user.name" path="name"/>
	<acme:textbox code="user.surname" path="surname"/>
	<acme:textbox code="user.email" path="email"/>
	<acme:textbox code="user.phone" path="phone"/>	
	</fieldset>

	<fieldset>
  	<legend><spring:message code="user.accountInfo"/>:</legend>
  	<acme:textbox code="user.username" path="username"/>
  	<acme:password code="user.password" path="password"/>
  	<acme:password code="user.secondPassword" path="secondPassword"/>
	</fieldset>
	
	<acme:checkbox code="user.termsAccepted" path="termsAccepted"/><a href="user/terms.do"> <spring:message code="user.moreInfo" /></a>
	<br></br>
	<acme:submit name="save" code="user.save"/>
	<acme:cancel code="user.cancel" url="/security/login.do"/>
</form:form>
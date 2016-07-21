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

<form:form action="administrator/edit.do" modelAttribute="administrator">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="folders"/>
	<form:hidden path="userAccount.authorities"/>

	<fieldset>
  	<legend><spring:message code="administrator.personalInfo"/>:</legend>
	<acme:textbox code="administrator.name" path="name"/>
	<acme:textbox code="administrator.surname" path="surname"/>
	<acme:textbox code="administrator.email" path="email"/>
	<acme:textbox code="administrator.phone" path="phone"/>	
	</fieldset>

	<fieldset>
  	<legend><spring:message code="administrator.accountInfo"/>:</legend>
  	<acme:textbox code="administrator.username" path="userAccount.username"/>
  	<acme:password code="administrator.password" path="userAccount.password"/>
	</fieldset>
	
	<br></br>
	<acme:submit name="save" code="administrator.save"/>
	<acme:cancel code="administrator.cancel" url="/security/login.do"/>
</form:form>
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

<form:form action="supervisor/administrator/edit.do" modelAttribute="supervisor">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="userAccount.authorities"/>

	<fieldset>
  	<legend><spring:message code="supervisor.personalInfo"/>:</legend>
	<acme:textbox code="supervisor.name" path="name"/>
	<acme:textbox code="supervisor.surname" path="surname"/>
	<acme:textbox code="supervisor.email" path="email"/>
	<acme:textbox code="supervisor.address" path="address"/>
	<acme:textbox code="supervisor.phone" path="phone"/>	
	</fieldset>

	<fieldset>
  	<legend><spring:message code="supervisor.accountInfo"/>:</legend>
  	<acme:textbox code="supervisor.username" path="userAccount.username"/>
  	<acme:password code="supervisor.password" path="userAccount.password"/>
	</fieldset>
	
	<br></br>
	<acme:submit name="save" code="supervisor.save"/>
	<acme:cancel code="supervisor.cancel" url="/security/login.do"/>
</form:form>
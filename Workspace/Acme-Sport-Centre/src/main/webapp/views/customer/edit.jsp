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

<form:form action="customer/edit.do" modelAttribute="customerForm">

	<fieldset>
  	<legend><spring:message code="customer.personalInfo"/>:</legend>

	<acme:textbox code="customer.name" path="name"/><br/>
	<acme:textbox code="customer.surname" path="surname"/><br/>
	<acme:textbox code="customer.email" path="email"/><br/>
	<acme:textbox code="customer.phone" path="phone"/><br/>
	<acme:textbox code="customer.address" path="address"/><br/>

	</fieldset>

	<fieldset>
  	<legend><spring:message code="customer.accountInfo"/>:</legend>

	<acme:textbox code="customer.username" path="username"/><br/>
	<acme:password code="customer.password" path="password"/><br/>
	<acme:password code="customer.secondPassword" path="secondPassword"/><br/>

	</fieldset>
	
	<acme:checkbox code="customer.termsAccepted" path="termsAccepted"/><a href="customer/terms.do" > <spring:message code="customer.moreInfo" /></a>
	<br></br>
	<acme:submit name="save" code="customer.save"/>
	<acme:cancel url="/security/login.do" code="customer.cancel"/>
	
</form:form>


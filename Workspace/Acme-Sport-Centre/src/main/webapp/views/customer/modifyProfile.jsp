<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="customer/modifyProfile.do" modelAttribute="customer">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />

	<fieldset>
  	<legend><spring:message code="customer.personalInfo"/>:</legend>
	
	<acme:textbox code="customer.name" path="name"/><br/>
	<acme:textbox code="customer.surname" path="surname"/><br/>
	<acme:textbox code="customer.email" path="email"/><br/>
	<acme:textbox code="customer.phone" path="phone"/><br/>
	<acme:textbox code="customer.address" path="address"/><br/>

	</fieldset>
	
	<acme:submit name="save" code="customer.save"/>
	<acme:cancel url="/" code="customer.cancel"/>
	
</form:form>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="polyglot/edit.do" modelAttribute="polyglotForm">

	<fieldset>
  	<legend><spring:message code="polyglot.personalInfo"/>:</legend>
	<acme:textbox code="polyglot.name" path="name"/>
	<acme:textbox code="polyglot.surname" path="surname"/>
	<acme:textbox code="polyglot.email" path="email"/>
	<acme:textbox code="polyglot.phone" path="phone"/>	
	</fieldset>

	<fieldset>
  	<legend><spring:message code="polyglot.accountInfo"/>:</legend>
  	<acme:textbox code="polyglot.username" path="username"/>
  	<acme:password code="polyglot.password" path="password"/>
  	<acme:password code="polyglot.secondPassword" path="secondPassword"/>
	</fieldset>
	
	<acme:checkbox code="polyglot.termsAccepted" path="termsAccepted"/><a href="polyglot/terms.do"> <spring:message code="polyglot.moreInfo" /></a>
	<br></br>
	<acme:submit name="save" code="polyglot.save"/>
	<acme:cancel code="polyglot.cancel" url="/security/login.do"/>
</form:form>
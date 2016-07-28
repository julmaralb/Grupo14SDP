<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="polyglot/modifyProfile.do" modelAttribute="polyglot">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="folders" />

	<fieldset>
  	<legend><spring:message code="polyglot.personalInfo"/>:</legend>
	<acme:textbox code="polyglot.name" path="name"/>
	<acme:textbox code="polyglot.surname" path="surname"/>
	<acme:textbox code="polyglot.email" path="email"/>
	<acme:textbox code="polyglot.phone" path="phone"/>	
	</fieldset>
	
	<br></br>
	<acme:submit name="save" code="polyglot.save"/>
	<acme:cancel code="polyglot.cancel" url="/"/>
</form:form>
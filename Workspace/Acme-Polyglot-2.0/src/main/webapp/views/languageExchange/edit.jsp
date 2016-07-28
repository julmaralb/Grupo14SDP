<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="languageExchange/polyglot/edit.do" modelAttribute="languageExchange">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="owner"/>
	<form:hidden path="participants"/>
	<form:hidden path="cancelled"/>
	
	<acme:textbox code="languageExchange.name" path="name"/><br/>
	
	<acme:textbox code="languageExchange.registrationDate" path="registrationDate"/><br/>
	
	<acme:textbox code="languageExchange.exchangePlace" path="exchangePlace"/><br/>
	
	<acme:select items="${languages}" itemLabel="code" code="languageExchange.languages" path="languages"/>
	
	<br></br>
	<acme:submit name="save" code="languageExchange.save"/>
	<acme:cancel code="languageExchange.cancel" url="/languageExchange/polyglot/list.do"/>
	<acme:delete code="languageExchange.delete" codeConfirm="languageExchange.confirm.delete" condition="${languageExchange.id != 0}"/>
	
</form:form>
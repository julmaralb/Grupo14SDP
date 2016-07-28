<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="sponsorship/agent/edit.do" modelAttribute="sponsorship">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="agent"/>
	<form:hidden path="banners"/>
	
	<acme:textbox code="sponsorship.name" path="name"/><br/>
	<acme:select items="${languageExchanges}" itemLabel="name" code="sponsorship.languageExchange" path="languageExchange"/>
	
	<br></br>
	<acme:submit name="save" code="sponsorship.save"/>
	<acme:cancel code="sponsorship.cancel" url="/sponsorship/agent/list.do"/>
	<acme:delete code="sponsorship.delete" codeConfirm="sponsorship.confirm.delete" condition="${sponsorship.id != 0}"/>
	
</form:form>
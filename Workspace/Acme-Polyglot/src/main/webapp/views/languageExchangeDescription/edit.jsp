<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="languageExchangeDescription/polyglot/edit.do" modelAttribute="languageExchangeDescription">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="languageExchangeDescription.title" path="title"/><br/>
	<acme:textarea code="languageExchangeDescription.text" path="text"/><br/>
	<acme:textbox code="languageExchangeDescription.code" path="code"/><br/>
	<acme:textarea code="languageExchangeDescription.infoLinks" path="infoLinks"/><br/>
	<acme:textarea code="languageExchangeDescription.tags" path="tags"/><br/>
	<acme:select items="${languageExchanges}" itemLabel="name" code="languageExchangeDescription.languageExchange" path="languageExchange"/>
	
	<br></br>
	<acme:submit name="save" code="languageExchangeDescription.save"/>
	<acme:cancel code="languageExchangeDescription.cancel" url="/languageExchange/polyglot/list.do"/>
	<acme:delete code="languageExchangeDescription.delete" codeConfirm="languageExchangeDescription.confirm.delete" condition="${languageExchangeDescription.id != 0}"/>
	
</form:form>
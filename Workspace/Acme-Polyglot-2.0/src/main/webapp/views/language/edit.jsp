<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="language/administrator/edit.do" modelAttribute="language">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="counter"/>
	<form:hidden path="languageExchanges"/>
	<form:hidden path="languageDescriptions"/>
	
	<acme:textbox code="language.code" path="code"/><br/>
	
	<br></br>
	<acme:submit name="save" code="language.save"/>
	<acme:cancel code="language.cancel" url="/language/adminsitrator/list.do"/>
	<acme:delete code="language.delete" codeConfirm="language.confirm.delete" condition="${language.id != 0}"/>
	
</form:form>
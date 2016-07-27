<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="languageDescription/administrator/edit.do" modelAttribute="languageDescription">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="languageDescription.title" path="title"/><br/>
	<acme:textarea code="languageDescription.text" path="text"/><br/>
	<acme:textbox code="languageDescription.code" path="code"/><br/>
	<acme:textarea code="languageDescription.infoLinks" path="infoLinks"/><br/>
	<acme:textarea code="languageDescription.tags" path="tags"/><br/>
	<acme:select items="${languages}" itemLabel="code" code="languageDescription.language" path="language"/>
	
	<br></br>
	<acme:submit name="save" code="languageDescription.save"/>
	<acme:cancel code="languageDescription.cancel" url="/language/administrator/list.do"/>
	<acme:delete code="languageDescription.delete" codeConfirm="languageDescription.confirm.delete" condition="${languageDescription.id != 0}"/>
	
</form:form>
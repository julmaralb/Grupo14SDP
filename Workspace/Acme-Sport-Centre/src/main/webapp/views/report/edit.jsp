<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="report/supervisor/edit.do" modelAttribute="report">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden path="supervisor"/>
	
	
	<acme:textbox code="report.title" path="title" />
	<acme:textarea code="report.description" path="description" />
	<acme:select items="${courts}" itemLabel="name" code="report.court" path="court"/>
	
	<br></br>
	<acme:submit name="save" code="report.save"/>
	<acme:cancel code="report.cancel" url="/"/>
	<acme:delete code="report.delete" codeConfirm="report.confirm.delete" condition="${report.id != 0}"/>
	
</form:form>
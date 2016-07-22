<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="rubric/lecturer/edit.do" modelAttribute="rubric">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="instantiated"/>
	<form:hidden path="number"/>
	
	
	
	<acme:textbox code="rubric.explanation" path="explanation"/>
	<br />
	<acme:textbox code="rubric.percentage" path="percentage"/>
	<br />
	<acme:select items="${assignments}" itemLabel="title" code="rubric.assignments" path="assignment"/>
	
	<br></br>
	<acme:submit name="save" code="rubric.save"/>
	<acme:cancel code="rubric.cancel" url="/rubric/list.do"/>
	<acme:delete code="rubric.delete" codeConfirm="rubric.confirm.delete" condition="${rubric.id != 0}"/>
	
</form:form>
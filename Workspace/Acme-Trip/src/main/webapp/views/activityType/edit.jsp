<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="activityType/administrator/edit.do" modelAttribute="activityType">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="activities"/>
	
	
	<acme:textbox code="activityType.name" path="name" />
	
	<br></br>
	<acme:submit name="save" code="activityType.save"/>
	<acme:cancel code="activityType.cancel" url="/activityType/adminsitrator/list.do"/>
	<acme:delete code="activityType.delete" codeConfirm="activityType.confirm.delete" condition="${activityType.id != 0}"/>
	
</form:form>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="slot/user/edit.do" modelAttribute="slot">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="dailyPlan"/>
	
	<acme:textbox code="slot.title" path="title"/><br/>
	<acme:textarea code="slot.description" path="description"/><br/>
	<acme:textarea code="slot.photos" path="photos"/><br/>
	<acme:textbox code="slot.startTime" path="startTime"/><br/>
	<acme:textbox code="slot.endTime" path="endTime"/><br/>
	<acme:select items="${activities}" itemLabel="title" code="slot.activity" path="activity"/>
	<input type="hidden" name= "dailyPlanId" value="${dailyPlanId}"/>
	
	<br></br>
	<acme:submit name="save" code="slot.save"/>
	<acme:cancel code="slot.cancel" url="/trip/user/list.do"/>
	<acme:delete code="slot.delete" codeConfirm="slot.confirm.delete" condition="${slot.id != 0}"/>
	
</form:form>
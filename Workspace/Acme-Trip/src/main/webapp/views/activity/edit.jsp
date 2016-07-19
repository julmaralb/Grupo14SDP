<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="activity/user/edit.do" modelAttribute="activity">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="user"/>
	<form:hidden path="activityComments"/>
	<form:hidden path="slots"/>
	<form:hidden path="inappropriate"/>
	
	<acme:textbox code="activity.title" path="title"/><br/>
	<acme:textarea code="activity.description" path="description"/><br/>
	<acme:textarea code="activity.photos" path="photos"/><br/>
	<acme:select items="${activityTypes}" itemLabel="name" code="activity.activityType" path="activityType"/>
	
	<br></br>
	<acme:submit name="save" code="activity.save"/>
	<acme:cancel code="activity.cancel" url="/activity/list.do"/>
	<acme:delete code="activity.delete" codeConfirm="activity.confirm.delete" condition="${activity.id != 0}"/>
	
</form:form>
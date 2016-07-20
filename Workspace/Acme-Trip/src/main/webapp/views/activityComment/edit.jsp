<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="activityComment/actor/edit.do" modelAttribute="activityComment">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="actor"/>
	<form:hidden path="inappropriate"/>
	<form:hidden path="activity"/>
	<form:hidden path="moment"/>
		
	<acme:textbox code="activityComment.title" path="title"/>
	<acme:textarea code="activityComment.text" path="text"/>
	<input type="hidden" name= "activityId" value="${activityId}"/>

	<br></br>
	<acme:submit name="save" code="activityComment.save"/>
	<acme:cancel code="activityComment.cancel" url="/activityComment/list.do"/>
	<acme:delete code="activityComment.delete" codeConfirm="activityComment.confirm.delete" condition="${activityComment.id != 0}"/>
	
</form:form>
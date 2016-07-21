<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="tripComment/actor/edit.do" modelAttribute="tripComment">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="actor"/>
	<form:hidden path="inappropriate"/>
	<form:hidden path="trip"/>
	<form:hidden path="moment"/>
	
	<acme:textbox code="tripComment.title" path="title"/>
	<acme:textarea code="tripComment.text" path="text"/>
	<input type="hidden" name= "tripId" value="${tripId}"/>
	
	<br></br>
	<acme:submit name="save" code="tripComment.save"/>
	<acme:cancel code="tripComment.cancel" url="/trip/list.do"/>
	<acme:delete code="tripComment.delete" codeConfirm="tripComment.confirm.delete" condition="${tripComment.id != 0}"/>
	
</form:form>
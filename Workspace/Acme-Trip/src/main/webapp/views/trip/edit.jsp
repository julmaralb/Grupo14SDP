<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="trip/user/edit.do" modelAttribute="trip">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="owner"/>
	<form:hidden path="dailyPlans"/>
	<form:hidden path="tripComments"/>
	<form:hidden path="subscriptors"/>
	
	<acme:textbox code="trip.title" path="title"/><br/>
	
	<acme:textbox code="trip.startingDate" path="startingDate"/><br/>
	
	<acme:textbox code="trip.endingDate" path="endingDate"/><br/>
	
	<acme:textarea code="trip.description" path="description"/><br/>
	
	<acme:textarea code="trip.photos" path="photos"/>
	
	<br></br>
	<acme:submit name="save" code="trip.save"/>
	<acme:cancel code="trip.cancel" url="/trip/user/list.do"/>
	<acme:delete code="trip.delete" codeConfirm="trip.confirm.delete" condition="${trip.id != 0}"/>
	
</form:form>
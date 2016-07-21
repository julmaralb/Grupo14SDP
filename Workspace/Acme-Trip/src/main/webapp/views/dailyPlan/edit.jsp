<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="dailyPlan/user/edit.do" modelAttribute="dailyPlan">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="trip"/>
	<form:hidden path="slots"/>
	
		
	<form:label path="${weekDay}">
		<spring:message code="dailyPlan.weekDay" />
	</form:label>
	<form:select path="weekDay">
		<form:option value="1"><spring:message code="dailyPlan.weekDay.monday" /></form:option>
		<form:option value="2"><spring:message code="dailyPlan.weekDay.tuesday" /></form:option>
		<form:option value="3"><spring:message code="dailyPlan.weekDay.wednesday" /></form:option>
		<form:option value="4"><spring:message code="dailyPlan.weekDay.thursday" /></form:option>
		<form:option value="5"><spring:message code="dailyPlan.weekDay.friday" /></form:option>
		<form:option value="6"><spring:message code="dailyPlan.weekDay.saturday" /></form:option>
		<form:option value="7"><spring:message code="dailyPlan.weekDay.sunday" /></form:option>
	</form:select>
	<br />
	<br />
	
	<acme:textbox code="dailyPlan.title" path="title"/>
	<acme:textarea code="dailyPlan.description" path="description"/>
	<acme:textarea code="dailyPlan.photos" path="photos"/>
	<input type="hidden" name= "tripId" value="${tripId}"/>
	
	<br></br>
	<acme:submit name="save" code="dailyPlan.save"/>
	<acme:cancel code="dailyPlan.cancel" url="/trip/list.do"/>
	<acme:delete code="dailyPlan.delete" codeConfirm="dailyPlan.confirm.delete" condition="${dailyPlan.id != 0}"/>
	
</form:form>
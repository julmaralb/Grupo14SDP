<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="campaign/manager/edit.do" modelAttribute="campaign">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="manager"/>
	<form:hidden path="banners"/>
	<form:hidden path="cancelled"/>
	
	<acme:textbox code="campaign.title" path="title" />
	<acme:textbox code="campaign.startMoment" path="startMoment" />
	<acme:textbox code="campaign.finishMoment" path="finishMoment" />
	<acme:select items="${creditCards}" itemLabel="number" code="campaign.creditCard" path="creditCard"/>
	
	<br></br>
	<acme:submit name="save" code="campaign.save"/>
	<acme:cancel code="campaign.cancel" url="/campaign/manager/list.do"/>
	<acme:delete code="campaign.delete" codeConfirm="campaign.confirm.delete" condition="${campaign.id != 0}"/>
	
</form:form>
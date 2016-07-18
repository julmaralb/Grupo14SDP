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
	
	<acme:textbox code="slot.title" path="title" readonly="true"/>
	<acme:textbox code="slot.description" path="description"/>
	<acme:textarea code="slot.photos" path="photos"/>
	<acme:textbox code="slot.startTime" path="startTime"/>
	<acme:textbox code="slot.endTime" path="endTime"/>
	
	<br></br>
	<acme:submit name="save" code="slot.save"/>
	<acme:cancel code="slot.cancel" url="/slot/list.do"/>
	<acme:delete code="slot.delete" codeConfirm="slot.confirm.delete" condition="${slot.id != 0}"/>
	
</form:form>
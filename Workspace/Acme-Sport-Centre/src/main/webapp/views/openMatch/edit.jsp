<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="openMatch/customer/edit.do" modelAttribute="openMatch">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="players"/>
	<form:hidden path="numPlayers"/>
	<form:hidden path="owner"/>
	
	<acme:textbox code="openMatch.title" path="title"/><br/>
	<acme:textarea code="openMatch.description" path="description"/><br/>
	<acme:textbox code="openMatch.moment" path="moment"/><br/>
	<acme:textbox code="openMatch.maxPlayers" path="maxPlayers"/><br/>
	<acme:select items="${reservations}" itemLabel="code" code="openMatch.reservation" path="reservation"/>
	
	<br></br>
	<acme:submit name="save" code="openMatch.save"/>
	<acme:cancel code="openMatch.cancel" url="/"/>
	<acme:delete code="openMatch.delete" codeConfirm="openMatch.confirm.delete" condition="${openMatch.id != 0}"/>
	
</form:form>
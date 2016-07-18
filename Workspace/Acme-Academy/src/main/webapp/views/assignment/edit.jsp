<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="assignment/lecturer/edit.do" modelAttribute="assignment">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="assignment.title" path="title"/>
	<acme:textarea code="assignment.description" path="description"/>
	<acme:textbox code="assignment.points" path="points"/>
	<acme:textbox code="assignment.opening" path="opening"/>
	<acme:textbox code="assignment.deadline" path="deadline"/>
	<acme:select items="${groups}" itemLabel="name" code="assignment.group" path="group"/>
	<br></br>
	
	<acme:submit name="save" code="assignment.save"/>
	<acme:cancel code="assignment.cancel" url="group/lecturer/list.do"/>
</form:form>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="group/lecturer/edit.do" modelAttribute="group">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="group.name" path="name"/><br/>
	<acme:textarea code="group.description" path="description"/><br/>	
	<acme:textbox code="group.academicYear" path="academicYear"/><br/>
	<acme:select items="${subjects}" itemLabel="title" code="group.subject" path="subject"/>
	<br></br>
	
	<acme:submit name="save" code="group.save"/>
	<acme:cancel code="student.cancel" url="group/lecturer/list.do"/>	
</form:form>
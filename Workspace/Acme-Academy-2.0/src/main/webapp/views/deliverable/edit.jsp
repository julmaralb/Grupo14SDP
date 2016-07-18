<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="deliverable/student/edit.do" modelAttribute="deliverable">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="student"/>
	<form:hidden path="assignment"/>
	<form:hidden path="deliverableVersion"/>
	
	<acme:textbox code="deliverable.moment" path="moment" readonly="true"/>
	<br></br>
	<acme:textarea code="deliverable.contents" path="contents"/>
	
	<input type="hidden" name= "assignmentId" value="${assignmentId}"/>
	
	<br></br>
	<acme:submit name="save" code="deliverable.save"/>
	<acme:cancel code="student.cancel" url="group/student/listMyGroups.do"/>
</form:form>
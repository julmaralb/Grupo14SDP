<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="student/modifyProfile.do" modelAttribute="student">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />

	<fieldset>
  	<legend><spring:message code="student.personalInfo"/>:</legend>
	<acme:textbox code="student.name" path="name"/>
	<acme:textbox code="student.surname" path="surname"/>
	<acme:textbox code="student.email" path="email"/>
	<acme:textbox code="student.phone" path="phone"/>	
	</fieldset>
	
	<br></br>
	<acme:submit name="save" code="student.save"/>
	<acme:cancel code="student.cancel" url="/"/>
</form:form>
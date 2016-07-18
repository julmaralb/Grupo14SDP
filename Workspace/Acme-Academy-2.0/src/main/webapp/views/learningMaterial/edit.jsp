<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="learningMaterial/lecturer/edit.do" modelAttribute="learningMaterial">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="learningMaterial.title" path="title"/>
	<acme:textbox code="learningMaterial.notes" path="notes"/>
	<br></br>
	<acme:textarea code="learningMaterial.contents" path="contents"/>
	
	<acme:select items="${groups}" itemLabel="name" code="learningMaterial.groups" path="group"/>
	
	<br></br>
	<acme:submit name="save" code="learningMaterial.save"/>
	<acme:cancel code="student.cancel" url="group/lecturer/list.do"/>
</form:form>
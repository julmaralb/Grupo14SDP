<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="bibliography/lecturer/edit.do" modelAttribute="bibliography">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="counter"/>
	
	<acme:textbox code="bibliography.title" path="title"/>
	<br />
	<acme:textarea code="bibliography.authors" path="authors"/>
	<br />
	<acme:textbox code="bibliography.locator" path="locator"/>
	<br />
	<acme:textbox code="bibliography.URL" path="URL"/>
	<br />
	
	<br></br>
	<acme:submit name="save" code="bibliography.save"/>
	<acme:cancel code="bibliography.cancel" url="/bibliography/list.do"/>
	<jstl:if test="${bibliography.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="bibliography.delete"/>"
			onclick="return confirm('<spring:message code="bibliography.confirm.delete" />')" />
		&nbsp
	</jstl:if>
	
</form:form>
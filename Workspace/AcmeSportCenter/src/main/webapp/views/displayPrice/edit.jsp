<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="displayPrice/administrator/edit.do" modelAttribute="displayPrice">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="displayPrice.courtPrice" path="courtPrice" />
	<acme:textbox code="displayPrice.tax" path="tax" />
	
	<br></br>
	<acme:submit name="save" code="displayPrice.save"/>
	<acme:cancel code="displayPrice.cancel" url="/"/>
	
</form:form>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="centre/administrator/edit.do" modelAttribute="centre">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	
	<acme:textbox code="centre.name" path="name"/><br/>
	<acme:textbox code="centre.address" path="address"/><br/>
	<acme:textbox code="centre.phone" path="phone"/><br/>
	<acme:textbox code="centre.picture" path="picture"/><br/>
	
	<acme:submit name="save" code="centre.save"/>
	<acme:cancel url="/centre/list.do" code="centre.cancel"/>

</form:form>


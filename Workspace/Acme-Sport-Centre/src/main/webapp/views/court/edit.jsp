<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="court/administrator/edit.do" modelAttribute="court">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="days"/>
	
	<acme:textbox code="court.name" path="name"/><br/>
	<acme:textbox code="court.category" path="category"/><br/>
	<acme:select items="${centres}" itemLabel="name" code="court.centre" path="centre"/><br/>
	
	<acme:submit name="save" code="court.save"/>
	<acme:cancel url="/court/administrator/list.do" code="court.cancel"/>

</form:form>


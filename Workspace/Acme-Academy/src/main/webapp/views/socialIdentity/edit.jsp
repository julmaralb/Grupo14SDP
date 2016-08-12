<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="socialIdentity/edit.do" modelAttribute="socialIdentity">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="actor"/>
	
	<acme:textbox code="socialIdentity.name" path="name"/><br/>
	<acme:textbox code="socialIdentity.nickname" path="nickname"/><br/>
	<acme:textbox code="socialIdentity.homePage" path="homePage"/><br/>
	<acme:textbox code="socialIdentity.email" path="email"/>
	
	<br></br>
	<acme:submit name="save" code="socialIdentity.save"/>
	<acme:cancel code="student.cancel" url="socialIdentity/list.do"/>
	<acme:delete code="socialIdentity.delete" codeConfirm="socialIdentity.confirm.delete" condition="${socialIdentity.id != 0}"/>

</form:form>
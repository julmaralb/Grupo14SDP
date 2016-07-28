<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="folder/actor/edit.do" modelAttribute="folder">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="actor"/>
	
	<acme:textbox code="folder.name" path="name" />
	
	<br></br>
	<acme:submit name="save" code="folder.save"/>
	<acme:cancel code="folder.cancel" url="/folder/actor/list.do"/>
	<acme:delete code="folder.delete" codeConfirm="folder.confirm.delete" condition="${folder.id != 0}"/>
	
</form:form>
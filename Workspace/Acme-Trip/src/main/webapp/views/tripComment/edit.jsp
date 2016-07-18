<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="tripComment/user/edit.do" modelAttribute="tripComment">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="tripComment.title" path="title" readonly="true"/>
	<acme:textbox code="tripComment.moment" path="moment"/>
	<acme:textbox code="tripComment.text" path="text"/>
	<acme:textbox code="tripComment.inappropriate" path="inappropriate"/>
	
	<br></br>
	<acme:submit name="save" code="tripComment.save"/>
	<acme:cancel code="tripComment.cancel" url="/tripComment/list.do"/>
	<acme:delete code="tripComment.delete" codeConfirm="tripComment.confirm.delete" condition="${tripComment.id != 0}"/>
	
</form:form>
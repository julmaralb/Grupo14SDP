<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="banner/manager/edit.do" modelAttribute="banner">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="dayDisplays"/>
	
	<acme:textbox code="banner.photo" path="photo" />
	<acme:textarea code="banner.keywords" path="keywords" />
	<acme:textbox code="banner.maxDisplayTimes" path="maxDisplayTimes" />
	<acme:select items="${campaigns}" itemLabel="title" code="banner.campaign" path="campaign"/>
	
	<br></br>
	<acme:submit name="save" code="banner.save"/>
	<acme:cancel code="banner.cancel" url="/banner/list.do"/>
	<acme:delete code="banner.delete" codeConfirm="banner.confirm.delete" condition="banner.id != 0"/>
	
</form:form>
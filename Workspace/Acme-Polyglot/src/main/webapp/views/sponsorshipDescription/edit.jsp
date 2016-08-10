<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="sponsorshipDescription/agent/edit.do" modelAttribute="sponsorshipDescription">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="sponsorshipDescription.title" path="title"/><br/>
	<acme:textarea code="sponsorshipDescription.text" path="text"/><br/>
	<acme:selectWithId items="${languages}" itemLabel="code" code="sponsorshipDescription.code" path="code" itemValue="code"/><br/>
	<acme:textarea code="sponsorshipDescription.infoLinks" path="infoLinks"/><br/>
	<acme:textarea code="sponsorshipDescription.tags" path="tags"/><br/>
	<acme:select items="${sponsorships}" itemLabel="name" code="sponsorshipDescription.sponsorship" path="sponsorship"/>
	
	<br></br>
	<acme:submit name="save" code="sponsorshipDescription.save"/>
	<acme:cancel code="sponsorshipDescription.cancel" url="/sponsorship/agent/list.do"/>
	<acme:delete code="sponsorshipDescription.delete" codeConfirm="sponsorshipDescription.confirm.delete" condition="${sponsorshipDescription.id != 0}"/>
	
</form:form>
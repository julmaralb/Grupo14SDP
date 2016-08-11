<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="subject/administrator/edit.do" modelAttribute="subject">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="subject.code" path="code" readonly="true"/><br/>
	<acme:textbox code="subject.title" path="title"/><br/>
	<acme:textarea code="subject.syllabus" path="syllabus"/><br/>
	<acme:select items="${lecturers}" itemLabel="userAccount.username" code="subject.lecturer" path="lecturer"/>
	
	<br></br>
	<acme:submit name="save" code="subject.save"/>
	<acme:cancel code="subject.cancel" url="/subject/list.do"/>
	<acme:delete code="subject.delete" codeConfirm="subject.confirm.delete" condition="${subject.id != 0}"/>
	
</form:form>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="syllabus/lecturer/edit.do" modelAttribute="syllabus">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="syllabus.academicYear" path="academicYear"/>
	<br />
	<acme:textbox code="syllabus.summary" path="summary"/>
	<br />
	<acme:textarea code="syllabus.goals" path="goals"/>
	<br />
	<acme:textarea code="syllabus.prerequisites" path="prerequisites"/>
	<br />
	<acme:textarea code="syllabus.evalAndGradPolicies" path="evaluationAndGradingPolicies"/>
	<br />
	<acme:select items="${subjects}" itemLabel="title" code="syllabus.subjects" path="subject"/>
	<br />
	<acme:select items="${bibliographies}" itemLabel="title" code="syllabus.bibliographies" path="bibliographies"/>
	
	<br></br>
	<acme:submit name="save" code="syllabus.save"/>
	<acme:cancel code="syllabus.cancel" url="/syllabus/list.do"/>
	<acme:delete code="syllabus.delete" codeConfirm="syllabus.confirm.delete" condition="${syllabus.id != 0}"/>
	
</form:form>
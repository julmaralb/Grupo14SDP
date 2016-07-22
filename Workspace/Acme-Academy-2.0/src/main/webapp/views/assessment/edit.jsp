<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="assessment/lecturer/edit.do" modelAttribute="assessment">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="deliverable"/>
	<form:hidden path="number"/>
	
	<acme:textbox code="assessment.explanation" path="explanation"/>
	<br />
	<acme:textbox code="assessment.points" path="points"/>
	<br />
	
	<select name="rubricId" >
	<jstl:forEach items="${rubrics}" var="r">
    	<option value="${r.id}"><jstl:out value="${r.explanation}"/></option>
    </jstl:forEach>
  	</select>
	
	
	<input type="hidden" name= "deliverableId" value="${deliverableId}"/>
	
	<br></br>
	<acme:submit name="save" code="assessment.save"/>
	<acme:cancel code="assessment.cancel" url="/assessment/list.do"/>
	<acme:delete code="assessment.delete" codeConfirm="assessment.confirm.delete" condition="${assessment.id != 0}"/>
	
</form:form>
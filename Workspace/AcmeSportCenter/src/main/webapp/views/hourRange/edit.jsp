<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="hourRange/edit.do" modelAttribute="hourRange">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<form:label path="start">
		<spring:message code="hourRange.start"/>:  
	</form:label>		
	<form:input path="start"/>
	<form:errors cssClass="error" path="start"/>
	<br></br>
	
	<form:label path="end">
		<spring:message code="hourRange.end"/>: 
	</form:label>
	<form:textarea path="end"/>
	<form:errors cssClass="error" path="end"/>
	<br></br>
	
	<form:label path="available">
		<spring:message code="hourRange.available"/>: 
	</form:label>
	<form:textarea path="available"/>
	<form:errors cssClass="error" path="available"/>
	<br></br>
	
	<input type="submit" name="save"
		value="<spring:message code="hourRange.save"/>"/>
	&nbsp
	<jstl:if test="${hourRange.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="hourRange.delete"/>"
			onclick="return confirm('<spring:message code="hourRange.confirm.delete" />')" />
		&nbsp
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="hourRange.cancel" />"
		onclick="javascript: window.location.replace('hourRange/list.do');" />
</form:form>
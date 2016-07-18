<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="day/edit.do" modelAttribute="day">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="hourRanges"/>
	
	
	<form:label path="day">
		<spring:message code="day.day"/>:  
	</form:label>		
	<form:input path="day"/>
	<form:errors cssClass="error" path="day"/>
	<br></br>
	
	<input type="submit" name="save"
		value="<spring:message code="day.save"/>"/>
	&nbsp
	<jstl:if test="${day.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="day.delete"/>"
			onclick="return confirm('<spring:message code="day.confirm.delete" />')" />
		&nbsp
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="day.cancel" />"
		onclick="javascript: window.location.replace('day/list.do');" />
</form:form>


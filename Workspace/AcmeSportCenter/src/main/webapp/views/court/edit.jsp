<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="court/administrator/edit.do" modelAttribute="court">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="days"/>
	
	
	<form:label path="name">
		<spring:message code="court.name"/>:  
	</form:label>		
	<form:input path="name"/>
	<form:errors cssClass="error" path="name"/>
	<br></br>
	
	<form:label path="category">
		<spring:message code="court.category"/>:  
	</form:label>		
	<form:input path="category"/>
	<form:errors cssClass="error" path="category"/>
	<br></br>
	
	<form:select path="centre">
		<form:option value="0">---</form:option>
		<form:options
			items="${centres}"
			itemLabel="name"
			itemValue="id"
		/>
	</form:select>
	<form:errors cssClass="error" path="centre"/>
	<br></br>
	
	<input type="submit" name="save"
		value="<spring:message code="court.save"/>"/>
	&nbsp
	<jstl:if test="${court.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="court.delete"/>"
			onclick="return confirm('<spring:message code="court.confirm.delete" />')" />
		&nbsp
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="court.cancel" />"
		onclick="javascript: window.location.replace('court/administrator/list.do');" />
</form:form>


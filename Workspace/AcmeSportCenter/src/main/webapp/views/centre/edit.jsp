<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="centre/administrator/edit.do" modelAttribute="centre">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	
	<form:label path="name">
		<spring:message code="centre.name"/>:  
	</form:label>		
	<form:input path="name"/>
	<form:errors cssClass="error" path="name"/>
	<br></br>
	
	<form:label path="address">
		<spring:message code="centre.address"/>:  
	</form:label>		
	<form:input path="address"/>
	<form:errors cssClass="error" path="address"/>
	<br></br>
	
	<form:label path="phone">
		<spring:message code="centre.phone"/>:  
	</form:label>		
	<form:input path="phone"/>
	<form:errors cssClass="error" path="phone"/>
	<br></br>
	
	<form:label path="picture">
		<spring:message code="centre.picture"/>:  
	</form:label>		
	<form:input path="picture"/>
	<form:errors cssClass="error" path="picture"/>
	<br></br>
	
	<input type="submit" name="save"
		value="<spring:message code="centre.save"/>"/>
	&nbsp
	<jstl:if test="${centre.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="centre.delete"/>"
			onclick="return confirm('<spring:message code="centre.confirm.delete" />')" />
		&nbsp
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="centre.cancel" />"
		onclick="javascript: window.location.replace('centre/list.do');" />
</form:form>


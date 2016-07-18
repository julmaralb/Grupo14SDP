<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="customer/modifyProfile.do" modelAttribute="customer">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />

	<fieldset>
  	<legend><spring:message code="customer.personalInfo"/>:</legend>
	<form:label path="name">
		<spring:message code="customer.name" />:  
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br></br>

	<form:label path="surname">
		<spring:message code="customer.surname" />:  
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br></br>

	<form:label path="email">
		<spring:message code="customer.email" />:  
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br></br>

	<form:label path="phone">
		<spring:message code="customer.phone" />:  
	</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br></br>
	
	<form:label path="address">
		<spring:message code="customer.address" />:  
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br></br>
	</fieldset>
	

	<input type="submit" name="save"
		value="<spring:message code="customer.save"/>" />
	&nbsp
	<input type="button" name="cancel"
		value="<spring:message code="customer.cancel" />"
		onclick="javascript: window.location.replace('security/login.do');" />
</form:form>
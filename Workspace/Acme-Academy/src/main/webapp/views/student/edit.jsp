<%--
 * edit.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<script>
function myFunction(str) {
	    var patt = new RegExp("^([+][0-9]{1,2})?([0-9]{3})?(?:[0-9]{4,})");
	    if(patt.test(str)==false){
	    	window.alert("The phone number introduced does not match the usual pattern continue anyways?");
	    }  
	}
</script>

<form:form action="student/edit.do" modelAttribute="studentForm">

	<fieldset>
  	<legend><spring:message code="student.personalInfo"/>:</legend>
	<acme:textbox code="student.name" path="name"/>
	<acme:textbox code="student.surname" path="surname"/>
	<acme:textbox code="student.email" path="email"/>
	<acme:idtextbox code="student.phone" id="phoneId" path="phone"/>
	</fieldset>

	<fieldset>
  	<legend><spring:message code="student.accountInfo"/>:</legend>
  	<acme:textbox code="student.username" path="username"/>
  	<acme:password code="student.password" path="password"/>
  	<acme:password code="student.secondPassword" path="secondPassword"/>
	</fieldset>
	
	<acme:checkbox code="student.termsAccepted" path="termsAccepted"/><a href="student/terms.do"> <spring:message code="student.moreInfo" /></a>
	<br></br>
	
	<button type="submit" name="save" onclick="myFunction(this.form.phoneId.value)">
	<spring:message code="student.save" />
	</button>
	<acme:cancel code="student.cancel" url="security/login.do"/>
</form:form>
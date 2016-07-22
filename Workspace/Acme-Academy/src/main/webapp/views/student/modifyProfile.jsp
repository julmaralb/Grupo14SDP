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

<form:form action="student/modifyProfile.do" modelAttribute="student">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />

	<fieldset>
  	<legend><spring:message code="student.personalInfo"/>:</legend>
	<acme:textbox code="student.name" path="name"/>
	<acme:textbox code="student.surname" path="surname"/>
	<acme:textbox code="student.email" path="email"/>
	<acme:idtextbox code="student.phone" id="phoneId" path="phone"/>	
	</fieldset>
	
	<br></br>
	<button type="submit" name="save" onclick="myFunction(this.form.phoneId.value)">
	<spring:message code="student.save" />
	</button>
	<acme:cancel code="student.cancel" url="/"/>
</form:form>
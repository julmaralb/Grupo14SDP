<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="creditCard/manager/edit.do" modelAttribute="creditCard">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="manager"/>
	<form:hidden path="campaigns"/>
	<form:hidden path="chargeRecords"/>
	
	<acme:textbox code="creditCard.holderName" path="holderName" />
	<acme:textbox code="creditCard.brandName" path="brandName" />
	<acme:textbox code="creditCard.number" path="number" />
	<acme:textbox code="creditCard.expMonth" path="expMonth" />
	<acme:textbox code="creditCard.expYear" path="expYear" />
	<acme:textbox code="creditCard.CVV" path="CVV" />
	
	<br></br>
	<acme:submit name="save" code="creditCard.save"/>
	<acme:cancel code="creditCard.cancel" url="/creditCard/manager/list.do"/>
	<acme:delete code="creditCard.delete" codeConfirm="creditCard.confirm.delete" condition="${creditCard.id != 0}"/>
	
</form:form>
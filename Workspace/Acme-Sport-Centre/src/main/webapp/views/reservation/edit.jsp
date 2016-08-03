<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="reservation/customer/edit.do" modelAttribute="reservation">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="court"/>
	<form:hidden path="customer"/>
	
	<acme:textbox code="reservation.code" path="code" readonly="true"/><br/>
	<acme:textbox code="reservation.day" path="day" readonly="true"/><br/>
	<acme:textbox code="reservation.start" path="start" readonly="true"/><br/>
	<acme:textbox code="reservation.end" path="end" readonly="true"/><br/>
	<acme:textbox code="renting.totalPrice" path="totalPrice" readonly="true"/><br/>
	
	<fieldset>
  	<legend><spring:message code="reservation.creditCard"/>:</legend>
	
	<acme:textbox code="reservation.creditCard.holderName" path="creditCard.holderName"/><br/>
	<acme:textbox code="reservation.creditCard.brandName" path="creditCard.brandName"/><br/>
	<acme:textbox code="reservation.creditCard.number" path="creditCard.number"/><br/>
	<acme:textbox code="reservation.creditCard.expMonth" path="creditCard.expMonth"/><br/>
	<acme:textbox code="reservation.creditCard.expYear" path="creditCard.expYear"/><br/>
	<acme:textbox code="reservation.creditCard.CVV" path="creditCard.CVV"/><br/>
	
	</fieldset>
	
	<input type="hidden" name= "hourRangeId" value="${hourRangeId}"/>
	
	<br></br>
	<acme:submit name="save" code="reservation.save"/>
	<acme:delete code="reservation.delete" codeConfirm="reservation.confirm.delete" condition="${reservation.id != 0}"/>
	<acme:cancel url="/centre/list.do" code="reservation.cancel"/>
	
</form:form>


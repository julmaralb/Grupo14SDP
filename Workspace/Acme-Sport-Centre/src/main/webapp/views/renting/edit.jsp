<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="renting/customer/edit.do" modelAttribute="renting">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="customer"/>
	<form:hidden path="sportEquipment"/>
	
	<acme:textbox code="renting.code" path="code" readonly="true"/><br/>
	<acme:textbox code="renting.code" path="sportEquipment.name" readonly="true"/><br/>
	<acme:textbox code="renting.day" path="day" readonly="true"/><br/>
	<acme:textbox code="renting.start" path="start" readonly="true"/><br/>
	<acme:textbox code="renting.end" path="end" readonly="true"/><br/>
	<acme:textbox code="renting.totalPrice" path="totalPrice" readonly="true"/><br/>
	
	<fieldset>
  	<legend><spring:message code="renting.creditCard"/>:</legend>
	
	<acme:textbox code="renting.creditCard.holderName" path="creditCard.holderName"/><br/>
	<acme:textbox code="renting.creditCard.brandName" path="creditCard.brandName"/><br/>
	<acme:textbox code="renting.creditCard.number" path="creditCard.number"/><br/>
	<acme:textbox code="renting.creditCard.expMonth" path="creditCard.expMonth"/><br/>
	<acme:textbox code="renting.creditCard.expYear" path="creditCard.expYear"/><br/>
	<acme:textbox code="renting.creditCard.CVV" path="creditCard.CVV"/><br/>
	
	</fieldset>
	
	<input type="hidden" name="sportEquipmentId" value="${sportEquipmentId}"/>
	
	<br></br>
	<acme:submit name="save" code="renting.save"/>
	<acme:delete code="renting.delete" codeConfirm="renting.confirm.delete" condition="${renting.id != 0}"/>
	<acme:cancel url="/renting/customer/list.do" code="renting.cancel"/>
	
</form:form>


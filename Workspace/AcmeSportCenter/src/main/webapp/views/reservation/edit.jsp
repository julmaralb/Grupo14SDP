<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="reservation/customer/edit.do" modelAttribute="reservation">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="court"/>
	<form:hidden path="customer"/>
	
	<form:label path="day">
		<spring:message code="reservation.day"/>:  
	</form:label>		
	<form:input path="day" readonly="true"/>
	<form:errors cssClass="error" path="day"/>
	<br></br>
	
	<form:label path="start">
		<spring:message code="reservation.start"/>:  
	</form:label>		
	<form:input path="start" readonly="true"/>
	<form:errors cssClass="error" path="start"/>
	<br></br>
	
	<form:label path="end">
		<spring:message code="reservation.end"/>: 
	</form:label>
	<form:input path="end" readonly="true"/>
	<form:errors cssClass="error" path="end"/>
	<br></br>
	
	<fieldset>
  	<legend><spring:message code="reservation.creditCard"/>:</legend>
	
	<form:label path="creditCard.holderName">
		<spring:message code="reservation.creditCard.holderName"/>:  
	</form:label>		
	<form:input path="creditCard.holderName"/>
	<form:errors cssClass="error" path="creditCard.holderName"/>
	<br></br>
	
	<form:label path="creditCard.brandName">
		<spring:message code="reservation.creditCard.brandName"/>:  
	</form:label>		
	<form:input path="creditCard.brandName"/>
	<form:errors cssClass="error" path="creditCard.brandName"/>
	<br></br>
	
	<form:label path="creditCard.number">
		<spring:message code="reservation.creditCard.number"/>:  
	</form:label>		
	<form:input path="creditCard.number"/>
	<form:errors cssClass="error" path="creditCard.number"/>
	<br></br>
	
	<form:label path="creditCard.expMonth">
		<spring:message code="reservation.creditCard.expMonth"/>:  
	</form:label>		
	<form:input path="creditCard.expMonth"/>
	<form:errors cssClass="error" path="creditCard.expMonth"/>
	<br></br>
	
	<form:label path="creditCard.expYear">
		<spring:message code="reservation.creditCard.expYear"/>:  
	</form:label>		
	<form:input path="creditCard.expYear" min="2000"/>
	<form:errors cssClass="error" path="creditCard.expYear"/>
	<br></br>
	
	<form:label path="creditCard.CVV">
		<spring:message code="reservation.creditCard.CVV"/>:  
	</form:label>		
	<form:input path="creditCard.CVV"/>
	<form:errors cssClass="error" path="creditCard.CVV"/>
	<br></br>
	
	</fieldset>
	
	<input type="hidden" name= "hourRangeId" value="${hourRangeId}"/>
	
	<br></br>
	
	<input type="submit" name="save"
		value="<spring:message code="reservation.save"/>"/>
	&nbsp
	<jstl:if test="${reservation.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="reservation.delete"/>"
			onclick="return confirm('<spring:message code="reservation.confirm.delete" />')" />
		&nbsp
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="reservation.cancel" />"
		onclick="javascript: window.location.replace('reservation/list.do');" />
</form:form>


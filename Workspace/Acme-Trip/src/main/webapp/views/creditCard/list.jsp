<%--
 * list.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="creditCards" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="creditCard.holderName" property="holderName"/>
	
	<acme:column code="creditCard.brandName" property="brandName"/>
	
	<acme:column code="creditCard.number" property="number"/>
	
	<acme:column code="creditCard.expMonth" property="expMonth"/>
	
	<acme:column code="creditCard.expYear" property="expYear"/>
	
	<acme:column code="creditCard.CVV" property="CVV"/>
	
	<acme:refColumn ref="chargeRecord/manager/list.do?creditCardId=${row.id}" code="creditCard.chargeRecords"/>
	
	<acme:refColumn ref="creditCard/manager/edit.do?creditCardId=${row.id}" code="creditCard.edit"/>
				
</display:table>

<div>
<a href="creditCard/manager/create.do"><spring:message code="creditCard.create" /></a>
</div>
<acme:cancel url="/" code="creditCard.back"/>
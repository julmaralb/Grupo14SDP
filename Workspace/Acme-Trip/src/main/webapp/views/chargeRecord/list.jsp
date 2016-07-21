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

<display:table name="chargeRecords" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="chargeRecord.price" property="price"/>
	
	<acme:column code="chargeRecord.tax" property="tax"/>
	
	<acme:column code="chargeRecord.moment" property="moment"/>
	
	<acme:column code="chargeRecord.creditCard" property="creditCard.number"/>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:column code="chargeRecord.manager" property="creditCard.manager.userAccount.username"/>
	</security:authorize>
				
</display:table>

<security:authorize access="hasRole('MANAGER')">
<acme:cancel url="/creditCard/manager/list.do" code="chargeRecord.back"/>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<acme:cancel url="/" code="chargeRecord.back"/>
</security:authorize>
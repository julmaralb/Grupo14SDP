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

<display:table name="customers" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="customer.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="customer.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}"
		sortable="true" />

	<spring:message code="customer.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}"
		sortable="false" />

	<spring:message code="customer.phone" var="phoneHeader" />
	<display:column property="phone" title="${phoneHeader}"
		sortable="false" />
	
	<security:authorize access="hasRole('ADMIN')">	
	<spring:message code="customer.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}"
		sortable="false" />
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="customer.username" var="userHeader" />
	<display:column property="userAccount.username" title="${userHeader}"
		sortable="false" />
	</security:authorize>

</display:table>

<input type="button" name="cancel" value="<spring:message code="customer.back" />" onclick="window.history.go(-1);" />
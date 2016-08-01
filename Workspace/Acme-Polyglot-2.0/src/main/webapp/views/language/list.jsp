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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="languages" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="language.code" property="code" sortable="true"/>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:column code="language.counter" property="counter" sortable="true"/>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="language.display" var="displayHeader"/>
	<display:column title="${displayHeader}">
	<jstl:forEach items="${row.languageDescriptions}" var="a"><a href="languageDescription/administrator/display.do?code=${a.code}&languageId=${row.id}"> <jstl:out value="${a.code}"/></a> </jstl:forEach>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:refColumn ref="language/administrator/edit.do?languageId=${row.id}" code="language.edit"/>
	</security:authorize>
	
	<acme:refColumn ref="languageExchange/listByLanguage.do?languageId=${row.id}" code="language.languageExchanges"/>
				
</display:table>
<acme:cancel url="/" code="language.back"/>
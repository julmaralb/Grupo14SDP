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

<display:table name="keywords" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="keyword.keyword" var="keywordHeader" />
	<display:column title="${keywordHeader}" sortable="true">
	<jstl:out value="${row[0]}"/>
	</display:column>
	
	<spring:message code="keyword.count" var="countHeader" />
	<display:column title="${countHeader}" sortable="true">
	<jstl:out value="${row[1]}"/>
	</display:column>
				
</display:table>
<acme:cancel url="/administrator/dashboard.do" code="keyword.back"/>
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

<display:table name="sponsorships" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="sponsorship.name" property="name" sortable="true"/>
	
	<spring:message code="sponsorship.display" var="displayHeader"/>
	<display:column title="${displayHeader}">
	<jstl:forEach items="${languages}" var="a"><a href="sponsorshipDescription/agent/display.do?code=${a.code}&sponsorshipId=${row.id}"> <jstl:out value="${a.code}"/></a> </jstl:forEach>
	</display:column>
	
	<acme:refColumn ref="sponsorship/agent/edit.do?sponsorshipId=${row.id}" code="sponsorship.edit"/>
				
</display:table>
<acme:cancel url="/" code="sponsorship.back"/>
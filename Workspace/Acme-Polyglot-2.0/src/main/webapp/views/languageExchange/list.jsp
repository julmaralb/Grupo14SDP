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

<security:authorize access="isAnonymous()">
<form:form action="languageExchange/listKeyword1.do" modelAttribute="languageExchange">

<input type="text" name="keyword">
&nbsp;
<input type="submit" name="search" value="<spring:message code="languageExchange.search" />" />
<br></br>
</form:form>
</security:authorize>

<security:authorize access="isAuthenticated()">
<form:form action="languageExchange/listKeyword2.do" modelAttribute="languageExchange">

<input type="text" name="keyword">
&nbsp;
<input type="submit" name="search" value="<spring:message code="languageExchange.search" />" />
<br></br>
</form:form>
</security:authorize>

<display:table name="languageExchanges" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="languageExchange.name" property="name" sortable="true"/>
	
	<spring:message code="languageExchange.languages" var="languageHeader" />
	<display:column title="${languageHeader}">
	<jstl:forEach items="${row.languages}" var="l">
	<jstl:out value="${l.code}"/>
	</jstl:forEach>
	</display:column>

	<acme:column code="languageExchange.registrationDate" property="registrationDate" sortable="true"/>
	
	<acme:column code="languageExchange.exchangePlace" property="exchangePlace" sortable="true"/>
	
	<acme:column code="languageExchange.cancelled" property="cancelled"/>
	
	<acme:column code="languageExchange.owner" property="owner.userAccount.username"/>	
	
	<spring:message code="languageExchange.display" var="displayHeader"/>
	<display:column title="${displayHeader}">
	<jstl:forEach items="${row.languageExchangeDescriptions}" var="a"><a href="languageExchangeDescription/display.do?code=${a.code}&languageExchangeId=${row.id}"> <jstl:out value="${a.code}"/></a> </jstl:forEach>
	</display:column>
	
	<security:authorize access="hasRole('POLYGLOT')">
	<acme:refConditionColumn ref="languageExchange/polyglot/join.do?languageExchangeId=${row.id}" code="languageExchange.join" condition="${!fn:contains(row.participants,principal)}"/>
	</security:authorize>
	
	<security:authorize access="hasRole('POLYGLOT')">
	<acme:refConditionColumn ref="languageExchange/polyglot/unJoin.do?languageExchangeId=${row.id}" code="languageExchange.unJoin" condition="${fn:contains(row.participants,principal)}"/>
	</security:authorize>
	
	<security:authorize access="hasRole('POLYGLOT')">
	<acme:refConditionColumn ref="languageExchange/polyglot/cancelExchange.do?languageExchangeId=${row.id}" code="languageExchange.cancel" condition="${row.owner == principal && row.cancelled == false}"/>
	</security:authorize>
	
	<security:authorize access="hasRole('POLYGLOT')">
	<acme:refConditionColumn ref="languageExchange/polyglot/edit.do?languageExchangeId=${row.id}" code="languageExchange.edit" condition="${row.owner == principal}"/>
	</security:authorize>
	
	<security:authorize access="hasRole('POLYGLOT')">
	<acme:refConditionColumn ref="message/actor/createBroadcast.do?languageExchangeId=${row.id}" code="languageExchange.broadcastMessage" condition="${fn:contains(row.participants,principal) || row.owner == principal}"/>
	</security:authorize>
				
</display:table>
<acme:cancel url="/" code="languageExchange.back"/>
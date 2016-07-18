<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<display:table name="openMatches" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<spring:message code="openMatch.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}" sortable="false"/>
	
	<spring:message code="openMatch.description" var="descriptionHeader"/>
	<display:column property="description" title="${descriptionHeader}" sortable="false"/>
	
	<spring:message code="openMatch.moment" var="momentHeader"/>
	<display:column property="moment" title="${momentHeader}" sortable="true"/>
	
	<spring:message code="openMatch.centre" var="centreHeader"/>
	<display:column property="reservation.court.centre.name" title="${centreHeader}" sortable="true"/>
	
	<spring:message code="openMatch.court" var="courtHeader"/>
	<display:column property="reservation.court.name" title="${courtHeader}" sortable="false"/>
	
	<spring:message code="openMatch.owner" var="ownerHeader"/>
	<display:column title="${ownerHeader}" sortable="false">
	<jstl:out value="${row.owner.name}"> </jstl:out> <jstl:out value="${row.owner.surname}"> </jstl:out>
	</display:column>
	
	<spring:message code="openMatch.numPlayers" var="numPlayersHeader"/>
	<display:column property="numPlayers" title="${numPlayersHeader}" sortable="false"/>
	
	<spring:message code="openMatch.maxPlayers" var="maxPlayersHeader"/>
	<display:column property="maxPlayers" title="${maxPlayersHeader}" sortable="false"/>
	
	<display:column>
	<a href="customer/listPlayers.do?openMatchId=<jstl:out value="${row.id}"/>"><spring:message code="openMatch.players"/></a>
	</display:column>  
	
	<security:authorize access="hasRole('CUSTOMER')">
	<display:column>
	<jstl:if test="${!fn:contains(row.players,principal)}">
	<a href="openMatch/customer/join.do?openMatchId=<jstl:out value="${row.id}"/>"><spring:message code="openMatch.join"/></a>
	</jstl:if>
	</display:column>  
	</security:authorize>
	
	<display:column> 
	<jstl:if test="${row.owner == principal}">
	<a href="openMatch/customer/edit.do?openMatchId=<jstl:out value="${row.id}"/>"><spring:message code="openMatch.edit"/></a>
	</jstl:if>
	</display:column>
	
	
</display:table>

	<div>
		<a href="openMatch/customer/create.do"> <spring:message
				code="openMatch.create" />
		</a>
	</div>
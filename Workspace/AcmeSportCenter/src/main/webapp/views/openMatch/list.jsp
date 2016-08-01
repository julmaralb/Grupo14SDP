<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="openMatches" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<acme:column code="openMatch.title" property="title"/>
	<acme:column code="openMatch.description" property="description"/>
	<acme:column code="openMatch.centre" property="reservation.court.centre.name"/>
	<acme:column code="openMatch.court" property="reservation.court.name"/>
	<acme:column code="openMatch.moment" property="moment"/>
	<acme:column code="openMatch.owner" property="owner.userAccount.username"/>
	<acme:column code="openMatch.numPlayers" property="numPlayers"/>
	<acme:column code="openMatch.maxPlayers" property="maxPlayers"/>  
	
	<acme:refColumn ref="customer/listPlayers.do?openMatchId=${row.id}" code="openMatch.players"/>
	
	<security:authorize access="hasRole('CUSTOMER')">
	<acme:refConditionColumn ref="openMatch/customer/join.do?openMatchId=${row.id}" code="openMatch.join" condition="${!fn:contains(row.players,principal)}"/>
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">
	<acme:refConditionColumn ref="openMatch/customer/edit.do?openMatchId=${row.id}" code="openMatch.edit" condition="${row.owner == principal}"/>
	</security:authorize>	
	
</display:table>

	<div>
		<a href="openMatch/customer/create.do"> <spring:message
				code="openMatch.create" />
		</a>
	</div>
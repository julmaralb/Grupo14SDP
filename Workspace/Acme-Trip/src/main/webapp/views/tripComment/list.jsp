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

<display:table name="tripComments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="activityComment.actor" property="actor.userAccount.username"/>
	
	<acme:column code="tripComment.title" property="title" />
	
	<acme:column code="tripComment.text" property="text" />
	
	<acme:column code="activityComment.moment" property="moment"/>
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:refColumn ref="comment/administrator/flagComment.do?commentId=${row.id}" code="tripComment.flagComment"/>	
	</security:authorize>
					
</display:table>

<a href="tripComment/actor/create.do?tripId=${param['tripId']}"><spring:message code="tripComment.create"></spring:message> </a> <br/>

<acme:cancel url="/trip/list.do" code="tripComment.back"/>
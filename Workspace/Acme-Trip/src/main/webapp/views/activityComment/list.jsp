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

<display:table name="activityComments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="activityComment.text" property="text"/>

	<acme:column code="activityComment.title" property="title"/>
		
	<acme:column code="activityComment.actor" property="actor.userAccount.username"/>
	
	<acme:column code="activityComment.moment" property="moment"/>
					
</display:table>

<a href="activityComment/actor/create.do?activityId=${param['activityId']}"><spring:message code="activityComment.create"></spring:message> </a> <br/>

<acme:cancel url="/trip/list.do" code="activityComment.back"/>
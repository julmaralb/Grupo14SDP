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

<display:table name="campaigns" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="campaign.title" property="title"/>
	
	<acme:column code="campaign.startMoment" property="startMoment"/>
	
	<acme:column code="campaign.finishMoment" property="finishMoment"/>
	
	<acme:column code="campaign.cancelled" property="cancelled"/>
	
	<acme:column code="campaign.creditCard" property="creditCard"/>
	
	<acme:refColumn ref="banner/manager/listByCampaign?campaignId=${row.id}" code="campaign.banners"/>
				
</display:table>

<div>
<a href="campaign/manager/create.do"><spring:message code="campaign.create" /></a>
</div>
<acme:cancel url="/" code="campaign.back"/>
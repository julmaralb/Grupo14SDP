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


<display:table name="users" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="user.name" property="name" sortable="true"/>
	
	<acme:column code="user.surname" property="surname" sortable="true"/>
	
	<acme:column code="user.email" property="email"/>
	
	<acme:column code="user.phone" property="phone"/>
	
	<acme:refColumn ref="trip/listByUser.do?userId=${row.id}" code="user.trips" />		
			
</display:table>
<acme:cancel url="/" code="user.back"/>
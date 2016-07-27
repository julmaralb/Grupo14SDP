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

<display:table name="languageExchangeDescriptions" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="languageExchangeDescription.title" property="title" sortable="true"/>
	<acme:column code="languageExchangeDescription.text" property="text" />
	<acme:column code="languageExchangeDescription.code" property="code" sortable="true"/>
	<acme:column code="languageExchangeDescription.infoLinks" property="infoLinks" />
	<acme:column code="languageExchangeDescription.tags" property="tags" />
					
</display:table>
<acme:cancel url="/" code="languageExchangeDescription.back"/>
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

<display:table name="banners" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="banner.keywords" property="keywords"/>
	
	<acme:column code="banner.maxDisplayTimes" property="maxDisplayTimes"/>
	
	<acme:column code="banner.dayDisplays" property="dayDisplays"/>
	
	<acme:onePictureColumn code="banner.photo" alt="" src="${row.photo}"/>
				
</display:table>

<div>
<a href="banner/manager/create.do"><spring:message code="banner.create" /></a>
</div>
<acme:cancel url="/campaign/manager/list.do" code="banner.back"/>
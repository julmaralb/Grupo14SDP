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

<form:form action="activityComment/listKeyword.do" modelAttribute="activityComment">

<input type="text" name="keyword">
&nbsp;
<input type="submit" name="search" value="<spring:message code="activityComment.search" />" />
<br></br>
</form:form>


<display:table name="activityComments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="activityComment.title" property="title" sortable="true"/>
	
	<acme:column code="activityComment.moment" property="moment" sortable="true"/>
	
	<acme:column code="activityComment.text" property="text" sortable="true"/>
	
	<acme:column code="activityComment.inappropriate" property="inappropriate" sortable="true"/>		
					
</display:table>
<acme:cancel url="/" code="activityComment.back"/>
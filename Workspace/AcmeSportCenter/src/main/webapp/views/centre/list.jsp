<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="centres" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<acme:onePictureColumn code="centre.picture" alt="${row.name }" src="${row.picture}"/>
	<acme:column code="centre.name" property="name" sortable="true"/>
	<acme:column code="centre.address" property="address"/>
	<acme:column code="centre.phone" property="phone"/>

	<acme:refColumn ref="day/list.do?centreId=${row.id}" code="centre.days"/>
	  	
	<security:authorize access="hasRole('ADMIN')">  	
	<acme:refColumn ref="centre/administrator/edit.do?centreId=${row.id}" code="centre.edit"/>
	</security:authorize>
	
</display:table>

	<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="centre/administrator/create.do"> <spring:message code="centre.create" /></a>
	</div>
	</security:authorize>
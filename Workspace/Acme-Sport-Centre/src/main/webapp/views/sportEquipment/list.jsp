<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="sportEquipments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<acme:onePictureColumn code="sportEquipment.picture" alt="${row.name}" src="${row.picture}"/>
	<acme:column code="sportEquipment.SKU" property="SKU"/>
	<acme:column code="sportEquipment.name" property="name" sortable="true"/>
	<acme:column code="sportEquipment.price" property="price"/>
	
	<input type="hidden" name= "reservationId" value="${reservationId}"/>
	
	<security:authorize access="hasRole('CUSTOMER')">
	<acme:refColumn ref="renting/customer/create.do?sportEquipmentId=${row.id}&reservationId=${reservationId}" code="sportEquipment.rent"/>
	</security:authorize>
	  	
	<security:authorize access="hasRole('ADMIN')">  	
	<acme:refColumn ref="sportEquipment/administrator/edit.do?sportEquipmentId=${row.id}" code="sportEquipment.edit"/>
	</security:authorize>
	
</display:table>

	<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="sportEquipment/administrator/create.do"> <spring:message code="sportEquipment.create" /></a>
	</div>
	</security:authorize>
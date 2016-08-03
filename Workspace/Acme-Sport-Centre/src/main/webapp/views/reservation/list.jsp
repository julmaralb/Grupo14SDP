<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="reservations" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<security:authorize access="hasRole('ADMIN')">
	<acme:column code="reservation.customer" property="customer.userAccount.username"/>
	</security:authorize>
	
	<acme:column code="reservation.code" property="code"/>
	
	<acme:column code="reservation.centre" property="court.centre.name"/>
	
	<acme:column code="reservation.court" property="court.name"/>
	
	<acme:column code="reservation.totalPrice" property="totalPrice"/>
	
	<acme:column code="reservation.day" property="day"/>
	
	<acme:column code="reservation.start" property="start"/>
	
	<acme:column code="reservation.end" property="end"/>
	
	<acme:refColumn ref="sportEquipment/customer/listByCourt.do?reservationId=${row.id}" code="reservation.rent"/>
	
</display:table>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="days" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<acme:column code="day.day" property="day" sortable="true"/>
	<acme:refColumn ref="reservation/schedule.do?dayId=${row.id}&centreId=${centreId}" code="day.schedules"/>
	
</display:table>

<acme:cancel url="/centre/list.do" code="customer.back"/>
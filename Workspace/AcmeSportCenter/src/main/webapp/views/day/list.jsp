<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="days" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<spring:message code="day.day" var="dayHeader"/>
	<display:column property="day" title="${dayHeader}" sortable="true"/>
	  	
	<security:authorize access="hasRole('ADMIN')">
	<display:column> <a href="day/edit.do?dayId=<jstl:out value="${row.id}"/>"><spring:message code="day.edit"/></a></display:column>
	</security:authorize>
	
	<display:column> <a href="reservation/schedule.do?dayId=<jstl:out value="${row.id}"/>&centreId=<jstl:out value="${centreId}"/>"><spring:message code="day.schedules"/></a></display:column>
	
</display:table>
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="day/create.do"> <spring:message
				code="day.create" />
		</a>
	</div>
</security:authorize>

<input type="button" name="cancel" value="<spring:message code="customer.back" />" onclick="window.history.go(-1);" />
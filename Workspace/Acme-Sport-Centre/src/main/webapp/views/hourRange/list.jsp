<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="hourRange" id="row" requestURI="hourRange/list.do"
	pagesize="5" class="displaytag">
	
	<spring:message code="hourRange.start" var="startHeader"/>
	<display:column property="start" title="${startHeader}" sortable="true"/>
	
	<spring:message code="hourRange.end" var="endHeader"/>
	<display:column property="end" title="${endHeader}" sortable="false"/>
	
	<spring:message code="hourRange.available" var="availableHeader"/>
	<display:column property="available" title="${availableHeader}" sortable="false"/>
	  	
	<display:column> <a href="hourRange/edit.do?hourRangeId=<jstl:out value="${row.id}"/>"><spring:message code="hourRange.edit"/></a></display:column>
	
</display:table>

	<div>
		<a href="hourRange/create.do"> <spring:message
				code="hourRange.create" />
		</a>
	</div>
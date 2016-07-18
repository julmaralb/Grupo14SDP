<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="centres" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<spring:message code="centre.picture" var="pictureHeader"/>
	<display:column><center><img src="${row.picture }" alt="${row.name }" height="80" ></center>
	</display:column>

	<spring:message code="centre.name" var="nameHeader"/>
	<display:column property="name" title="${nameHeader}" sortable="true"/>
	
	<spring:message code="centre.address" var="addressHeader"/>
	<display:column property="address" title="${addressHeader}" sortable="false"/>
	
	<spring:message code="centre.phone" var="phoneHeader"/>
	<display:column property="phone" title="${phoneHeader}" sortable="false"/>
	
	<display:column> <a href="day/list.do?centreId=<jstl:out value="${row.id}"/>"><spring:message code="centre.days"/></a></display:column>
	  	
	<security:authorize access="hasRole('ADMIN')">  	
	<display:column> <a href="centre/administrator/edit.do?centreId=<jstl:out value="${row.id}"/>"><spring:message code="centre.edit"/></a></display:column>
	</security:authorize>
	
</display:table>

	<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="centre/administrator/create.do"> <spring:message
				code="centre.create" />
		</a>
	</div>
	</security:authorize>
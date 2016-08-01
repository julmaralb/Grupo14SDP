<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table name="actors" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="actor.name" property="name" sortable="true"/>
	
	<acme:column code="actor.surname" property="surname" sortable="true"/>
	
	<acme:column code="actor.email" property="email"/>
	
	<acme:column code="actor.phone" property="phone"/>
	
	<acme:column code="actor.username" property="userAccount.username"/>
	
	<acme:refConditionColumn ref="actor/administrator/ban.do?actorId=${row.id}" code="actor.ban" condition="${true}"/>
			
</display:table>
<acme:cancel url="/" code="actor.back"/>
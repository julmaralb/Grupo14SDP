<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table name="polyglots" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="polyglot.name" property="name" sortable="true"/>
	
	<acme:column code="polyglot.surname" property="surname" sortable="true"/>
	
	<acme:column code="polyglot.email" property="email"/>
	
	<acme:column code="polyglot.phone" property="phone"/>
			
</display:table>
<acme:cancel url="/" code="polyglot.back"/>
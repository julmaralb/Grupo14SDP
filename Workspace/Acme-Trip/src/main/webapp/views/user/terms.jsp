<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<p style ="font-size:18px">
	<spring:message code="user.terms" />
</p>

<spring:message code="user.termsText" />

<p style ="font-size:18px">
	<spring:message code="user.cookies" />
</p>

<spring:message code="user.cookiesText" />
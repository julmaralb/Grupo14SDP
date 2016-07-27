<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<fieldset>
	<legend><spring:message code="languageDescription.details"/></legend>
	<div>
    	<spring:message code="languageDescription.title"/> : <jstl:out value="${languageDescription.title}"></jstl:out><br/>
    	<spring:message code="languageDescription.text"/> : <jstl:out value="${languageDescription.text}"></jstl:out><br/>
    	<spring:message code="languageDescription.code"/> : <jstl:out value="${languageDescription.code}"></jstl:out><br/>
    	<spring:message code="languageDescription.infoLinks"/> : 
    	<jstl:forEach items="${languageDescription.infoLinks}" var="il">
    	<jstl:out value="${il}"></jstl:out>
    	</jstl:forEach><br/>
    	<spring:message code="languageDescription.tags"/> : 
    	<jstl:forEach items="${languageDescription.tags}" var="t">
    	<jstl:out value="${t}"></jstl:out>
    	</jstl:forEach><br/>
    </div>
  </fieldset>
<br/>

<acme:cancel url="/language/administrator/list.do" code="languageDescription.back"/>
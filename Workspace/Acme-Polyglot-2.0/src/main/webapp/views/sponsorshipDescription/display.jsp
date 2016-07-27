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
	<legend><spring:message code="sponsorshipDescription.details"/></legend>
	<div>
    	<spring:message code="sponsorshipDescription.title"/> : <jstl:out value="${sponsorshipDescription.title}"></jstl:out><br/>
    	<spring:message code="sponsorshipDescription.text"/> : <jstl:out value="${sponsorshipDescription.text}"></jstl:out><br/>
    	<spring:message code="sponsorshipDescription.code"/> : <jstl:out value="${sponsorshipDescription.code}"></jstl:out><br/>
    	<spring:message code="sponsorshipDescription.infoLinks"/> : 
    	<jstl:forEach items="${sponsorshipDescription.infoLinks}" var="il">
    	<jstl:out value="${il}"></jstl:out>
    	</jstl:forEach><br/>
    	<spring:message code="sponsorshipDescription.tags"/> : 
    	<jstl:forEach items="${sponsorshipDescription.tags}" var="t">
    	<jstl:out value="${t}"></jstl:out>
    	</jstl:forEach><br/>
    </div>
  </fieldset>
<br/>

<acme:cancel url="/sponsorship/agent/list.do" code="sponsorshipDescription.back"/>
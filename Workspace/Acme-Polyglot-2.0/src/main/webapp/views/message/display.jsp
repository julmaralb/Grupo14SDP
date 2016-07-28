<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<fieldset>
	<legend><spring:message code="message.details"/></legend>
	<div>
    	<spring:message code="message.sender"/> : <jstl:out value="${msg.sender.email}"></jstl:out><br/>
    	<spring:message code="message.recipient"/> : <jstl:out value="${msg.recipient.email}"></jstl:out><br/>
    	<spring:message code="message.code"/> : <jstl:out value="${msg.code}"></jstl:out><br/>
    	<spring:message code="message.title"/> : <jstl:out value="${msg.title}"></jstl:out><br/>
    	<spring:message code="message.text"/> : <jstl:out value="${msg.text}"></jstl:out><br/>
    	<spring:message code="message.infoLinks"/> : <jstl:out value="${msg.infoLinks}"></jstl:out><br/>
    	<spring:message code="message.tags"/> : <jstl:out value="${msg.tags}"></jstl:out><br/>
  		<spring:message code="message.sentMoment"/> : <jstl:out value="${msg.sentMoment}"></jstl:out><br/>
  		<spring:message code="message.receivedMoment"/> : <jstl:out value="${msg.receivedMoment}"></jstl:out><br/>
    </div>
  </fieldset>
<br/>
<acme:cancel url="folder/actor/list.do" code="message.back"/>

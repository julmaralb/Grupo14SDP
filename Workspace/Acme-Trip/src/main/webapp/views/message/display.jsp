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
    	<spring:message code="message.priority"/> : 
    	<jstl:choose>
    		<jstl:when test="${msg.priority==1}">
    			<spring:message code="message.priority.low" var="priority" /><jstl:out value="${priority}"/>
    		</jstl:when>
    		<jstl:when test="${msg.priority==2}">
    			<spring:message code="message.priority.neutral" var="priority" /><jstl:out value="${priority}"/>
    		</jstl:when>
    		<jstl:when test="${msg.priority==3}">
    			<spring:message code="message.priority.high" var="priority" /><jstl:out value="${priority}"/>
    		</jstl:when>
    	</jstl:choose>
    	<br/>
    	<spring:message code="message.subject"/> : <jstl:out value="${msg.subject}"></jstl:out><br/>
    	<spring:message code="message.moment"/> : <jstl:out value="${msg.moment}"></jstl:out><br/>
    	<spring:message code="message.body"/> : <jstl:out value="${msg.body}"></jstl:out><br/>
    </div>
  </fieldset>
<br/>
<acme:cancel url="folder/actor/list.do" code="message.back"/>

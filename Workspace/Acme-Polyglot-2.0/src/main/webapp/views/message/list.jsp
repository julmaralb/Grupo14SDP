<%--
 * list.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<a href="message/actor/create.do"><spring:message code="message.sendMessage"/></a> 

<display:table name="messages" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="message.sender" property="sender.email" sortable="true"/>
	
	<acme:column code="message.recipient" property="recipient.email" sortable="true"/>
	
	<acme:column code="message.code" property="code"/>
	
	<acme:column code="message.title" property="title"/>
	
	<acme:column code="message.text" property="text"/>
	
	<acme:column code="message.infoLinks" property="infoLinks"/>
	
	<acme:column code="message.tags" property="tags"/>
	
	<acme:column code="message.sentMoment" property="sentMoment"/>
	
	<acme:column code="message.receivedMoment" property="receivedMoment"/>
	
	<acme:refColumn ref="message/actor/display.do?messageId=${row.id}" code="message.display"/>
	
	<acme:refConditionColumn ref="message/actor/spam.do?messageId=${row.id}" code="message.spam" condition="${row.folder.id != spamId}"/>
    
    <spring:message code="message.move" var="moveHeader"/>
	<display:column title="${moveHeader}" sortable="false">
	<form:form action="message/actor/move.do">
	<select name="folder">
	<jstl:forEach items="${folders}" var="f">
		 <option value="${f.id}"><jstl:out value="${f.name}"/><option>
	</jstl:forEach>
	</select>
	<input type="hidden" name="messageId" value="${row.id}">
	<input type="submit" name="move" value="<spring:message code="message.move"/>"/>
	</form:form>
	</display:column>
    
	<acme:refConditionConfirmDeleteColumn ref="message/actor/delete.do?messageId=${row.id}" code="message.delete" codeConfirm="message.confirm.delete" condition="${true}"/>
				
</display:table>

<acme:cancel url="folder/actor/list.do" code="message.back"/>
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

<a href="message/actor/create.do"><spring:message code="folder.sendMessage"/></a> 

<display:table name="folders" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<acme:column code="folder.name" property="name" sortable="true"/>
	
	<acme:refColumn ref="message/actor/list.do?folderId=${row.id}" code="folder.ViewMessages"/>
	
	<acme:refConditionColumn ref="folder/actor/edit.do?folderId=${row.id}" code="folder.edit" condition="${!row.isSystem}"/>
	
	<acme:refConditionConfirmDeleteColumn ref="folder/actor/deleteFolder.do?folderId=${row.id}" code="folder.delete" codeConfirm="folder.confirm.delete" condition="${!row.isSystem}"/>
				
</display:table>

<div>
<a href="folder/actor/create.do"><spring:message code="folder.create" /></a>
</div>
<acme:cancel url="/" code="folder.back"/>
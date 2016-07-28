<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="message/actor/edit.do" modelAttribute="messa">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="sender" />
	<form:hidden path="sentMoment" />
	<form:hidden path="receivedMoment" />
	<form:hidden path="folder" />
	
	
	<acme:select items="${recipients}" itemLabel="email" code="message.recipient" path="recipient"/>
	<br />
	
	<acme:textbox code="message.code" path="code"/>
	<br />
	
	<acme:textbox code="message.title" path="title"/>
	<br />
	
	<acme:textarea code="message.text" path="text"/>
	<br />
	
	<acme:textarea code="message.infoLinks" path="infoLinks"/>
	<br />
	
	<acme:textarea code="message.tags" path="tags"/>
	<br />
	
	<acme:submit name="send" code="message.send"/>
	<acme:cancel code="message.cancel" url="/folder/actor/list.do"/>
	<acme:delete code="message.delete" codeConfirm="message.confirm.delete" condition="${messa.id != 0}"/>
	
</form:form>
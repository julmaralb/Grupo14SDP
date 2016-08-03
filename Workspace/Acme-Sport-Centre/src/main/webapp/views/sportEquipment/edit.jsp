<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="sportEquipment/administrator/edit.do" modelAttribute="sportEquipment">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
		
	<acme:textbox code="sportEquipment.SKU" path="SKU" readonly="true"/><br/>
	<acme:textbox code="sportEquipment.name" path="name"/><br/>
	<acme:textbox code="sportEquipment.picture" path="picture"/><br/>
	<acme:textbox code="sportEquipment.price" path="price"/><br/>
	<acme:select items="${courts}" itemLabel="name" code="sportEquipment.court" path="court"/>
	
	<acme:submit name="save" code="sportEquipment.save"/>
	<acme:delete code="sportEquipment.delete" codeConfirm="sportEquipment.confirm.delete" condition="${sportEquipment.id != 0}"/>
	<acme:cancel url="/sportEquipment/administrator/list.do" code="sportEquipment.cancel"/>

</form:form>


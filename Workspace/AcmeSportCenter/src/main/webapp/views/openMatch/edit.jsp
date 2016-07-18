<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="openMatch/customer/edit.do" modelAttribute="openMatch">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="players"/>
	<form:hidden path="numPlayers"/>
	<form:hidden path="owner"/>
	
	<form:label path="title">
		<spring:message code="openMatch.title"/>:  
	</form:label>		
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br></br>
	
	<form:label path="description">
		<spring:message code="openMatch.description"/>:  
	</form:label>		
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="description"/>
	<br></br>
	
	<form:label path="moment">
		<spring:message code="openMatch.moment"/>:  
	</form:label>		
	<form:input path="moment"/>
	<form:errors cssClass="error" path="moment"/>
	<br></br>
	
	<form:label path="maxPlayers">
		<spring:message code="openMatch.maxPlayers"/>:  
	</form:label>		
	<form:input path="maxPlayers"/>
	<form:errors cssClass="error" path="maxPlayers"/>
	<br></br>
	
	<form:select path="reservation">
		<form:option value="0">---</form:option>
		<form:options
			items="${reservations}"
			itemLabel="day"
			itemValue="id"
		/>
	</form:select>
	<form:errors cssClass="error" path="reservation"/>
	<br></br>
	
	<input type="submit" name="save"
		value="<spring:message code="openMatch.save"/>"/>
	&nbsp
	<jstl:if test="${openMatch.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="openMatch.delete"/>"
			onclick="return confirm('<spring:message code="openMatch.confirm.delete" />')" />
		&nbsp
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="openMatch.cancel" />"
		onclick="window.history.go(-1);" />
</form:form>
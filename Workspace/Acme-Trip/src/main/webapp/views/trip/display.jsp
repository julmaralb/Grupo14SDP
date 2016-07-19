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
	<legend><spring:message code="trip.details"/></legend>
	<div>
    	<spring:message code="trip.title"/> : <jstl:out value="${trip.title}"></jstl:out><br/>
    	<spring:message code="trip.startingDate"/> : <jstl:out value="${trip.startingDate}"></jstl:out><br/>
    	<spring:message code="trip.endingDate"/> : <jstl:out value="${trip.endingDate}"></jstl:out><br/>
    	<spring:message code="trip.description"/> : <jstl:out value="${trip.description}"></jstl:out><br/>
    	<spring:message code="trip.photos"/> : 
    	<jstl:forEach items="${trip.photos}" var="p">
    	<img src="${p}" alt="${trip.title}" width="244" height="166">
    	</jstl:forEach><br/>
    </div>
  </fieldset>
<br/>

<fieldset>
	<legend><spring:message code="trip.dailyPlans"/></legend>
	<div>
		<jstl:forEach items="${trip.dailyPlans}" var="dp">
			<spring:message code="dailyPlan.weekDay"/> : <jstl:out value="${dp.weekDay}"></jstl:out><br/>
    		<spring:message code="dailyPlan.title"/> : <jstl:out value="${dp.title}"></jstl:out><br/>
    		<spring:message code="dailyPlan.description"/> : <jstl:out value="${dp.description}"></jstl:out><br/>
    		<spring:message code="dailyPlan.photos"/> : 
    		<jstl:forEach items="${dp.photos}" var="dpp">
    		<img src="${dpp}" alt="${dp.title}" width="244" height="166">
    		</jstl:forEach><br/><br/>
    		
    		<fieldset>
			<legend><spring:message code="dailyPlan.slots"/></legend>
			<div>
    			<jstl:forEach items="${dp.slots}" var="s">
    				<spring:message code="slot.title"/> : <jstl:out value="${s.title}"></jstl:out><br/>
    				<spring:message code="slot.startTime"/> : <jstl:out value="${s.startTime}"></jstl:out><br/>
    				<spring:message code="slot.endTime"/> : <jstl:out value="${s.endTime}"></jstl:out><br/>
    				<spring:message code="slot.description"/> : <jstl:out value="${s.description}"></jstl:out><br/>
    				<spring:message code="slot.photos"/> : 
    				<jstl:forEach items="${s.photos}" var="sp">
    					<img src="${sp}" alt="${s.title}" width="244" height="166">
    				</jstl:forEach><br/>
    			</jstl:forEach><br/><br/>
			</div>
 			</fieldset><br/>
			</jstl:forEach>
			
	</div>
  </fieldset>
<acme:cancel url="trip/list.do" code="trip.back"/>
<%--
 * dashboard.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h2><spring:message code="dashboard.keywords.statistics"/></h2>
<acme:cancel url="/actor/administrator/list.do" code="dashboard.actors.keywords.statistics"/>
<acme:cancel url="/keyword/administrator/list.do" code="dashboard.general.keywords.statistics"/>

<h2><spring:message code="dashboard.languagesAndCountExchanges"/></h2>
<display:table name="languagesAndCountOfExchangesInvolved" id="row" requestURI="administrator/dashboard.do"
	pagesize="5" class="displaytag">

	<spring:message code="dashboard.languages" var="languagesHeader" />
	<display:column title="${languagesHeader}">
	<jstl:out value="${row[0].code}"/>
	</display:column>
	
	<spring:message code="dashboard.exchanges" var="exchangesHeader" />
	<display:column title="${exchangesHeader}" sortable="true">
	<jstl:out value="${row[1]}"/>
	</display:column>
	
</display:table>

<h2><spring:message code="dashboard.polyglotsAndCountExchanges"/></h2>
<display:table name="polyglotsAndCountOfExchangesOrganised" id="row" requestURI="administrator/dashboard.do"
	pagesize="5" class="displaytag">

	<spring:message code="dashboard.polyglots" var="polyglotsHeader" />
	<display:column title="${polyglotsHeader}">
	<jstl:out value="${row[0].userAccount.username}"/>
	</display:column>
	
	<spring:message code="dashboard.exchanges" var="exchangesHeader" />
	<display:column title="${exchangesHeader}" sortable="true">
	<jstl:out value="${row[1]}"/>
	</display:column>
	
</display:table>

<h2><spring:message code="dashboard.polyglotsAndCountExchangesJ"/></h2>
<display:table name="polyglotsAndCountOfExchangesJoined" id="row" requestURI="administrator/dashboard.do"
	pagesize="5" class="displaytag">

	<spring:message code="dashboard.polyglots" var="polyglotsHeader" />
	<display:column title="${polyglotsHeader}">
	<jstl:out value="${row[0].userAccount.username}"/>
	</display:column>
	
	<spring:message code="dashboard.exchanges" var="exchangesHeader" />
	<display:column title="${exchangesHeader}" sortable="true">
	<jstl:out value="${row[1]}"/>
	</display:column>
	
</display:table>

<h2><spring:message code="dashboard.exchangesAndCountSponsorships"/></h2>
<display:table name="languageExchangesAndCountOfSponsorships" id="row" requestURI="administrator/dashboard.do"
	pagesize="5" class="displaytag">

	<spring:message code="dashboard.exchanges" var="exchangesHeader" />
	<display:column title="${exchangesHeader}">
	<jstl:out value="${row[0].name}"/>
	</display:column>
	
	<spring:message code="dashboard.sponsorships" var="sponsorshipsHeader" />
	<display:column title="${sponsorshipsHeader}" sortable="true">
	<jstl:out value="${row[1]}"/>
	</display:column>
	
</display:table>

<h2><spring:message code="dashboard.polyglotsAndCountSponsorships"/></h2>
<display:table name="polyglotsAndCountOfSponsoredExchanges" id="row" requestURI="administrator/dashboard.do"
	pagesize="5" class="displaytag">

	<spring:message code="dashboard.polyglots" var="polyglotsHeader" />
	<display:column title="${polyglotsHeader}">
	<jstl:out value="${row[0].userAccount.username}"/>
	</display:column>
	
	<spring:message code="dashboard.sponsorships" var="sponsorshipsHeader" />
	<display:column title="${sponsorshipsHeader}" sortable="true">
	<jstl:out value="${row[1]}"/>
	</display:column>
	
</display:table>

<h2><spring:message code="dashboard.avgMinMaxSponsorsPerPolyglot"/></h2>
<table style="width:100%">
  <tr>
    <th><spring:message code="dashboard.avg"/></th>
    <th><spring:message code="dashboard.minimun"/></th> 
    <th><spring:message code="dashboard.maximun"/></th>
  </tr>
  <tr>
  	<jstl:forEach items="${avgMinMaxSponsoredExchangesPerPolyglot}" var="a">
    <td><jstl:out value="${a[0]}" /></td>
    <td><jstl:out value="${a[1]}" /></td>
    <td><jstl:out value="${a[2]}" /></td>
    </jstl:forEach>
  </tr>

</table>

<h2><spring:message code="dashboard.meanMinMaxMessagesPerFolder"/></h2>
<table style="width:100%">
  <tr>
    <th><spring:message code="dashboard.mean"/></th>
    <th><spring:message code="dashboard.minimun"/></th> 
    <th><spring:message code="dashboard.maximun"/></th>
  </tr>
  <tr>
  	<jstl:forEach items="${meanMinMaxMessagesPerFolder}" var="a">
    <td><jstl:out value="${a[0]}" /></td>
    <td><jstl:out value="${a[1]}" /></td>
    <td><jstl:out value="${a[2]}" /></td>
    </jstl:forEach>
  </tr>

</table>

<h2><spring:message code="dashboard.ratioLaguagesMessages"/></h2>
<display:table name="ratioLaguagesMessages" id="row" requestURI="administrator/dashboard.do"
	pagesize="5" class="displaytag">

	<spring:message code="dashboard.languages" var="languagesHeader" />
	<display:column title="${languagesHeader}">
	<jstl:out value="${row[0]}"/>
	</display:column>
	
	<spring:message code="dashboard.ratio" var="ratioHeader" />
	<display:column title="${ratioHeader}" sortable="true">
	<jstl:out value="${row[1]}"/>
	</display:column>
	
</display:table>
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


<table id="administrator" border="1">

<tr>
  	<td></td>
    <td><spring:message code="administrator.dashboard"/></td>
  </tr>

 <tr>
    <th><spring:message code="administrator.userRegistered"/></th>
	
	<td><jstl:out value=" ${userRegistered}"/></td>
  </tr>	
  
   <tr>
    <th><spring:message code="administrator.numbersTrip"/></th>
	
	<td><jstl:out value=" ${numbersTrip}"/></td>
  </tr>	
  
 <tr>
    <th><spring:message code="administrator.avgTripsPerUser"/></th>
	
	<td><jstl:out value=" ${avgTripsPerUser}"/></td>
  </tr>	
  
  <tr>
    <th><spring:message code="administrator.standardDevTripsPerUser"/></th>
	
	<td><jstl:out value=" ${standardDevTripsPerUser}"/></td>
  </tr>	



<tr>
    <th><spring:message code="administrator.avgDailyPlansPerUser"/></th>
	
	<td><jstl:out value=" ${avgDailyPlansPerUser}"/></td>
  </tr>	
  
  <tr>
    <th><spring:message code="administrator.standardDevTripsPerUser"/></th>
	
	<td><jstl:out value=" ${standardDevDailyPlansPerUser}"/></td>
  </tr>	

<tr>

  <th><spring:message code="administrator.usersWithMoreThan80PMaxTripsRegistered"/></th>
 
  <td>
  <display:table name="usersWithMoreThan80PMaxTripsRegistered" id="row" requestURI="administrator/dashboard.do" class="displaytag">
		
		</display:table>
  </td>
  
  </tr>
  
	<%--Falta hace el usuarios inactivos --%>
	<%--Falta hace el usuarios inactivos --%>
	<%--Falta hace el usuarios inactivos --%>
	
							<%--DashBoard B --%>

<tr>

  <th><spring:message code="administrator.minMaxAvgCampaignsPerManager"/></th>
 
  <td>
		<jstl:forEach items="${minMaxAvgCampaignsPerManager}" var="a"><jstl:out value="${a}"/></jstl:forEach>
  </td>
  
  </tr>


<tr>
    <th><spring:message code="administrator.avgAmountMoneyPerCampaign"/></th>
	
	<td><jstl:out value=" ${avgAmountMoneyPerCampaign}"/></td>
  </tr>

<tr>

  <th><spring:message code="administrator.avgAndStandardDevDaysThatCampaignsLast"/></th>
 
 <td><jstl:out value=" ${avgAndStandardDevDaysThatCampaignsLast}"/></td>
  
  </tr>

<tr>
	<th><spring:message code="administrator.managerWithMoreCampaigns"/></th>
 
  <td>
  <display:table name="managerWithMoreCampaigns" id="row" requestURI="administrator/dashboard.do" class="displaytag">
		
		</display:table>
  </td>
  </tr>
  
  <tr>
	<th><spring:message code="administrator.activeBannersDisplayedMoreThan10PAvg"/></th>
 
  <td>
  <display:table name="activeBannersDisplayedMoreThan10PAvg" id="row" pagesize="5" requestURI="administrator/dashboard.do" class="displaytag">
  	<spring:message code="administrator.activeBannersDisplayedMoreThan10PAvg" var="photoHeader" />
	<display:column title="${photoHeader}" sortable="false" ><img src="${row.photo }" alt="${row.id}" height="80">
	</display:column>
  </display:table>
  </td>
  </tr>
  
  <tr>
	<th><spring:message code="administrator.activeBannersDisplayedLessThan10PAvg"/></th>
 
  <td>
  <display:table name="activeBannersDisplayedLessThan10PAvg" id="row" pagesize="5" requestURI="administrator/dashboard.do" class="displaytag">
  	<spring:message code="administrator.activeBannersDisplayedLessThan10PAvg" var="photoHeader" />
	<display:column title="${photoHeader}" sortable="false" ><img src="${row.photo }" alt="${row.id}" height="80">
	</display:column>
  </display:table>
  </td>
  </tr>
  
 
</table>
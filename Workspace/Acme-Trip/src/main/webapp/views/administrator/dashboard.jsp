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
  
<tr>

  <th><spring:message code="administrator.userInactiveMoreOneyear"/></th>
 
  <td>
  <display:table name="userInactiveMoreOneyear" id="row" requestURI="administrator/dashboard.do" class="displaytag">
		
		</display:table>
  </td>
  
  </tr>
	
							<%--DashBoard B --%>

<tr>

  <th><spring:message code="administrator.minMaxAvgCampaignsPerManager"/></th>
 
  <td>
	<display:table name="minMaxAvgCampaignsPerManager" id="row" requestURI="administrator/dashboard.do" class="displaytag">
	<display:column>
	<jstl:forEach items="${row}" var="a"><jstl:out value="${a}"/> </jstl:forEach>
	</display:column>
	</display:table>
  </td>
  
  </tr>


<tr>
    <th><spring:message code="administrator.avgAmountMoneyPerCampaign"/></th>
	
	<td><jstl:out value=" ${avgAmountMoneyPerCampaign}"/></td>
  </tr>

<tr>

  <th><spring:message code="administrator.avgAndStandardDevDaysThatCampaignsLast"/></th>
 
 <td>
	<display:table name="avgAndStandardDevDaysThatCampaignsLast" id="row" requestURI="administrator/dashboard.do" class="displaytag">
	<display:column>
	<jstl:forEach items="${row}" var="a"><jstl:out value="${a}"/> </jstl:forEach>
	</display:column>
	 </display:table>
</td>
 	
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
  <display:table name="activeBannersDisplayedMoreThan10PAvg" id="row" pagesize="3" requestURI="administrator/dashboard.do" class="displaytag">
	<display:column ><img src="${row.photo }" alt="${row.id}" height="80">
	</display:column>
  </display:table>
  </td>
  </tr>
  
  <tr>
	<th><spring:message code="administrator.activeBannersDisplayedLessThan10PAvg"/></th>
 
  <td>
  <display:table name="activeBannersDisplayedLessThan10PAvg" id="row" pagesize="3" requestURI="administrator/dashboard.do" class="displaytag">
	<display:column><img src="${row.photo }" alt="${row.id}" height="80">
	</display:column>
  </display:table>
  </td>
  </tr>
  
 
</table>
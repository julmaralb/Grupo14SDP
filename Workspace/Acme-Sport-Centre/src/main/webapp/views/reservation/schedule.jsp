<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    height: 40px;
}
</style>
</head>
<body>

<table>
    <tr>
        <th>
        <jstl:forEach items="${hourRanges}" var="hr"> 
        <th><fmt:formatDate value="${hr.start}" pattern="HH:mm"/>-<fmt:formatDate value="${hr.end}" pattern="HH:mm"/></th>  
        </jstl:forEach>
        </th>
    </tr>
    <jstl:forEach items="${days}"  var="x">
    <tr>
        <th scope="row" ><jstl:out value="${x.court.name} (${x.court.category})"></jstl:out></th>   
        	<jstl:forEach items="${x.hourRanges}"  var="z">
        		<jstl:choose>
        			<jstl:when test="${z.available==true}">
        			
        			<security:authorize access="isAuthenticated()">
        			<td scope="row" bgcolor="#d3e8a3" >
        				<div style="text-align:center">
        					<form:form action="reservation/customer/createReservation.do" modelAttribute="reservation">
        					<input type="hidden" name="courtId" value="${x.court.id}">
        					<input type="hidden" name="hourRangeId" value="${z.id}">
        					<input type="hidden" name="dayId" value="<%= request.getParameter("dayId") %>">
							<input type="submit" name="bookCourt" value="<spring:message code="reservation.book" />" />
							<br></br>
							</form:form>
						</div>
					</td>
					</security:authorize>
					<security:authorize access="isAnonymous()">
					<td scope="row" bgcolor="#d3e8a3">
						<spring:message code="reservation.available" />
					</td>
					</security:authorize>
					
        			</jstl:when>
        			<jstl:when test="${z.available==false}">
        			<td scope="row" bgcolor="#e34b3d">
        				<spring:message code="reservation.notAvailable" />
        			</td>
        			</jstl:when>
        		</jstl:choose>
        	</jstl:forEach>   	
    </tr>
    </jstl:forEach>
</table>

<input type="button" name="cancel" value="<spring:message code="customer.back" />" onclick="window.history.go(-1);" />

</body>
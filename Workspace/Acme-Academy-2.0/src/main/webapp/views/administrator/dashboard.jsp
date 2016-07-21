<%--
 * dashboard.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
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


<table id="consumers" border="1">
  <tr>
  	<td></td>
    <td><spring:message code="administrator.dashboard"/></td>
  </tr>
  
  <tr>
    <th><spring:message code="administrator.averageStudentPerSubject"/></th>
	
	<td><jstl:out value=" ${averageStudentPerSubject}"/></td>
  </tr>		
  
  
  <tr>

  <th><spring:message code="administrator.studentsEnrolledMoreOrLessThan20PAvg"/></th>
 
  <td>
  <display:table name="studentRolesSubject" id="row" requestURI="administrator/dashboard.do" class="displaytag">
		
		</display:table>
  </td>
  
  </tr>
  
  
    <tr>
    <th><spring:message code="administrator.averageSubjectPerLecturer"/></th>
	
	<td><jstl:out value=" ${averageSubjectPerLecturer}"/></td>
  </tr>		
  
  
  <tr>

  <th><spring:message code="administrator.lecturersTeachMoreOrLessThan20Avg"/></th>
 
  <td>
  <display:table name="subjectTeachLecturer" id="row" requestURI="administrator/dashboard.do" class="displaytag">
		
		</display:table>
  </td>
  
  </tr>
  
  
     <tr>
    <th><spring:message code="administrator.averageStudentPerGroup"/></th>
	
	<td><jstl:out value=" ${averageStudentPerGroup}"/></td>
  </tr>		
  
  
  <tr>

  <th><spring:message code="administrator.groupMoreOrLessThan20Avg"/></th>
 
  <td>
  <display:table name="groupStudent" id="row" requestURI="administrator/dashboard.do" class="displaytag">
		
		</display:table>
  </td>
  
  </tr>
		
		
		<tr>

  <th><spring:message code="administrator.lecturersUploadMoreLM"/></th>
 
  <td>
  <display:table name="lecturersUploadMoreLM" id="row" requestURI="administrator/dashboard.do" class="displaytag">
		
		</display:table>
  </td>
  
  </tr>
  
       <tr>
    <th><spring:message code="administrator.averageLearningMaterialPerGroup"/></th>
	
	<td><jstl:out value=" ${averageLearningMaterialPerGroup}"/></td>
  </tr>
  
  		<tr>

  <th><spring:message code="administrator.subjectMoreLMAvailable"/></th>
 
  <td>
  <display:table name="subjectMoreLMAvailable" id="row" requestURI="administrator/dashboard.do" class="displaytag">
		
		</display:table>
  </td>
  
  </tr>
  
       <tr>
    <th><spring:message code="administrator.averageSocialIdentitiesPerActor"/></th>
	
	<td><jstl:out value=" ${averageSocialIdentitiesPerActor}"/></td>
  </tr>
  					<%-- Dashboard 2.0 - C --%>
   <tr>
    <th><spring:message code="administrator.averageSyllabiPerSubject"/></th>
	
	<td><jstl:out value=" ${averageaverageSyllabiPerSubject}"/></td>
  </tr>
  
  <tr>
    <th><spring:message code="administrator.averageBibliographyPerSyllabus"/></th>
	
	<td><jstl:out value=" ${averageBibliographyPerSyllabus}"/></td>
  </tr>
  
  <tr>
  <th><spring:message code="administrator.subjectLargestBibliography"/></th>
  <td>
  <display:table name="subjectLargestBibliography" id="row" requestURI="administrator/dashboard.do" class="displaytag">
	</display:table>
  </td>
    </tr>
   						 <%-- Dashboard 2.0 - B --%>
     <tr>
  <th><spring:message code="administrator.assignmentsmoreorless20AVG"/></th>
  <td>
  <display:table name="assignmentsmoreorless20AVG" id="row" requestURI="administrator/dashboard.do" class="displaytag">
	</display:table>
  </td>
    </tr>
    
         <tr>
  <th><spring:message code="administrator.lecturerWithMoreRubricsPerAssignment"/></th>
  <td>
  <display:table name="lecturerWithMoreRubricsPerAssignment" id="row" requestURI="administrator/dashboard.do" class="displaytag">
	</display:table>
  </td>
    </tr>
    
     <tr>
  <th><spring:message code="administrator.lecturerWithLessRubricsPerAssignment"/></th>
  <td>
  <display:table name="lecturerWithLessRubricsPerAssignment" id="row" requestURI="administrator/dashboard.do" class="displaytag">
	</display:table>
  </td>
    </tr>
  		</table>
		
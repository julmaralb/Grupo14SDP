<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<fieldset>
  	<legend><spring:message code="subject.info"/>:</legend>
<div>
	<spring:message code="subject.title"/>  :  <jstl:out value="${subject.title}"></jstl:out>
</div>
<div>
	<spring:message code="subject.code"/>  :  <jstl:out value="${subject.code}"></jstl:out>		
</div>
<div>
	<spring:message code="subject.lecturer"/>  :  <jstl:out value="${subject.lecturer.name}"></jstl:out>  <jstl:out value="${subject.lecturer.surname}"></jstl:out>		
</div>
</fieldset>

<fieldset>
  	<legend><spring:message code="syllabus.info"/>:</legend>
<div>
	<spring:message code="syllabus.academicYear"/>  :  <jstl:out value="${syllabus.academicYear}"></jstl:out>
</div>
<div>
	<spring:message code="syllabus.summary"/>  :  <jstl:out value="${syllabus.summary}"></jstl:out>
</div>
<div>
	<spring:message code="syllabus.goals"/>  :  <jstl:out value="${syllabus.goals}"></jstl:out>
</div>
<div>
	<spring:message code="syllabus.prerequisites"/>  :  <jstl:out value="${syllabus.prerequisites}"></jstl:out>
</div>
<div>
	<spring:message code="syllabus.evalAndGradPolicies"/>  :  <jstl:out value="${syllabus.evaluationAndGradingPolicies}"></jstl:out>
</div>
</fieldset>
<br/>
<a href="subject/lecturer/listSyllabi.do?subjectId=<%= request.getParameter("subjectId") %>"><spring:message code="subject.syllabi"/></a>
<br/>
<br/>
<input type="button" name="cancel" value="<spring:message code="lecturer.back" />" onclick="javascript: window.location.replace('subject/lecturer/list.do');" />
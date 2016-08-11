<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
		function relativeRedir(loc) {	
			var b = document.getElementsByTagName('base');
			if (b && b[0] && b[0].href) {
	  			if (b[0].href.substr(b[0].href.length - 1) == '/' && loc.charAt(0) == '/')
	    		loc = loc.substr(1);
	  			loc = b[0].href + loc;
			}
			window.location.replace(loc);
		}
	</script>

<div>
	<img src="images/logoacademy.png" alt="Sample Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="subject/administrator/create.do"><spring:message code="master.page.administrator.subject.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('LECTURER')">
			<li><a class="fNiv"><spring:message	code="master.page.lecturer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="subject/lecturer/list.do"><spring:message code="master.page.lecturer.subject.list" /></a></li>
					<li><a href="assignment/lecturer/create.do"><spring:message code="master.page.lecturer.assignment.create" /></a></li>
					<li><a href="learningMaterial/lecturer/create.do"><spring:message code="master.page.lecturer.learningMaterial.create" /></a></li>			
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('LECTURER')">
			<li><a class="fNiv"><spring:message	code="master.page.groups" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="group/lecturer/list.do"><spring:message code="master.page.lecturer.group.list" /></a></li>
					<li><a href="group/lecturer/create.do"><spring:message code="master.page.lecturer.group.create" /></a></li>				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('STUDENT')">
			<li><a class="fNiv"><spring:message	code="master.page.groups" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="group/student/list.do"><spring:message code="master.page.lecturer.group.list" /></a></li>	
					<li><a href="group/student/listMyGroups.do"><spring:message code="master.page.lecturer.group.listMyGroups" /></a></li>			
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="lecturer/list.do"><spring:message code="master.page.lecturer.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="lecturer/list.do"><spring:message code="master.page.lecturer.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="subject/list.do"><spring:message code="master.page.subject.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="subject/list.do"><spring:message code="master.page.subject.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="student/create.do"><spring:message code="master.page.register" /></a></li>
		</security:authorize>
		
			<security:authorize access="hasRole('STUDENT')">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="student/modifyProfile.do"><spring:message code="master.page.student.modifyProfile" /></a></li>
					<li><a href="socialIdentity/create.do"><spring:message code="master.page.socialIdentity.create" /></a></li>
					<li><a href="socialIdentity/list.do"><spring:message code="master.page.socialIdentity.list" /></a></li>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>

		
		<security:authorize access="hasRole('LECTURER')">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>	
					<li><a href="socialIdentity/create.do"><spring:message code="master.page.socialIdentity.create" /></a></li>
					<li><a href="socialIdentity/list.do"><spring:message code="master.page.socialIdentity.list" /></a></li>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>	
					<li><a href="socialIdentity/create.do"><spring:message code="master.page.socialIdentity.create" /></a></li>
					<li><a href="socialIdentity/list.do"><spring:message code="master.page.socialIdentity.list" /></a></li>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>

	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>


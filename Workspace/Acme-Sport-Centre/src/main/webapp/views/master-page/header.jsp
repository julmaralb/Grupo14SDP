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

<div>
	<img src="images/acmesportlogo.png" alt="Acme-Sport-Centre Co., Inc."  height="150" width="300"/>
</div>

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
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="supervisor/administrator/create.do"><spring:message code="master.page.administrator.registerSupervisor" /></a></li>
					<li><a href="displayPrice/administrator/edit.do"><spring:message code="master.page.administrator.displayPrice.edit" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator.sportEquipment" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sportEquipment/administrator/list.do"><spring:message code="master.page.administrator.sportEquipment.list" /></a></li>
					<li><a href="sportEquipment/administrator/create.do"><spring:message code="master.page.administrator.sportEquipment.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv" href="day/administrator/createToday.do"><spring:message	code="master.page.createToday" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.centres" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="centre/administrator/list.do"><spring:message code="master.page.centre.list" /></a></li>
					<li><a href="centre/administrator/create.do"><spring:message code="master.page.centre.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.courts" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="court/administrator/list.do"><spring:message code="master.page.court.list" /></a></li>
					<li><a href="court/administrator/create.do"><spring:message code="master.page.court.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('SUPERVISOR')">
			<li><a class="fNiv"><spring:message	code="master.page.reports" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="report/supervisor/list.do"><spring:message code="master.page.reports.list" /></a></li>
					<li><a href="report/supervisor/create.do"><spring:message code="master.page.reports.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
			
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="renting/customer/list.do"><spring:message	code="master.page.renting.customer.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="reservation/customer/list.do"><spring:message	code="master.page.reservation.customer.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.openMatches" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="openMatch/customer/list.do"><spring:message code="master.page.openMatch.list" /></a></li>
					<li><a href="openMatch/customer/listMine.do"><spring:message code="master.page.openMatch.listMine" /></a></li>
					<li><a href="openMatch/customer/create.do"><spring:message code="master.page.openMatch.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="centre/list.do"><spring:message code="master.page.reservation.schedule" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="centre/list.do"><spring:message code="master.page.reservation.schedule" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="customer/create.do"><spring:message code="master.page.register" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/modifyProfile.do"><spring:message code="master.page.customer.modifyProfile" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('SUPERVISOR')">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>					
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
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>


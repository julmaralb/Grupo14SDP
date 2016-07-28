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
	<img src="images/logoPolyglots2.png" alt="Acme-Polyglot-2.0 Co., Inc." />
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
					<li><a href="administrator/action-1.do"><spring:message code="master.page.administrator.action.1" /></a></li>
					<li><a href="administrator/action-2.do"><spring:message code="master.page.administrator.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator.languages" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="language/administrator/list.do"><spring:message code="master.page.administrator.language.list" /></a></li>
					<li><a href="language/administrator/create.do"><spring:message code="master.page.administrator.language.create" /></a></li>		
					<li><a href="languageDescription/administrator/create.do"><spring:message code="master.page.administrator.languageDescription.create" /></a></li>			
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AGENT')">
			<li><a class="fNiv"><spring:message	code="master.page.sponsorships" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsorship/agent/list.do"><spring:message code="master.page.agent.sponsorship.list" /></a></li>
					<li><a href="sponsorship/agent/create.do"><spring:message code="master.page.agent.sponsorship.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AGENT')">
			<li><a class="fNiv"><spring:message	code="master.page.descriptions" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsorshipDescription/agent/list.do"><spring:message code="master.page.agent.sponsorshipDescription.list" /></a></li>
					<li><a href="sponsorshipDescription/agent/create.do"><spring:message code="master.page.agent.sponsorshipDescription.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AGENT')">
			<li><a class="fNiv"><spring:message	code="master.page.banners" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="banner/agent/list.do"><spring:message code="master.page.agent.banner.list" /></a></li>
					<li><a href="banner/agent/create.do"><spring:message code="master.page.agent.banner.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('POLYGLOT')">
			<li><a class="fNiv"><spring:message	code="master.page.languageExchanges" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="languageExchange/polyglot/list.do"><spring:message code="master.page.languageExchange.list" /></a></li>
					<li><a href="languageExchange/polyglot/listJoined.do"><spring:message code="master.page.languageExchange.listJoined" /></a></li>
					<li><a href="languageExchange/polyglot/create.do"><spring:message code="master.page.languageExchange.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('POLYGLOT')">
			<li><a class="fNiv"><spring:message	code="master.page.descriptions" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="languageExchangeDescription/polyglot/list.do"><spring:message code="master.page.languageExchangeDescription.list" /></a></li>
					<li><a href="languageExchangeDescription/polyglot/create.do"><spring:message code="master.page.languageExchangeDescription.create" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="languageExchange/list3MonthsAgo.do"><spring:message code="master.page.list3MonthsAgo" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="languageExchange/list3MonthsTime.do"><spring:message code="master.page.list3MonthsTime" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="polyglot/create.do"><spring:message code="master.page.register" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="languageExchange/actor/list3MonthsAgo.do"><spring:message code="master.page.list3MonthsAgo" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="languageExchange/actor/list3MonthsTime.do"><spring:message code="master.page.list3MonthsTime" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="folder/actor/list.do"><spring:message code="master.page.list.folders" /></a></li>
					<li><a href="polyglot/modifyProfile.do"><spring:message code="master.page.modifyProfile" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>


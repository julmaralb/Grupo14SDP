<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%-- Attributes --%> 
 
<%@ attribute name="code" required="true" %>
<%@ attribute name="ref" required="true" %>
<%@ attribute name="condition" required="true" %>
<%@ attribute name="codeConfirm" required="true" %>

<%-- Definition --%>
<div>
	<spring:message code="${code}" var="headerTag" />
	<display:column title="${headerTag}">
	<jstl:if test="${condition}">
	<a href="${ref}" onclick="return confirm('<spring:message code="${codeConfirm}"/>')">
	<spring:message code="${code}"/>
	</a>
	</jstl:if>
	</display:column>
</div>
	
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
<%@ attribute name="alt" required="true" %>
<%@ attribute name="src" required="true" %>
<%@ attribute name="sortable" required="false" %>


<jstl:if test="${sortable == null}">
	<jstl:set var="sortable" value="false" />
</jstl:if>

<%-- Definition --%>
<div>
	<spring:message code="${code}" var="headerTag" />
	<display:column title="${headerTag}">
	<img src="${src}" alt="${alt}" width="244" height="166">
	</display:column>
</div>
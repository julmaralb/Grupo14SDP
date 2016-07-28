<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>

<script
    src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script>
var map;
var geocoder;
var address = "${languageExchange.exchangePlace}";
function initialize() {
  geocoder = new google.maps.Geocoder();
  var mapOptions = {
    zoom: 14,
    center: new google.maps.LatLng(37.3824, -5.9965)
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
  
  if (geocoder) {
	    geocoder.geocode({
	      'address': address
	    }, function(results, status) {
	      if (status == google.maps.GeocoderStatus.OK) {
	        if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
	          map.setCenter(results[0].geometry.location);

	          var infowindow = new google.maps.InfoWindow({
	            content: '<b>' + address + '</b>',
	            size: new google.maps.Size(150, 50)
	          });

	          var marker = new google.maps.Marker({
	            position: results[0].geometry.location,
	            map: map,
	            title: address
	          });
	          google.maps.event.addListener(marker, 'click', function() {
	            infowindow.open(map, marker);
	          });

	        } else {
	          alert("No results found");
	        }
	      } else {
	        alert("Geocode was not successful for the following reason: " + status);
	      }
	    });
	  }
	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>

</head>

<body>

<jstl:if test="${banner != null}">
<img src="${banner.photo}" width="600" height="166">
</jstl:if>

<fieldset>
	<legend><spring:message code="languageExchange.details"/></legend>
	<div>
		<spring:message code="languageExchange.name"/> : <jstl:out value="${languageExchange.name}"></jstl:out><br/>
    	<spring:message code="languageExchange.registrationDate"/> : <jstl:out value="${languageExchange.registrationDate}"></jstl:out><br/>
    	<spring:message code="languageExchange.exchangePlace"/> : <jstl:out value="${languageExchange.exchangePlace}"></jstl:out><br/>
    </div>
  </fieldset>

<fieldset>
	<legend><spring:message code="languageExchangeDescription.details"/></legend>
	<div>
    	<spring:message code="languageExchangeDescription.title"/> : <jstl:out value="${languageExchangeDescription.title}"></jstl:out><br/>
    	<spring:message code="languageExchangeDescription.text"/> : <jstl:out value="${languageExchangeDescription.text}"></jstl:out><br/>
    	<spring:message code="languageExchangeDescription.code"/> : <jstl:out value="${languageExchangeDescription.code}"></jstl:out><br/>
    	<spring:message code="languageExchangeDescription.infoLinks"/> : 
    	<jstl:forEach items="${languageExchangeDescription.infoLinks}" var="il">
    	<jstl:out value="${il}"></jstl:out>
    	</jstl:forEach><br/>
    	<spring:message code="languageExchangeDescription.tags"/> : 
    	<jstl:forEach items="${languageExchangeDescription.tags}" var="t">
    	<jstl:out value="${t}"></jstl:out>
    	</jstl:forEach><br/>
    </div>
  </fieldset>
<br/>

<div id="map-canvas" style="height:300px; width:500px"></div>

<script async defer
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA0Bx2IH546c7E3E5mqtSwQq8z-inqpWts&callback=initMap">
    </script>

</body>
</html>

<acme:cancel url="/" code="languageExchangeDescription.back"/>
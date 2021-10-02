<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="hotel">
    <h2>Hotel</h2>

    <table id="hotelesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Direccion</th>
            <th style="width: 120px">Telefono</th>
            <th style="width: 120px">Provincia</th>
            
        </tr>
        </thead>
        
        
        <tbody>
        <c:forEach items="${selections}" var="hotel">
        <c:if test="${hotel.valido}">
            <tr>
                <td>
                    <spring:url value="/hoteles/{hotelId}" var="hotelUrl">
                        <spring:param name="hotelId" value="${hotel.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(hotelUrl)}">
                    <c:out value="${hotel.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${hotel.direccion}"/>
                </td>
                <td>
                	<c:out value="${hotel.telefono}"/>
                </td>
                <td>
                	<spring:url value="/hoteles/provincias?provincia={hotelProvincia}" var="hotelProvUrl">
                		<spring:param name="hotelProvincia" value="${hotel.provincia}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(hotelProvUrl)}">
                	<c:out value="${hotel.provincia}"/></a>
                </td>           
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

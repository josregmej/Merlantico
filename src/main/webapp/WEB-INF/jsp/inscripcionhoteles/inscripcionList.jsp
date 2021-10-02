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
            <th style="width: 120px">Descripcion</th>
            <th style="width: 120px">Provincia</th>
            <th style="width: 120px">Actividades</th>
            
        </tr>
        </thead>
        
        
        <tbody>
        <c:forEach items="${selections}" var="inscripcionHotel">
            <tr>
                <td>
                    <spring:url value="/inscripciones/{inscripcionHotelId}" var="inscripcionHotelUrl">
                        <spring:param name="inscripcionHotelId" value="${inscripcionHotel.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(inscripcionHotelUrl)}">
                    <c:out value="${inscripcionHotel.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${inscripcionHotel.direccion}"/>
                </td>
                <td>
                	<c:out value="${inscripcionHotel.descripcion}"/>
                </td>
                <td>
                	<c:out value="${inscripcionHotel.actividades}"/>
                </td>
                <td>
                	<c:out value="${inscripcionHotel.provincia}"/>
                </td>
                           
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
    
    
</petclinic:layout>

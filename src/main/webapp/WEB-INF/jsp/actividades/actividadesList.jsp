<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="actividad">
    <h2>Listado de Actividades</h2>

    <table id="actividadTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Descripcion</th>
            <th style="width: 120px">Valoración</th>
            <th style="width: 150px">Dirección</th>
            <th style="width: 150px">Precio</th>
            <th style="width: 150px">Fecha</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="actividad">
            <tr>
                <td>
                    <spring:url value="/actividades/{actividadId}" var="actividadUrl">
                        <spring:param name="actividadId" value="${actividad.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(actividadUrl)}">
                    <c:out value="${actividad.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${actividad.descripcion}"/>
                </td>
                <td>
                    <c:out value="${actividad.valoracion}"/>
                </td>       
                <td>
                    <c:out value="${actividad.direccion}"/>
                </td>  
                <td>
                    <c:out value="${actividad.precio}"/>
                </td>
                <td>
                    <c:out value="${actividad.fecha}"/>
                </td>              
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

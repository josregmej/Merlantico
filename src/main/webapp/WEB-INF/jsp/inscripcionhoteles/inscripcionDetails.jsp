<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="inscripciones">

    <h2>Información sobre Inscripcion</h2>

    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${inscripcionHotel.nombre}"/></b></td>
        </tr>
        <tr>
            <th>Direccion</th>
            <td><c:out value="${inscripcionHotel.direccion}"/></td>
        </tr>
        <tr>
            <th>Descripcion</th>
            <td><c:out value="${inscripcionHotel.descripcion}"/></td>
        
        <tr>
            <th>Actividad</th>
            <td><c:out value="${inscripcionHotel.actividades}"/></td>
        </tr>
        <tr>
            <th>Provincia</th>
            <td><c:out value="${inscripcionHotel.provincia}"/></td>
        </tr>
    </table>
    
</petclinic:layout>




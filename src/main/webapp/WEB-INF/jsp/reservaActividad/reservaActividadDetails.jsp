<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="reservaActividad">

    <h2>Información sobre las reservas de la actividad</h2>

    <table class="table table-striped">
        <tr>
            <th>Fecha de la reserva</th>
            <td><b><c:out value="${reservaActividad.fechaReserva}"/></b></td>
        </tr>
        <tr>
            <th>Fecha de actividad</th>
            <td><c:out value="${reservaActividad.entrada}"/></td>
        </tr>
        <tr>
            <th>Precio de la actividad</th>
            <td><c:out value="${reservaActividad.precioFinal}"/> euros</td>
        </tr>
        <tr>
        	<th>User</th>
            <td><c:out value="${reservaActividad.user.username}"/></td>
        </tr>
	</table>
  <table class="table table-striped">
  <h3>Información de la Actividad</h3>
			<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Nombre</dt>
                        <dd><c:out value="${reservaActividad.actividad.nombre}"/></dd>
                        <dt>Descripcion</dt>
                        <dd><c:out value="${reservaActividad.actividad.descripcion}"/></dd>
                        <dt>Direccion</dt>
                        <dd><c:out value="${reservaActividad.actividad.direccion}"/></dd>
                        <dt>Agencia</dt>
                        <dd><c:out value="${reservaActividad.actividad.agenact.nombre}"/></dd>
                    </dl>
                </td>
			</tr>
  </table>
    <br/>
    <br/>
    <br/>
	<a class="btn btn-default" href='<spring:url value="/actividades" htmlEscape="true"/>'>Volver a los actividades</a>

</petclinic:layout>

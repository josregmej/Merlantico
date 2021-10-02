<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="reservaHoteles">

    <h2>Información sobre las reservas de tu habitacion</h2>

    <table class="table table-striped">
        <tr>
            <th>Fecha de la reserva</th>
            <td><b><c:out value="${reservaHabitacion.fechaReserva}"/></b></td>
        </tr>
        <tr>
            <th>Fecha de entrada</th>
            <td><c:out value="${reservaHabitacion.entrada}"/></td>
        </tr>
        <tr>
            <th>Fecha de salida</th>
            <td><c:out value="${reservaHabitacion.salida}"/></td>
        </tr>
        <tr>
            <th>Precio de la reserva</th>
            <td><c:out value="${reservaHabitacion.precioFinal}"/> euros</td>
        </tr>
	</table>
  <table class="table table-striped">
  <h3>Información de la habitación</h3>
			<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Nº Habitacion</dt>
                        <dd><c:out value="${reservaHabitacion.habitacion.nhabitacion}"/></dd>
                        <dt>Nº de camas</dt>
                        <dd><c:out value="${reservaHabitacion.habitacion.ncamas}"/></dd>
                        <dt>Precio por dia</dt>
                        <dd><c:out value="${reservaHabitacion.habitacion.precio}"/></dd>
                        <dt>User</dt>
                        <dd><c:out value="${reservaHabitacion.user.username}"/></dd>
                    </dl>
                </td>
			</tr>
  </table>
    <br/>
    <br/>
    <br/>
	<a class="btn btn-default" href='<spring:url value="/hoteles" htmlEscape="true"/>'>Volver a los hoteles</a>

</petclinic:layout>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="reservaVuelo">

    <h2>Información sobre las reservas del Vuelo</h2>

    <table class="table table-striped">
        <tr>
            <th>Fecha de la reserva</th>
            <td><b><c:out value="${reservaVuelo.fechaReserva}"/></b></td>
        </tr>
        <tr>
            <th>Fecha de ida</th>
            <td><c:out value="${reservaVuelo.ida}"/></td>
        </tr>
        <tr>
            <th>Fecha de vuelta</th>
            <td><c:out value="${reservaVuelo.vuelta}"/></td>
        </tr>
        <c:if test="${reservaVuelo.precioFinal!=reservaVuelo.vuelo.precio*reservaVuelo.vuelo.billetes}">
         <tr>
            <th>Descuento</th>
            <c:if test="${reservaVuelo.codigo eq 'BIENVENIDODP'}">
            <td><c:out value="${reservaVuelo.vuelo.precio*reservaVuelo.vuelo.billetes*0.05}"/> euros</td>
            </c:if>
             <c:if test="${reservaVuelo.codigo eq 'DESCUENTO10'}">
              <td><c:out value="${reservaVuelo.vuelo.precio*reservaVuelo.vuelo.billetes*0.10}"/> euros</td>
             </c:if>
        </tr>
        </c:if>     
        <tr>
            <th>Precio del vuelo</th>
            <td><c:out value="${reservaVuelo.precioFinal}"/> euros</td>
        </tr>
        <tr>
        	<th>User</th>
            <td><c:out value="${reservaVuelo.user.username}"/></td>
        </tr>
	</table>
  <table class="table table-striped">
  <h3>Información del vuelo</h3>
			<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Lugar de origen</dt>
                        <dd><c:out value="${reservaVuelo.vuelo.origen}"/></dd>
                        <dt>Lugar de destino</dt>
                        <dd><c:out value="${reservaVuelo.vuelo.destino}"/></dd>
                        <dt>Agencia de vuelo</dt>
                        <dd><c:out value="${reservaVuelo.vuelo.compVuelo.nombre}"/></dd>
                    </dl>
                </td>
			</tr>
  </table>
    <br/>
    <br/>
    <br/>
	<a class="btn btn-default" href='<spring:url value="/vuelos" htmlEscape="true"/>'>Volver a los Vuelos</a>

</petclinic:layout>

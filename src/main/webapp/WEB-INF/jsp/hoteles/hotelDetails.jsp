<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="hoteles">

    <h2>Información sobre Hotel</h2>

	<spring:url value="/hoteles/{hotelId}/edit" var="editUrl">
        <spring:param name="hotelId" value="${hotel.id}"/>
    </spring:url>
    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${hotel.nombre}"/></b></td>
        </tr>
        <tr>
            <th>Direccion</th>
            <td><c:out value="${hotel.direccion}"/></td>
        </tr>
        <tr>
            <th>Telefono</th>
            <td><c:out value="${hotel.telefono}"/></td>
        
        <tr>
            <th>Estrellas</th>
            <td><c:out value="${hotel.estrellas}"/></td>
        </tr>
        <tr>
            <th>Provincia</th>
            <td><c:out value="${hotel.provincia}"/></td>
        </tr>
    </table>

  <table class="table table-striped">
   <h3>Habitaciones</h3>
        <c:forEach var="habitaciones" items="${hotel.habitaciones}">
			<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Nº Habitacion</dt>
                        <dd><c:out value="${habitaciones.nhabitacion}"/></dd>
                        <dt>Nº de camas</dt>
                        <dd><c:out value="${habitaciones.ncamas}"/></dd>
                        <dt>Precio</dt>
                        <dd><c:out value="${habitaciones.precio}"/></dd>
                        <dt>Disp.</dt>
                      <c:if test="${habitaciones.disponible}">
						<dd>Si</dd>
						<sec:authorize access="isAuthenticated()">
						<a class="btn btn-default" href='<spring:url value="/hoteles/${hotel.id}/${habitaciones.nhabitacion}/reservaHabitacion/new" htmlEscape="true"/>'>Reservar</a>
					  	</sec:authorize>
					  </c:if>
                      <c:if test="${!habitaciones.disponible}">
                       <dd>No</dd>
                      </c:if>
                    </dl>
                </td>
           </tr>
      </c:forEach>
  </table>
  <table>
  <h3>Comentarios</h3>
        <c:forEach var="comentarios" items="${hotel.comentarios}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Puntuacion</dt>
                        <dd><c:out value="${comentarios.puntuacion}"/></dd>
                        <dt>Mensaje</dt>
                        <dd><c:out value="${comentarios.mensaje}"/></dd>
                    </dl>
                </td>
          </c:forEach>
  </table>
  
	<sec:authorize access="isAuthenticated()">
	<a class="btn btn-default" href='<spring:url value="/hoteles/${hotel.id}/comentarios/new" htmlEscape="true"/>'>Anadir comentario</a>
	</sec:authorize>
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/hoteles/${hotel.id}/edit" htmlEscape="true"/>'>Editar hotel</a>
	</sec:authorize>
	<sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/hoteles/${hotel.id}/delete" htmlEscape="true"/>'>Baja de hotel</a>
	</sec:authorize>
	<sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/hoteles/${hotel.id}/habitaciones/new" htmlEscape="true"/>'>Añadir habitacion</a>
	</sec:authorize>
  
    <br/>
    <br/>
    <br/>

</petclinic:layout>

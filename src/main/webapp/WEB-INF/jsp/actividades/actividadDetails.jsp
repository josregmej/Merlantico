<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<petclinic:layout pageName="actividades">

    <h2>Información de la Actividad</h2>

	<spring:url value="/actividades/{actividadId}/edit" var="editUrl">
        <spring:param name="actividadId" value="${actividades.id}"/>
    </spring:url>
    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${actividades.nombre}"/></b></td>
        </tr>
        <tr>
            <th>Descripcion</th>
            <td><c:out value="${actividades.descripcion}"/></td>
        </tr>
        <tr>
            <th>Valoración</th>
            <td><c:out value="${actividades.valoracion}"/></td>
        </tr>
        <tr>
            <th>Dirección</th>
            <td><c:out value="${actividades.direccion}"/></td>
        </tr>
        <tr>
            <th>Provincia</th>
            <td><c:out value="${actividades.provincia}"/></td>
        </tr>
        <tr>
            <th>Precio</th>
            <td><c:out value="${actividades.precio}"/></td>
        </tr>
         <tr>
            <th>Fecha</th>
            <td><c:out value="${actividades.fecha}"/></td>
        </tr>
        <tr>
        	<th>Agencia</th>
        	<td><c:out value="${actividades.agenact.nombre}"/></td>
        </tr>
    </table>
    
    <sec:authorize access="isAuthenticated()">
	<a class="btn btn-default" href='<spring:url value="/actividades/${actividades.id}/reservaActividad/new" htmlEscape="true"/>'>Reservar</a>
    </sec:authorize>
    <br/>
    <br/>
    
    <table>
  <h3>Comentarios</h3>
        <c:forEach var="comentarios" items="${actividades.comentarios}">
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
	<a class="btn btn-default" href='<spring:url value="/actividades/${actividades.id}/comentarios/new" htmlEscape="true"/>'>Anadir comentario</a>
	</sec:authorize>
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/actividades/${actividades.id}/edit" htmlEscape="true"/>'>Editar Actividad</a>
	</sec:authorize>
    <br/>
    <br/>
    <br/>

</petclinic:layout>

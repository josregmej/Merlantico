<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<petclinic:layout pageName="vuelos">

    <h2>Información del Vuelo</h2>

	<spring:url value="/vuelos/{vueloId}/edit" var="editUrl">
        <spring:param name="vueloId" value="${vuelos.id}"/>
    </spring:url>
    <table class="table table-striped">
        <tr>
            <th>Origen</th>
            <td><b><c:out value="${vuelos.origen}"/></b></td>
        </tr>
        <tr>
            <th>Destino</th>
            <td><c:out value="${vuelos.destino}"/></td>
        </tr>
        <tr>
            <th>Precio por billetes</th>
            <td><c:out value="${vuelos.precio}"/></td>
        </tr>
        <tr>
            <th>Fecha ida</th>
            <td><c:out value="${vuelos.fechaIda}"/></td>
        </tr>
        <tr>
            <th>Fecha vuelta</th>
            <td><c:out value="${vuelos.fechaVuelta}"/></td>
        </tr>
        <tr>
            <th>Número de plazas disponibles</th>
            <td><c:out value="${vuelos.numeroPlazas}"/></td>
        </tr>
        <tr>
            <th>Número de billetes</th>
            <td><c:out value="${vuelos.billetes}"/></td>
        </tr>
        <tr>
            <th>Compañia Aérea</th>
            <td><c:out value="${vuelos.compVuelo.nombre}"/></td>
        </tr>
        <tr>
            <th>Precio final</th>
            <td><c:out value="${vuelos.precio*vuelos.billetes}"/></td>
        </tr>
          <c:forEach var="usuarios" items="${vuelos.users}">
            <tr>
           		<th>Usuario</th>
                <td><c:out value="${usuarios.username}"/></td>
              </tr>      
          </c:forEach>
    </table>
    <c:if test="${vuelos.numeroPlazas-vuelos.billetes>=0}">
    <sec:authorize access="isAuthenticated()">
	<a class="btn btn-default" href='<spring:url value="/vuelos/${vuelos.id}/reservaVuelo/new" htmlEscape="true"/>'>Reservar</a>
    </sec:authorize>
    </c:if>
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/vuelos/${vuelos.id}/edit" htmlEscape="true"/>'>Editar vuelo</a>
	</sec:authorize>
    <br/>
    <br/>
    <br/>

</petclinic:layout>

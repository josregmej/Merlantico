<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="compvuelos">

    <h2>Información de Compañía de vuelos</h2>

	<spring:url value="/compvuelos/{compVuelosId}/edit" var="editUrl">
        <spring:param name="compVuelosId" value="${compvuelos.id}"/>
    </spring:url>
    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${compvuelos.nombre}"/></b></td>
        </tr>
        <tr>
            <th>Sede</th>
            <td><c:out value="${compvuelos.sede}"/></td>
        </tr>
        <tr>
            <th>País</th>
            <td><c:out value="${compvuelos.pais}"/></td>
        </tr>
    </table>
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/compvuelos/${compvuelos.id}/edit" htmlEscape="true"/>'>Editar vuelo</a>
	</sec:authorize>
    <br/>
    <br/>
    <br/>

</petclinic:layout>

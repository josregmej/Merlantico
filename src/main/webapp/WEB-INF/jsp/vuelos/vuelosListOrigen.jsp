<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vuelo">
    <h2>Listado de Vuelos</h2>
	<button>
	<a href="/search">
                    Buscar todos los hoteles y todos los vuelos</a>
    </button>
    <table id="vuelosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Origen</th>
            <th style="width: 200px;">Destino</th>
            <th style="width: 120px">Precio</th>
            <th style="width: 150px">Fecha ida</th>
            <th style="width: 150px">Fecha vuelta</th>
            <th style="width: 150px">Compañía aérea</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="vuelo">
            <tr>
                <td>
                    <spring:url value="/vuelos/{vueloId}" var="vueloUrl">
                        <spring:param name="vueloId" value="${vuelo.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(vueloUrl)}">
                    <c:out value="${vuelo.origen}"/></a>
                </td>
                <td>
                    <spring:url value="/search?provincia={vacaciones}" var="searchUrl">
                		<spring:param name="vacaciones" value="${vuelo.destino}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(searchUrl)}">
                	<c:out value="${vuelo.destino}"/></a>
                </td>
                <td>
                    <c:out value="${vuelo.precio}"/>
                </td>       
                <td>
                    <c:out value="${vuelo.fechaIda}"/>
                </td>  
                <td>
                    <c:out value="${vuelo.fechaVuelta}"/>
                </td> 
                <td>
                    <c:out value="${vuelo.compVuelo.nombre}"/>
                </td>             
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

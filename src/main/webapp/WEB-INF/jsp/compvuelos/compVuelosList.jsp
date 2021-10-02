<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>



<petclinic:layout pageName="compvuelos">
    <h2>Compañías de vuelo</h2>

    <table id="compVuelosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Sede</th>
            <th style="width: 120px">País</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="compVuelos">
            <tr>
                <td>
                    <spring:url value="/compvuelos/{compVuelosId}" var="compvuelosUrl">
                        <spring:param name="compVuelosId" value="${compVuelos.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(compvuelosUrl)}">
                    <c:out value="${compVuelos.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${compVuelos.sede}"/>
                </td>
                <td>
                    <c:out value="${compVuelos.pais}"/>
                </td>                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

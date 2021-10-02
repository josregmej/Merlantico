<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="agenact">
    <h2>Agencias de actividades</h2>

    <table id="agenactsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Sede</th>
            <th style="width: 120px">Telefono</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="agenact">
            <tr>
                <td>
                    <spring:url value="/agenacts/{agenactId}" var="agenactUrl">
                        <spring:param name="agenactId" value="${agenact.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(agenactUrl)}">
                    <c:out value="${agenact.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${agenact.sede}"/>
                </td>
                <td>
                    <c:out value="${agenact.telefono}"/>
                </td>                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

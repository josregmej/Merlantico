<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="search">
    <h2>Estos son los hoteles encontrados </h2> 

    <table id="hotelesTable" class="table table-striped" align="left">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Direccion</th>
            <th style="width: 120px">Telefono</th>
            <th style="width: 120px">Provincia</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selectionsH}" var="hotel">
        <c:if test="${hotel.valido}">
            <tr>
                <td>
                    <spring:url value="/hoteles/{hotelId}" var="hotelUrl">
                        <spring:param name="hotelId" value="${hotel.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(hotelUrl)}">
                    <c:out value="${hotel.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${hotel.direccion}"/>
                </td>
                <td>
                	<c:out value="${hotel.telefono}"/>
                </td> 
                <td>
                	<c:out value="${hotel.provincia}"/>
                </td>              
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    
    <h2>Estos son los vuelos encontrados </h2> 
    <table id="vuelosTable" class="table table-striped" align="right">
        <thead>
        <tr>
            <th style="width: 150px;">Origen</th>
            <th style="width: 150px;">Destino</th>
            <th style="width: 120px">Precio</th>
            <th style="width: 150px">Fecha ida</th>
            <th style="width: 150px">Fecha vuelta</th>
            <th style="width: 150px">Compa��a a�rea</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selectionsV}" var="vuelo">
            <tr>
                <td>
                    <spring:url value="/vuelos/{vueloId}" var="vueloUrl">
                        <spring:param name="vueloId" value="${vuelo.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(vueloUrl)}">
                    <c:out value="${vuelo.origen}"/></a>
                </td>
                <td>
                    <c:out value="${vuelo.destino}"/>
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

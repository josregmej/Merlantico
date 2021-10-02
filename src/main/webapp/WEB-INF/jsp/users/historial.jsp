<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">

    <h1>Historial de reservas</h1>

	<h2>Vuelos</h2>
	
	<table id="historialvueloTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha Reserva</th>
            <th style="width: 200px;">Fecha ida</th>
            <th style="width: 200px;">Origen</th>
            <th style="width: 120px">Fecha vuelta</th>
            <th style="width: 200px;">Destino</th>
            <th style="width: 150px">Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selectionsVuelo}" var="hv">
            <tr>
                <td>
                    <c:out value="${hv.fechaReserva}"/>
                </td>
                <td>
                    <c:out value="${hv.ida}"/>
                </td>
                <td>
                    <c:out value="${hv.vuelo.origen}"/>
                </td>       
                <td>
                    <c:out value="${hv.vuelta}"/>
                </td>  
                <td>
                    <c:out value="${hv.vuelo.destino}"/>
                </td>
                <td>
                    <c:out value="${hv.precioFinal}"/>
                </td>              
            </tr>
        </c:forEach>
        </tbody>
    </table>
	
	
	
	<h2>Actividades</h2>
	
	<table id="historialactividadTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha Reserva</th>
            <th style="width: 200px;">Fecha Actividad</th>
            <th style="width: 200px;">Precio</th>
            <th style="width: 120px">Nombre Actividad</th>
            <th style="width: 200px;">Direccion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selectionsActividad}" var="ha">
            <tr>
                <td>
                    <c:out value="${ha.fechaReserva}"/>
                </td>
                <td>
                    <c:out value="${ha.entrada}"/>
                </td>
                <td>
                    <c:out value="${ha.precioFinal}"/>
                </td>       
                <td>
                    <c:out value="${ha.actividad.nombre}"/>
                </td>  
                <td>
                    <c:out value="${ha.actividad.direccion}"/>
                </td>       
            </tr>
        </c:forEach>
        </tbody>
    </table>
	
	<h2>Habitaciones</h2>
	<table id="historialHabitacionesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha Reserva</th>
            <th style="width: 200px;">Fecha Entrada</th>
            <th style="width: 200px;">Fecha Salida</th>
            <th style="width: 120px">Precio</th>
            <th style="width: 200px;">Nº Habitacion</th>
            <th style="width: 150px">Hotel</th>
            <th style="width: 150px">Direccion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selectionsHabitacion}" var="hh">
            <tr>
                <td>
                    <c:out value="${hh.fechaReserva}"/>
                </td>
                <td>
                    <c:out value="${hh.entrada}"/>
                </td>
                <td>
                    <c:out value="${hh.salida}"/>
                </td>       
                <td>
                    <c:out value="${hh.precioFinal}"/>
                </td>  
                <td>
                    <c:out value="${hh.habitacion.nhabitacion}"/>
                </td>
                <td>
                    <c:out value="${hh.habitacion.hotel.nombre}"/>
                </td>
                <td>
                    <c:out value="${hh.habitacion.hotel.direccion}"/>
                </td>                 
            </tr>
        </c:forEach>
        </tbody>
    </table>
	


</petclinic:layout>
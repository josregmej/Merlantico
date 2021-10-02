<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="reservasActividad">
    <jsp:body>
    <h2>
        Reservar Actividad
    </h2>
    <form:form modelAttribute="reservaActividad" class="form-horizontal" id="add-reservaActividad-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Número de tarjeta" name="numeroTarjeta"/>
            <petclinic:inputField label="CVC" name="cvc"/>
            <input type="hidden" name="entrada" value="${reservaActividad.actividad.fecha}"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               		<button class="btn btn-default" type="submit">Reservar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>

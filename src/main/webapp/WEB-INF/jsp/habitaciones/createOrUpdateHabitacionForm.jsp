<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="habitaciones">
    <h2>
        <c:if test="${habitaciones['new']}">New </c:if> Habitacion
    </h2>
    <form:form modelAttribute="habitacion" class="form-horizontal" id="add-habitacion-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Numero de Habitacion" name="nhabitacion"/>
            <petclinic:inputField label="Numero de camas" name="ncamas"/>
            <petclinic:inputField label="Precio" name="precio"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Add Habitacion</button>
       		</div>
        </div>
    </form:form>
</petclinic:layout>

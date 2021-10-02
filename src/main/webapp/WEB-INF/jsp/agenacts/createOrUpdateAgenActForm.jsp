<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="agenacts">
    <h2>
        <c:if test="${agenact['new']}">Añadir </c:if> Agencia de actividades
    </h2>
    <form:form modelAttribute="agenact" class="form-horizontal" id="add-agenact-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Sede" name="sede"/>
            <petclinic:inputField label="Telefono" name="telefono"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${agenact['new']}">
                        <button class="btn btn-default" type="submit">Añadir Agencia</button>
                    </c:when>
                    <c:otherwise>
                        
                        <button class="btn btn-default" type="submit">Editar Agencia</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>

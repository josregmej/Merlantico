<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<petclinic:layout pageName="vuelos">
<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaIda").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
        <script>
            $(function () {
                $("#fechaVuelta").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    
    <jsp:body>
    <h2>
        <c:if test="${vuelos['new']}">A�adir </c:if> Vuelo
    </h2>
    <form:form modelAttribute="vuelos" class="form-horizontal" id="add-vuelo-form">
        <div class="form-group has-feedback">
        	<petclinic:inputField label="N�mero de billetes" name="billetes"/>
        	<petclinic:inputField label="Destino" name="destino"/>
        	<petclinic:inputField label="Fecha de ida" name="fechaIda"/>
        	<petclinic:inputField label="Fecha de vuelta" name="fechaVuelta"/>
        	<petclinic:inputField label="Numero de plazas" name="numeroPlazas"/>
            <petclinic:inputField label="Origen" name="origen"/>
            <petclinic:inputField label="Precio" name="precio"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${vuelos['new']}">
                        <button class="btn btn-default" type="submit">A�adir Vuelo</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Editar Vuelo</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<petclinic:layout pageName="actividadesform">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fecha").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
<jsp:body>
    <h2>
        <c:if test="${actividades['new']}">Añadir </c:if> Actividad
    </h2>
    <form:form modelAttribute="actividades" class="form-horizontal" id="add-actividades-form">
        <div class="form-group has-feedback">
        	<petclinic:inputField label="Descripcion" name="descripcion"/>
        	<petclinic:inputField label="Dirección" name="direccion"/>
        	<petclinic:inputField label="Fecha" name="fecha"/>
        	<petclinic:inputField label="Nombre de la actividad" name="nombre"/>
        	<petclinic:inputField label="Provincia" name="provincia"/>
        	<petclinic:inputField label="Precio" name="precio"/>
        	<petclinic:inputField label="Valoración" name="valoracion"/>
        	
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${actividades['new']}">
                        <button class="btn btn-default" type="submit">Añadir Actividad</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Editar Actividad</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</jsp:body>
</petclinic:layout>

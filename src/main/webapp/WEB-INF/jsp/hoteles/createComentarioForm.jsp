<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="comentarioshotel">
    <h2>
        Anadir Comentario
    </h2>
    <form:form modelAttribute="comentarioHotel" class="form-horizontal" id="add-comentarioHotel-form">
        <div class="form-group has-feedback">
        
            <petclinic:inputNumberField label="Puntuacion" name="puntuacion"></petclinic:inputNumberField>
            
            <petclinic:inputField label="Mensaje" name="mensaje"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">Anadir Comentario</button>        
            </div>
        </div>
    </form:form>
</petclinic:layout>

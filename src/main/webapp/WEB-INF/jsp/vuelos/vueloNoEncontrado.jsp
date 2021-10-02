<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vuelosNoEncontrado">

    <h2>El vuelo buscado no se encuentra disponible.</h2>
    <p>Por favor, vuelva a buscar otro vuelo.</p>
    
<form:form  modelAttribute="vuelos" action="/vuelos/find" method="get" class="form-horizontal"
               id="search-vuelos-form" >
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Volver</button>
            </div>
        </div>
</form:form>    
</petclinic:layout>

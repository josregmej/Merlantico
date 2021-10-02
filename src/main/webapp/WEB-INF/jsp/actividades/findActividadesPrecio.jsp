<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<petclinic:layout pageName="actividad">

    <h2>Buscar Actividades</h2>

    
    <form:form  modelAttribute="actividad" action="/actividades/precio" method="get" class="form-horizontal"
               id="search-vuelos-form" >
        <div class="form-group">
            <div class="control-group" id="precio">
               	<petclinic:inputNumberField label="Precio" name="precio"></petclinic:inputNumberField>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Buscar</button>
            </div>
        </div>
    </form:form>	
    <br/> 
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/actividades/new" htmlEscape="true"/>'>Añadir Actividad</a>
	</sec:authorize>
</petclinic:layout>

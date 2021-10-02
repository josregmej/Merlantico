<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="inscripciones">

    <h2>Inscripcion enviada con exito.</h2>
    
    <a class="btn btn-default" href='<spring:url value="/inscripciones/${inscripcionHotel.id}" htmlEscape="true"/>'>Ir a solicitud</a>
</petclinic:layout>

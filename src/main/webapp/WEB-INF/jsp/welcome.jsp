<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <div class="row">
    <h2 class="text-center"><fmt:message key="welcome"/></h2>
    <h2>${title}</h2>
    <h2>${group}</h2>
    <ul>
    <c:forEach items="${persons}" var="person">
        <li>${person.firstName} ${' '}  ${person.lastName}</li>
    </c:forEach>
    </ul>
    </div>
    
<table class="table table-striped">
<tr></tr>
<td>
	<h1>FAQ</h1>
	<h2>1. ¿Como se usa la aplicacion?</h2>
		<p align="justify">Para poder usar nuestra aplicacion es necesario que usted este registrado, para ello debe de pulsar 
		en el boton de registrar, una vez pulsado debera rellenar todos los campos del formulario</p>
	<h2>2. ¿Como buscar un hotel?</h2>
		<p align="justify">Para poder buscar un hotel debes de seleccionar en el menu el boton buscador de hoteles, si no indica ningun
		nombre de hotel, el buscador te mostrara todos los hoteles que se encuentra en nuestra aplicacion, en el caso 
		contrario buscara un hotel con los parametros que ha establecido si hay mas de uno mostrara cada uno de ellos</p>
	<h2>3. ¿Como buscar un vuelo?</h2>
		<p align="justify">Para poder buscar un vuelo debes de seleccionar en el menu el boton buscador de vuelo, si no indica ningun
		lugar de origen, el buscador te mostrara todos los vuelos que se encuentra en nuestra aplicacion, en el caso 
		contrario buscara un vuelo con los parametros que ha establecido si hay mas de uno mostrara cada uno de ellos </p>
	<h2>4. ¿Mas dudas?</h2>
		<p align="justify"> Si tienes mas dudas pongase en contacto con el soporte tecnico de aplicacion</p>
</td>
</table>
<div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/logo.png" htmlEscape="true" var="logo"/>
            <img class="img-responsive center" src="${logo}"/>
        </div>
    </div>    
</petclinic:layout>
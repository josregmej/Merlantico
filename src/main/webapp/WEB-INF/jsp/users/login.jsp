<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %> <%-- Para  tildes, ñ y caracteres especiales como el € --%>

<petclinic:layout pageName="login">
    <h2 class="text-center">
            ¡Inicia sesión en Merlántico Vacaciones!
    </h2>
    <br/>
<div class="container justify-content-center" style="display:block;">
      <form:form class="form-horizontal center" id="add-user-form" action="/login">
            <div class="form-group">
                <label class="sr-only">Nombre de usuario</label>
                <input type="text" name="username" class="form-control"
                value="" placeholder="Nombre de usuario" required>
              </div>

            <div class="form-group">
            	<label class="sr-only">Contraseña</label>
              <input type="password" name="password" class="form-control" value="" placeholder="Contraseña" required>
      </div>
      
      <div class="form-group">
            <div class="col-md-12 text-center">
                        <br/><button class="btn btn-default" type="submit">Iniciar sesión</button>
                        </div>
   					</div>
            	</form:form>
		</div>
</petclinic:layout>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <h2>
        <c:if test="${user['new']}">Nuevo </c:if> Usuario
    </h2>
    <form:form modelAttribute="user" class="form-horizontal" id="add-user-form">
        <div class="form-group has-feedback">
        	<petclinic:inputField label="Username" name="username"/>
            <petclinic:inputField label="Password" name="password"/>
            <petclinic:inputField label="Telefono" name="telefono"/>
            <petclinic:inputField label="DNI" name="dni"/>
            <center> <input type="checkbox" required> He le?do y acepto pol?tica de privacidad </center>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${user['new']}">
                        <button class="btn btn-default" type="submit">A?adir usuario</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Confirmar edici?n</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
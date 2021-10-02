<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="users">
<body> 
   <h1>
       Usuario dado de baja con éxito.
   </h1>
   <h1>
       Cierre sesión para completar el proceso.
   </h1>
   <a href="<c:url value="/logout" />"
		class="btn btn-default">Cerrar mi sesion</a>
</body>
</petclinic:layout>

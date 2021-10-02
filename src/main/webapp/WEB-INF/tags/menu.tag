<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
<div class="container">
       <div class="navbar-header">
            <a class="navbar-brand img-responsive" href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-navbar">
                <span class="sr-only"><os-p>Toggle navigation</os-p></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>

            </button>
        </div>
        <div class="navbar-collapse collapse" id="main-navbar">
            <ul class="nav navbar-nav navbar-right">
	
				<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							<strong>BUSCADOR</strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
												<a href="/actividades/find" class="btn btn-primary btn-block">Actividades</a>
												<a href="/vuelos/find"class="btn btn-primary btn-block">Vuelos</a>
												<a href="/hoteles/find"class="btn btn-primary btn-block">Hoteles</a>
												<a href="/search/find"class="btn btn-primary btn-block">Hotel + Vuelo</a>
												<a href="/hotelActividad/find"class="btn btn-primary btn-block">Hotel + Actividad</a>
										</div>
									</div>
								</div>
							</li>

						</ul></li>

				<sec:authorize access="hasAuthority('propietario')">
				<petclinic:menuItem active="${name eq 'inscripciones'}" url="/inscripciones/new"
					title="inscripciones">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Inscripciones de hotel</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin')">
				<petclinic:menuItem active="${name eq 'inscripciones'}" url="/inscripciones"
					title="inscripciones">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Inscripciones de hotel</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin')">
				<petclinic:menuItem active="${name eq 'compvuelos'}" url="/compvuelos/find"
					title="find compvuelos">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Compañía de Vuelos</span>
				</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('admin')">
				<petclinic:menuItem active="${name eq 'agenacts'}" url="/agenacts/find"
					title="find agenacts">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Agencias de Eventos</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="!isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							 <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li class="divider"></li>
 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
												<a href="/login" class="btn btn-primary btn-block">Iniciar sesión</a>
												<a href="/users/new" class="btn btn-primary btn-block">Registrarse</a>
										</div>
									</div>
								</div>
							</li>

						</ul></li>
					
					
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											
												<a href="/users/<sec:authentication property="name" />" class="btn btn-primary btn-block">Mi perfil</a>
												<a href="/users/<sec:authentication property="name" />/historial" class="btn btn-primary btn-block">Historial</a>
												<a href="/users/<sec:authentication property="name" />/delete" class="btn btn-primary btn-block">Borrar mi perfil</a>
											
										</div>
									</div>
								</div>
							</li>

						</ul></li>
				</sec:authorize>
			</ul>
			
		</div>
	</div>
</nav>
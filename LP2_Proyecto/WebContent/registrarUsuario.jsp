<%@page import="beans.UsuarioDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/libreria.tld" prefix="ct"%>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuarios - Registrar</title>
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/shop.css" rel="stylesheet">
<link href="css/cart.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
		<div class="container">
			<a class="navbar-brand py-0 my-0 by-0 h1"
				href="tiendaSer?btnes=l&cat=0"><span class="mb-0 h3">miTienda</span></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<%
						UsuarioDTO u = (UsuarioDTO) request.getSession().getAttribute("usuario");
						if (u == null) {
							request.getRequestDispatcher("/loginRegistro.jsp").forward(request, response);
					%>
					<li class="nav-item"><a class="nav-link" href="loginRegistro.jsp">Iniciar
							Sesion</a></li>
					<%
						} else {
							if (u.getIdTipo() == 0) {
								request.getSession().invalidate();
								request.getRequestDispatcher("/loginRegistro.jsp").forward(request, response);
							}
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Bienvenido ${usuario.nombre} </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<%
								if (u.getIdTipo() == 1) {
							%>
							<a class="dropdown-item" href="tiendaSer?btnes=l&cat=0">Tienda</a> <a
								class="dropdown-item" href="listadoProductos.jsp">Mantenimiento</a>
							<a class="dropdown-item" href="reporteStock.jsp">Reportes</a>
							<div class="dropdown-divider"></div>

							<%
								}
							%>

							<a class="dropdown-item" href="crudUsu">Cerrar Sesion</a>
						</div></li>
					<%
						}
					%>

				</ul>
			</div>
		</div>
	</nav>
	<section class="jumbotron text-center mb-0">
		<div class="container">
			<h1 class="jumbotron-heading">Registrar Usuario</h1>
		</div>
	</section>
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top-2 mb-3"
		style="background-color: #343a40;">
		<div class="container">
			<ul class="navbar-nav pl-0">
				<li class="nav-item dropdown pl-0"><a
					class="nav-link dropdown-toggle pl-0 pr-5" href="#"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <span
						class="pl-0 h6">PRODUCTOS</span>
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="listadoProductos.jsp">Listar</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="registrarProducto.jsp">Registrar</a>
					</div></li>
				<li class="nav-item dropdown active pl-0"><a
					class="nav-link dropdown-toggle pl-0 pr-5" href="#"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <span
						class="pl-0 h6">USUARIOS</span>
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="listadoUsuarios.jsp">Listar</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="registrarUsuario.jsp">Registrar</a>
					</div></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<h5 class="my-4">Filtros</h5>
				<form action="crudUsu" method="post">
				<div class="form-group">
						<label for="exampleInputFecha1">Tipo: </label> <select
							class="form-control" name="cboTipo">
							<ct:llenaTipo value="${cboTip}" />
						</select>
					</div>
					<div class="form-group">
						<label for="exampleInputFecha1">Distrito: </label> <select
							class="form-control" name="cboDistrito">
							<ct:llenaDistrito value="${cboDis}" />
						</select>
					</div>
					<div class="form-group">
						<label for="exampleInputFecha1">Estado: </label> <select
							class="form-control" name="cboEstado" id="exampleInputFecha1">
							<ct:llenaEstado value="2" />
						</select>
					</div>
					<button type="submit" class="btn btn-primary float-right"
						name="btnes" value="l">Listar</button>
				</form>
				<br> <br> <br>
				<p>${mensaje}</p>
			</div>
			<div class="col-lg-9">
			<br>
				<form action="crudUsu" method="post">
					<div class="form-group">
						<label for="exampleInputNombre1">Codigo de usuario: </label> <input
							type="text" class="form-control" id="exampleInputNombre1"
							aria-describedby="emailHelp" placeholder="" name="txtCodigo"
							readonly>
					</div>
					<div class="form-group">
						<label for="exampleInputApellido1">Nombre: </label> <input
							type="text" class="form-control" id="exampleInputApellido1"
							placeholder="Ingrese nombre" pattern="[A-Za-z]{1,15}" required="required"
							name="txtNombre"  value="${data.nombre}">
					</div>
					<div class="form-group">
						<label for="exampleInputApellido1">Apellido: </label>
						<input class="form-control" id="exampleInputApellido1"
							placeholder="Ingrese la descripcion del producto"  value="${data.apellido}"
							 name="txtApellido" pattern="[A-Za-z ]{1,30}" required="required">
					</div>
					<div class="form-group">
						<label for="exampleInputFecha1">Distrito: </label> <select
							class="form-control" name="cboDistrito" id="exampleInputFecha1">
							<ct:llenaDistrito value="${data.codDistrito}" />
						</select>
					</div>
					<div class="form-group">
						<label for="exampleInputApellido1">Usuario: </label>
						<input class="form-control" id="exampleInputApellido1"
							placeholder="Ingrese la descripcion del producto"  value="${data.usuario}"
							name="txtUsuario" pattern="[A-Za-z@0-9]{1,30}" required="required">
					</div>
					<div class="form-group">
						<label for="exampleInputApellido1">Clave: </label>
						<input class="form-control" id="exampleInputApellido1"
							placeholder="Ingrese la descripcion del producto"  value="${data.clave}"
							name="txtClave" pattern="[A-Za-z@0-9]{1,30}" required="required">
					</div>
					<div class="form-group">
						<label for="exampleInputFecha1">Tipo: </label> <select
							class="form-control" name="cboTipo" id="exampleInputFecha1">
							<ct:llenaTipo  value="${data.idTipo}"/>
						</select>
					</div>
					<div class="form-group">
						<label for="exampleInputFecha1">Estado: </label> <select
							class="form-control" name="cboEstado" id="exampleInputFecha1">
							<ct:llenaEstado value="${data.estado}" />
						</select>
					</div>

					<button type="submit" class="btn btn-primary mb-3 float-right"
						name="btnes" value="r">Registrar</button>
				</form>
				<br>
			</div>
		</div>
	</div>
	<footer class="text-light">
		<div class="container">
			<div class="row">
				<div class="col-md-3 col-lg-4 col-xl-3">
					<h5>About</h5>
					<hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
					<p class="mb-0">Le Lorem Ipsum est simplement du faux texte
						employé dans la composition et la mise en page avant impression.</p>
				</div>

				<div class="col-md-2 col-lg-2 col-xl-2 mx-auto">
					<h5>Informations</h5>
					<hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
					<ul class="list-unstyled">
						<li><a href="">Link 1</a></li>
						<li><a href="">Link 2</a></li>
						<li><a href="">Link 3</a></li>
						<li><a href="">Link 4</a></li>
					</ul>
				</div>

				<div class="col-md-3 col-lg-2 col-xl-2 mx-auto">
					<h5>Others links</h5>
					<hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
					<ul class="list-unstyled">
						<li><a href="">Link 1</a></li>
						<li><a href="">Link 2</a></li>
						<li><a href="">Link 3</a></li>
						<li><a href="">Link 4</a></li>
					</ul>
				</div>

				<div class="col-md-4 col-lg-3 col-xl-3">
					<h5>Contact</h5>
					<hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
					<ul class="list-unstyled">
						<li><i class="fa fa-home mr-2"></i> My company</li>
						<li><i class="fa fa-envelope mr-2"></i> email@example.com</li>
						<li><i class="fa fa-phone mr-2"></i> + 33 12 14 15 16</li>
						<li><i class="fa fa-print mr-2"></i> + 33 12 14 15 16</li>
					</ul>
				</div>
				<div class="col-12 copyright mt-3">
					<p class="float-left">
						<a href="#">Back to top</a>
					</p>

				</div>
			</div>
		</div>
	</footer>

	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
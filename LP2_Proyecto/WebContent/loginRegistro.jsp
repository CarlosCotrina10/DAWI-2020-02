<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-bootstrap-tags" prefix="sb" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>

<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Tienda - miTienda</title>

<link href="css/login.css" rel="stylesheet">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/cart.css" rel="stylesheet">
<link href="css/shop.css" rel="stylesheet">
<s:head/>
<sj:head/>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
	body {
		color:#0275d8;
	}
</style>
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
					
					<s:if test="1==1"> <!-- Usuario es null -->
						<li class="nav-item"><a class="nav-link" href="loginRegistro.jsp">Iniciar
							Sesion</a></li>
					</s:if>
					
					<s:else> <!-- Usuario se logeo -->
						<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Bienvenido ${usuario.nombre} </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">	
											
							<s:if test="1==0"> <!-- si el usuario es de tipo 1 --> 
								<a class="dropdown-item" href="tiendaSer?btnes=l&cat=0">Tienda</a> <a
								class="dropdown-item" href="listadoProductos.jsp">Mantenimiento</a>
								<a class="dropdown-item" href="reporteStock.jsp">Reportes</a>
								<div class="dropdown-divider"></div>
							</s:if>
							
							<a class="dropdown-item" href="crudUsu">Cerrar Sesion</a>
						</div></li>
					</s:else>
					
				</ul>
				<a class="btn btn-success btn-sm ml-3" href="carrito.jsp"> <i
					class="fa fa-shopping-cart"></i> Cart <span
					class="badge badge-light">${cantArticulos }</span>
				</a>
			</div>
		</div>
	</nav>
	<br>
	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">Iniciar Sesion</label> <input id="tab-2"
				type="radio" name="tab" class="sign-up"><label for="tab-2"
				class="tab">Registrar</label>
			<div class="login-form">
				<form action="login" method="post">
					<div class="sign-in-htm">
						<div class="group">
						<br>
						<br>
						<br>
						<br>
							<label for="user" class="label">Usuario</label> <input id="user"
								type="text" name="u.usuario" class="input" required="required">
						</div>
						<div class="group">
							<label for="pass" class="label">Contraseña</label> <input
								id="pass" type="password" name="u.clave" class="input"
								data-type="password" required="required">
						</div>
						<br>
						<div class="group">
						<s:hidden name="p.idCategoria" value="-1"/>
							<!--<button type="submit" class="btn btn-primary" name="btnes" value="v">Ingresar</button>-->
							<input type="submit" class="button" name="btnes" value="Iniciar Sesion">
						</div>						
						<s:actionmessage/>
						<s:actionerror/>
					</div>
				</form>			
				<form action="crudUsu">
					<div class="sign-up-htm">
						<div class="group">
							<label for="user" class="label">Nombre</label> <input id="nombre"
								type="text" name="txtNombre" class="input" required="required" pattern="[A-Za-z]{1,15}">
						</div>
						<div class="group">
							<label for="user" class="label">Apellido</label> <input
								id="apellido" type="text" name="txtApellido" class="input" required="required" pattern="[A-Za-z ]{1,30}">
						</div>
						<div class="group">
							<label for="user" class="label">Distrito</label>  <select
								name="cboDistrito" class="input">
								<!--dist:LlenaCombo/-->
								<ct:llenaDistrito/>
							</select>
						</div>
						<div class="group">
							<label for="user" class="label">Usuario</label> <input id="user"
								type="text" name="txtUsuario" class="input" required="required">
						</div>
						<div class="group">
							<label for="pass" class="label">Clave</label> <input id="pass"
								type="password" name="txtClave" class="input"
								data-type="password" required="required">
						</div>
						<br>
						<div class="group">
							<input type="submit" class="button" name="btnes" value="Registrar">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
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

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>


</html>

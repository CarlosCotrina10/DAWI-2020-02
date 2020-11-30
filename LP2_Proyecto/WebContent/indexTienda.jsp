<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-bootstrap-tags" prefix="sb"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Tienda - miTienda</title>
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/shop.css" rel="stylesheet">
<link href="css/cart.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<s:head />
<sj:head />
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
	<div class="container">
		<a class="navbar-brand py-0 my-0 by-0 h1"
			href="cargarProdTienda"><span class="mb-0 h3">miTienda</span></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<s:set var="usu" value="0" />
				<s:if test="#session.usuario != null">
					<s:set var="usu" value="#session.usuario" />
				</s:if>

				<s:if test="#usu == 0">
					<!-- si el usuario es null, regresar al login-->
					<li class="nav-item"><a class="nav-link"
						href="loginRegistro.jsp">Iniciar Sesion</a></li>
				</s:if>

				<s:else>

					<s:if test="1 == 1">
						<!-- si el usuario es un cliente, tipo = 0 se dirige al loginregistro-->
					</s:if>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Bienvenido <s:property
								value="#session.usuario.nombre" />
					</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">

							<s:if test="#session.usuario.idTipo == 1">
								<!-- si el usuario es un Admin, tipo = 1-->
								<a class="dropdown-item" href="index.jsp">Tienda</a>
								<a class="dropdown-item" href="listadoProductos.jsp">Mantenimiento</a>
								<a class="dropdown-item" href="reporteStock.jsp">Reportes</a>
								<div class="dropdown-divider"></div>
							</s:if>

							<a class="dropdown-item" href="logout">Cerrar Sesion</a>
						</div></li>
				</s:else>

			</ul>
			<a class="btn btn-success btn-sm ml-3" href="carrito.jsp"> 
				<i class="fa fa-shopping-cart">
				</i> Cart 
				<s:set name="contador" value="0"/>
				<s:if test="#session.contador != null">
					<s:set name="contador" value="#session.contador"/>
				</s:if>
				<span class="badge badge-light"><s:property value="#contador"/> </span>
			</a>
		</div>
	</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-lg-3" id="test">

				<h5 class="my-4">Categorias</h5>
				<s:div cssClass="list-group">
						<s:if test="p.idCategoria == -1">
							<a href="cargarProdTienda?p.idCategoria=-1" class="list-group-item active"> Todo </a>
						</s:if>
						<s:else>
							<a href="cargarProdTienda?p.idCategoria=-1" class="list-group-item"> Todo </a>
						</s:else>
					<s:iterator value="lstCategoria">
						<s:if test="p.idCategoria == idCategoria">
							<a href="cargarProdTienda?p.idCategoria=<s:property value="idCategoria"/>" class="list-group-item active"> <s:property value="descripcion"/> </a>
						</s:if>						
						<s:else>
							<a href="cargarProdTienda?p.idCategoria=<s:property value="idCategoria"/>" class="list-group-item"> <s:property value="descripcion"/> </a>
						</s:else>
					</s:iterator>						
				</s:div>

			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">

				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<img class="d-block img-fluid" src="img/CyberWow.jpg"
								alt="First slide">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid" src="img/carr3.png"
								alt="Second slide">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid" src="img/cyberwow2.png"
								alt="Third slide">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
				<div class="row">
					
					
					
					<s:iterator value="lstProducto">
						<div class="col-lg-4 col-md-6 mb-4">
							<div class="card h-100">

								<a href="buscarProduc?&p.idProd=<s:property value="idProd"/>"><img
									class="card-img-top"
									src="img/imgs_700x400/<s:property value="idProd"/>.jpg"
									alt="No found"></a>
								<div class="card-body">
									<h4 class="card-title">
										<a href="buscarProduc?&p.idProd=<s:property value="idProd"/>"><s:property
												value="nomProd" /></a>
									</h4>
									<h5>
										S/
										<s:property value="precio" />
									</h5>
									<p class="card-text">
										<s:property value="descripcion" />
									</p>
								</div>
								<div class="card-footer">
									<small class="text-muted">&#9733; &#9733; &#9733;
										&#9733; &#9733;</small>
								</div>
							</div>
						</div>
					</s:iterator>
						
				</div>
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

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>

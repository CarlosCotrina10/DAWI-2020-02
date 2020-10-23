<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-bootstrap-tags" prefix="sb"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="UTF-8">

<title>Productos - Listado</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/shop.css">
<link href="css/cart.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<s:head/>
<sj:head/>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
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
				
					<s:if test="1==1">
						<!-- si el usuario es null, regresar al login-->
						<li class="nav-item"><a class="nav-link"
							href="loginRegistro.jsp">Iniciar Sesion</a></li>
					</s:if>

					<s:else>

						<s:if test="">
							<!-- si el usuario es un cliente, tipo = 0 se dirige al loginregistro-->
						</s:if>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> Bienvenido ${usuario.nombre} </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">

								<s:if test="">
									<!-- si el usuario es un Admin, tipo = 1-->
									<a class="dropdown-item" href="tiendaSer?btnes=l&cat=0">Tienda</a>
									<a class="dropdown-item" href="listadoProductos.jsp">Mantenimiento</a>
									<a class="dropdown-item" href="reporteStock.jsp">Reportes</a>
									<div class="dropdown-divider"></div>
								</s:if>

								<a class="dropdown-item" href="crudUsu">Cerrar Sesion</a>
							</div></li>
					</s:else>

				</ul>
			</div>
		</div>
	</nav>
	<section class="jumbotron text-center mb-0">
		<div class="container">
			<h1 class="jumbotron-heading">Mantenimiento de Productos</h1>
		</div>
	</section>
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top-2 mb-3"
		style="background-color: #343a40;">
		<div class="container">
			<ul class="navbar-nav pl-0">
				<li class="nav-item dropdown active pl-0"><a
					class="nav-link dropdown-toggle pl-0 pr-5" href="#"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <span
						class="pl-0 h6">PRODUCTOS</span>
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="listadoProd?pro.idCategoria=-1&pro.estado=-1">Listar</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="registrarProducto.jsp">Registrar</a>
					</div></li>
				<li class="nav-item dropdown pl-0"><a
					class="nav-link dropdown-toggle pl-0 pr-5" href="#"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <span
						class="pl-0 h6">USUARIOS</span>
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="listadoUsuario?usu.idTipo=-1&usu.codDistrito=-1&usu.estado=-1">Listar</a>
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
				<s:form action="listadoProd" method="post" theme="bootstrap">
					<s:url id="idCateg" action="listadoCat" />
					<sj:select label="Categorias" href="%{idCateg}" list="lstCategoria"
						listKey="idCategoria" listValue="descripcion" headerKey="-1"
						headerValue="Todas" name="pro.idCategoria"
						cssClass="form-control" />
					<s:select label="Estado"
								list="#{'-1':'Todos los Estados','0':'Desactivado','1':'Activo' }" name="pro.estado"
								cssClass="form-control" />
					<s:submit value="Listar" cssClass="btn btn-primary float-right"/>
				</s:form>
				<br> <br> <br>
				<p>${mensaje}</p>
			</div>
			<div class="col-lg-9">
				<br>
				<table id="table" class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Categoria</th>
							<th>Stock</th>
							<th>Precio</th>
							<th></th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<s:iterator value="lstProducto">						
							<tr class="grilla_campo">
								<td><s:property value="idProd"/></td>
								<td><s:property value="nomProd"/></td>
								<td><s:property value="descripcion"/></td>
								<td><s:property value="idCategoria"/></td>
								<td><s:property value="stock"/></td>
								<td style="color: red;">S/.<s:property value="precio"/></td>
								<td><a href="buscarProd?p.idProd=<s:property value="idProd"/>"> <img
										alt="editar" src="img/edit1.png" height=21px width=21px
										title="Actualizar">
								</a></td>
								<td><a href="crudProd?btn=Eliminar&p.idProd=<s:property value="idProd"/>&p.estado=0&pro.idCategoria=-1&pro.estado=-1">      
										<img alt="editar" src="img/disable1.png" height=21px
										width=21px title="Descativar">
								</a></td>
							</tr>
						</s:iterator>						
					</tbody>
				</table>
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
						employ� dans la composition et la mise en page avant impression.</p>
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
</body>
<script>
	$(document).ready(function() {
		$('#table').DataTable();
	});
</script>

</html>
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

<title>Reporte - Productos</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/shop.css">
<link href="css/cart.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<s:if test="#session.usuario == null">
		<s:action name="principal" executeResult="true"/>
	</s:if>
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
			</div>
		</div>
	</nav>
	<section class="jumbotron text-center mb-0">
		<div class="container">
			<h1 class="jumbotron-heading">Reportes</h1>
		</div>
	</section>
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top-2 mb-3"
		style="background-color: #343a40;">
		<div class="container">
			<ul class="navbar-nav pl-0">
				<li class="nav-item pl-0"><a class="nav-link pl-0 pr-5"
					href="reporteStock.jsp" id="navbarDropdown" aria-haspopup="true"
					aria-expanded="false"> <span class="pl-0 h6">STOCK</span>
				</a></li>
				<li class="nav-item pl-0"><a class="nav-link pl-0 pr-5"
					href="reporteVentas.jsp" id="navbarDropdown" aria-haspopup="true"
					aria-expanded="false"> <span class="pl-0 h6">VENTAS POR
							MES</span>
				</a></li>
				<li class="nav-item active pl-0"><a class="nav-link pl-0 pr-5"
					href="reporteProductos.jsp" id="navbarDropdown"
					aria-haspopup="true" aria-expanded="false"> <span
						class="pl-0 h6">VENTAS DE PRODUCTOS</span>
				</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<h5 class="my-4">Filtros</h5>				
				<s:form action="ventaProducto" method="post" cssStyle="width: 100%">
					<s:div cssClass="form-group clearfix">
						<s:textfield label="Desde" cssClass="form-control col-lg-11 text-center float-right" 
						type="date"  name="p.fech1" placeholder="yyyy/MM/dd" required="required"/>
					</s:div>
					<s:div cssClass="form-group clearfix" >
						<s:textfield label="Hasta" cssStyle="margin-top: 15px;" cssClass="form-control col-lg-11 text-center float-right" 
						type="date"  name="p.fech2" placeholder="yyyy/MM/dd" required="required"/>
					</s:div>
					<s:submit value="Listar" cssClass="btn btn-primary float-right" cssStyle="margin-top: 15px;"/>					
				</s:form>
				<br> <br> <br>
				<p>${mensaje}</p>
			</div>
			<div class="col-lg-9">
				<br>
				<table id="table" class="table table-hover">
					<thead>
						<tr>
							<th class="text-right">Id</th>
							<th class="text-right">Nombre</th>
							<th class="text-right">Stock</th>
							<th class="text-right">Categoria</th>
							<th class="text-right">Cantidad vendidos</th>
							<th class="text-right">Estado</th>
							<th class="text-right">Total</th>
						</tr>
					</thead>

					<tbody>
						<s:iterator value="lstVentaProducto">
							<tr class="grilla_campo">
								<td class="text-right"> <s:property value="idprod"/> </td>
								<td class="text-right"><s:property value="nombre"/></td>
								<td class="text-right"><s:property value="stock"/></td>
								<td class="text-right"><s:property value="categoria"/></td>
								<td class="text-right"><s:property value="cantidad"/></td>
								<td class="text-right"><s:property value="estado"/></td>
								<td class="text-right">S/.<s:property value="total"/></td>
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
</body>
<script>
	$(document).ready(function() {
		$('#table').DataTable({
			"order" : [ [ 6, "desc" ] ]
		});
	});
</script>

</html>
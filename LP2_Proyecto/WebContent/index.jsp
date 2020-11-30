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
	<s:action name="cargarProdTienda" executeResult="true"/>
	
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>

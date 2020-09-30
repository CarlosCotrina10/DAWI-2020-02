package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProductoDTO;
import dao.DAOFactory;
import interfaces.ProductoDAO;

/**
 * Servlet implementation class ProductoServlet
 */
@WebServlet(name = "crudProducto", urlPatterns = { "/crudProducto" })
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al Servlet de productos");
		String opc = request.getParameter("btnes");
		System.out.println("Opcion: " + opc);
		switch (opc) {
		case "r": registro(request, response);break;
		case "a": actualizar(request, response);break;
		case "e": desactivar(request, response);break;
		case "l": listado(request, response);break;
		case "q": buscar(request, response);break; 
		default:PrintWriter out = response.getWriter();
				out.println("<h1>Error, reingrese</h1>");
				break;
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entró a buscar el producto");
		int idprod;
		
		idprod=Integer.parseInt(request.getParameter("cod"));

		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = fabrica.getProductoDAO();
		ProductoDTO p = dao.buscar(idprod);
		p.setEstado(p.getEstado()+1);
		
		request.getSession().setAttribute("categoriaT", p.getIdcategoria());
		request.setAttribute("data", p);
		request.getRequestDispatcher("/actualizarProducto.jsp").forward(request, response);
	}

	private void listado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idcategoria, estado;
		
		estado = Integer.parseInt(request.getParameter("cboEstado"));
		idcategoria = Integer.parseInt(request.getParameter("cboCategoria"));
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = fabrica.getProductoDAO();
		ArrayList<ProductoDTO> lista = dao.listado(idcategoria, estado);
		
		request.setAttribute("milista", lista);
		request.getSession().setAttribute("cboCat", idcategoria);
		
		if (estado == 0) {
			request.setAttribute("mensaje", "Cargó la lista de productos desactivados");
			request.getRequestDispatcher("/listadoProductosDesactivados.jsp").forward(request, response);
			return;
		} else {
			request.setAttribute("mensaje", "Cargó la lista de productos activos");
			request.getRequestDispatcher("/listadoProductos.jsp").forward(request, response);
			return;
		}
	}

	private void desactivar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al cambiarEstado de producto");
		String mensaje, url;
		ArrayList<ProductoDTO> lista = null;
		int estado, idprod, idcategoria;
		
		idprod = Integer.parseInt(request.getParameter("cod"));
		estado = Integer.parseInt(request.getParameter("est"));
		idcategoria = (int) request.getSession().getAttribute("cboCat");
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = fabrica.getProductoDAO();		
		int ok = dao.cambiarEstado(idprod, estado);
			
		if (estado == 1) {
			mensaje = "Se activó el producto " + idprod;
			lista = dao.listado(idcategoria, 0);
			url = "/listadoProductosDesactivados.jsp";
		} else {
			mensaje = "Se descativó el producto " + idprod;
			lista = dao.listado(idcategoria, 1);
			url = "/listadoProductos.jsp";
		}
		
		if (ok==0) {
			mensaje = "Error al cambiar estado del producto";
		} 
		
		request.setAttribute("milista", lista);
		request.setAttribute("mensaje", mensaje);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al actualizar de producto");
		String mensaje;
		
		int stock, idcategoria, estado, idprod;
		String nombre, descripcion;
		double precio;
		
		idprod = Integer.parseInt(request.getParameter("txtCodigo"));
		nombre = request.getParameter("txtNombre");
		descripcion = request.getParameter("txtDescripcion");
		stock = Integer.parseInt(request.getParameter("txtStock"));
		precio = Double.parseDouble(request.getParameter("txtPrecio"));
		idcategoria = Integer.parseInt(request.getParameter("cboCategoria"));
		estado = Integer.parseInt(request.getParameter("cboEstado"));
		
		ProductoDTO p = new ProductoDTO();
		p.setIdprod(idprod);
		p.setNomprod(nombre);
		p.setDescripcion(descripcion);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setIdcategoria(idcategoria);
		p.setEstado(estado);
		
		if (idcategoria == 0 && estado == -1) {
			request.setAttribute("mensaje", "Seleccione un estado y categoria");
			p.setEstado(p.getEstado()+1);
			request.setAttribute("data", p);
			request.getRequestDispatcher("/actualizarProducto.jsp").forward(request, response);
			return;
		} else if (idcategoria == 0) {
			request.setAttribute("mensaje", "Seleccione una categoria");
			p.setEstado(p.getEstado()+1);
			request.setAttribute("data", p);
			request.getRequestDispatcher("/actualizarProducto.jsp").forward(request, response);
			return;
		} else if (estado == -1) {
			request.setAttribute("mensaje", "Seleccione un estado");
			p.setEstado(p.getEstado()+1);
			request.setAttribute("data", p);
			request.getRequestDispatcher("/actualizarProducto.jsp").forward(request, response);
			return;
		}
		
		System.out.println(p);
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = fabrica.getProductoDAO();		
		int ok = dao.actualizar(p);
		
		if (ok==0) {
			mensaje = "Error al actualizar producto";
		} else {
			mensaje = "Actualizacion de producto " + idprod + " OK";
		}
		
		request.setAttribute("data", p);
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("/actualizarProducto.jsp").forward(request, response);
	}

	private void registro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entró a registrar producto");

		String nomprod, descripcion;
		int stock,idcategoria,estado;
		double precio;
		String mensaje;
		
		nomprod   = request.getParameter("txtNombre");
		descripcion = request.getParameter("txtDescripcion");
		stock=Integer.parseInt(request.getParameter("txtStock"));
		precio=Double.parseDouble(request.getParameter("txtPrecio"));
		idcategoria=Integer.parseInt(request.getParameter("cboCategoria"));
		estado=Integer.parseInt(request.getParameter("cboEstado"));
		
		ProductoDTO p = new ProductoDTO();
		p.setNomprod(nomprod);
		p.setDescripcion(descripcion);
		p.setStock(stock);
	    p.setPrecio(precio);
	    p.setIdcategoria(idcategoria);
	    p.setEstado(estado);
		System.out.println(p);
		
		if (idcategoria == 0 && estado ==- 1) {
			request.setAttribute("mensaje", "Seleccione un estado y categoria");
			System.out.println("a");
			p.setEstado(p.getEstado()+1);
			request.setAttribute("data", p);
			request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
			return;
		} else if (idcategoria == 0) {
			request.setAttribute("mensaje", "Seleccione una categoria");
			p.setEstado(p.getEstado()+1);
			request.setAttribute("data", p);
			request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
			return;
		} else if (estado == -1) {
			request.setAttribute("mensaje", "Seleccione un estado");
			p.setEstado(p.getEstado()+1);
			request.setAttribute("data", p);
			request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
			return;
		}
		System.out.println("b");
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = fabrica.getProductoDAO();
		int ok = dao.registrar(p);
		
		if (ok == 0) {
			mensaje = "Error al registrar los datos!!!";
		} else {
			mensaje = "Registro del producto " + nomprod + " OK";
		}
		
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
	}

}

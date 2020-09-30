package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CabeceraBoletaDTO;
import beans.DetalleBoletaDTO;
import beans.ProductoDTO;
import beans.UsuarioDTO;
import dao.DAOFactory;
import interfaces.ProductoDAO;
import interfaces.VentaDAO;

/**
 * Servlet implementation class TiendaServlet
 */
@WebServlet(name = "tiendaSer", urlPatterns = { "/tiendaSer" })
public class TiendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al Servlet de la tienda");
		String opc = request.getParameter("btnes");
		System.out.println("Opcion: " + opc);
		switch (opc) {
		case "q": buscar(request, response);break; 
		case "a": agregar(request, response);break; 
		case "e": eliminar(request, response);break; 
		case "b": generarBoleta(request, response);break;
		case "l": listar(request, response);break;
		case "i": index(request, response);break;
		default:PrintWriter out = response.getWriter();
				out.println("<h1>Error, reingrese</h1>");
				break;
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cat;
		cat = Integer.parseInt(request.getParameter("cat"));
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		VentaDAO dao = fabrica.getVentaDAO();		
		ArrayList<ProductoDTO> tienda = dao.listado(cat);
		
		request.getSession().setAttribute("categoriaT", cat);
		request.getSession().setAttribute("tienda", tienda);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void generarBoleta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al metodo de generar boleta");	
		
		String mensaje , url;
		UsuarioDTO u = (UsuarioDTO) request.getSession().getAttribute("usuario");
		if (u == null) {
			request.setAttribute("mensaje", "Inicie sesion para realizar la compra");
			request.getRequestDispatcher("/loginRegistro.jsp").forward(request, response);
			return;
		}
		double subTotalVenta = (double) request.getSession().getAttribute("subTotalVenta");
		ArrayList<DetalleBoletaDTO> carro = (ArrayList<DetalleBoletaDTO>) request.getSession().getAttribute("carro");
		
		if(carro.size() == 0) {
			request.setAttribute("mensaje", "Agregue productos a su carrito");
			request.getRequestDispatcher("/carrito.jsp").forward(request, response);
			return;
		}
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		VentaDAO v = fabrica.getVentaDAO();		
		
		String numBoleta = v.generaNumBoleta();
		CabeceraBoletaDTO cab = new CabeceraBoletaDTO();
		cab.setCodUsuario(u.getCodUsuario()); 
		cab.setNumBoleta(numBoleta); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		cab.setFecha(sdf.format(date));
		cab.setTotal(subTotalVenta);
		
		int ok = v.realizarVenta(cab, carro);
		if (ok==-1) {
			mensaje = "Error al procesar compra";
			url = "/carrito.jsp";
		} else {
			carro = new ArrayList<DetalleBoletaDTO>();
	        int cantArticulos = 0;
	        subTotalVenta = 0.0;
	        request.getSession().setAttribute("carro", carro);
	        request.getSession().setAttribute("cantArticulos", cantArticulos);
	        request.getSession().setAttribute("subTotalVenta", subTotalVenta);
	        
			mensaje = "Compra realizada con Numero de Boleta : " + numBoleta;
			url = "/carrito.jsp";
		}
		
		//volver a listar los productos para la tienda
		VentaDAO dao = fabrica.getVentaDAO();
		ArrayList<ProductoDTO> tienda = dao.listado(0);
		request.getSession().setAttribute("tienda", tienda);
		
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher(url).forward(request, response);
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<DetalleBoletaDTO> carro = (ArrayList<DetalleBoletaDTO>) request.getSession().getAttribute("carro");
		int i = Integer.parseInt(request.getParameter("con"));
		
		int cant = carro.get(i).getCantidad();
		int cantArticulos = (int) request.getSession().getAttribute("cantArticulos");
		cantArticulos = cantArticulos - cant;

		carro.remove(i);
		
		double subTotalVenta = 0;
		
		for (DetalleBoletaDTO d : carro) {
			subTotalVenta += d.getPreciovta()*d.getCantidad();
		}
		
		subTotalVenta = Math.round(subTotalVenta*10);
		subTotalVenta = subTotalVenta/10;
		
		request.getSession().setAttribute("subTotalVenta", subTotalVenta);
		request.getSession().setAttribute("cantArticulos", cantArticulos);
		request.getSession().setAttribute("carro", carro);
		request.setAttribute("mensaje", "Producto Eliminado");
		request.getRequestDispatcher("/carrito.jsp").forward(request, response);
		
	}

	private void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entro al metodo agregar de tienda");
		String mensaje = "";
		boolean exist = false;
		
		int cant = Integer.parseInt(request.getParameter("cantidad"));
		ProductoDTO p = (ProductoDTO) request.getSession().getAttribute("p");
		
		ArrayList<DetalleBoletaDTO> carro = (ArrayList<DetalleBoletaDTO>) request.getSession().getAttribute("carro");
		int cantArticulos = (int) request.getSession().getAttribute("cantArticulos");
		double subTotalVenta = (double) request.getSession().getAttribute("subTotalVenta");
			
		for (int i = 0; i < carro.size(); i++) {
			if(carro.get(i).getIdprod() == p.getIdprod()) {
				exist = true;
				cant += carro.get(i).getCantidad();
				if(p.getStock() >= cant) {
					carro.get(i).setCantidad(cant);
				} else {
					cant = p.getStock() - carro.get(i).getCantidad();
					carro.get(i).setCantidad(p.getStock());
					mensaje = "No se pudo agregar todo debido al stock";
				}
				break;
			}
		}
		
		double importe = cant * p.getPrecio();
		cantArticulos += cant;
		subTotalVenta += importe;
		
		subTotalVenta = Math.round(subTotalVenta*10);
		subTotalVenta = subTotalVenta/10;
		
		if (!exist) {
			DetalleBoletaDTO v = new DetalleBoletaDTO();
			v.setIdprod(p.getIdprod());
			v.setNombre(p.getNomprod());
			v.setCantidad(cant);
			v.setPreciovta(p.getPrecio());
			carro.add(v);
			mensaje = "Producto agregado";
		}
		
		request.setAttribute("mensaje", mensaje);
		request.getSession().setAttribute("carro", carro);
		request.getSession().setAttribute("cantArticulos", cantArticulos);
		request.getSession().setAttribute("subTotalVenta", subTotalVenta);
		
		request.getRequestDispatcher("/carrito.jsp").forward(request, response);
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al buscar producto en tienda");
		int cod;
		cod = Integer.parseInt(request.getParameter("cod"));
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = fabrica.getProductoDAO();		
		ProductoDTO p = dao.buscar(cod);
		
		request.getSession().setAttribute("p", p);
		request.getRequestDispatcher("/producto.jsp").forward(request, response);
	}

}

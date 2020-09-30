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
import beans.VentasMesDTO;
import beans.VentasProductosDTO;
import dao.DAOFactory;
import interfaces.ReporteDAO;

/**
 * Servlet implementation class ReporteServlet
 */
@WebServlet(name = "reporte", urlPatterns = { "/reporte" })
public class ReporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al Servlet de reportes");
		String opc = request.getParameter("btnes");
		System.out.println("Opcion: " + opc);
		switch (opc) {
		case "s": stock(request, response);break; 
		case "m": mes(request, response);break; 
		case "p": producto(request, response);break;
		default:PrintWriter out = response.getWriter();
				out.println("<h1>Error, reingrese</h1>");
				break;
		}
	}

	private void producto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date1, date2;
		date1 = request.getParameter("txtFecha1");
		date2 = request.getParameter("txtFecha2");
		
		System.out.println("Fecha1 : " + date1);
		System.out.println("Fecha2 : " + date2);
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ReporteDAO dao = fabrica.getReporteDAO();
		ArrayList<VentasProductosDTO> lista = dao.listadoVentaProductos(date1, date2);
		
		request.setAttribute("fecha1F", date1);
		request.setAttribute("fecha2F", date2);
		request.setAttribute("milista", lista);
		request.setAttribute("mensaje", "Cargó el roporte de ventas de productos");
		request.getRequestDispatcher("/reporteProductos.jsp").forward(request, response);
	}

	private void mes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = Integer.parseInt(request.getParameter("txtYear"));
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ReporteDAO dao = fabrica.getReporteDAO();
		ArrayList<VentasMesDTO> lista = dao.listadoVentasMes(year);
		
		request.setAttribute("milista", lista);
		request.setAttribute("yearF", year);
		request.setAttribute("mensaje", "Cargó el roporte de ventas por mes");
		request.getRequestDispatcher("/reporteVentas.jsp").forward(request, response);
	}

	private void stock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int stock = Integer.parseInt(request.getParameter("txtStock"));
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ReporteDAO dao = fabrica.getReporteDAO();
		ArrayList<ProductoDTO> lista = dao.listaStock(stock);
		
		request.setAttribute("milista", lista);
		request.setAttribute("stockF", stock);
		
		
		request.setAttribute("mensaje", "Cargó el roporte de producto con " + stock + " de stock o menos");
		request.getRequestDispatcher("/reporteStock.jsp").forward(request, response);
		
		
	}

}

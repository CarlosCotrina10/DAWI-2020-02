package servlets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import beans.DetalleBoletaDTO;
import beans.ProductoDTO;
import dao.DAOFactory;
import interfaces.VentaDAO;

/**
 * Application Lifecycle Listener implementation class ProyectoListener
 *
 */
@WebListener
public class ProyectoListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public ProyectoListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	System.out.println("Creando la session");
        System.out.println("ID de la session : " + arg0.getSession().getId());
        SimpleDateFormat sdf = new SimpleDateFormat();
        System.out.println("Creado el : " + sdf.format(arg0.getSession().getCreationTime()));
        System.out.println("Intervalo : " + arg0.getSession().getMaxInactiveInterval());
       
        carrito(arg0);
        listaTienda(arg0);
        
    }

	private void listaTienda(HttpSessionEvent arg0) {
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		VentaDAO dao = fabrica.getVentaDAO();
		ArrayList<ProductoDTO> tienda = dao.listado(0);
		arg0.getSession().setAttribute("categoriaT", 0);
		arg0.getSession().setAttribute("tienda", tienda);
		
	}

	private void carrito(HttpSessionEvent arg0) {
		ArrayList<DetalleBoletaDTO> carro = new ArrayList<DetalleBoletaDTO>();
        int cantArticulos = 0;
        double subTotalVenta = 0.0;
        
        arg0.getSession().setAttribute("carro", carro);
        arg0.getSession().setAttribute("cantArticulos", cantArticulos);
        arg0.getSession().setAttribute("subTotalVenta", subTotalVenta);
	}

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("Finalizando la session");
        System.out.println("ID de la session : " + arg0.getSession().getId());
        SimpleDateFormat sdf = new SimpleDateFormat();
        System.out.println("Ultimo acceso : " + sdf.format(arg0.getSession().getLastAccessedTime()));
    }
	
}

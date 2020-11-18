package action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Boleta;
import model.Categoria;
import model.DetalleBoleta;
import model.Producto;
import model.Usuario;
import servicios.ProductoService;
import servicios.VentaService;

@ParentPackage("dawi")
public class VentaAction extends ActionSupport {
	
	//clase
	private ArrayList<Producto> lstProductos;
	private ArrayList<Categoria> lstCategoria;
	private Producto p;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private ArrayList<DetalleBoleta> lstDetalleBoleta;
	private DetalleBoleta det;
	private int cantidad;
	
	//metodos
	@Action(value = "listadoProduc",results = {@Result(name = "ok",location = "index.jsp")})
	public String listaProductos() {
		lstProductos = new VentaService().listado(p);
		lstCategoria = new ProductoService().listadoCategoria();
		return "ok";
	}
	
	@Action(value = "/buscarProduc" , results = {@Result(name = "ok" , location = "/producto.jsp")})
	public String buscarProducto() {
		p = new ProductoService().buscar(p.getIdProd());	
		return "ok";
	}

	@Action(value = "/tiendaSer" , results = {@Result(name = "ok" , location = "/carrito.jsp")})
	public String carrito() {		
		int contador = 0;
		double total = 0;
		if(session.get("carrito") == null) {
			lstDetalleBoleta = new ArrayList<DetalleBoleta>();
		}else {
			lstDetalleBoleta = (ArrayList<DetalleBoleta>) session.get("carrito");
			contador = (int) session.get("contador");
		}
		DetalleBoleta db = new DetalleBoleta();
		db.setIdProd(p.getIdProd());
		db.setCantidad(cantidad);
		db.setPreciovta(p.getPrecio());
		db.setProducto(p.getNomProd());
		
		int n = -1 , salida = -1;
		for(DetalleBoleta d : lstDetalleBoleta) {
			n++;
			if(d.getIdProd() == db.getIdProd()) {
				d.setCantidad(d.getCantidad()+db.getCantidad());
				salida = n;
			}
			total += d.getCantidad()*d.getPreciovta();
		}		
		if(salida == -1 ) {
			lstDetalleBoleta.add(db);
			total += db.getCantidad()*db.getPreciovta();
		}
		contador += cantidad;		
		session.put("contador",contador);
		session.put("total",total);
		session.put("carrito", lstDetalleBoleta);
		
		return "ok";
	}
	
	@Action(value = "/ElimProdCarro" , results = {@Result(name = "ok" , location = "/carrito.jsp")})
	public String EliminarProdCarrito() {
		lstDetalleBoleta = (ArrayList<DetalleBoleta>) session.get("carrito");
		int contador = (int) session.get("contador");
		double total = (double) session.get("total");
		int l = -1 , c = -1 ;
		for(DetalleBoleta d : lstDetalleBoleta) {
			l++;
			if(det.getIdProd() == d.getIdProd()) {
				c = l;
				contador = contador - d.getCantidad();
				total = total - d.getCantidad()*d.getPreciovta();
			}
		}
		lstDetalleBoleta.remove(c);
		session.put("contador", contador);
		session.put("total", total);
		session.put("carrito", lstDetalleBoleta);
		return "ok";
	}
	
	@Action(value = "/principal" , results = {@Result(name = "ok" , location = "/index.jsp")})
	public String validarPaginas() {		
		return "ok";
	}
	
	@Action(value = "/registroBoleta" , results = {@Result(name = "ok" , location = "/carrito.jsp")})
	public String registrarBoleta() {
		Boleta b = new Boleta();
		Usuario usu = (Usuario) session.get("usuario");
		String codigo = new VentaService().generaNumBoleta();
		b.setNumBoleta(codigo);
		b.setCodUsuario(usu.getCodUsuario());
		b.setTotal((double) session.get("total"));
		b.setDetalle((ArrayList<DetalleBoleta>) session.get("carrito"));
		
		int rs = new VentaService().realizarVenta(b);
		if(rs != 0) {
			addActionMessage("Boleta generado");
			session.remove("contador");
			session.remove("total");
			session.remove("carrito");
		}else {
			addActionError("Error al registrar boleta");
		}
		return "ok";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Categoria> getLstCategoria() {
		return lstCategoria;
	}

	public void setLstCategoria(ArrayList<Categoria> lstCategoria) {
		this.lstCategoria = lstCategoria;
	}

	public ArrayList<Producto> getLstProductos() {
		return lstProductos;
	}

	public void setLstProductos(ArrayList<Producto> lstProductos) {
		this.lstProductos = lstProductos;
	}

	public Producto getP() {
		return p;
	}

	public void setP(Producto p) {
		this.p = p;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<DetalleBoleta> getLstDetalleBoleta() {
		return lstDetalleBoleta;
	}

	public void setLstDetalleBoleta(ArrayList<DetalleBoleta> lstDetalleBoleta) {
		this.lstDetalleBoleta = lstDetalleBoleta;
	}

	public DetalleBoleta getDet() {
		return det;
	}

	public void setDet(DetalleBoleta det) {
		this.det = det;
	}
	
}

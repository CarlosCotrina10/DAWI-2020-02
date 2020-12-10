package action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Boleta;
import model.BoletaImpresa;
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
	private ArrayList<BoletaImpresa> boletaImpresa;
	private int compraExitosa;
	
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
		lstCategoria = new ProductoService().listadoCategoria();
		return "ok";
	}

	@Action(value = "/tiendaSer" , results = {@Result(name = "ok" , location = "/carrito.jsp")})
	public String carrito() {		
		int contador = 0;
		double total = 0;
		int temp;
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
				temp = d.getCantidad();
				d.setCantidad(d.getCantidad()+db.getCantidad());
				salida = n;
				if(p.getStock() < d.getCantidad()) {
					cantidad = p.getStock() - temp;
					d.setCantidad(p.getStock());
					addActionError("No se pudieron agregar todos los productos debido al Stock");
				}
			}
			total += d.getCantidad()*d.getPreciovta();
		}		
		if(salida == -1 ) {
			lstDetalleBoleta.add(db);
			total += db.getCantidad()*db.getPreciovta();
		}
		total = Math.round(total*10);
		total = total/10;
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
		total = 0;
		for(DetalleBoleta d : lstDetalleBoleta) {
			l++;
			if(det.getIdProd() == d.getIdProd()) {
				c = l;
				contador = contador - d.getCantidad();
			} else {
				total += d.getPreciovta()*d.getCantidad();
			}
		}
		total = Math.round(total*10);
		total = total/10;
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
	
	@Action(value = "/registroBoleta" , results = {@Result(name = "ok" , location = "/carrito.jsp"),
												   @Result(name = "login", location = "/loginRegistro.jsp")	})
	public String registrarBoleta() {
		Boleta b = new Boleta();
		Usuario usu = (Usuario) session.get("usuario");
		
		if(session.get("carrito") == null) {
			addActionError("No hay productos en el carrito");
			return "ok";
		}
		if(usu == null) {
			return "login";
		}
				
		String codigo = new VentaService().generaNumBoleta();
		b.setNumBoleta(codigo);
		b.setCodUsuario(usu.getCodUsuario());
		b.setTotal((double) session.get("total"));
		b.setDetalle((ArrayList<DetalleBoleta>) session.get("carrito"));
		
		int rs = new VentaService().realizarVenta(b);
		if(rs != 0) {
			addActionMessage("Boleta generada con codigo: " + codigo);
			session.remove("contador");
			session.remove("total");
			session.remove("carrito");
			compraExitosa = 1;
		}else {
			addActionError("Error al registrar boleta");
		}
		return "ok";
	}
	
	@Action(value = "/boletaPDF", results = { @Result(name = "ok", type = "jasper", 
			params = {"location","/reportes/Boleta.jasper","dataSource","boletaImpresa","format","PDF",}  ) })
	public String boletaPDF() {
		boletaImpresa = new VentaService().boletaImprimir();
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

	public ArrayList<BoletaImpresa> getBoletaImpresa() {
		return boletaImpresa;
	}

	public void setBoletaImpresa(ArrayList<BoletaImpresa> boletaImpresa) {
		this.boletaImpresa = boletaImpresa;
	}

	public int getCompraExitosa() {
		return compraExitosa;
	}

	public void setCompraExitosa(int compraExitosa) {
		this.compraExitosa = compraExitosa;
	}
	
}

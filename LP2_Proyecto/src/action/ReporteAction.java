package action;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import beans.ProductoDTO;
import model.Parametro;
import model.VentaMes;
import model.VentasProductos;
import servicios.ReporteService;

@ParentPackage("dawi")
public class ReporteAction  extends  ActionSupport{
	
	private ArrayList<VentaMes> lstVentaMes;
	private ArrayList<VentasProductos> lstVentaProducto;
	private ArrayList<ProductoDTO> lstProducto;
	private ProductoDTO pro;
	private Parametro p;
	
	@Action(value = "/ventaMes", results = {@Result(name = "ok", location = "/reporteVentas.jsp")})
	public String ventasMes() {
		lstVentaMes = new ReporteService().listadoVentasMes(p);
		return "ok";
	}

	@Action(value = "/ventaProducto", results = {@Result(name = "ok", location = "/reporteProductos.jsp")})
	public String ventasProducto() {
		lstVentaProducto = new ReporteService().listadoVentaProductos(p);
		return "ok";
	}
	
	@Action(value = "/stockProducto", results = {@Result(name = "ok", location = "/reporteStock.jsp")})
	public String stockProducto() {
		lstProducto = new ReporteService().listaStock(pro);
		return "ok";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<VentasProductos> getLstVentaProducto() {
		return lstVentaProducto;
	}

	public void setLstVentaProducto(ArrayList<VentasProductos> lstVentaProducto) {
		this.lstVentaProducto = lstVentaProducto;
	}

	public ArrayList<VentaMes> getLstVentaMes() {
		return lstVentaMes;
	}

	public void setLstVentaMes(ArrayList<VentaMes> lstVentaMes) {
		this.lstVentaMes = lstVentaMes;
	}

	public Parametro getP() {
		return p;
	}

	public void setP(Parametro p) {
		this.p = p;
	}

	public ArrayList<ProductoDTO> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(ArrayList<ProductoDTO> lstProducto) {
		this.lstProducto = lstProducto;
	}

	public ProductoDTO getPro() {
		return pro;
	}

	public void setPro(ProductoDTO pro) {
		this.pro = pro;
	}
	
	
}

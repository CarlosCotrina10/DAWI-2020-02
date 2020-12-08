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
	private String btn;
	
	@Action(value = "/ventaMes", results = {@Result(name = "ok", location = "/reporteVentas.jsp"),
			@Result(name = "pdf", type = "jasper",params = {"location","/reportes/reporteVentaMes.jasper","dataSource","lstVentaMes","format","PDF",} )})
	public String ventasMes() {
		lstVentaMes = new ReporteService().listadoVentasMes(p);
		switch (btn) {
		case "Listar":
			return "ok";
		case "Generar PDF":
			return "pdf";
		default:
			return "ok";
		}
	}

	@Action(value = "/ventaProducto", results = {@Result(name = "ok", location = "/reporteProductos.jsp"),
		@Result(name = "pdf", type = "jasper",params = {"location","/reportes/reporteVentaProducto.jasper","dataSource","lstVentaProducto","format","PDF",} )})
	public String ventasProducto() {
		lstVentaProducto = new ReporteService().listadoVentaProductos(p);
		switch (btn) {
		case "Listar":
			return "ok";
		case "Generar PDF":
			return "pdf";
		default:
			return "ok";
		}
	}
	
	@Action(value = "/stockProducto", results = {@Result(name = "ok", location = "/reporteStock.jsp"),
		@Result(name = "pdf", type = "jasper",params = {"location","/reportes/reporteStock.jasper","dataSource","lstProducto","format","PDF",} )})
	public String stockProducto() {
		lstProducto = new ReporteService().listaStock(pro);
		switch (btn) {
		case "Listar":
			return "ok";
		case "Generar PDF":
			return "pdf";
		default:
			return "ok";
		}
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

	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}
	
	
}

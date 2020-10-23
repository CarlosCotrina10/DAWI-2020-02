package action;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import model.Categoria;
import model.Producto;
import servicios.ProductoService;
import servicios.VentaService;

@ParentPackage("dawi")
public class VentaAction {
	
	//clase
	private ArrayList<Producto> lstProductos;
	private ArrayList<Categoria> lstCategoria;
	private Producto p;
	
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
	
}

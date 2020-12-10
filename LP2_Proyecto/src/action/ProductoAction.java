package action;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import model.Categoria;
import model.Producto;
import servicios.ProductoService;
import servicios.UsuarioService;
import servicios.VentaService;

@ParentPackage("dawi")
public class ProductoAction extends ActionSupport {

	// variables
	private ArrayList<Categoria> lstCategoria;
	private ArrayList<Producto> lstProducto;
	private Producto p, pro;
	private String btn;
	private int cod, idCat;

	// metodos

	@Action(value = "listadoCat", results = { @Result(name = "ok", type = "json") })
	public String listadoCategoria() {
		lstCategoria = new ProductoService().listadoCategoria();
		return "ok";
	}

	@Action(value = "/crudProd", results = { @Result(name = "okR", location = "/registrarProducto.jsp"),
			@Result(name = "okA", location = "/actualizarProducto.jsp"),
			@Result(name = "okE", location = "/listadoProductos.jsp") })
	public String crudProducto() {
		int ok = 0;
		String error = "", message = "", salida = "";

		switch (btn) {
		case "Registrar":
			ok = new ProductoService().registrar(p);
			salida = "okR";
			if (ok == 0)
				error = "Error al Registrar";
			else
				message = "Registro exitoso";
			break;
		case "Actualizar":
			ok = new ProductoService().actualizar(p);
			salida = "okA";
			if (ok == 0)
				error = "Error al Actualizar";
			else
				message = "Actualizacion exitoso";
			break;
		case "Eliminar":
			ok = new ProductoService().cambiarEstado(p);
			lstProducto = new ProductoService().listado(pro);

			salida = "okE";

			if (ok == 0)
				error = "Error al Cambiar estado";
			else
				message = "Eliminacion exitoso";
			break;
		default:
			break;
		}
		if (ok == 0)
			addActionError(error);
		else
			addActionMessage(message);
		return salida;
	}

	@Action(value = "/buscarProd", results = { @Result(name = "ok", location = "/actualizarProducto.jsp") })
	public String buscarProducto() {
		p = new ProductoService().buscar(p.getIdProd());
		lstCategoria = new ProductoService().listadoCategoria();
		return "ok";
	}

	@Action(value = "/listadoProd", results = { @Result(name = "ok", location = "/listadoProductos.jsp") })
	public String listadoProducto() {
		lstProducto = new ProductoService().listado(pro);
		return "ok";
	}

	@Action(value = "/cargarProdTienda", results = { @Result(name = "ok", location = "/indexTienda.jsp") })
	public String cargarProductosTienda() {
		if(p == null) { 
			p = new Producto();
			p.setIdCategoria(-1);
		}
		p.setEstado(-1);
		System.out.println(p);
		lstProducto = new VentaService().listado(p);
		lstCategoria = new ProductoService().listadoCategoria();
		return "ok";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Categoria> getLstCategoria() {
		return lstCategoria;
	}

	public void setLstCategoria(ArrayList<Categoria> lstCategoria) {
		this.lstCategoria = lstCategoria;
	}

	public Producto getP() {
		return p;
	}

	public void setP(Producto p) {
		this.p = p;
	}

	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}

	public ArrayList<Producto> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(ArrayList<Producto> lstProducto) {
		this.lstProducto = lstProducto;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Producto getPro() {
		return pro;
	}

	public void setPro(Producto pro) {
		this.pro = pro;
	}

	public int getIdCat() {
		return idCat;
	}

	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}

	

}

package action;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import model.Categoria;
import servicios.CategoriasService;
import servicios.ProductoService;

@ParentPackage("dawi")
public class CategoriaAction extends ActionSupport {
	private ArrayList<Categoria> lstCategoria;
	private Categoria c;
	private int tipoCat;
	
	@Action(value = "/listadoCategoria", results = { @Result(name = "ok", location = "/listadoCategorias.jsp") })
	public String listadoCategoria() {
		if(tipoCat==1) {
			lstCategoria = new CategoriasService().listadoConProd();
		} else {
			lstCategoria = new CategoriasService().listadoSinProd();
		}
		return "ok";
	}
	
	@Action(value = "/registrarCategoria", results = { @Result(name = "ok", location = "/registrarCategoria.jsp") })
	public String registrarCat() {
		int ok = 0;
		ok = new CategoriasService().registrar(c);
		if (ok == 0)
			addActionError("Error al Registrar");
		else
			addActionMessage("Registro exitoso");
		return "ok";
	}
	
	@Action(value = "/actualizarCategoria", results = { @Result(name = "ok", location = "/actualizarCategoria.jsp") })
	public String actualizarCat() {
		int ok = 0;
		ok = new CategoriasService().actualizar(c);
		if (ok == 0)
			addActionError("Error al Actualizar");
		else
			addActionMessage("Actualizacion exitosa");
		return "ok";
	}
	
	@Action(value = "/eliminarCategoria", results = { @Result(name = "ok", location = "/listadoCategorias.jsp") })
	public String eliminarCat() {
		int ok = 0;
		ok = new CategoriasService().eliminar(c);
		if(tipoCat==1) {
			lstCategoria = new CategoriasService().listadoConProd();
		} else {
			lstCategoria = new CategoriasService().listadoSinProd();
		}
		if (ok == 0)
			addActionError("Error al Eliminar");
		else
			addActionMessage("Eliminacion exitosa");
		return "ok";
	}
	
	@Action(value = "/buscarCategoria", results = { @Result(name = "ok", location = "/actualizarCategoria.jsp") })
	public String buscarCat() {
		c = new CategoriasService().buscar(c);
		return "ok";
	}
	

	
	
	
	
	
	
	public ArrayList<Categoria> getLstCategoria() {
		return lstCategoria;
	}
	public void setLstCategoria(ArrayList<Categoria> lstCategoria) {
		this.lstCategoria = lstCategoria;
	}
	public Categoria getC() {
		return c;
	}
	public void setC(Categoria c) {
		this.c = c;
	}

	public int getTipoCat() {
		return tipoCat;
	}

	public void setTipoCat(int tipoCat) {
		this.tipoCat = tipoCat;
	}
	
}

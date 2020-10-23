package action;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import model.Categoria;
import model.Distrito;
import model.Producto;
import model.Tipo;
import model.Usuario;
import servicios.ProductoService;
import servicios.UsuarioService;
import servicios.VentaService;

@ParentPackage("dawi")
public class UsuarioAction extends ActionSupport{
	//variables
	private Usuario u ,usu;
	private ArrayList<Distrito> lstDistrito;
	private ArrayList<Tipo> lstTipo;
	private ArrayList<Usuario> lstUsuario;
	private ArrayList<Producto> lstProductos;
	private ArrayList<Categoria> lstCategoria;
	private Producto p;
	private String btn;
	private int cod;
	
	
	//metodos
	@Action(value = "/login",results = {@Result(name="error",location = "/loginRegistro.jsp"),
			@Result(name="ok",location = "/index.jsp")})
	public String validarUsuario() {
		String ok = "";
		u = new UsuarioService().validar(u);
		if(u != null) {
			addActionMessage("Usuario logeado");
			lstProductos = new VentaService().listado(p);
			lstCategoria = new ProductoService().listadoCategoria();
			ok = "ok";
		}
		else {
			addActionError("Usuario no encontrado");
			ok = "error";
		}
		return ok;
	}
	
	@Action(value = "/crudUsua" ,results = {@Result(name = "okR",location = "/registrarUsuario.jsp"),
			@Result(name = "okA",location = "/actualizarUsuario.jsp"),@Result(name = "okE",location = "/listadoUsuarios.jsp")})
	public String crudProducto() {
		int ok = 0;
		String error = "", message = "" , salida = "";
		
		switch (btn) {
		case "Registrar":
			ok = new UsuarioService().registrar(u);
			salida = "okR";
			if(ok == 0)
				error= "Error al Registrar";
			else
				message = "Registro exitoso";
			break;
		case "Actualizar":
			ok = new UsuarioService().actualizar(u);
			salida = "okA";
			if(ok == 0)
				error= "Error al Actualizar";
			else
				message = "Actualizacion exitoso";
			break;
		case "Eliminar":
			ok = new UsuarioService().cambiarEstado(u);
			lstUsuario = new UsuarioService().listado(usu);		
			System.out.println(u);
			salida = "okE";
			if(ok == 0)
				error= "Error al Cambiar estado";
			else
				message = "Eliminacion exitoso";
			break;
		default:
			break;
		}
		if(ok == 0)
			addActionError(error);
		else
			addActionMessage(message);
		return salida;
	}
	
	@Action(value = "/buscarUsua" , results = {@Result(name = "ok" , location = "/actualizarUsuario.jsp")})
	public String buscarProducto() {
		u = new UsuarioService().buscar(cod);
		return "ok";
	}
	
	@Action(value = "/listadoDistrito",results = {@Result(name = "ok", type = "json")})
	public String listadoDitrito() {
		lstDistrito = new UsuarioService().listadoDistrito();		
		return "ok";
	}	
	
	@Action(value = "/listadoTipo",results = {@Result(name = "ok", type = "json")})
	public String listadoTipo() {
		lstTipo = new UsuarioService().listadoTipo();
		return "ok";
	}
	
	@Action(value = "/listadoUsuario",results = {@Result(name = "ok", location = "/listadoUsuarios.jsp")})
	public String listadoUsuario() {
		lstUsuario = new UsuarioService().listado(usu);		
		return "ok";
	}
	
	
	
	
	
	
	
	
	
	
	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	public ArrayList<Usuario> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(ArrayList<Usuario> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}

	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Usuario getU() {
		return u;
	}
	public void setU(Usuario u) {
		this.u = u;
	}

	public ArrayList<Distrito> getLstDistrito() {
		return lstDistrito;
	}

	public void setLstDistrito(ArrayList<Distrito> lstDistrito) {
		this.lstDistrito = lstDistrito;
	}

	public ArrayList<Tipo> getLstTipo() {
		return lstTipo;
	}

	public void setLstTipo(ArrayList<Tipo> lstTipo) {
		this.lstTipo = lstTipo;
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

	public ArrayList<Categoria> getLstCategoria() {
		return lstCategoria;
	}

	public void setLstCategoria(ArrayList<Categoria> lstCategoria) {
		this.lstCategoria = lstCategoria;
	}
	
}

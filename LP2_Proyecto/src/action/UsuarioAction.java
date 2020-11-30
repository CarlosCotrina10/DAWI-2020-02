package action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
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
	private ArrayList<Producto> lstProducto;
	private Producto pro;
	private String btn;
	private int cod, estadoReg;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	
	//metodos
	@Action(value = "/login",results = {@Result(name="error",location = "/loginRegistro.jsp"),
			@Result(name="okCli",location = "/index.jsp"),@Result(name="okAdm",location = "/listadoProductos.jsp")})
	public String validarUsuario() {
		String ok = "";
		u = new UsuarioService().validar(u);
		if(u != null) {	
			session.put("usuario", u);
			if(u.getIdTipo() == 0) {
				ok = "okCli";
			}else {
				pro = new Producto();
				pro.setEstado(1);
				pro.setIdCategoria(-1);
				lstProducto = new ProductoService().listado(pro);
				ok = "okAdm";
			}
		}
		else {
			addActionError("Usuario o contraseña incorrecto");
			ok = "error";
		}
		return ok;
	}
	
	@Action(value = "/crudUsua" ,results = {@Result(name = "okR",location = "/registrarUsuario.jsp"),
			@Result(name = "okRCli",location = "/loginRegistro.jsp"),
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
			if(estadoReg == 1) {
				salida = "okRCli";
				message = "Usuario "+ u.getUsuario() +" registrado";
			}
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
	
	@Action(value="logout",results = {@Result(name="ok" ,location = "/index.jsp")})
	public String logout() {
		session.remove("usuario");
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

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ArrayList<Producto> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(ArrayList<Producto> lstProducto) {
		this.lstProducto = lstProducto;
	}

	public Producto getPro() {
		return pro;
	}

	public void setPro(Producto pro) {
		this.pro = pro;
	}

	public int getEstadoReg() {
		return estadoReg;
	}

	public void setEstadoReg(int estadoReg) {
		this.estadoReg = estadoReg;
	}
	
}

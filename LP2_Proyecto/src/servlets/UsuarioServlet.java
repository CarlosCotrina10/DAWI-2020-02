package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UsuarioDTO;
import dao.DAOFactory;
import interfaces.UsuarioDAO;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet(name = "crudUsu", urlPatterns = { "/crudUsu" })
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al Servlet de usuarios");
		String opc = request.getParameter("btnes");
		System.out.println("Opcion: " + opc);
		opc = (opc == null ? "x" : opc);
		switch (opc) {
		case "r": registro(request, response);break;
		case "Registrar" : registroCliente(request, response);break;
		case "a": actualizar(request, response);break;
		case "e": desactivar(request, response);break;
		case "l": listado(request, response);break;
		case "q": buscar(request, response);break; 
		case "Iniciar Sesion": validar(request, response);break;
		default:
			System.out.println("Cerrar la sesion");
			request.getSession().invalidate();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	private void registroCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entro al metodo gegistrar cliente");
		
		/*  Variables  */
		String nom, ape, usur, clave;
		int iddist;
		String mensaje,url;
		
		/*  Entradas  */
		nom    = request.getParameter("txtNombre");
		ape    = request.getParameter("txtApellido");
		iddist = Integer.parseInt(request.getParameter("cboDistrito"));
		usur   = request.getParameter("txtUsuario");
		clave  = request.getParameter("txtClave");
		
		UsuarioDTO usu= new UsuarioDTO();
		usu.setNombre(nom);
		usu.setApellido(ape);
		usu.setCodDistrito(iddist);
		usu.setUsuario(usur);
		usu.setClave(clave);
		System.out.println(usu);
		
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UsuarioDAO dao=factory.getUsuarioDAO();
		
		/* Salidas*/
		int ok= dao.registrar(usu, 0);
		if(ok==0) {
			mensaje="No se pudo registrar al Usuario";
			url="/loginRegistro.jsp";
		}else {
			mensaje="Usuario" + usu.getUsuario() +  " registrado";
			url="/loginRegistro.jsp";
		}
		
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al metodo validar usuario");
		
		// declaramos variables
		String usuario, clave;
		String url;
		String mensaje = null;
		
		// fijamos parametros
		usuario = request.getParameter("txtUsuario");
		clave =request.getParameter("txtClave");
		
		DAOFactory fact=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UsuarioDAO dao=fact.getUsuarioDAO();
		UsuarioDTO u=dao.validar(usuario, clave);
		
		//mostramos el ID de la sesion
		System.out.println("ID sesion :" + request.getSession().getId());
		SimpleDateFormat sdf=new SimpleDateFormat();
		System.out.println("Creado el :" + sdf.format(request.getSession().getCreationTime()));
		
		if(u==null) {
			mensaje= "<script>alert('Usuario o Clave Incorrecto')</script>";
			url="/loginRegistro.jsp";
		}else {
			url="/index.jsp";
			request.getSession().setAttribute("usuario", u);
			if (u.getIdTipo() == 1) {
				url = "/listadoProductos.jsp";
			} else {
				url="/index.jsp";
			}
			System.out.println("Logueo exitoso: "+usuario+" "+clave+" "+u.getIdTipo());
		}
		
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher(url).forward(request, response);
		
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entró a buscar el producto");
		int codUsuario;
		
		codUsuario=Integer.parseInt(request.getParameter("cod"));

		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UsuarioDAO dao = fabrica.getUsuarioDAO();
		UsuarioDTO u = dao.buscar(codUsuario);
		u.setIdTipo(u.getIdTipo()+1);
		u.setEstado(u.getEstado()+1);
		
		request.setAttribute("data", u);
		request.getRequestDispatcher("/actualizarUsuario.jsp").forward(request, response);
	}

	private void listado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idDistrito, estado, idtip;
		
		idtip = Integer.parseInt(request.getParameter("cboTipo"));
		estado = Integer.parseInt(request.getParameter("cboEstado"));
		idDistrito = Integer.parseInt(request.getParameter("cboDistrito"));
		
		System.out.println("Parametros: ");
		System.out.println("Tipo: " + idtip);
		System.out.println("Distrito: " + idDistrito);
		System.out.println("Estado: " + estado);
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UsuarioDAO dao = fabrica.getUsuarioDAO();
		ArrayList<UsuarioDTO> lista = dao.listado(idtip, estado, idDistrito);
		
		idtip += 1;
		request.setAttribute("milista", lista);
		request.getSession().setAttribute("cboDis", idDistrito);
		request.getSession().setAttribute("cboTip", idtip);
		
		if (estado == 0) {
			request.setAttribute("mensaje", "Cargó la lista de usuarios desactivados");
			request.getRequestDispatcher("/listadoUsuariosDesactivados.jsp").forward(request, response);
			return;
		} else {
			request.setAttribute("mensaje", "Cargó la lista de usuarios activos");
			request.getRequestDispatcher("/listadoUsuarios.jsp").forward(request, response);
			return;
		}
	}

	private void desactivar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al cambiarEstado de usuario");
		String mensaje, url;
		ArrayList<UsuarioDTO> lista = null;
		int distrito, tipo, codigo, estado;
		
		codigo = Integer.parseInt(request.getParameter("cod"));
		estado = Integer.parseInt(request.getParameter("est"));
		tipo = (int) request.getSession().getAttribute("cboTip");
		distrito = (int) request.getSession().getAttribute("cboDis");
		tipo = tipo - 1;
		
		System.out.println("Parametros: ");
		System.out.println("Tipo: " + tipo);
		System.out.println("Distrito: " + distrito);
		System.out.println("Estado: " + estado);
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UsuarioDAO dao = fabrica.getUsuarioDAO();		
		int ok = dao.cambiarEstado(codigo, estado);
			
		if (estado == 1) {
			mensaje = "Se activó el producto " + codigo;
			lista = dao.listado(tipo, 0, distrito);
			url = "/listadoUsuariosDesactivados.jsp";
		} else {
			mensaje = "Se descativó el producto " + codigo;
			lista = dao.listado(tipo, 1, distrito);
			url = "/listadoUsuarios.jsp";
		}
		
		if (ok==0) {
			mensaje = "Error al cambiar estado del producto";
		} 
		
		request.setAttribute("milista", lista);
		request.setAttribute("mensaje", mensaje);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al actualizar de usuario");
		String mensaje;
		
		int codUsuario, idTipo, estado, codDistrito;
		String nombre, apellido, usuario, clave;
		
		codUsuario = Integer.parseInt(request.getParameter("txtCodigo"));
		nombre = request.getParameter("txtNombre");
		apellido = request.getParameter("txtApellido");
		codDistrito = Integer.parseInt(request.getParameter("cboDistrito"));
		usuario = request.getParameter("txtUsuario");
		clave = request.getParameter("txtClave");
		idTipo = Integer.parseInt(request.getParameter("cboTipo"));
		estado = Integer.parseInt(request.getParameter("cboEstado"));
		
		UsuarioDTO u = new UsuarioDTO();
		u.setCodUsuario(codUsuario);
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setCodDistrito(codDistrito);
		u.setUsuario(usuario);
		u.setClave(clave);
		u.setIdTipo(idTipo);
		u.setEstado(estado);
		
		
		if (codDistrito == 0) {
			request.setAttribute("mensaje", "Seleccione un distrito");
			u.setEstado(u.getEstado()+1);
			u.setIdTipo(u.getIdTipo()+1);
			request.setAttribute("data", u);
			request.getRequestDispatcher("/registrarUsuario.jsp").forward(request, response);
			return;
		} else if (idTipo == -1) {
			request.setAttribute("mensaje", "Seleccione un tipo");
			u.setEstado(u.getEstado()+1);
			u.setIdTipo(u.getIdTipo()+1);
			request.setAttribute("data", u);
			request.getRequestDispatcher("/registrarUsuario.jsp").forward(request, response);
			return;
		} else if (estado == -1) {
			request.setAttribute("mensaje", "Seleccione un estado");
			u.setEstado(u.getEstado()+1);
			u.setIdTipo(u.getIdTipo()+1);
			request.setAttribute("data", u);
			request.getRequestDispatcher("/registrarUsuario.jsp").forward(request, response);
			return;
		}
		
		System.out.println(u);
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UsuarioDAO dao = fabrica.getUsuarioDAO();		
		int ok = dao.actualizar(u);
		
		if (ok==0) {
			mensaje = "Error al actualizar producto";
		} else {
			mensaje = "Actualizacion de usuario " + codUsuario + " OK";
		}
		
		request.setAttribute("data", u);
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("/actualizarUsuario.jsp").forward(request, response);
	}

	private void registro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al registrar usuario");
		String mensaje;
		
		int idTipo, estado, codDistrito;
		String nombre, apellido, usuario, clave;
		
		nombre = request.getParameter("txtNombre");
		apellido = request.getParameter("txtApellido");
		codDistrito = Integer.parseInt(request.getParameter("cboDistrito"));
		usuario = request.getParameter("txtUsuario");
		clave = request.getParameter("txtClave");
		idTipo = Integer.parseInt(request.getParameter("cboTipo"));
		estado = Integer.parseInt(request.getParameter("cboEstado"));
		
		UsuarioDTO u = new UsuarioDTO();
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setCodDistrito(codDistrito);
		u.setUsuario(usuario);
		u.setClave(clave);
		u.setIdTipo(idTipo);
		u.setEstado(estado);
		
		if (codDistrito == 0) {
			request.setAttribute("mensaje", "Seleccione un distrito");
			u.setEstado(u.getEstado()+1);
			u.setIdTipo(u.getIdTipo()+1);
			request.setAttribute("data", u);
			request.getRequestDispatcher("/registrarUsuario.jsp").forward(request, response);
			return;
		} else if (idTipo == -1) {
			request.setAttribute("mensaje", "Seleccione un tipo");
			u.setEstado(u.getEstado()+1);
			u.setIdTipo(u.getIdTipo()+1);
			request.setAttribute("data", u);
			request.getRequestDispatcher("/registrarUsuario.jsp").forward(request, response);
			return;
		} else if (estado == -1) {
			request.setAttribute("mensaje", "Seleccione un estado");
			u.setEstado(u.getEstado()+1);
			u.setIdTipo(u.getIdTipo()+1);
			request.setAttribute("data", u);
			request.getRequestDispatcher("/registrarUsuario.jsp").forward(request, response);
			return;
		}
		
		System.out.println(u);
		
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UsuarioDAO dao = fabrica.getUsuarioDAO();
		int ok = dao.registrar(u, 1);
		
		if (ok == 0) {
			mensaje = "Error al registrar los datos!!!";
		} else {
			mensaje = "Registro del usuario " + nombre + "OK";
		}
		
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("/registrarUsuario.jsp").forward(request, response);
	}

}

package model;

public class Usuario {
	private int codUsuario, codDistrito, idTipo, estado;
	private String nombre, apellido, usuario, clave;

	@Override
	public String toString() {
		return "Usuario [codUsuario=" + codUsuario + ", codDistrito=" + codDistrito + ", idTipo=" + idTipo + ", nombre="
				+ nombre + ", apellido=" + apellido + ", usuario=" + usuario + ", clave=" + clave + ", estado=" + estado
				+ "]";
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public int getCodDistrito() {
		return codDistrito;
	}

	public void setCodDistrito(int codDistrito) {
		this.codDistrito = codDistrito;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}

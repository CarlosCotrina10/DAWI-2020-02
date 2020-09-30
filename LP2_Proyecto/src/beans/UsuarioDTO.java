package beans;

public class UsuarioDTO {
	private int codUsuario, idTipo, estado, codDistrito;
	private String nombre, apellido, usuario, clave, desTipo, nomDistrito;
	
	@Override
	public String toString() {
		return "UsuarioDTO [codUsuario=" + codUsuario + ", idTipo=" + idTipo + ", estado=" + estado + ", codDistrito="
				+ codDistrito + ", nombre=" + nombre + ", apellido=" + apellido + ", usuario=" + usuario + ", clave="
				+ clave + ", desTipo=" + desTipo + ", nomDistrito=" + nomDistrito + "]";
	}
	
	public int getCodDistrito() {
		return codDistrito;
	}
	public void setCodDistrito(int codDistrito) {
		this.codDistrito = codDistrito;
	}
	public String getNomDistrito() {
		return nomDistrito;
	}
	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}
	public String getDesTipo() {
		return desTipo;
	}
	public void setDesTipo(String desTipo) {
		this.desTipo = desTipo;
	}
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
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
	
}

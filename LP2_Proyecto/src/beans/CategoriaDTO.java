package beans;

public class CategoriaDTO {
	private int idcategoria;
	private String descripcion;
	
	@Override
	public String toString() {
		return "CategoriaDTO [idcategoria=" + idcategoria + ", descripcion=" + descripcion + "]";
	}
	
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

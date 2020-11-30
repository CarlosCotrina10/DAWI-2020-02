package model;

public class Categoria {
	private int idCategoria;
	private String descripcion;

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", descripcion=" + descripcion + "]";
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

package model;

public class Tipo {
	private int idTipo;
	private String descripcion;

	// Method toString();
	@Override
	public String toString() {
		return "Tipo [idTipo=" + idTipo + ", descripcion=" + descripcion + "]";
	}

	// Methods Get's and Set's
	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

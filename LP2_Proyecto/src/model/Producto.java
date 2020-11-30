package model;

public class Producto {
	private int idProd, idCategoria, stock, estado;
	private String nomProd, descripcion;
	private double precio;

	@Override
	public String toString() {
		return "Producto [idProd=" + idProd + ", idCategoria=" + idCategoria + ", stock=" + stock + ", nomProd="
				+ nomProd + ", descripcion=" + descripcion + ", precio=" + precio + ", estado=" + estado + "]";
	}

	public int getIdProd() {
		return idProd;
	}

	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getNomProd() {
		return nomProd;
	}

	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}	

}

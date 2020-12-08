package model;

public class ProductoCategoria {
	private int idProd,idCategoria, stock, estado;
	private String nomProd, descripcion, categoria;
	private double precio;	
		
	@Override
	public String toString() {
		return "ProductoDTO [idProd=" + idProd + ", idCategoria=" + idCategoria + ", stock=" + stock + ", estado="
				+ estado + ", nomProd=" + nomProd + ", descripcion=" + descripcion + ", categoria=" + categoria
				+ ", precio=" + precio + "]";
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public int getIdProd() {
		return idProd;
	}
	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}

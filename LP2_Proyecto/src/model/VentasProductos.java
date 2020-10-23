package model;

public class VentasProductos {
	private int idprod, stock, cantidad;
	private String nombre, categoria, estado;
	private Double total;
	
	@Override
	public String toString() {
		return "VentasProductosDTO [idprod=" + idprod + ", stock=" + stock + ", cantidad=" + cantidad + ", nombre="
				+ nombre + ", categoria=" + categoria + ", estado=" + estado + ", total=" + total + "]";
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getIdprod() {
		return idprod;
	}
	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
}

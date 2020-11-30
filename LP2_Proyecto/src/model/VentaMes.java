package model;

public class VentaMes {
	private String mes;
	private int anio, productos, clientes, users;
	private double total;
	
	@Override
	public String toString() {
		return "VentaMes [mes=" + mes + ", anio=" + anio + ", productos=" + productos + ", clientes=" + clientes
				+ ", users=" + users + ", total=" + total + "]";
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getProductos() {
		return productos;
	}
	public void setProductos(int productos) {
		this.productos = productos;
	}
	public int getClientes() {
		return clientes;
	}
	public void setClientes(int clientes) {
		this.clientes = clientes;
	}
	public int getUsers() {
		return users;
	}
	public void setUsers(int users) {
		this.users = users;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}

package model;

public class Parametro {
	private int stock, anio;
	private String fech1 ,fech2;
	
	@Override
	public String toString() {
		return "Parametro [stock=" + stock + ", anio=" + anio + ", fech1=" + fech1 + ", fech2=" + fech2 + "]";
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public String getFech1() {
		return fech1;
	}
	public void setFech1(String fech1) {
		this.fech1 = fech1;
	}
	public String getFech2() {
		return fech2;
	}
	public void setFech2(String fech2) {
		this.fech2 = fech2;
	}
	
}

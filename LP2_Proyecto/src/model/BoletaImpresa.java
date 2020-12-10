package model;

public class BoletaImpresa {
	private String numBoleta, nombre, nomProd, fecha;
	private int cantidad, idprod;
	private double total, importe, precioUnitario;
	
	@Override
	public String toString() {
		return "BoletaImpresa [numBoleta=" + numBoleta + ", nombre=" + nombre + ", nomProd=" + nomProd + ", fecha="
				+ fecha + ", cantidad=" + cantidad + ", idprod=" + idprod + ", total=" + total + ", importe=" + importe
				+ ", precioUnitario=" + precioUnitario + "]";
	}
	
	public String getNumBoleta() {
		return numBoleta;
	}
	public void setNumBoleta(String numBoleta) {
		this.numBoleta = numBoleta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNomProd() {
		return nomProd;
	}
	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getIdprod() {
		return idprod;
	}
	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
}

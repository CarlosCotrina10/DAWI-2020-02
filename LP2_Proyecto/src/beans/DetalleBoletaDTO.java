package beans;

public class DetalleBoletaDTO {
	private int numBoleta, idprod, cantidad;
	private String nombre;
	private double preciovta;
	
	@Override
	public String toString() {
		return "DetalleBoletaDTO [numBoleta=" + numBoleta + ", idprod=" + idprod + ", cantidad=" + cantidad
				+ ", nombre=" + nombre + ", preciovta=" + preciovta + "]";
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumBoleta() {
		return numBoleta;
	}
	public void setNumBoleta(int numBoleta) {
		this.numBoleta = numBoleta;
	}
	public int getIdprod() {
		return idprod;
	}
	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPreciovta() {
		return preciovta;
	}
	public void setPreciovta(double preciovta) {
		this.preciovta = preciovta;
	}
}

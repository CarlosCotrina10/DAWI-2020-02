package model;

public class DetalleBoleta {
	private String numBoleta;
	private int idprod, cantidad;
	private double preciovta;

	@Override
	public String toString() {
		return "DetalleBoleta [numBoleta=" + numBoleta + ", idprod=" + idprod + ", cantidad=" + cantidad
				+ ", preciovta=" + preciovta + "]";
	}

	public String getNumBoleta() {
		return numBoleta;
	}

	public void setNumBoleta(String numBoleta) {
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

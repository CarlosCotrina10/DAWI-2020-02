package model;

public class DetalleBoleta {
	private String numBoleta , producto;
	private int idProd, cantidad;
	private double preciovta;
	
	@Override
	public String toString() {
		return "DetalleBoleta [numBoleta=" + numBoleta + ", producto=" + producto + ", idProd=" + idProd + ", cantidad="
				+ cantidad + ", preciovta=" + preciovta + "]";
	}

	public String getNumBoleta() {
		return numBoleta;
	}

	public void setNumBoleta(String numBoleta) {
		this.numBoleta = numBoleta;
	}

	public int getIdProd() {
		return idProd;
	}

	public void setIdProd(int idProd) {
		this.idProd = idProd;
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



	public String getProducto() {
		return producto;
	}



	public void setProducto(String producto) {
		this.producto = producto;
	}

}

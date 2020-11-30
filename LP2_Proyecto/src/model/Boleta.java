package model;

import java.util.ArrayList;

public class Boleta {
	private String numBoleta, fecha;
	private int codUsuario;
	private double total;
	private ArrayList<DetalleBoleta> detalle;

	@Override
	public String toString() {
		return "Boleta [numBoleta=" + numBoleta + ", fecha=" + fecha + ", codUsuario=" + codUsuario + ", total=" + total
				+ ", detalle=" + detalle + "]";
	}

	public String getNumBoleta() {
		return numBoleta;
	}

	public void setNumBoleta(String numBoleta) {
		this.numBoleta = numBoleta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public ArrayList<DetalleBoleta> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<DetalleBoleta> detalle) {
		this.detalle = detalle;
	}

}

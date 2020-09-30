package beans;

public class CabeceraBoletaDTO {
	private String numBoleta, fecha;
	private int codUsuario;
	private double total;
	
	@Override
	public String toString() {
		return "CabeceraBoleta [numBoleta=" + numBoleta + ", fecha=" + fecha + ", codUsuario=" + codUsuario + ", total="
				+ total + "]";
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
}

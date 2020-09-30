package beans;

public class VentasMesDTO {
	private String mes;
	private Double suma;
	private int candidadPro, clientesActivos, clientesTotales, year;
	
	@Override
	public String toString() {
		return "VentasMesDTO [mes=" + mes + ", suma=" + suma + ", candidadPro=" + candidadPro + ", clientesActivos="
				+ clientesActivos + ", clientesTotales=" + clientesTotales + ", year=" + year + "]";
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Double getSuma() {
		return suma;
	}
	public void setSuma(Double suma) {
		this.suma = suma;
	}
	public int getCandidadPro() {
		return candidadPro;
	}
	public void setCandidadPro(int candidadPro) {
		this.candidadPro = candidadPro;
	}
	public int getClientesActivos() {
		return clientesActivos;
	}
	public void setClientesActivos(int clientesActivos) {
		this.clientesActivos = clientesActivos;
	}
	public int getClientesTotales() {
		return clientesTotales;
	}
	public void setClientesTotales(int clientesTotales) {
		this.clientesTotales = clientesTotales;
	}
}

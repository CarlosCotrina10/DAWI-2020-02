package beans;

public class DistritoDTO {
	private int codDistrito;
	private String nomDistrito;
	
	@Override
	public String toString() {
		return "DistritoDTO [codDistrito=" + codDistrito + ", nomDistrito=" + nomDistrito + "]";
	}
	
	public int getCodDistrito() {
		return codDistrito;
	}
	public void setCodDistrito(int codDistrito) {
		this.codDistrito = codDistrito;
	}
	public String getNomDistrito() {
		return nomDistrito;
	}
	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}
}

package beans;

public class ProductoDTO {
	private String nomprod, descripcion, nomcat, desEstado;
	private int idprod, stock, idcategoria, estado;
	private double precio;
	
	@Override
	public String toString() {
		return "ProductoDTO [nomprod=" + nomprod + ", descripcion=" + descripcion + ", nomcat=" + nomcat
				+ ", desEstado=" + desEstado + ", idprod=" + idprod + ", stock=" + stock + ", idcategoria="
				+ idcategoria + ", estado=" + estado + ", precio=" + precio + "]";
	}

	public String getDesEstado() {
		return desEstado;
	}

	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getNomprod() {
		return nomprod;
	}
	public void setNomprod(String nomprod) {
		this.nomprod = nomprod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNomcat() {
		return nomcat;
	}
	public void setNomcat(String nomcat) {
		this.nomcat = nomcat;
	}
	public int getIdprod() {
		return idprod;
	}
	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
		
}

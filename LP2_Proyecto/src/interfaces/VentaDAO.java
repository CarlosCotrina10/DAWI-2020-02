package interfaces;

import java.util.ArrayList;

import beans.CabeceraBoletaDTO;
import beans.DetalleBoletaDTO;
import beans.ProductoDTO;

public interface VentaDAO {
	
	public String generaNumBoleta();
	
	public int realizarVenta(CabeceraBoletaDTO cab, ArrayList<DetalleBoletaDTO> det);
	
	public ArrayList<ProductoDTO> listado(int idcat);
}

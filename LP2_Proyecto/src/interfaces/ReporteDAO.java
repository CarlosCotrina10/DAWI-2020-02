package interfaces;

import java.util.ArrayList;

import beans.ProductoDTO;
import beans.VentasMesDTO;
import beans.VentasProductosDTO;

public interface ReporteDAO {
	public ArrayList<ProductoDTO> listaStock(int stock);
	
	public ArrayList<VentasMesDTO> listadoVentasMes(int year);
	
	public ArrayList<VentasProductosDTO> listadoVentaProductos(String date1, String date2);
}

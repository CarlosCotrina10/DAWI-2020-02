package mybatis.mapper;

import java.util.ArrayList;
import model.Producto;
import model.VentasMes;
import model.VentasProductos;

public interface ReporteMapper {
	public ArrayList<Producto> listaStock(int stock);
	
	public ArrayList<VentasMes> listadoVentasMes(int year);
	
	public ArrayList<VentasProductos> listadoVentaProductos(String date1, String date2);
}

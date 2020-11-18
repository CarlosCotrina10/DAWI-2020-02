package mybatis.mapper;

import java.util.ArrayList;

import beans.ProductoDTO;
import model.Parametro;
import model.VentaMes;
import model.VentasProductos;

public interface ReporteMapper {
	public ArrayList<ProductoDTO> listaStock(ProductoDTO p);
	
	public ArrayList<VentaMes> listadoVentasMes(Parametro p);
	
	public ArrayList<VentasProductos> listadoVentaProductos(Parametro p);
}

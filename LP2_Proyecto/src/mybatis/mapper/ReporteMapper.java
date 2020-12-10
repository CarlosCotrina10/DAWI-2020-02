package mybatis.mapper;

import java.util.ArrayList;

import model.Parametro;
import model.ProductoCategoria;
import model.VentaMes;
import model.VentasProductos;

public interface ReporteMapper {
	public ArrayList<ProductoCategoria> listaStock(ProductoCategoria p);
	
	public ArrayList<VentaMes> listadoVentasMes(Parametro p);
	
	public ArrayList<VentasProductos> listadoVentaProductos(Parametro p);
}

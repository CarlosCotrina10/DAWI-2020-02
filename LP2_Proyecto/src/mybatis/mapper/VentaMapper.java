package mybatis.mapper;

import java.util.ArrayList;

import model.Boleta;
import model.DetalleBoleta;
import model.Producto;

public interface VentaMapper {
	
//	public String generaNumBoleta();
//	
//	public int realizarVenta(Boleta cab, ArrayList<DetalleBoleta> det);
	
	public ArrayList<Producto> listado(Producto p);
}

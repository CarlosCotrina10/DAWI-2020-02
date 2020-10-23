package mybatis.mapper;

import java.util.ArrayList;

import model.Categoria;
import model.Producto;

public interface ProductoMapper {
	
	public ArrayList<Producto> listado(Producto p);
	
	public int registrar(Producto p);
	
	public Producto buscar(int cod);
	
	public int actualizar(Producto p);
	
	public int cambiarEstado(Producto p);
	
	public ArrayList<Categoria> listadoCategoria();
		
}

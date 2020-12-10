package mybatis.mapper;

import java.util.ArrayList;

import model.Categoria;
import model.Producto;

public interface CategoriaMapper {
	
	public ArrayList<Categoria> listadoSinProd();
	
	public ArrayList<Categoria> listadoConProd();
	
	public int registrar(Categoria c);
	
	public Categoria buscar(Categoria c);
	
	public int actualizar(Categoria c);
	
	public int eliminar(Categoria c);
	
}

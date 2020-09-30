package interfaces;

import java.util.ArrayList;

import beans.CategoriaDTO;
import beans.ProductoDTO;

public interface ProductoDAO {
	
	public ArrayList<ProductoDTO> listado(int idcat, int est);
	
	public int registrar(ProductoDTO p);
	
	public ProductoDTO buscar(int cod);
	
	public int actualizar(ProductoDTO p);
	
	public int cambiarEstado(int cod, int est);
	
	public ArrayList<CategoriaDTO> listadoCategoria();
		
}

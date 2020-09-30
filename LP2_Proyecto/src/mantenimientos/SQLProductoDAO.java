package mantenimientos;

import java.util.ArrayList;

import beans.CategoriaDTO;
import beans.ProductoDTO;
import interfaces.ProductoDAO;

public class SQLProductoDAO implements ProductoDAO {

	@Override
	public ArrayList<ProductoDTO> listado(int idcat, int est) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registrar(ProductoDTO p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProductoDTO buscar(int cod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int actualizar(ProductoDTO p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<CategoriaDTO> listadoCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cambiarEstado(int cod, int est) {
		// TODO Auto-generated method stub
		return 0;
	}

}

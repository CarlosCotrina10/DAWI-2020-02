package mybatis.mapper;

import java.util.ArrayList;

public interface CRUD_Mapper<T> {

	public int registrar(T reg);

	public int cambiarEstado(String id, int estado);

	// Sobrecarga --> int o string
	public int cambiarEstado(int id, int estado);

	public int actualizar(T reg);

	public ArrayList<T> listar();

}

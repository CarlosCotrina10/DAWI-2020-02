package mybatis.mapper;


import java.util.ArrayList;
import model.Distrito;
import model.Tipo;
import model.Usuario;

public interface UsuarioMapper {

	public int registrar(Usuario u);
	
	public Usuario validar(Usuario u);
	
	public ArrayList<Usuario> listado(Usuario u);
	
	public Usuario buscar(int cod);
	
	public int actualizar(Usuario u);
	
	public int cambiarEstado(Usuario u);
	
	public ArrayList<Distrito> listadoDistrito();
	
	public ArrayList<Tipo> listadoTipo();
}

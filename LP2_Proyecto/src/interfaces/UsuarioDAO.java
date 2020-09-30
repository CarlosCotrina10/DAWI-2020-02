package interfaces;

import java.util.ArrayList;

import beans.DistritoDTO;
import beans.UsuarioDTO;

public interface UsuarioDAO {

	public int registrar(UsuarioDTO u, int tipo);
	
	public UsuarioDTO validar(String usuario, String clave);
	
	public ArrayList<UsuarioDTO> listado(int idtipo, int est, int dis);
	
	public UsuarioDTO buscar(int cod);
	
	public int actualizar(UsuarioDTO u);
	
	public int cambiarEstado(int codigo, int est);
	
	public ArrayList<DistritoDTO> listadoDistrito();
}

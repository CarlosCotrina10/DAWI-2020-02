package mantenimientos;

import java.util.ArrayList;

import beans.DistritoDTO;
import beans.UsuarioDTO;
import interfaces.UsuarioDAO;

public class SQLUsuarioDAO implements UsuarioDAO {

	@Override
	public UsuarioDTO validar(String Usuario, String clave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO buscar(int cod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int actualizar(UsuarioDTO u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DistritoDTO> listadoDistrito() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UsuarioDTO> listado(int idtipo, int est, int dis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registrar(UsuarioDTO u, int tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cambiarEstado(int codigo, int est) {
		// TODO Auto-generated method stub
		return 0;
	}
}

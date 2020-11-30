package servicios;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import model.Distrito;
import model.Tipo;
import model.Usuario;
import mybatis.MyBatisUtil;
import mybatis.mapper.UsuarioMapper;

public class UsuarioService implements UsuarioMapper{

	@Override
	public Usuario validar(Usuario u) {
		Usuario usu = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper um = session.getMapper(UsuarioMapper.class);
			usu = um.validar(u);
		} catch (Exception e) {
			System.out.println("Error al validar Usuario " + e.getMessage());
		}finally {
			session.close();
		}
		return usu;
	}

	@Override
	public int registrar(Usuario u) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper um = session.getMapper(UsuarioMapper.class);
			rs = um.registrar(u);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al registrar Usuario " + e.getMessage());
		}finally {
			session.close();
		}
		return rs;
	}

	@Override
	public ArrayList<Usuario> listado(Usuario u) {
		ArrayList<Usuario> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper um = session.getMapper(UsuarioMapper.class);
			listado = um.listado(u);
		} catch (Exception e) {
			System.out.println("Error al listar Usuario " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

	@Override
	public Usuario buscar(int cod) {
		Usuario usu = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper um = session.getMapper(UsuarioMapper.class);
			usu = um.buscar(cod);
		} catch (Exception e) {
			System.out.println("Error al buscar Usuario " + e.getMessage());
		}finally {
			session.close();
		}
		return usu;
	}

	@Override
	public int actualizar(Usuario u) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper um = session.getMapper(UsuarioMapper.class);
			rs = um.actualizar(u);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al actualizar Usuario " + e.getMessage());
		}finally {
			session.close();
		}
		return rs;
	}

	@Override
	public int cambiarEstado(Usuario u) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper um = session.getMapper(UsuarioMapper.class);
			rs = um.cambiarEstado(u);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al cambiarEstado Usuario " + e.getMessage());
		}finally {
			session.close();
		}
		return rs;
	}

	@Override
	public ArrayList<Distrito> listadoDistrito() {
		ArrayList<Distrito> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper um = session.getMapper(UsuarioMapper.class);
			listado = um.listadoDistrito();
		} catch (Exception e) {
			System.out.println("Error al listar Distrito " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

	@Override
	public ArrayList<Tipo> listadoTipo() {
		ArrayList<Tipo> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper um = session.getMapper(UsuarioMapper.class);
			listado = um.listadoTipo();
		} catch (Exception e) {
			System.out.println("Error al listar Tipo Usuario " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

}

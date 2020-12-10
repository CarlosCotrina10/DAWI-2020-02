package servicios;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import model.Categoria;
import mybatis.MyBatisUtil;
import mybatis.mapper.CategoriaMapper;

public class CategoriasService implements CategoriaMapper {

	@Override
	public ArrayList<Categoria> listadoSinProd() {
		ArrayList<Categoria> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CategoriaMapper pm = session.getMapper(CategoriaMapper.class);
			listado = pm.listadoSinProd();
		} catch (Exception e) {
			System.out.println("Error en listadoSinProd: " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

	@Override
	public ArrayList<Categoria> listadoConProd() {
		ArrayList<Categoria> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CategoriaMapper pm = session.getMapper(CategoriaMapper.class);
			listado = pm.listadoConProd();
		} catch (Exception e) {
			System.out.println("Error en listadoConProd: " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

	@Override
	public int registrar(Categoria c) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CategoriaMapper pm = session.getMapper(CategoriaMapper.class);
			rs = pm.registrar(c);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al registrar Categoria: " + e.getMessage());
		}finally {
			session.close();
		}
		return rs;
	}

	@Override
	public Categoria buscar(Categoria c) {
		Categoria cat = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CategoriaMapper pm = session.getMapper(CategoriaMapper.class);
			cat = pm.buscar(c);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al null Categoria: " + e.getMessage());
		}finally {
			session.close();
		}
		return cat;
	}

	@Override
	public int actualizar(Categoria c) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CategoriaMapper pm = session.getMapper(CategoriaMapper.class);
			rs = pm.actualizar(c);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al actualizar Categoria: " + e.getMessage());
		}finally {
			session.close();
		}
		return rs;
	}

	@Override
	public int eliminar(Categoria c) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CategoriaMapper pm = session.getMapper(CategoriaMapper.class);
			rs = pm.eliminar(c);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al eliminar Categoria: " + e.getMessage());
		}finally {
			session.close();
		}
		return rs;
	}

}

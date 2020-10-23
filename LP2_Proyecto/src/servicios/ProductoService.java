package servicios;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import model.Categoria;
import model.Producto;
import mybatis.MyBatisUtil;
import mybatis.mapper.ProductoMapper;

public class ProductoService implements ProductoMapper{

	@Override
	public ArrayList<Producto> listado(Producto p) {
		ArrayList<Producto> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductoMapper pm = session.getMapper(ProductoMapper.class);
			listado = pm.listado(p);
		} catch (Exception e) {
			System.out.println("Error en el filtro listado de Producto " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

	@Override
	public int registrar(Producto p) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductoMapper pm = session.getMapper(ProductoMapper.class);
			rs = pm.registrar(p);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al registrar Producto" + e.getMessage());
		}finally {
			session.close();
		}
		return rs;
	}

	@Override
	public Producto buscar(int cod) {
		Producto prod = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductoMapper pm = session.getMapper(ProductoMapper.class);
			prod = pm.buscar(cod);
		} catch (Exception e) {
			System.out.println("Error al buscar Producto" + e.getMessage());
		}finally {
			session.close();
		}
		return prod;
	}

	@Override
	public int actualizar(Producto p) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductoMapper pm = session.getMapper(ProductoMapper.class);
			rs = pm.actualizar(p);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al actualizar Producto" + e.getMessage());
		}finally {
			session.close();
		}
		return rs;
	}

	@Override
	public int cambiarEstado(Producto p) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductoMapper pm = session.getMapper(ProductoMapper.class);
			rs = pm.cambiarEstado(p);
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al cambiar estado Producto" + e.getMessage());
		}finally {
			session.close();
		}
		return rs;
	}

	@Override
	public ArrayList<Categoria> listadoCategoria() {
		ArrayList<Categoria> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProductoMapper pm = session.getMapper(ProductoMapper.class);
			listado = pm.listadoCategoria();
		} catch (Exception e) {
			System.out.println("Error al listar categoria" + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

}

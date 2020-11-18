package servicios;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import model.Boleta;
import model.Producto;
import mybatis.MyBatisUtil;
import mybatis.mapper.VentaMapper;

public class VentaService implements VentaMapper{

	@Override
	public ArrayList<Producto> listado(Producto p) {
		ArrayList<Producto> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			VentaMapper vm = session.getMapper(VentaMapper.class);
			listado = vm.listado(p);
		} catch (Exception e) {
			System.out.println("Error al listar Productos por Categoria " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

	@Override
	public int realizarVenta(Boleta boleta) {
		int rs = 0;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			rs += session.insert("mybatis.mapper.VentaMapper.insertarBoleta",boleta);
			for(model.DetalleBoleta db : boleta.getDetalle()) {
				db.setNumBoleta(boleta.getNumBoleta());
				rs += session.insert("mybatis.mapper.VentaMapper.insertBoletaDetalle",db);
				rs += session.update("mybatis.mapper.VentaMapper.actualizarProducto",db);
			}
			session.commit();
		} catch (Exception e) {
			System.out.println("Error al registrar Boleta " + e.getMessage());
			rs = 0;
			session.rollback();
		}finally {
			session.close();
		}
		return rs;
	}

	@Override
	public String generaNumBoleta() {
		String codigo = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			VentaMapper vm = session.getMapper(VentaMapper.class);
			codigo = vm.generaNumBoleta();
		} catch (Exception e) {
			System.out.println("Error al generar codigo de boleta " + e.getMessage());
		}finally {
			session.close();
		}
		return codigo;
	}

}

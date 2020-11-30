package servicios;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import beans.ProductoDTO;
import model.Parametro;
import model.Producto;
import model.VentaMes;
import model.VentasProductos;
import mybatis.MyBatisUtil;
import mybatis.mapper.ReporteMapper;

public class ReporteService implements ReporteMapper{

	@Override
	public ArrayList<ProductoDTO> listaStock(ProductoDTO p) {
		ArrayList<ProductoDTO> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ReporteMapper pm = session.getMapper(ReporteMapper.class);
			listado = pm.listaStock(p);
		} catch (Exception e) {
			System.out.println("Error al listaStock " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

	@Override
	public ArrayList<VentaMes> listadoVentasMes(Parametro p) {
		ArrayList<VentaMes> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ReporteMapper pm = session.getMapper(ReporteMapper.class);
			listado = pm.listadoVentasMes(p);
		} catch (Exception e) {
			System.out.println("Error al listadoVentasMes " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

	@Override
	public ArrayList<VentasProductos> listadoVentaProductos(Parametro p) {
		ArrayList<VentasProductos> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ReporteMapper pm = session.getMapper(ReporteMapper.class);
			listado = pm.listadoVentaProductos(p);
		} catch (Exception e) {
			System.out.println("Error al listadoVentaProductos " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

}
